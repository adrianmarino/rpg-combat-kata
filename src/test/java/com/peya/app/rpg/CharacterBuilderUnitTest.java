package com.peya.app.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.peya.app.rpg.object.character.CharacterBuilder.aCharacter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CharacterBuilderUnitTest {

    @Test
    @DisplayName("When create a default character it has 100% health, is alive and it's on first level")
    void test1() {
        // Perform
        var character = aCharacter().build();

        // Assets
        assertThat(character.getHealth(), is(100F));
        assertThat(character.getLevel(), is(1));
        assertThat(character.destroyed(), is(false));
    }

    @Test
    @DisplayName("When create a character with a negative level it throws an exception")
    void test4() {
        assertThrows(IllegalArgumentException.class, () -> aCharacter().level(-1).build());
    }

    @Test
    @DisplayName("When create a character with a negative health it throws an exception")
    void test5() {
        assertThrows(IllegalArgumentException.class, () -> aCharacter().health(-10).build());
    }

    @Test
    @DisplayName("When create a character with an invalid position it throws an exception")
    void test10() {
        assertThrows(IllegalArgumentException.class, () -> aCharacter().position(-1, 3).build());
    }
}
