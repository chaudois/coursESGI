package com.damie.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class secondScreen extends AppCompatActivity {
    String text="";
    MessageHolder message;
    private static final String TAG_SECOND_ACTIVITY = "TAG_SECOND_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Log.i(TAG_SECOND_ACTIVITY, "onCreate");

        //getIntent récupère l'intent qui a créé l'activity
         message=(MessageHolder)getIntent().getSerializableExtra(MainActivity.MAIN_MESSAGE_HOLDER);//l'objet messageHolder doit etre serializable
        text=getIntent().getStringExtra("EDIT_TEXT_CONTENT").toString();
        ((EditText) findViewById(R.id.editText2)).setText(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG_SECOND_ACTIVITY, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG_SECOND_ACTIVITY, "onResume");
        Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();//popup qui affiche le text récuperé dans l'intent qui a créé la fenetre

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG_SECOND_ACTIVITY, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG_SECOND_ACTIVITY, "onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG_SECOND_ACTIVITY, "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG_SECOND_ACTIVITY, "onDestroy");

    }

    public void returnToHome(View vue){
        Intent intent=new Intent();
        intent.putExtra("EDIT_TEXT_CONTENT",text);
        setResult(RESULT_OK,intent);
        finish();
    }
}
