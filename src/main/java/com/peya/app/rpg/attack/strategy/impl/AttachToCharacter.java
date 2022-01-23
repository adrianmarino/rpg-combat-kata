package com.peya.app.rpg.attack.strategy.impl;

import com.peya.app.rpg.attack.DamageMultiplier;
import com.peya.app.rpg.attack.strategy.AttackStrategy;
import com.peya.app.rpg.object.AttackableObject;
import com.peya.app.rpg.object.character.Character;

import static com.peya.app.rpg.attack.DamageMultiplier.defaultDamageMultiplier;

public class AttachToCharacter extends AttackStrategy {

    private final DamageMultiplier multiplier;

    public AttachToCharacter() {
        this(defaultDamageMultiplier());
    }

    public AttachToCharacter(DamageMultiplier multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    protected void attack(Character attacker, AttackableObject target, float damage) {
        if (attacker.isAlliedWith((Character) target)) return;
        target.damage(damage * multiplier.getMultiplier(attacker, (Character) target));
    }
}
