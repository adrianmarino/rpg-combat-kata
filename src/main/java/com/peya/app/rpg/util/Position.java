package com.peya.app.rpg.util;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
public class Position {
    private final float x, y;

    public Position() {
        this(0F, 0F);
    }

    public Position(float x, float y) {
        checkArgument(x >= 0, "X must be greater than or equal to 0");
        checkArgument(y >= 0, "X must be greater than or equal to 0");
        this.x = x;
        this.y = y;
    }

    public float distance(Position other) {
        return (float) sqrt(pow(other.x - x, 2) + pow(other.y - y, 2));
    }
}
