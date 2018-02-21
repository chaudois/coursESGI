package fr.esgi.stack;

/**
 * Created by damie on 04/07/2017.
 */
public class bitTree<T extends Comparable<T>> {
    public bitNode<T> root;

    private int getHeight (bitNode<T> node){
        if(node==null)return 0;
        return 1+Math.max(getHeight(node.left),getHeight(node.right));
    }
    private int getSize (bitNode<T> node){
        if(node==null)return 0;
        return 1+getSize(node.left)+getSize(node.right);
    }
    public bitTree(bitNode<T> root){
        this.root=root;
    }
    public boolean isComplete(bitNode<T> node ){
        int height=getHeight(node);
        int size=getSize(node);
        if(Math.pow(2,height)-1==size)return true;
        return false;
    }
    public boolean isBST(bitNode<T> node){
        return isBST(node,null,null);
    }
    public boolean isBST(bitNode<T> node,bitNode<T> min,bitNode<T> max){
        if(node==null)return true;
        if(min!=null && min.value.compareTo(node.value)==1)return false;
        if(max!=null && max.value.compareTo(node.value)==1)return false;
        return isBST(node.left,min,node)&&isBST(node.right,node,max);

    }
}
