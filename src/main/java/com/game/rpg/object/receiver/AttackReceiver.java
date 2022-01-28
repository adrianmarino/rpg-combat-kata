package com.game.rpg.object.receiver;

import com.game.rpg.exception.impl.CantDamageItselfException;
import com.game.rpg.exception.impl.UnexpectedDamageException;
import com.game.rpg.exception.impl.UnexpectedHealthException;
import com.game.rpg.object.character.Character;
import com.game.rpg.util.Position;
import lombok.Getter;

import static com.game.rpg.exception.Assertions.assertChecked;
import static com.game.rpg.util.JSONUtil.toJson;
import static java.lang.Math.max;

@Getter
public class AttackReceiver {

    protected final Position position;
    protected final String name;
    protected float maxHealth;
    protected float health;

    protected AttackReceiver(String name, float health, Position position) throws UnexpectedHealthException {
        this.name = name;
        this.position = position;
        initializeHealth(health);
    }

    private void initializeHealth(float health) throws UnexpectedHealthException {
        assertChecked(health > 0, () -> new UnexpectedHealthException(health));
        this.health = health;
        this.maxHealth = health;
    }

    public void receiveAttack(Character attacker, float damage) throws CantDamageItselfException,
            UnexpectedDamageException {
        assertChecked(!attacker.equals(this), () -> new CantDamageItselfException(this.getName()));
        if (!attacker.isInsideAttachRange(this)) return;

        attack(attacker, damage);
    }

    protected void attack(Character attacker, float damage) throws UnexpectedDamageException {
        this.damage(damage);
    }

    protected void damage(float value) throws UnexpectedDamageException {
        assertChecked(value >= 0, () -> new UnexpectedDamageException(value));
        health = max(health - value, 0);
    }

    public boolean destroyed() {
        return health <= 0;
    }

    @Override
    public String toString() {
        return toJson(this);
    }
}
