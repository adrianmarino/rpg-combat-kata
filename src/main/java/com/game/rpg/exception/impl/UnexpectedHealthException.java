package com.game.rpg.exception.impl;

import com.game.rpg.exception.RPGException;

import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_CHARACTER_LEVEL;
import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_HEALTH;
import static java.lang.String.format;

public class UnexpectedHealthException extends RPGException {
    public UnexpectedHealthException(float health) {
        super(UNEXPECTED_HEALTH.code(), format(UNEXPECTED_HEALTH.message(), health));
    }
}
