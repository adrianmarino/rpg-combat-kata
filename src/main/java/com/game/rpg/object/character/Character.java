package com.game.rpg.object.character;


import com.game.rpg.DamageMultiplier;
import com.game.rpg.exception.impl.*;
import com.game.rpg.object.Faction;
import com.game.rpg.object.receiver.AttackReceiver;
import com.game.rpg.util.Position;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;
import java.util.Set;

import static com.game.rpg.exception.Assertions.assertChecked;
import static com.google.common.collect.Sets.intersection;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Math.min;

@Getter
public class Character extends AttackReceiver {

    private final float attachMaxRange;

    private final Set<Faction> factions;

    private DamageMultiplier damageMultiplier;

    private int level;

    Character(
            String name,
            Position position,
            float health,
            int level,
            float attachMaxRange,
            DamageMultiplier damageMultiplier) throws UnexpectedCharacterLevelException, UnexpectedHealthException {
        super(name, health, position);
        this.attachMaxRange = attachMaxRange;
        this.factions = newHashSet();
        initializeLevel(level);
        initializeDamageMultiplier(damageMultiplier);
    }

    private void initializeDamageMultiplier(@NonNull DamageMultiplier damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    private void initializeLevel(int level) throws UnexpectedCharacterLevelException {
        assertChecked(level > 0, () -> new UnexpectedCharacterLevelException(level));
        this.level = level;
    }

    // Attack

    public void attach(AttackReceiver target, float damage) throws CantDamageItselfException,
            UnexpectedDamageException {
        target.receiveAttack(this, damage);
    }

    public boolean isInsideAttachRange(AttackReceiver target) {
        return this.getPosition().distance(target.getPosition()) <= attachMaxRange;
    }

    public float levelDiff(Character target) {
        return this.getLevel() - target.getLevel();
    }

    @Override
    protected void attack(Character attacker, float damage) throws UnexpectedDamageException {
        if (attacker.isAlliedWith(this)) return;
        this.damage(damage * damageMultiplier.getMultiplier(attacker, this));
    }

    // Heal

    public void healTo(Character anotherCharacter, float health) throws UnexpectedHealException {
        if (this.isAlliedWith(anotherCharacter) && !anotherCharacter.destroyed())
            anotherCharacter.heal(health);
    }

    void heal(float value) throws UnexpectedHealException {
        assertChecked(value >= 0, () -> new UnexpectedHealException(value));
        health = min(health + value, maxHealth);
    }

    // Faction

    public boolean belongTo(Faction faction) {
        return faction.includes(this);
    }

    public Character joinTo(Faction faction) {
        if (faction.add(this)) factions.add(faction);
        return this;
    }

    public void leave(Faction faction) {
        if (faction.remove(this)) factions.remove(faction);
    }

    public boolean isAlliedWith(Character target) {
        return intersection(this.getFactions(), target.getFactions()).size() > 0;
    }
}