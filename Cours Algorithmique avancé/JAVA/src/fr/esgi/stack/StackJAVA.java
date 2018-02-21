package fr.esgi.stack;
import java.util.Stack;
/**
 * Created by damie on 13/04/2017.
 */
public class StackJAVA {
    Stack<Character> stack=new Stack<Character>();
    public StackJAVA(){

    }
    public boolean validParenthese(String chaine){
        for(int i=0;i<chaine.length();i++){
            switch(chaine.charAt(i)){
                case ')':
                    if(stack.peek()=='('){
                        stack.pop();
                    }else
                    {
                        return false;
                    }
                    break;
                case ']':
                    if(stack.peek()=='['){
                        stack.pop();
                    }else
                    {
                        return false;
                    }
                    break;
                case '}':
                    if(stack.peek()=='{'){
                        stack.pop();
                    }else
                    {
                        return false;
                    }
                    break;
                default:
                    stack.push(chaine.charAt(i));
                    break;

            }
        }
        return stack.isEmpty();
    }
}
