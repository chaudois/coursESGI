package fr.esgi.stack;

import java.util.EmptyStackException;

/**
 * Created by damie on 30/03/2017.
 */
public class MinStack extends Stack {

    Stack buffer;
    public  MinStack(){
        buffer=new Stack();
    }
    @Override
    public void push(double value){

        super.push(value);

        if(buffer.isEmpty()||value<=buffer.peek())this.buffer.push(value);
    }
    @Override
    public double pop() throws EmptyStackException {
        double value=super.pop();
        if(value==buffer.peek())buffer.pop();
        return value;

    }
    public double getMin(){
        return buffer.peek();
    }
}
