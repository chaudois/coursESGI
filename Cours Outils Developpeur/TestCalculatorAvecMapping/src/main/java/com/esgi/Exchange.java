package com.esgi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by Killian KEOLIO on 10/04/2017 at ESGI.
 * TestExample is a program designed for :
 * com.esgi contains all classes needed for :
 * This class is used for :
 */
public class Exchange {

    private State s;

    private Map<State, List<State>> transitions;

    public Exchange(State t){
        this.s  = t;
        initializeTransitions();
    }

    private void initializeTransitions(){
        transitions = new HashMap<>();
        transitions.put(State.PENDING, asList(State.READY));
        transitions.put(State.READY, asList(State.CONFLICTED,State.FINALIZED));
    }

    public State getState(){
        return this.s;
    }



    public void moveTo(State state){
        State origin = getState();

        if(!transitions.get(origin).contains(state))
            throw new ImpossibleMoveException();
        
        this.s = state;
    }
}
