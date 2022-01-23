package com.game.rpg.object.receiver;

import com.game.rpg.util.JSONUtils;
import com.game.rpg.object.character.Character;
import com.game.rpg.util.Position;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;
import static com.game.rpg.util.JSONUtils.toJson;
import static java.lang.Math.max;

@Getter
public class AttackReceiver {

    protected final Position position;
    protected final float maxHealth;
    protected final String name;
    protected float health;

    public AttackReceiver(String name, float health, Position position) {
        this.name = name;
        this.maxHealth = health;
        this.position = position;
        initializeHealth(health);
    }

    private void initializeHealth(float health) {
        checkArgument(health > 0, "Health must be greater than 0%");
        this.health = health;
    }

    public void receiveAttack(Character attacker, float damage) {
        checkArgument(!attacker.equals(this), "A character cannot damage itself");
        if (!attacker.isInsideAttachRange(this)) return;

        attack(attacker, damage);
    }

    protected void attack(Character attacker, float damage) {
        this.damage(damage);
    }

    protected void damage(float value) {
        checkArgument(value >= 0, "Damage value must be greater than or equal to 0");
        health = max(health - value, 0);
    }

    public boolean destroyed() {
        return health <= 0;
    }

    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}