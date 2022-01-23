package com.peya.app.rpg;

import com.peya.app.rpg.object.AttackableObject;
import com.peya.app.rpg.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.peya.app.rpg.object.character.CharacterBuilder.aCharacter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttackableObjectUnitTest {

    @Test
    @DisplayName("When attach a thing inside attach range it has effect")
    void test1() {
        // Prepare
        var character = aCharacter().build();
        var tree = new AttackableObject("tree", 2000F, new Position(0, 0));

        // Perform
        character.attach(tree, 1000F);

        // Asserts
        assertThat(tree.getHealth(), is(1000F));
    }

    @Test
    @DisplayName("When attach a thing outside attach range it has no effect")
    void test2() {
        // Prepare
        var character = aCharacter().build();
        var tree = new AttackableObject("tree", 2000F, new Position(50, 0));

        // Perform
        character.attach(tree, 1000F);

        // Asserts
        assertThat(tree.getHealth(), is(2000F));
    }
}
