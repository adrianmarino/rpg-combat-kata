package com.game.rpg.object.receiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.game.rpg.object.character.CharacterBuilder.aCharacter;
import static com.game.rpg.object.receiver.AttackReceiverBuilder.anAttackReceiver;
import static com.game.rpg.util.Position.zero;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AttackReceiverUnitTest {

    @Test
    @DisplayName("When apply a 50% damage to a character with 100 health it decrease 50 health and keep alive")
    void test1() {
        // Prepare
        var character = aCharacter().build();
        var attackReceiver = anAttackReceiver().build();

        // Perform
        attackReceiver.receiveAttack(character, 50F);

        // Asserts
        assertThat(attackReceiver.getHealth(), is(50F));
        assertThat(attackReceiver.destroyed(), is(false));
    }

    @Test
    @DisplayName("When apply a 1000 damage to a character with 100 health it decrease 0 health and dead")
    void test2() {
        // Prepare
        var character = aCharacter().build();
        var attackReceiver = anAttackReceiver().build();

        // Perform
        attackReceiver.receiveAttack(character, 1000F);

        // Asserts
        assertThat(attackReceiver.getHealth(), is(0F));
        assertThat(attackReceiver.destroyed(), is(true));
    }


    @Test
    @DisplayName("When attach a thing inside attach range it has effect")
    void test3() {
        // Prepare
        var tree = anAttackReceiver().name("tree").build();

        // Perform
        aCharacter().build().attach(tree, 50F);

        // Asserts
        assertThat(tree.getHealth(), is(50F));
    }

    @Test
    @DisplayName("When attach a thing outside attach range it has no effect")
    void test4() {
        // Prepare
        var character = aCharacter().position(zero()).build();
        var tree = anAttackReceiver()
                .name("tree")
                .position(50, 50)
                .build();

        // Perform
        character.attach(tree, 100F);

        // Asserts
        assertThat(tree.getHealth(), is(100F));
    }

    @Test
    @DisplayName("When damage with a negative value it throws an exception")
    void test5() {
        // Prepare
        var character = aCharacter().build();
        var attackReceiver = anAttackReceiver().build();

        // Perform and assert
        assertThrows(
                IllegalArgumentException.class,
                () -> attackReceiver.receiveAttack(character, -10F));
    }
}
