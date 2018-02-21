package com.esgi;

/**
 * Created by damie on 03/05/2017.
 */
public class PhoneValidator {
    public boolean validate(String number){
        try{
            if(number.length()==10
                    &&number.charAt(0)=='0'
                    &&Integer.parseInt(number)>0){
                return true;
            }

        }catch (Exception e){
            return false;
        }
        return false;
    }
}
