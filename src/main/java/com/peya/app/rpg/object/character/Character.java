package com.peya.app.rpg.object.character;


import com.google.common.collect.Sets;
import com.peya.app.rpg.util.Position;
import com.peya.app.rpg.attack.AttackResolver;
import com.peya.app.rpg.object.AttackableObject;
import lombok.Getter;

import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.intersection;
import static java.lang.Math.min;

@Getter
public class Character extends AttackableObject {

    private final float attachMaxRange;
    private final Set<Faction> factions;
    private AttackResolver attackResolver;
    private int level;

    Character(
            String name,
            Position position,
            float health,
            int level,
            float attachMaxRange,
            AttackResolver attackResolver) {
        super(name, health, position);
        this.attachMaxRange = attachMaxRange;
        this.factions = Sets.newHashSet();
        initializeLevel(level);
        initializeAttachStrategyResolver(attackResolver);
    }

    private void initializeAttachStrategyResolver(AttackResolver attackResolver) {
        checkNotNull(attackResolver, "An attach strategy resolve is required to create a Character");
        this.attackResolver = attackResolver;
    }

    private void initializeLevel(int level) {
        checkArgument(level > 0, "Level must be greater than 0");
        this.level = level;
    }

    // Attack

    public void attach(AttackableObject target, float damage) {
        var attachStrategy = attackResolver.getAttach(target);

        attachStrategy.perform(this, target, damage);
    }

    public boolean isInsideAttachRange(AttackableObject target) {
        return this.getPosition().distance(target.getPosition()) <= attachMaxRange;
    }

    public float levelDiff(Character target) {
        return this.getLevel() - target.getLevel();
    }

    // Heal

    public void healTo(Character anotherCharacter, float health) {
        if (this.isAlliedWith(anotherCharacter) && !anotherCharacter.destroyed())
            anotherCharacter.heal(health);
    }

    void heal(float value) {
        checkArgument(value >= 0, "Heal value must be greater than or equal to 0");
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