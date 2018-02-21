package com.damie.tpfinal;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by damie on 05/10/2017.
 */

                                    //toujours sur la structure 1-recue 2-progression 3-resultat
public class BetterHTTPTask extends AsyncTask<String,Long,String> {

    TextView textView;
    public BetterHTTPTask(TextView textView) {
        this.textView=textView;
    }

    //s'execute quand on lancer HTTPTask.execute, fonctionne comme un thread
    @Override
    protected String doInBackground(String... strings) {
        String valueToReturn="";


        try{
            URL url=new URL(strings[0]);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            //config
            httpURLConnection.setConnectTimeout(1500);
            httpURLConnection.setRequestMethod("GET");
            //.........
            httpURLConnection.connect();
            int statusCode=httpURLConnection.getResponseCode();
            //Body
            InputStream inputStream=httpURLConnection.getInputStream();

            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String line="";
            while((line=bufferedReader.readLine())!=null){
                valueToReturn+=line+"\n";
                publishProgress(Long.valueOf(valueToReturn.length()));
            }
            inputStream.close();
            httpURLConnection.disconnect();

        }catch (Exception e){
            e.printStackTrace();
            valueToReturn=e.getLocalizedMessage();
        }
        return valueToReturn;
    }

    @Override
    protected void onProgressUpdate(Long... values) {

        super.onProgressUpdate(values);
        textView.setText("Read : "+values[0].toString()+" charcters\n");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


}
