package com.sample;

/**
 * Created by damie on 10/04/2017.
 */
public class Exchange {
    State etat;

    public Exchange(State etat){
        this.etat=etat;
    }

    public void moveTo(State etat) {

        if(this.etat==State.PENDING&&etat==State.READY||
            this.etat==State.READY&&etat==State.CONFLICTED||
            this.etat==State.READY&&etat==State.FINALIZED){
            this.etat=etat;
        }
        else{
            throw new ExceptionMovingState();
        }
    }

    public State getState() {
        return etat;
    }
}
