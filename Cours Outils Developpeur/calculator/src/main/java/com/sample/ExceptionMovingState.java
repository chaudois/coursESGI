package com.sample;

/**
 * Created by damie on 10/04/2017.
 */
public class ExceptionMovingState extends RuntimeException{
    public ExceptionMovingState(){
        super("erreur : mouvement non autorise");
    }
}
