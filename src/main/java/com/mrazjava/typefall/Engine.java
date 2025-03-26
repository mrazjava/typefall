package com.mrazjava.typefall;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Engine {

	private static final Logger log = LoggerFactory.getLogger(Engine.class);
	
    public void setRows(int rows) {
		this.rows = rows;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	private int rows = -1;
    private int columns = -1;

    
	public void enterMainLoop() throws IOException {
		
		if(rows < 0 || columns < 0)
			throw new IllegalStateException("rows and/or columns not properly set");
			
        while (true){
            refreshScreen();
            int key = readKey();
            if(handleKey(key) == 0) break;
        }
	}

    private void refreshScreen() {
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
