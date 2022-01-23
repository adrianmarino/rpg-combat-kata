package com.peya.app.rpg;

import org.junit.jupiter.api.Test;

import static com.peya.app.rpg.object.character.CharacterBuilder.aCharacter;
import static com.peya.app.rpg.DamageMultiplier.defaultDamageMultiplier;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DamageMultiplierUnitTest {

    @Test
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
    void test2() {
        // Prepare
        var attacker = aCharacter().build();
        var target = aCharacter().level(6).build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(.5F));
    }

    @Test
    void test3() {
        // Prepare
        var attacker = aCharacter().level(6).build();
        var target = aCharacter().build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(1.5F));
    }

    @Test
    void test4() {
        // Prepare
        var attacker = aCharacter().build();
        var target = aCharacter().build();
        var multiplier = defaultDamageMultiplier();

        // Perform and assert
        assertThat(multiplier.getMultiplier(attacker, target), is(1F));
    }
}
