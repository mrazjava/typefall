package com.mrazjava.typefall;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new TypefallModule());
        Game game = injector.getInstance(Game.class);
        game.run();
//    	// Setup terminal and screen layers
//        Terminal terminal = new DefaultTerminalFactory().createTerminal();
//        Screen screen = new TerminalScreen(terminal);
//        screen.startScreen();
//
//        // Create panel to hold components
//        Panel panel = new Panel();
//        panel.setLayoutManager(new GridLayout(2));
//
//        panel.addComponent(new Label("Forename"));
//        panel.addComponent(new TextBox());
//
//        panel.addComponent(new Label("Surname"));
//        panel.addComponent(new TextBox());
//
//        panel.addComponent(new EmptySpace(new TerminalSize(0,0))); // Empty space underneath labels
//        panel.addComponent(new Button("Submit"));
//
//        // Create window to hold the panel
//        BasicWindow window = new BasicWindow();
//        window.setComponent(panel);
//
//        // Create gui and start gui
//        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
//        gui.addWindowAndWait(window);
    }
}

class TypefallModule extends com.google.inject.AbstractModule {
    @Override
    protected void configure() {
        bind(ScreenFactory.class).to(DefaultScreenFactory.class);
    }
}