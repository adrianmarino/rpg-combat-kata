package com.game.rpg.exception.impl;

import com.game.rpg.exception.RPGException;

import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_DAMAGE;
import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_HEALTH;
import static java.lang.String.format;

public class UnexpectedDamageException extends RPGException {
    public UnexpectedDamageException(float damage) {
        super(UNEXPECTED_DAMAGE.code(), format(UNEXPECTED_DAMAGE.message(), damage));
    }
}
