/**
 * Created by damie on 02/02/2017.
 */
import java.util.*;
public class De {
    private int value;
    int roll(){
        Random hasard=new Random();
        return Math.abs(hasard.nextInt()%6)+1;
    }
    public De(){
        value=roll();
    }
    public int getValue(){
        return value;
    }
    public void printMultipleRoll(int numberRoll){
        for(int i=0;i<numberRoll;i++){
            value=roll();
            System.out.println(value);
        }
    }
}
