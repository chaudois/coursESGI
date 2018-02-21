package fr.esgi.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damie on 13/04/2017.
 */
public class StackJAVATest {
    StackJAVA stack;
    @Test
    public void validParenthese() throws Exception {
        stack=new StackJAVA();
        assertTrue(stack.validParenthese("{[(())]}()"));
        assertTrue(stack.validParenthese("()"));
        assertFalse(stack.validParenthese("([)]"));
        assertFalse(stack.validParenthese("{)"));
        assertFalse(stack.validParenthese("{"));
        assertFalse(stack.validParenthese("()))"));


    }

}