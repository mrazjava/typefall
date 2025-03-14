package com.mrazjava.typefall;

import com.google.inject.Inject;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Game {
    private final ScreenFactory screenFactory;

    @Inject
    public Game(ScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
    }

    public void run() throws Exception {
        Screen screen = screenFactory.createScreen();
        screen.startScreen();

        Terminal terminal = screenFactory.getTerminal();
        terminal.enterPrivateMode();

        boolean running = true;
        while (running) {
            screen.clear();
            screen.newTextGraphics().putString(0, 0, "Typefall: Press 'q' to quit");
            screen.refresh();

            KeyStroke key = screen.pollInput();
            if (key != null && key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                running = false;
            }

            Thread.sleep(100);
        }

        terminal.exitPrivateMode();
        screen.stopScreen();
    }
}