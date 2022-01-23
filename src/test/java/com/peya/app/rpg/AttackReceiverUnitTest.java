package com.peya.app.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.peya.app.rpg.object.character.CharacterBuilder.aCharacter;
import static com.peya.app.rpg.object.receiver.AttackReceiverBuilder.anAttackReceiver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttackReceiverUnitTest {

    @Test
    @DisplayName("When attach a thing inside attach range it has effect")
    void test1() {
        // Prepare
        var tree = anAttackReceiver().name("tree").build();

        // Perform
        aCharacter().build().attach(tree, 50F);

        // Asserts
        assertThat(tree.getHealth(), is(50F));
    }

    @Test
    @DisplayName("When attach a thing outside attach range it has no effect")
    void test2() {
        // Prepare
        var tree = anAttackReceiver()
                .name("tree")
                .position(50, 50)
                .build();

        // Perform
        aCharacter().build().attach(tree, 100F);

        // Asserts
        assertThat(tree.getHealth(), is(100F));
    }
}
