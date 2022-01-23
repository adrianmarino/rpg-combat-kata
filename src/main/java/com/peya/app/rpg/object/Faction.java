package com.peya.app.rpg.object;

import com.peya.app.rpg.object.character.Character;
import lombok.Getter;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Getter
public class Faction {

    private final String name;

    private final Set<Character> characters;

    public Faction() {
        this("faction");
    }

    public Faction(String name) {
        this.name = name;
        this.characters = newHashSet();
    }

    public boolean add(Character character) {
        return characters.add(character);
    }

    public boolean includes(Character character) {
        return characters.contains(character);
    }

    public boolean remove(Character character) {
        return characters.remove(character);
    }
}
