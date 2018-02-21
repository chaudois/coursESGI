import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by damie on 04/07/2017.
 */
public class Main {
    static int validParanthese(String string) {
        Stack pile=new Stack();
        int soluce=0;
        for(int i=0;i<string.length();i++){

            if(string.charAt(i)=='(' ) {
                pile.push(string.charAt(i));
            }else if(string.charAt(i)==')' && !pile.isEmpty() && pile.peek().toString().charAt(0)=='('){
                pile.pop();
                soluce+=2;
            }

        }
        return soluce;
    }
        static boolean diff(int[] A, int k) {
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                try{
                    if(A[i]-A[j]==k || A[j]-A[i]==k){
                        return true;
                    }
                }catch(Exception e){
                    System.out.println("BUG");
                }

            }
        }
        return false;
    }
    static int countMaxFruits(int[] fruits) {
        Arrays.sort(fruits);
        ArrayList<Integer> arrayFruits=new ArrayList<Integer>() ;
        for(int i=0;i<fruits.length;i++){
            arrayFruits.add(fruits[i]);
        }
        int retourMax=fruits.length/2;
        int retour=0;


        for(int i =0;i<arrayFruits.size();i++){
            if(arrayFruits.get(i)!=arrayFruits.get(i+1)){
                retour+=2;
                arrayFruits.remove(i+1);
                arrayFruits.remove(i);
                i=-1;
            }
        }

            return retour<retourMax?retour:retourMax;
    }

    public static void main(String args[]){
        int test[]={1,2,3,5};
        if(diff(test,3)){
            System.out.println("SUCCESS diff");
        }else{
            System.out.println("FAIL diff");
        }

        if(validParanthese(")()())")==4){
            System.out.println("SUCCESS validParanthese");
        }else{
            System.out.println("FAIL validParanthese : expected 4, get "+validParanthese("((((()()())((("));
        }
        int panier[] ={1,1,2,3,4,5,1,1,1,1,1,1};
        if(countMaxFruits(panier)==6){
            System.out.println("SUCCESS countMaxFruits");
        }else{
            System.out.println("FAIL countMaxFruits : expected 3, get "+countMaxFruits(panier));
        }
    }
}
