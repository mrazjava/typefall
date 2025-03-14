package com.mrazjava.typefall;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public interface ScreenFactory {
    Screen createScreen() throws Exception;
    Terminal getTerminal() throws Exception;
}