package com.game.rpg.exception.impl;

import com.game.rpg.exception.RPGException;

import static com.game.rpg.exception.ErrorDefinition.CANT_DAMAGE_ITSELF;
import static java.lang.String.format;

public class CantDamageItselfException extends RPGException {
    public CantDamageItselfException(String name) {
        super(CANT_DAMAGE_ITSELF.code(), format(CANT_DAMAGE_ITSELF.message(), name));
    }
}
