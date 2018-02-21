//créé par Damien Chaudois
//3AL1
package sample.esgiexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    @FXML
    TextArea txtBox1;
    @FXML
    TextArea txtBox2;
    @FXML
    RadioButton radio1;
    @FXML
    RadioButton radio2;
    @FXML
    RadioButton radio3;
    @FXML
    TextArea txtBoxDelta;

    int delta;

    public void switchAlgo(){
        if(radio1.isSelected()){
            txtBoxDelta.setDisable(false);
        }
        if(radio2.isSelected()){
            txtBoxDelta.setDisable(true);
        }
        if(radio3.isSelected()){
            txtBoxDelta.setDisable(true);
        }
    }
    public void pressButtonCrypt(javafx.event.ActionEvent event) throws IOException {

        if(radio1.isSelected()){
            delta=Integer.parseInt(txtBoxDelta.getText());

            System.out.println("crypting '"+txtBox1.getText()+"' with algo 1");
            System.out.println("result = '"+cryptAlgo1(txtBox1.getText())+"'");
            txtBox2.setText(cryptAlgo1(txtBox1.getText()));
        }
        if(radio2.isSelected()){
            System.out.println("crypting '"+txtBox1.getText()+"' with algo 2");
            System.out.println("result = '"+cryptAlgo2(txtBox1.getText())+"'");
            txtBox2.setText(cryptAlgo2(txtBox1.getText()));
        }
        if(radio3.isSelected()){
            System.out.println("crypting '"+txtBox1.getText()+"' with algo 3");
            System.out.println("result = '"+cryptAlgo3(txtBox1.getText())+"'");

            txtBox2.setText(cryptAlgo3(txtBox1.getText()));
        }
    }
    public void pressButtonUncrypt(javafx.event.ActionEvent event) throws IOException {
        System.out.println("uncrypting '"+txtBox2.getText()+"'");
        if(radio1.isSelected()){
            delta=-Integer.parseInt(txtBoxDelta.getText());
            txtBox1.setText(unCryptAlgo1(txtBox2.getText()));
        }
        if(radio2.isSelected()){
            txtBox1.setText(unCryptAlgo2(txtBox2.getText()));
        }
        if(radio3.isSelected()){
            txtBox1.setText(unCryptAlgo3(txtBox2.getText()));
        }
    }

    String cryptAlgo1(String text){
        text=text.toUpperCase();
        StringBuffer retour=new StringBuffer();
        for(int i=0;i<text.length();i++){
            String toCrypt=text.substring(i,i+1);
            if((toCrypt.charAt(0)>64 && toCrypt.charAt(0)<=90)){
                if((toCrypt.charAt(0)<=90 && toCrypt.charAt(0)>=65) && (toCrypt.charAt(0)+delta>90 || toCrypt.charAt(0)+delta<65)){
                    if(delta>0){
                        retour.append((char)(toCrypt.charAt(0)+delta-26));

                    }else  {
                        retour.append((char)(toCrypt.charAt(0)+delta+26));

                    }
                }
                else{
                    retour.append((char)(toCrypt.charAt(0)+delta));
                }
            }else{
                retour.append(toCrypt);
            }


        }
        return retour.toString().toLowerCase();
    }
    String cryptAlgo2(String text){
        text=text.toLowerCase();
        StringBuffer retour=new StringBuffer();

        for(int i=0;i<text.length();i++){
            char toCrypt=text.charAt(i);
            if(toCrypt>97 && toCrypt<=122){
                int ligne=((int)toCrypt-97)/6+1;
                long  colone=Math.round((((double)((int)toCrypt-97)/6+1)-ligne)*6+1);
                retour.append(String.valueOf(ligne));
                retour.append(String.valueOf(colone));


            }
            else if(toCrypt>=48 && toCrypt<=57) {
                toCrypt+=75;
                int ligne=((int)toCrypt-97)/6+1;
                long  colone=Math.round((((double)((int)toCrypt-97)/6+1)-ligne)*6+1);
                retour.append(String.valueOf(ligne));
                retour.append(String.valueOf(colone));
            }else{
                retour.append(text.charAt(i));

            }
        }



        return retour.toString();
    }
    String cryptAlgo3(String text){
        int[] permutA={21,27,31,40};
        int[] permutB={15};
        int[] permutC={1,33};
        int[] permutD={20,34};
        int[] permutE={5,78,54};

        int cptA=0;
        int cptB=0;
        int cptC=0;
        int cptD=0;
        int cptE=0;









        return "";
    }
    String unCryptAlgo1(String text){
        text=text.toUpperCase();
        StringBuffer retour=new StringBuffer();
        for(int i=0;i<text.length();i++){
            String toCrypt=text.substring(i,i+1);
            if((toCrypt.charAt(0)>64 && toCrypt.charAt(0)<=90)){
                if((toCrypt.charAt(0)<=90 && toCrypt.charAt(0)>=65) && (toCrypt.charAt(0)+delta>90 || toCrypt.charAt(0)+delta<65)){
                    if(delta>0){
                        retour.append((char)(toCrypt.charAt(0)+delta-26));

                    }else  {
                        retour.append((char)(toCrypt.charAt(0)+delta+26));

                    }
                }
                else{
                    retour.append((char)(toCrypt.charAt(0)+delta));
                }
            }else{
                retour.append(toCrypt);
            }


        }
        return retour.toString().toLowerCase();
    }
    String unCryptAlgo2(String text){

        StringBuffer retour=new StringBuffer();

        for(int i=0;i<text.length();i+=2){
            try{
                int result=(Integer.parseInt(String.valueOf(text.charAt(i)))-1)*6;
                result+=(Integer.parseInt(String.valueOf(text.charAt(i+1))));
                if(result>26){
                    retour.append((char)(result+21));
                }else{
                    retour.append((char)(result+96));

                }
            }catch (Exception e){
                retour.append(text.charAt(i));
                i--;
            }

        }


        return retour.toString();
    }
    String unCryptAlgo3(String text){
        return "";
    }
}
