package com.mrazjava.typefall;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DefaultScreenFactory implements ScreenFactory {
    private Terminal terminal;

    @Override
    public Screen createScreen() throws Exception {
        if (terminal == null) {
            terminal = new DefaultTerminalFactory().createTerminal();
        }
        return new TerminalScreen(terminal);
    }

    @Override
    public Terminal getTerminal() throws Exception {
        if (terminal == null) {
            terminal = new DefaultTerminalFactory().createTerminal();
        }
        return terminal;
    }
}