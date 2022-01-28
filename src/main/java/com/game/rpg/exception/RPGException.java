package com.game.rpg.exception;

import lombok.Getter;

@Getter
public abstract class RPGException extends Exception {

    private final long code;

    public RPGException(long code, String message) {
        super(message);
        this.code = code;
    }
}
