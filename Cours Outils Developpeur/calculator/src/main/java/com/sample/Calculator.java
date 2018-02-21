package com.sample;

/**
 * Created by damie on 10/04/2017.
 */
public class Calculator {

    public double add(double valueA,double valueB){
        return valueA+valueB;
    }
    public double substract(double valueA,double valueB){
        return valueA-valueB;
    }
    public double multiply(double valueA,double valueB){
        return valueA*valueB;
    }
    public double divide(double valueA,int valueB){
        if(valueB!=0){
            return valueA/valueB;
        }
        else{
            throw new DividePerZero("dividing by zero is forbidden dummy");
        }
    }
}
