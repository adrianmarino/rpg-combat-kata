package com.game.rpg.object.receiver;

import com.game.rpg.util.Position;

import static com.game.rpg.util.Position.zero;

public final class AttackReceiverBuilder {
    private Position position;
    private String name;
    private float health;

    private AttackReceiverBuilder() {
        name = AttackReceiver.class.getSimpleName();
        position = zero();
        health = 100;
    }

    public static AttackReceiverBuilder anAttackReceiver() {
        return new AttackReceiverBuilder();
    }

    public AttackReceiverBuilder position(float x, float y) {
        return position(new Position(x, y));
    }

    public AttackReceiverBuilder position(Position position) {
        this.position = position;
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
