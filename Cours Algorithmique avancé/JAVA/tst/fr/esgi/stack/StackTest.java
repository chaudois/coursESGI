package fr.esgi.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damie on 30/03/2017.
 */
public class StackTest {
    @Test
    public void push(){
        Stack s=new Stack();
        assertEquals(true,s.isEmpty());
        assertEquals(0,s.size());

        s.push(4);
        assertEquals(4,s.peek(),0);

        s.push(5);
        assertEquals(5,s.peek(),0);
        assertEquals(false,s.isEmpty());

        double value=s.pop();
        assertEquals(5,value,0);
        assertEquals(4,s.peek(),0);

        s.pop();
        assertEquals(true,s.isEmpty());
        assertEquals(0,s.size());
    }

}