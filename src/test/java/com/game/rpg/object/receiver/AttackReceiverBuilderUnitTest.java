package com.game.rpg.object.receiver;

import com.game.rpg.exception.impl.UnexpectedHealthException;
import com.game.rpg.util.Position;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.game.rpg.object.receiver.AttackReceiverBuilder.anAttackReceiver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttackReceiverBuilderUnitTest {

    @Test
    @DisplayName("When create a default character it has 100% health, is alive and it's on first level")
    void test1() throws Exception {
        // Perform
        var attackReceiver = anAttackReceiver().build();

        // Assets
        assertThat(attackReceiver.getHealth(), is(100F));
        assertThat(attackReceiver.getMaxHealth(), is(100F));
        assertThat(attackReceiver.getName(), is(AttackReceiver.class.getSimpleName()));
        assertThat(attackReceiver.getPosition(), Matchers.is(Position.zero()));
        assertThat(attackReceiver.destroyed(), is(false));
    }

    @Test
    @DisplayName("When create a character with a negative health it throws an exception")
    void test2() {
        assertThrows(UnexpectedHealthException.class, () -> anAttackReceiver().health(-10).build());
    }
}
