package com.game.rpg;

import com.game.rpg.object.character.Character;
import lombok.Getter;

@Getter
public class DamageMultiplier {
    private final int levelDiff;
    private final float leftDamage;
    private final float rightDamage;
    private final float nonDiffDamage;

    public DamageMultiplier(int levelDiff, float leftDamage, float rightDamage, float nonDiffDamage) {
        this.levelDiff = levelDiff;
        this.leftDamage = leftDamage;
        this.rightDamage = rightDamage;
        this.nonDiffDamage = nonDiffDamage;
    }

    public static DamageMultiplier defaultDamageMultiplier() {
        return new DamageMultiplier(5, .5F, 1.5F, 1F);
    }

    public float getMultiplier(Character attacker, Character target) {
        var currentLevelDiff = attacker.levelDiff(target);
        if (currentLevelDiff == 0) return nonDiffDamage;
        return currentLevelDiff <= this.levelDiff && currentLevelDiff < 0 ? leftDamage : rightDamage;
    }
}
