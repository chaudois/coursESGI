package com.damie.tpfinal;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by damie on 05/10/2017.
 */

public class IncrementerTask
        extends AsyncTask<Long,Long,String> {
    public static final String INCREMENT_IS_FINISHED = "Increment is finished";
    TextView textView;

    public IncrementerTask(TextView textView) {
        this.textView=textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("");
    }

    @Override
    protected String doInBackground(Long... longs) {
        Long maxValue=longs[0];
        long increment=0;
        while(increment<maxValue){
            increment++;
            if(increment%500==0){
                publishProgress(increment);
            }
        }
        return INCREMENT_IS_FINISHED;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        textView.setText(values[0].toString());
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(s);
    }
}
