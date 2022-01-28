package com.game.rpg.exception.impl;

import com.game.rpg.exception.RPGException;

import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_HEAL;
import static java.lang.String.format;

public class UnexpectedHealException extends RPGException {
    public UnexpectedHealException(float heal) {
        super(UNEXPECTED_HEAL.code(), format(UNEXPECTED_HEAL.message(), heal));
    }
}
