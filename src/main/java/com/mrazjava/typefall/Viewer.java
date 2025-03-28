package com.mrazjava.typefall;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Viewer {
	
	@Autowired
	private Engine engine;
	
    private static LibC.Termios originalAttributes;

    public void start() throws IOException {
    	
        enableRawMode();
        initEngine();
        
        engine.enterMainLoop();
        
        exitRawMode();
    }
    
    public void stop() {
    	
    }

    private void initEngine() {
        LibC.Winsize windowSize = getWindowSize();
        engine.setColumns(windowSize.ws_col);
        engine.setRows(windowSize.ws_row);
    }



    private void enableRawMode() {
        LibC.Termios termios = new LibC.Termios();
        int rc = LibC.INSTANCE.tcgetattr(LibC.SYSTEM_OUT_FD, termios);

        if (rc != 0) {
        	log.error("there was a problem calling tcgetattr");
        	System.err.println("there was a problem calling tcgetattr");
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
    
    private void exitRawMode() {
        System.out.print("\033[2J");
        System.out.print("\033[H");
        LibC.INSTANCE.tcsetattr(LibC.SYSTEM_OUT_FD, LibC.TCSAFLUSH, originalAttributes);
    }

    private LibC.Winsize getWindowSize() {
        final LibC.Winsize winsize = new LibC.Winsize();
        final int rc = LibC.INSTANCE.ioctl(LibC.SYSTEM_OUT_FD, LibC.TIOCGWINSZ, winsize);

        if (rc != 0) {
            log.error("ioctl failed with return code[={}]", rc);
            System.err.println("ioctl failed with return code[={}]" + rc);
            System.exit(1);
        }

        return winsize;
    }
}