package com.peya.app.rpg.attack.strategy;

import com.peya.app.rpg.object.AttackableObject;
import com.peya.app.rpg.object.character.Character;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AttackStrategy {

    public void perform(Character attacker, AttackableObject target, float damage) {
        checkArgument(!attacker.equals(target), "A character cannot damage itself");
        if (!attacker.isInsideAttachRange(target)) return;

        attack(attacker, target, damage);
    }

    protected abstract void attack(Character attacker, AttackableObject target, float damage);
}
