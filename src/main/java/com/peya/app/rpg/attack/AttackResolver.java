package com.peya.app.rpg.attack;

import com.peya.app.rpg.attack.strategy.impl.AttachToCharacter;
import com.peya.app.rpg.attack.strategy.impl.AttackToAttackable;
import com.peya.app.rpg.attack.strategy.AttackStrategy;
import com.peya.app.rpg.object.AttackableObject;
import com.peya.app.rpg.object.character.Character;

import java.util.HashMap;
import java.util.Map;

public class AttackResolver {

    public Map<Class<?>, AttackStrategy> strategies;

    public AttackResolver(DamageMultiplier multiplier) {
        strategies = new HashMap<>() {{
            put(Character.class, new AttachToCharacter(multiplier));
            put(AttackableObject.class, new AttackToAttackable());
        }};
    }

    public <T extends AttackableObject> AttackStrategy getAttach(T target) {
        return strategies.get(target.getClass());
    }
}
