package com.game.rpg.exception.impl;

import com.game.rpg.exception.RPGException;

import static com.game.rpg.exception.ErrorDefinition.UNEXPECTED_CHARACTER_LEVEL;
import static java.lang.String.format;

public class UnexpectedCharacterLevelException extends RPGException {
    public UnexpectedCharacterLevelException(int level) {
        super(UNEXPECTED_CHARACTER_LEVEL.code(), format(UNEXPECTED_CHARACTER_LEVEL.message(), level));
    }
}
