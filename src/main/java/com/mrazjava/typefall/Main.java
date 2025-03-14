package com.mrazjava.typefall;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new TypefallModule());
        Game game = injector.getInstance(Game.class);
        game.run();
    }
}

class TypefallModule extends com.google.inject.AbstractModule {
    @Override
    protected void configure() {
        bind(ScreenFactory.class).to(DefaultScreenFactory.class);
    }
}