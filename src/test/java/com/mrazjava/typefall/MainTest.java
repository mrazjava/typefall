package com.mrazjava.typefall;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class MainTest {
    @Test
    void testGameRun() throws Exception {
        ScreenFactory mockFactory = mock(ScreenFactory.class);
        Screen mockScreen = mock(Screen.class);
        Terminal mockTerminal = mock(Terminal.class);
        TextGraphics mockGraphics = mock(TextGraphics.class);

        when(mockFactory.createScreen()).thenReturn(mockScreen);
        when(mockFactory.getTerminal()).thenReturn(mockTerminal);
        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);
        when(mockScreen.pollInput()).thenReturn(null, new KeyStroke('q', false, false));

        Game game = new Game(mockFactory);
        assertDoesNotThrow(() -> game.run());

        verify(mockFactory).createScreen();
        verify(mockFactory).getTerminal();
        verify(mockScreen).startScreen();
        verify(mockTerminal).enterPrivateMode();
        verify(mockScreen, atLeastOnce()).clear();
        verify(mockScreen, atLeastOnce()).newTextGraphics();
        verify(mockGraphics, atLeastOnce()).putString(0, 0, "Typefall: Press 'q' to quit");
        verify(mockScreen, atLeastOnce()).refresh();
        verify(mockScreen, atLeastOnce()).pollInput();
        verify(mockTerminal).exitPrivateMode();
        verify(mockScreen).stopScreen();
    }
}