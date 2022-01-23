package com.game.rpg.object.character;

import com.game.rpg.DamageMultiplier;
import com.game.rpg.util.Position;

import static com.game.rpg.util.Position.zero;

public final class CharacterBuilder {
    private DamageMultiplier damageMultiplier;
    private String name;
    private float attachMaxRange;
    private Position position;
    private float health;
    private int level;

    private CharacterBuilder() {
        name = Character.class.getSimpleName();
        attachMaxRange = 10;
        position = zero();
        health = 100;
        level = 1;
        damageMultiplier = DamageMultiplier.defaultDamageMultiplier();
    }

    public static CharacterBuilder aCharacter() {
        return new CharacterBuilder();
    }

    public CharacterBuilder position(float x, float y) {
        return position(new Position(x, y));
    }

    public CharacterBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public CharacterBuilder health(float health) {
        this.health = health;
        return this;
    }

    public CharacterBuilder level(int level) {
        this.level = level;
        return this;
    }

    public CharacterBuilder attachMaxRange(int attachMaxRange) {
        this.attachMaxRange = attachMaxRange;
        return this;
    }

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder damageMultiplier(DamageMultiplier damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
        return this;
    }

    public Character build() {
        return new Character(name, position, health, level, attachMaxRange, damageMultiplier);
    }
}
