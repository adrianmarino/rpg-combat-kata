package com.game.rpg.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@EqualsAndHashCode
public class Position {

    private final float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Position zero() {
        return new Position(0F, 0F);
    }

    public float distance(Position other) {
        return (float) sqrt(pow(other.x - x, 2) + pow(other.y - y, 2));
    }
}
