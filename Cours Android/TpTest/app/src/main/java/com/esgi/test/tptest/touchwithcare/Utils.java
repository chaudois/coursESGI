package com.esgi.test.tptest.touchwithcare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by furki on 25/03/2016.
 */
public class Utils {

    public static String getNeededString(InputStream inputStream) throws IOException {
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        String line=null;
        StringBuilder sb = new StringBuilder();
        while((line=reader.readLine())!=null){
            sb.append(line).append("\n");
        }
        return sb.toString().replaceAll(".*extract\":", "").replaceAll("\\}","");
    }

}
