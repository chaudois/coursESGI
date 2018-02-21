package fr.esgi.stack;
import org.junit.Test;



import static org.junit.Assert.*;

/**
 * Created by damie on 30/03/2017.
 */

public class Stack_NPITest {

    @Test
    public void NPI(){
        Stack_NPI npi=new Stack_NPI();
//        assertEquals(0,npi.NPI(""));
//        assertEquals(0,npi.NPI(" "));
//        assertEquals(0,npi.NPI("0"));
//        assertEquals(0,npi.NPI("+"));
//        assertEquals(0,npi.NPI("0 +"));
        assertEquals(15,npi.NPI("3 4 1 2 + × +"),0);
    }
}