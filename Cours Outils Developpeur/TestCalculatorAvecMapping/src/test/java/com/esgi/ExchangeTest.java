package com.esgi;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Killian KEOLIO on 10/04/2017 at ESGI.
 * TestExample is a program designed for :
 * com.esgi contains all classes needed for :
 * This class is used for :
 */
public class ExchangeTest {
    Exchange exchange;

    @Test
    public void should_move_from_pending_to_ready() {
        //Given
        exchange = new Exchange(State.PENDING);

        //When
        exchange.moveTo(State.READY);

        assertThat(exchange.getState()).isEqualTo(State.READY);
    }

    @Test
    public void should_move_from_ready_to_conflicted() {
        //Given
        exchange = new Exchange(State.READY);

        //When
        exchange.moveTo(State.CONFLICTED);

        assertThat(exchange.getState()).isEqualTo(State.CONFLICTED);
    }

    @Test
    public void should_move_from_ready_to_finalized() {
        //Given
        exchange = new Exchange(State.READY);

        //When
        exchange.moveTo(State.FINALIZED);

        assertThat(exchange.getState()).isEqualTo(State.FINALIZED);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void should_not_move_from_ready_to_pending() {
        //Given
        exchange = new Exchange(State.READY);

        //When
        exchange.moveTo(State.PENDING);

    }

    @Test(expected = ImpossibleMoveException.class)
    public void should_not_move_from_his_state_to_his_state() {
        //Given
        exchange = new Exchange(State.READY);

        //When
        exchange.moveTo(State.READY);

    }

    @Test(expected = ImpossibleMoveException.class)
    public void should_not_move_from_conflicted_to_any_state() {
        //Given
        exchange = new Exchange(State.READY);

        //When
        exchange.moveTo(State.PENDING);

    }
}
