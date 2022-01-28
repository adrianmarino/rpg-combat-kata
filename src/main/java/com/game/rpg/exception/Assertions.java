package com.game.rpg.exception;

import java.util.function.Supplier;

public class Assertions {

    private Assertions() {
    }

    public static <E extends Exception> void assertChecked(boolean expectedExpression, Supplier<E> getException) throws E {
        if (!expectedExpression) throw getException.get();
    }
}
