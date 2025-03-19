package com.mrazjava.typefall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.ansi.UnixTerminal;

public class DefaultScreenFactory implements ScreenFactory {
	
	private static Logger log = LoggerFactory.getLogger(DefaultScreenFactory.class);
	
    private Terminal terminal;

    @Override
    public Screen createScreen() throws Exception {
        return new TerminalScreen(getTerminal());
    }

    @Override
    public Terminal getTerminal() throws Exception {
        if (terminal == null) {
        	//DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        	//terminalFactory.setForceTextTerminal(true);
            //terminal = terminalFactory.createTerminal();
        	terminal = new LinuxTerminal();
        	//terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        	log.debug("term size: " + terminal.getTerminalSize());
        }
        return terminal;
    }
}