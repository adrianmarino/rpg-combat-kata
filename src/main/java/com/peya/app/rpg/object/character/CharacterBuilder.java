package com.peya.app.rpg.object.character;

import com.peya.app.rpg.attack.DamageMultiplier;
import com.peya.app.rpg.util.Position;
import com.peya.app.rpg.attack.AttackResolver;

public final class CharacterBuilder {
    private AttackResolver attackResolver;
    private String name;
    private float attachMaxRange;
    private Position position;
    private float health;
    private int level;

    private CharacterBuilder() {
        name = "";
        attachMaxRange = 10;
        position = new Position();
        health = 100;
        level = 1;
        attackResolver = new AttackResolver(DamageMultiplier.defaultDamageMultiplier());
    }

    public static CharacterBuilder aCharacter() {
        return new CharacterBuilder();
    }

    public CharacterBuilder position(float x, float y) {
        this.position = new Position(x, y);
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

    public CharacterBuilder attackStrategyResolver(AttackResolver attackResolver) {
        this.attackResolver = attackResolver;
        return this;
    }

    public Character build() {
        return new Character(name, position, health, level, attachMaxRange, attackResolver);
    }
}
