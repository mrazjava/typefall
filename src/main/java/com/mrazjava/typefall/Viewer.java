package com.mrazjava.typefall;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Viewer {

	private static final Logger log = LoggerFactory.getLogger(Viewer.class);
	
    private static LibC.Termios originalAttributes;
    private static int rows = 10;
    private static int columns = 10;

    public static void main(String[] args) throws IOException {

    	SpringApplication.run(Application.class, args);
    	
    	log.debug("initializing ");
    	
        enableRawMode();
        initEditor();

        while (true){
            refreshScreen();
            int key = readKey();
            log.debug("key: " + key);
            if(handleKey(key) == 0) break;
        }

        log.info("bye :-)");
        log.debug("bye 2");
        System.exit(0);
    }

    private static void initEditor() {
        LibC.Winsize windowSize = getWindowSize();
        columns = windowSize.ws_col;
        rows = windowSize.ws_row;
    }

    private static void refreshScreen() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("\033[2J");
        builder.append("\033[H");

        for (int i = 0; i < rows - 1; i++) {
            builder.append("~\r\n");
        }

        String statusMessage = "typefall (Typing Tutor)";
        builder.append("\033[7m")
                .append(statusMessage)
                .append(" ".repeat(Math.max(0, columns - statusMessage.length())))
                .append("\033[0m");

        builder.append("\033[H");
        System.out.print(builder);
    }


    private static int readKey() throws IOException {
        return System.in.read();
    }

    /**
     * @param key
     * @return 0 if system should exit, 1 in any other case
     */
    private static int handleKey(int key) {
    	log.info("key: " + key);
        if (key == 'q') {
            System.out.print("\033[2J");
            System.out.print("\033[H");
            LibC.INSTANCE.tcsetattr(LibC.SYSTEM_OUT_FD, LibC.TCSAFLUSH, originalAttributes);
            return 0;
        }
        
        return 1;
    }



    private static void enableRawMode() {
        LibC.Termios termios = new LibC.Termios();
        int rc = LibC.INSTANCE.tcgetattr(LibC.SYSTEM_OUT_FD, termios);

        if (rc != 0) {
            System.err.println("There was a problem calling tcgetattr");
            System.exit(rc);
        }

        originalAttributes = LibC.Termios.of(termios);

        termios.c_lflag &= ~(LibC.ECHO | LibC.ICANON | LibC.IEXTEN | LibC.ISIG);
        termios.c_iflag &= ~(LibC.IXON | LibC.ICRNL);
        termios.c_oflag &= ~(LibC.OPOST);

       /* termios.c_cc[LibC.VMIN] = 0;
        termios.c_cc[LibC.VTIME] = 1;*/

        LibC.INSTANCE.tcsetattr(LibC.SYSTEM_OUT_FD, LibC.TCSAFLUSH, termios);
    }

    private static LibC.Winsize getWindowSize() {
        final LibC.Winsize winsize = new LibC.Winsize();
        final int rc = LibC.INSTANCE.ioctl(LibC.SYSTEM_OUT_FD, LibC.TIOCGWINSZ, winsize);

        if (rc != 0) {
            System.err.println("ioctl failed with return code[={}]" + rc);
            System.exit(1);
        }

        return winsize;
    }
}