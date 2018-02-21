package com.esgi.test.tptest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esgi.test.tptest.R;
import com.esgi.test.tptest.touchwithcare.Constants;
import com.esgi.test.tptest.touchwithcare.MyTouchListener;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private TextView[] textViews;
    private TextView scoreTextView;
    private TextView maxTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initTextViews();
        int max = getIntent().getIntExtra(Constants.MAX,-1);
        initSettings(max);
    }

    private void initTextViews() {
        maxTextView=(TextView)findViewById(R.id.max) ;
        scoreTextView=(TextView)findViewById(R.id.score) ;
        textViews = new TextView[9];

        LinearLayout n = (LinearLayout) findViewById(R.id.n_one);
        textViews[0]= (TextView) n.findViewById(R.id.o);
        textViews[1]= (TextView) n.findViewById(R.id.c);
        textViews[2]= (TextView) n.findViewById(R.id.e);

        LinearLayout c = (LinearLayout) findViewById(R.id.c_one);
        textViews[3]= (TextView) c.findViewById(R.id.o);
        textViews[4]= (TextView) c.findViewById(R.id.c);
        textViews[5]= (TextView) c.findViewById(R.id.e);

        LinearLayout s = (LinearLayout) findViewById(R.id.s_one);
        textViews[6]= (TextView) s.findViewById(R.id.o);
        textViews[7]= (TextView) s.findViewById(R.id.c);
        textViews[8]= (TextView) s.findViewById(R.id.e);

        for(int i=0;i<9;i++){
            textViews[i].setOnTouchListener(new MyTouchListener(this,scoreTextView,maxTextView,textViews));
        }
    }

    public void finishIt(View v){
        int score = 0;
        String scoreString = scoreTextView.getText().toString();
        if(scoreString!=null && scoreString.length()>0){
            score = Integer.valueOf(scoreString);
        }
        Intent data = new Intent();
        data.putExtra(Constants.SCORE,score);
        setResult(Constants.REQUEST_CODE_FOR_SECOND_ACTIVITY,data);
        finish();
    }



    private void initSettings(int max) {
        int rand = new Random().nextInt(9);
        textViews[rand].setText("2");
        textViews[rand].setBackgroundResource(R.color.two);
        scoreTextView.setText("2");
        if(max==0)
            maxTextView.setText("2");
        else
            maxTextView.setText(max + "");

    }



}
