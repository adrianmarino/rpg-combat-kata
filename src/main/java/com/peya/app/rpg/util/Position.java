package com.peya.app.rpg.util;

import lombok.Getter;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
public class Position {
    private final float x, y;

    public Position(float x, float y) {
        checkArgument(x >= 0, "X must be greater than or equal to 0");
        checkArgument(y >= 0, "X must be greater than or equal to 0");
        this.x = x;
        this.y = y;
    }

    public static Position zero() {
        return new Position(0F, 0F);
    }

    public float distance(Position other) {
        return (float) sqrt(pow(other.x - x, 2) + pow(other.y - y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Float.compare(position.x, x) == 0 && Float.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
