package com.sample;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.sample.State.*;


/**
 * Created by damie on 10/04/2017.
 */
public class ExchangeTest {
    Exchange exchange;
    @Test
    public void should_move_from_pending_to_ready(){
        //given
        exchange=new Exchange(PENDING);

        //when
        exchange.moveTo(READY);

        //then
        Assertions.assertThat(exchange.getState()).isEqualTo(READY);

    }
    @Test
    public void should_move_from_ready_to_finalize(){
        //given
        exchange=new Exchange(READY);

        //when
        exchange.moveTo(FINALIZED);


        //then
        Assertions.assertThat(exchange.getState()).isEqualTo(FINALIZED);

    }

    @Test
    public void should_move_from_ready_to_conflicted(){
        //given
        exchange=new Exchange(READY);

        //when
        exchange.moveTo(CONFLICTED);


        //then
        Assertions.assertThat(exchange.getState()).isEqualTo(CONFLICTED);

    }
    @Test
    public void should_not_move_from_finalize_to_ready(){
        //given
        exchange=new Exchange(FINALIZED);

        //when
        try{
            exchange.moveTo(READY);

        }catch(ExceptionMovingState e){//then
            Assertions.assertThat(e.getMessage()).contains("mouvement non autorise");
        }


    }


}