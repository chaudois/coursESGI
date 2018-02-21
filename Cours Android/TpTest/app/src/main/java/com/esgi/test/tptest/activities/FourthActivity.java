package com.esgi.test.tptest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.esgi.test.tptest.async.MyHttpAsync;
import com.esgi.test.tptest.R;

public class FourthActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        TextView textView = (TextView) findViewById(R.id.fourthText);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        MyHttpAsync myHttpAsync = new MyHttpAsync(textView,progressBar);
        myHttpAsync.execute(getResources().getString(R.string.helpFourth));
    }
}
