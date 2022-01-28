package com.game.rpg.object.character;

import com.game.rpg.exception.impl.UnexpectedCharacterLevelException;
import com.game.rpg.exception.impl.UnexpectedHealthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.game.rpg.object.character.CharacterBuilder.aCharacter;
import static com.game.rpg.util.Position.zero;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CharacterBuilderUnitTest {

    @Test
    @DisplayName("When create a default character it has 100% health, is alive and it's on first level")
    void test1() throws Exception {
        // Perform
        var character = aCharacter().build();

        // Assets
        assertThat(character.getLevel(), is(1));
        assertThat(character.getHealth(), is(100F));
        assertThat(character.getAttachMaxRange(), is(10F));
        assertThat(character.getMaxHealth(), is(100F));
        assertThat(character.getName(), is(Character.class.getSimpleName()));
        assertThat(character.getPosition(), is(zero()));
        assertThat(character.destroyed(), is(false));
    }

    @Test
    @DisplayName("When create a character with a negative level it throws an exception")
    void test2() {
        assertThrows(UnexpectedCharacterLevelException.class, () -> aCharacter().level(-1).build());
    }

    @Test
    @DisplayName("When create a character with a negative health it throws an exception")
    void test3() {
        assertThrows(UnexpectedHealthException.class, () -> aCharacter().health(-10).build());
    }
}
