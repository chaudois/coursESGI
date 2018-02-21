package com.esgi.test.tptest.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.esgi.test.tptest.touchwithcare.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpAsync  extends AsyncTask<String,Long,String>{
    TextView textView ;
    ProgressBar progressBar;

    public MyHttpAsync(TextView textView, ProgressBar progressBar) {
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        String toReturn="";
        try{
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            int status = conn.getResponseCode();//DO SOMETHING MAYBE

            InputStream inputStream = conn.getInputStream();
            toReturn = Utils.getNeededString(inputStream);

            inputStream.close();
            conn.disconnect();

            Thread.sleep(1000);

        } catch (Exception e) {
            Log.e("TOTO", "Error occured", e);
        }
        return toReturn;
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.setVisibility(View.GONE);
        textView.setText(s);
    }
}
