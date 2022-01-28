package com.game.rpg.exception;

public enum ErrorDefinition {

    UNEXPECTED_CHARACTER_LEVEL(101, "Character level must be greater than 0. Level: %s"),

    UNEXPECTED_HEAL(102, "Heal must must be greater than 0. Heal: %s"),

    UNEXPECTED_HEALTH(103, "Health must be greater than 0. Health: %s"),

    UNEXPECTED_DAMAGE(104, "Damage value must be greater than or equal to 0. Damage: %s"),

    CANT_DAMAGE_ITSELF(105, "Character or object cannot damage itself. Name: %s");

    private final long code;

    private final String message;

    ErrorDefinition(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long code() {
        return code;
    }

    public String message() {
        return message;
    }
}
