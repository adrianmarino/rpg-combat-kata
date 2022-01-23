package com.game.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.game.rpg.DamageMultiplier.defaultDamageMultiplier;
import static com.game.rpg.object.character.CharacterBuilder.aCharacter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DamageMultiplierUnitTest {

    @Test
    @DisplayName("When create a DamageMultiplier, it should be configured with default values")
    void test1() {
        // Perform
        var multiplier = defaultDamageMultiplier();

        // Asserts
        assertThat(multiplier.getNonDiffDamage(), is(1F));
        assertThat(multiplier.getLeftDamage(), is(.5F));
        assertThat(multiplier.getRightDamage(), is(1.5F));
        assertThat(multiplier.getLevelDiff(), is(5));
    }

    @Test
    @DisplayName("When attacker level is less than 5 it perform 50% less damage")
    void test2() {
        // Prepare
        var attacker = aCharacter().build();
        var target = aCharacter().level(6).build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(.5F));
    }

    @Test
    @DisplayName("When attacker level is greater than 5 it perform 50% more damage")
    void test3() {
        // Prepare
        var attacker = aCharacter().level(6).build();
        var target = aCharacter().build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(1.5F));
    }

    @Test
    @DisplayName("When both attacker and attackable has a level diff less than 5 it perform specified damage")
    void test4() {
        // Prepare
        var attacker = aCharacter().build();
        var target = aCharacter().build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(1F));
    }
}
