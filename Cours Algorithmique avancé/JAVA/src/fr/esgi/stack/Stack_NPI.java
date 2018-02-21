package fr.esgi.stack;

/**
 * Created by damie on 30/03/2017.
 */
//notation polonaise inverse
public class Stack_NPI extends Stack{

    public static double NPI(String npiString){

        Stack s = new Stack();
        double result=0;
        String tabChar[]=npiString.split(" ");
        for(String c : tabChar){
            try{
                s.push(Integer.parseInt(c));
            }
            catch(NumberFormatException e){
                double a=s.pop();
                double b=s.pop();
                switch(c){
                    case("+"):
                        result=a+b;
                        s.push(result);
                        break;
                    case("-"):
                        result=a-b;
                        s.push(result);
                        break;
                    case("/"):
                        result=(a!=0)?a/b:0;
                        s.push(result);
                        break;
                    case("×"):
                        result=a*b;
                        s.push(result);
                        break;
                    case("*"):
                        result=a*b;
                        s.push(result);
                        break;
                    case("x"):
                        result=a*b;
                        s.push(result);
                        break;

                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return result;
    }


}
