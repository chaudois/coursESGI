import fr.esgi.stack.Stack;
import fr.esgi.stack.Stack_NPI;

/**
 * Created by damie on 30/03/2017.
 */
public class Main {
    public static void main(String args[]){
        Stack s = new Stack();
        s.printStack();

        s.push(3);
        s.push(5);
        s.push(9);

        s.printStack();

        s.pop();
        s.printStack();

        System.out.println("Peek : "+ s.peek());
        s.printStack();

        Stack_NPI npi=new Stack_NPI();
        System.out.println(npi.NPI("3 4 1 2 + × +"));
    }
}
