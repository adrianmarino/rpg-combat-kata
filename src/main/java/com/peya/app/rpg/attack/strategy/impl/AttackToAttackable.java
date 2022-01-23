package com.peya.app.rpg.attack.strategy.impl;

import com.peya.app.rpg.attack.strategy.AttackStrategy;
import com.peya.app.rpg.object.AttackableObject;
import com.peya.app.rpg.object.character.Character;

public class AttackToAttackable extends AttackStrategy {
    @Override
    protected void attack(Character attacker, AttackableObject target, float damage) {
        target.damage(damage);
    }
}
