package com.mrazjava.typefall;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.mrazjava.typefall.csi.ClearScreen;
import com.mrazjava.typefall.csi.CsiScreen;
import com.mrazjava.typefall.csi.MoveCursor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Engine extends CsiScreen {

	@Setter
	private int rows = -1;
	
	@Setter
    private int columns = -1;
	
	private int xCursor = 1;
	private int yCursor = 1;

    
	public void enterMainLoop() throws IOException {
		
		if(rows < 0 || columns < 0)
			throw new IllegalStateException("rows and/or columns not properly set");

		refreshScreen();
		
		xCursor = 5;
		yCursor = 5;
		print(MoveCursor.to.specificPosition(xCursor, yCursor));
		
		do {
            int key = readKey();
            if(handleKey(key) == 0) break;
            refreshScreen();
		}
		while (true);
	}

    private void refreshScreen() {
        StringBuilder builder = new StringBuilder();
        
    	builder.append(ClearScreen.entirely.value());

        for (int i = 0; i < rows - 1; i++) {
            builder.append("~\r\n");
        }

        String statusMessage = "typefall (Typing Tutor)";
        builder.append("\033[7m")
                .append(statusMessage)
                .append(" ".repeat(Math.max(0, columns - statusMessage.length())))
                .append("\033[0m");

        builder.append("\033[H");
        //builder.append(MoveCursor.to.specificPosition(xCursor, yCursor));
        System.out.print(builder);
    }


    private int readKey() throws IOException {
        return System.in.read();
    }

    /**
     * @param key
     * @return 0 if system should exit, 1 in any other case
     */
    private int handleKey(int key) {
    	
    	log.debug("key: " + key);

    	if (key == 'q') {
            return 0;
        }
        
        return 1;
    }
}
