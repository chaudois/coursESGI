package fr.esgi.stack;

import java.util.EmptyStackException;

/**
 * Created by damie on 30/03/2017.
 */
public class Stack{
    Node top;
    int size;

    public Stack(){
        this.size=0;
    }

    public void push(double value){
        Node n =new Node(value);
        if(this.top !=null){
            n.next=this.top;
        }
        this.top=n;
        this.size +=1;
    }
    public double pop() throws EmptyStackException{
        if(this.top==null){
            throw new EmptyStackException();
        }
        double value=this.top.value;
        this.top=this.top.next;
        this.size-=1;
        return value;
    }
    public double peek()throws EmptyStackException{
        if(this.top==null){
            throw new EmptyStackException();
        }
        return this.top.value;
    }
    public boolean isEmpty(){
        return (this.size==0);
    }
    public int size(){
        return this.size;
    }
    public void printStack(){
        System.out.print("[");
        Node n=this.top;
        while(n!=null){
            System.out.print(n.value);
            n=n.next;
            if(n!=null){
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
}

