package com.peya.app.rpg.object.receiver;

import com.peya.app.rpg.object.character.CharacterBuilder;
import com.peya.app.rpg.util.Position;

public final class AttackReceiverBuilder {
    private Position position;
    private String name;
    private float health;

    private AttackReceiverBuilder() {
        name = "";
        position = new Position();
        health = 100;
    }

    public static AttackReceiverBuilder anAttackReceiver() {
        return new AttackReceiverBuilder();
    }

    public AttackReceiverBuilder position(float x, float y) {
        this.position = new Position(x, y);
        return this;
    }

    public AttackReceiverBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AttackReceiverBuilder health(float health) {
        this.health = health;
        return this;
    }

    public AttackReceiver build() {
        return new AttackReceiver(name, health, position);
    }
}
