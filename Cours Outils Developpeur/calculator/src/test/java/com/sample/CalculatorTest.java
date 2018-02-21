package com.sample;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damie on 10/04/2017.
 */
public class CalculatorTest {

    Calculator calc=new Calculator();
    @Test
    public void add() throws Exception {
        // Given
        int a=5;
        int b=6;

        //when
        double result=calc.add(a,b);

        //Then
        assertThat(calc.add(a,b)).isEqualTo(11);
    }

    @Test
    public void substract() throws Exception {
        // Given
        int a=5;
        int b=6;

        //when
        double result=calc.substract(a,b);

        //Then
        assertThat(calc.substract(a,b)).isEqualTo(-1);
    }

    @Test
    public void multiply() throws Exception {

        // Given
        int a=5;
        int b=6;

        //when
        double result=calc.multiply(a,b);

        //Then
        assertThat(calc.multiply(a,b)).isEqualTo(30);

    }

    @Test
    public void divide() throws Exception {

        // Given
        int a=5;
        int b=2;

        //when
        double result=calc.divide(a,b);

        //Then
        assertThat(calc.divide(a,b)).isEqualTo(2.5);
        try{
            calc.divide(a,0);
        }catch(DividePerZero e){
            assertThat(e.getMessage()).contains("by zero");
        }
    }

}