package fr.esgi.stack;

/**
 * Created by damie on 30/03/2017.
 */
public class minStack_Node extends Stack {
    public minStack_Node(){

    }
    @Override
    public void push(double value){
        double min = value;
        if(this.top!=null){
            min=(value<=this.top.min)?value:this.top.min;
        }
        super.push(value);
        this.top.min=min;
    }
    public double getMin(){
        return this.top.min;
    }
}
