
import java.io.File;
import java.util.*;
import java.lang.String;

public class Exercices{
    private int variableBidon1;
    private char variableBidon2;
    public Exercices(){
        variableBidon1=5;
        variableBidon2='c';
    }
    public static void test(String[] args){
        try{

            //essaye de récuperer les parametres de base. en cas d'echec, stop le programme
            String alphabet=args[0];
            String mot=args[1];
            String commande="";
            String paramCommande="";
            //essaye de récuperer les parametres secondaire
            try{
                commande=args[2];
                paramCommande=args[3];

            }
            catch(ArrayIndexOutOfBoundsException e){
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(checkValid(alphabet,mot)){

                System.out.println("le mot entre se trouve dans le dictionnaire");

                if(commande.equals("/a") && paramCommande!="" && isAnagrame(mot,paramCommande)){
                    System.out.println("le  mot que vous avez entree est  un anagrame");
                }
                else if(commande.equals("/a") && paramCommande!="" && !isAnagrame(mot,paramCommande)){
                    System.out.println("le  mot que vous avez entree n'est pas un anagrame");
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("\nparametres d'entrees invalides\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("fermeture du programme");
        }

    }
    public static double addValueSafely(String[] tabValue)throws NumberFormatException {
        int cpt=0;
        int result=0;
        int error=0;
            for(String value:tabValue){
                try{
                    result+=Integer.parseInt(value);
                }
                catch(NumberFormatException e){
                    System.out.println(e.getMessage());
                    error++;
                }
            }
            if(error>0){
                throw new NumberFormatException(Integer.toString(result/(tabValue.length-error)));
            }
            return result/(tabValue.length-error);

    }
    public static String rotateLeft(String word, String P_alphabet,int rotate){

        char[] result=word.toCharArray();
        char[] alphabet=P_alphabet.toCharArray();
        for(int a=0;a<word.length();a++){
            int i=0;
            while(i<rotate){
                if((Character.getNumericValue(result[a])-1>=Character.getNumericValue(alphabet[0]))){
                    result[a]--;
                    i++;
                }
                else{
                    result[a]=alphabet[alphabet.length-1];
                    i++;
                }
            }

        }
        String retour=new String(result);
        return retour;
    }
    public static String rotateRight(String word, String P_alphabet,int rotate){

        char[] result=word.toCharArray();
        char[] alphabet=P_alphabet.toCharArray();
        for(int a=0;a<word.length();a++){
            int i=0;
            while(i<rotate){
                if((Character.getNumericValue(result[a])+1<=Character.getNumericValue(alphabet[alphabet.length-1]))){
                    result[a]++;
                    i++;
                }
                else{
                    result[a]=alphabet[0];
                    i++;
                }
            }

        }
        String retour=new String(result);
        return retour;
    }
    public static boolean checkValid(String p_alphabet,String p_mot){
        String alphabet="";
        String mot="";
        int error=0;

        try{
            alphabet=p_alphabet;
            mot=p_mot;
            alphabet=alphabet.toLowerCase();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        for(char c:mot.toCharArray()){
            if(!(alphabet.indexOf(c)>=0)){
                error++;
                break;
            }
        }
        if(error>0){
            System.out.println("erreur : la chaine entree ne se trouve pas dasn le dictionnaire entree");
            return false;
        }
        return true;
    }
    public static boolean isAnagrame(String p_alphabet,String p_mot){

        StringBuilder mot= new StringBuilder(p_mot);
        StringBuilder alphabet=new StringBuilder(p_alphabet);
        if(alphabet.equals("")){
            return false;
        }
        for(int i=0;i<alphabet.length();i++){
            for(int a=0;a<mot.length();a++){
                if(mot.charAt(a)==alphabet.charAt(i)){
                    alphabet.deleteCharAt(i);
                    mot.deleteCharAt(a);
                    a--;
                    i--;
                    break;
                }
            }
        }
        if(mot.length()==0){
            return true;
        }
        else{
            return false;
        }
    }


    public  Object clone() throws CloneNotSupportedException{
        Exercices objet = new Exercices();
        return objet;
    }
    public static void main(String[] args){

        De monDe=new De();
        monDe.printMultipleRoll(25);

    }

}


