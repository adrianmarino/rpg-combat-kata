package com.game.rpg.object.character;

import com.game.rpg.object.Faction;
import com.game.rpg.object.character.CharacterBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

// https://github.com/ardalis/kata-catalog/blob/main/katas/RPG%20Combat.md

class CharacterUnitTest {

    // Heal

    @Test
    @DisplayName("When heal an alive character it increase your health")
    void test1() {
        // Prepare
        var faction = new Faction();
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);
        CharacterBuilder.aCharacter().build().attach(characterB, 50F);

        // Perform
        characterA.healTo(characterB, 1000F);

        // Asserts
        assertThat(characterB.getHealth(), is(100F));
        assertThat(characterB.destroyed(), is(false));
    }

    @Test
    @DisplayName("When heal a dead character it not increase your health and stay dead")
    void test2() {
        // Prepare
        var faction = new Faction();
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);
        CharacterBuilder.aCharacter().build().attach(characterB, 100F);

        // Perform
        characterA.healTo(characterB, 100F);

        // Asserts
        assertThat(characterB.getHealth(), is(0F));
        assertThat(characterB.destroyed(), is(true));
    }

    @Test
    @DisplayName("When heal an alive character with 100 health it health stay same")
    void test3() {
        // Prepare
        var faction = new Faction();
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);

        // Perform
        characterA.healTo(characterB, 10000F);

        // Asserts
        assertThat(characterB.getHealth(), is(100F));
        assertThat(characterB.destroyed(), is(false));
    }


    @Test
    @DisplayName("When heal with a negative value it throws an exception")
    void test4() {
        // Prepare
        var faction = new Faction("faction");
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);

        // Perform and assert
        assertThrows(IllegalArgumentException.class, () -> characterA.healTo(characterB, -10F));
    }

    // Attack

    @Test
    @DisplayName("When a character A apply a 50% damage to a character B with 100 health it second decrease 50 health and keep alive")
    void test5() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().build();
        var characterB = CharacterBuilder.aCharacter().build();

        // Perform
        characterA.attach(characterB, 50F);

        // Asserts
        assertThat(characterB.getHealth(), is(50F));
        assertThat(characterB.destroyed(), is(false));
    }

    @Test
    @DisplayName("When a character A applies a damage to itself it throw an exception because it cant damage itself")
    void test6() {
        // Prepare
        var character = CharacterBuilder.aCharacter().build();

        // Perform and assert
        assertThrows(IllegalArgumentException.class, () -> character.attach(character, 1000F));
    }

    @Test
    @DisplayName("When a character A applies a damage another character B with 5 levels up it damage decrease to 50%")
    void test7() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().build();
        var characterB = CharacterBuilder.aCharacter().level(6).build();

        // Perform
        characterA.attach(characterB, 100F);

        // Asserts
        assertThat(characterB.getHealth(), is(50F));
        assertThat(characterB.destroyed(), is(false));
    }

    @Test
    @DisplayName("When a character A applies a damage another character B with 5 levels down it damage increase to 50%")
    void test8() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().level(6).build();
        var characterB = CharacterBuilder.aCharacter().build();

        // Perform
        characterA.attach(characterB, 10);

        // Asserts
        assertThat(characterB.getHealth(), is(85F));
        assertThat(characterB.destroyed(), is(false));
    }

    @Test
    @DisplayName("When a character A applies a damage to character B outside to attach range it cant deal damage to B")
    void test9() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().position(0, 0).build();
        var characterB = CharacterBuilder.aCharacter().position(50, 50).build();

        // Perform
        characterA.attach(characterB, 100);

        // Asserts
        assertThat(characterB.getHealth(), is(100F));
        assertThat(characterB.destroyed(), is(false));
    }


    // Faction

    @Test
    @DisplayName("When has a character that belongs to a faction and check this it return true")
    void test10() {
        // Prepare
        var faction = new Faction();
        var character = CharacterBuilder.aCharacter().build().joinTo(faction);

        // Perform and assert
        assertThat(character.belongTo(faction), is(true));
    }

    @Test
    @DisplayName("When has a character that not belongs to a faction and check this it return false")
    void test11() {
        // Prepare
        var character = CharacterBuilder.aCharacter().build();
        var faction = new Faction();

        // Perform and assert
        assertThat(character.belongTo(faction), is(false));
    }

    @Test
    @DisplayName("When has a character that leave a faction and check if it belong to faction this it return false")
    void test12() {
        // Prepare
        var character = CharacterBuilder.aCharacter().build();
        var faction = new Faction();
        character.joinTo(faction);
        character.leave(faction);

        // Perform and assert
        assertThat(character.belongTo(faction), is(false));
    }


    @Test
    @DisplayName("When two character belongs to same faction it return true")
    void test13() {
        // Prepare
        var faction = new Faction();
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);

        // Perform and assert
        assertThat(characterA.isAlliedWith(characterB), is(true));
    }

    @Test
    @DisplayName("When two character not belongs to same faction it return false")
    void test14() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().build().joinTo(new Faction("A"));
        var characterB = CharacterBuilder.aCharacter().build().joinTo(new Faction("B"));

        // Perform and assert
        assertThat(characterA.isAlliedWith(characterB), is(false));
    }

    @Test
    @DisplayName("When aliases characters apply damage to each other it has no effect")
    void test15() {
        // Prepare
        var faction = new Faction();
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);

        // Perform
        characterA.attach(characterB, 100);

        // Assert
        assertThat(characterB.getHealth(), is(100F));
    }


    @Test
    @DisplayName("When non aliases characters apply damage to each other it has effect")
    void test16() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().build().joinTo(new Faction("A"));
        var characterB = CharacterBuilder.aCharacter().build().joinTo(new Faction("B"));

        // Perform
        characterA.attach(characterB, 100F);

        // Assert
        assertThat(characterB.destroyed(), is(true));
    }

    @Test
    @DisplayName("When aliases characters heal to each other it has effect")
    void test17() {
        // Prepare
        var faction = new Faction("Faction");
        var characterA = CharacterBuilder.aCharacter().build().joinTo(faction);
        var characterB = CharacterBuilder.aCharacter().build().joinTo(faction);
        CharacterBuilder.aCharacter().build().attach(characterB, 50F);

        // Perform
        characterA.healTo(characterB, 50F);

        // Assert
        assertThat(characterB.getHealth(), is(100F));
    }

    @Test
    @DisplayName("When non aliases characters heal to each other it has no effect")
    void test18() {
        // Prepare
        var characterA = CharacterBuilder.aCharacter().build();
        var characterB = CharacterBuilder.aCharacter().build();
        characterA.attach(characterB, 50);

        // Perform
        characterA.healTo(characterB, 50);

        // Assert
        assertThat(characterB.getHealth(), is(50F));
    }
}