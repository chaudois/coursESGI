package com.esgi.test.tptest.touchwithcare;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.test.tptest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyTouchListener extends OnSwipeTouchListener {
    TextView[] textViews;
    TextView scoreTextView;
    TextView maxTextView;
    Context context;

    public MyTouchListener(Context context, TextView scoreTextView,TextView maxTextView, TextView[] textViews) {
        super(context);
        this.context=context;
        this.scoreTextView=scoreTextView;
        this.maxTextView=maxTextView;
        this.textViews=textViews;
    }

    private int valueOfTextView(TextView textView){
        if(null == textView.getText() || textView.getText().length()==0){
            return 0;
        }
        return Integer.valueOf( textView.getText().toString());
    }
    private final static int UP=0;
    private final static int LEFT=1;
    private final static int RIGHT=2;
    private final static int DOWN=3;

    private void move(int to, int from,String newVal,int action){
        textViews[from].setText("");
        textViews[to].setText(newVal);
    }
    @Override
    public void onSwipeUp() {
        boolean changed =simpleUp();
        endTouch(changed);
        super.onSwipeUp();
    }

    private boolean simpleUp() {
        boolean changed =false;
        for(int i = 0 ; i<3;i++){
            for(int j = 0 ; j<2;j++){
                int val1 = valueOfTextView(textViews[i+(j*3)]);
                int val2 = valueOfTextView(textViews[i+((j+1)*3)]);
                if(0!=val2){
                    if(0==val1){
                        String newVal = val2+"";
                        move(i+(j*3),i+((j+1)*3),newVal,UP);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i,i+(j*3),newVal,UP);
                                changed=true;
                            }else if(textViews[i].getText().equals(textViews[i+(j*3)].getText())) {
                                newVal = ""+(val2*2);
                                move(i, i + (j * 3), newVal,UP);
                                changed=true;
                            }
                        }
                    }else if(val1==val2){
                        String newVal = val1+val2+"";
                        move(i+(j*3), i+((j+1)*3),newVal,UP);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i + (j * 3), newVal,UP);
                                changed=true;
                            }
                        }
                    }

                }
            }
        }
        return changed;
    }

    @Override
    public void onSwipeLeft() {
        boolean changed = simpleLeft();
        endTouch(changed);
        super.onSwipeLeft();
    }

    private boolean simpleLeft() {
        boolean changed = false;
        for(int i = 0 ; i<9;i+=3){
            for(int j = 0 ; j<2;j++){
                int val1 = valueOfTextView(textViews[i+j]);
                int val2 = valueOfTextView(textViews[i+j+1]);
                if(0!=val2){
                    if(0==val1){
                        String newVal = val2+"";
                        move(i+j, i+j+1,newVal,LEFT);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i + j, newVal,LEFT);
                                changed=true;
                            }else if(textViews[i].getText().equals(textViews[i+j].getText())) {
                                newVal = ""+(val2*2);
                                move(i, i+j,newVal,LEFT);
                                changed=true;
                            }
                        }
                    }else if(val1==val2){
                        String newVal = val1+val2+"";
                        move(i + j, i + j + 1, newVal,LEFT);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i + j, newVal,LEFT);
                                changed=true;
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    @Override
    public void onSwipeRight() {
        boolean changed =simpleRight();
        endTouch(changed);
        super.onSwipeRight();
    }

    private boolean simpleRight() {
        boolean changed = false;
        for(int i = 8 ; i>0 ;i-=3){
            for(int j = 0 ; j<2;j++){
                int val1 = valueOfTextView(textViews[i-j]);
                int val2 = valueOfTextView(textViews[i-j-1]);
                if(0!=val2) {
                    if (0 == val1) {
                        String newVal = val2 + "";
                        move(i - j, i - j - 1, newVal,RIGHT);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i - j, newVal,RIGHT);
                                changed=true;
                            }else if(textViews[i].getText().equals(textViews[i-j].getText())) {
                                newVal = ""+(val2*2);
                                move(i, i - j, newVal,RIGHT);
                                changed=true;
                            }
                        }
                    } else if (val1 == val2) {
                        String newVal = val1 + val2 + "";
                        move(i - j, i - j - 1, newVal,RIGHT);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i - j, newVal,RIGHT);
                                changed=true;
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    @Override
    public void onSwipeDown() {
        boolean changed = simpleDown();
        endTouch(changed);
        super.onSwipeDown();
    }

    private boolean simpleDown() {
        boolean changed= false;
        for(int i = 8 ; i>5 ;i--){
            for(int j = 0 ; j<2;j++){
                int val1 = valueOfTextView(textViews[i-(j*3)]);
                int val2 = valueOfTextView(textViews[i-((j+1)*3)]);
                if(0!=val2){
                    if(0==val1){
                        String newVal = val2+"";
                        move(i - (j * 3), i-((j+1)*3),newVal,DOWN);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i - (j * 3), newVal,DOWN);
                                changed=true;
                            }else if(textViews[i].getText().equals(textViews[i-(j*3)].getText())) {
                                newVal = ""+(val2*2);
                                move(i, i - (j * 3), newVal,DOWN);
                                changed=true;
                            }
                        }
                    }else  if(val1==val2){
                        String newVal = val1+val2+"";
                        move(i - (j * 3),i-((j+1)*3), newVal,DOWN);
                        changed=true;
                        if(j==1){
                            if(textViews[i].getText().length()==0){
                                move(i, i - (j * 3), newVal,DOWN);
                                changed=true;
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }
    int score = 0;
    private void endTouch(boolean addNew){
        score = 0;
        List<Integer> notused = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int val = valueOfTextView(textViews[i]);
            if (val == 0) {
                notused.add(i);
            }
            score += val;
            textViews[i].setBackgroundResource(getColor(textViews[i]));
        }
        if (notused.size() > 0 && addNew) {
            Collections.shuffle(notused);
            textViews[notused.get(0)].setText("2");
            textViews[notused.get(0)].setBackgroundResource(getColor(textViews[notused.get(0)]));
            score+=2;
        }else if(notused.size() == 0){
            boolean found = false;
            for (int i = 0; i < 9; i++) {
                if ((i % 3) == 0) {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i + 1])) {
                        found = true;
                        break;
                    }
                } else if (i == 2 || i == 5 || i == 8) {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i - 1])) {
                        found = true;
                        break;
                    }
                } else {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i - 1]) || valueOfTextView(textViews[i]) == valueOfTextView(textViews[i + 1])) {
                        found = true;
                        break;
                    }
                }
                if (i < 3) {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i + 3])) {
                        found = true;
                        break;
                    }
                } else if (i > 5) {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i - 3])) {
                        found = true;
                        break;
                    }
                } else {
                    if (valueOfTextView(textViews[i]) == valueOfTextView(textViews[i - 3]) || valueOfTextView(textViews[i]) == valueOfTextView(textViews[i + 3])) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                Toast.makeText(context, "LOOSER !!!", Toast.LENGTH_SHORT).show();
            }
        }
        scoreTextView.setText(score + "");
        int max = (maxTextView.getText().toString().length()==0)?0:Integer.valueOf(maxTextView.getText().toString());
        if(score>max){
            maxTextView.setText(score+"");
        }
    }

    private int getColor(TextView textView) {
        int color = R.color.white;
        switch (valueOfTextView(textView)){
            case 2 :
                color= R.color.two;
                break;
            case 4 :
                color = R.color.four;
                break;
            case 8 :
                color = R.color.eight;
                break;
            case 16 :
                color = R.color.sixteen;
                break;
            case 32 :
                color = R.color.thirtyTwo;
                break;
            case 64 :
                color = R.color.sixtyFour;
                break;
            case 128 :
                color = R.color.hundredTwentyEight;
                break;
            case 256 :
                color= R.color.twoHundredFiftySix;
                break;
            case 512 :
                color=R.color.fiveHundredTwelve;
                break;
            case 1024 :
                color = R.color.thousandTwentyFour;
                break;
            case 2048 :
                color = R.color.twoThousandFortyEight;
                break;
            default:
                color =  R.color.white;
                break;
        }
        return color;
    }

}