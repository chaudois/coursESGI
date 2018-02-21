package com.damie.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG_MAIN_ACTIVITY = "TAG_MAIN_ACTIVITY";
    public static final String EDIT_TEXT_CONTENT = "EDIT_TEXT_CONTENT";
    public static final int REQUEST_CODE = 2;
    public static final String MAIN_MESSAGE_HOLDER = "MAIN_MESSAGE_HOLDER";
    private int nbClick=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//lancé à l'instanciation de l'ecran
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Log.i(TAG_MAIN_ACTIVITY, "onCreate");

        if(savedInstanceState!=null){//si il y a des data dans savedInstanceState,
                                    // c'est que c'est un autre ecran qui a appelé celui ci, et non le boot de l'appli
            nbClick=savedInstanceState.getInt("nbClick",-1);
        }


        Button go = (Button) findViewById(R.id.buttonGo);//récuperation du bouton
        go.setOnClickListener(new View.OnClickListener() {//association de l'action onclick du bouton avec une fonction (ici une lambda)
            @Override
            public void onClick(View view) {
                goToSecondActivity(view);
                nbClick++;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG_MAIN_ACTIVITY, "onStart");
    }

    @Override
    protected void onResume() {//dès qu'on revien sur cet ecrans
        super.onResume();
        Log.i(TAG_MAIN_ACTIVITY, "onResume");
        TextView textNbClick=(TextView) findViewById(R.id.textViewNbClick);//récupération de la textView
        //on set le text avec le nombre de fois qu'on as clické sur le bouton
        textNbClick.setText(textNbClick.getText().toString().substring(0,textNbClick.getText().toString().length()-1)+String.valueOf(nbClick));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG_MAIN_ACTIVITY, "onPause NB="+nbClick);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG_MAIN_ACTIVITY, "onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG_MAIN_ACTIVITY, "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG_MAIN_ACTIVITY, "onDestroy");

    }


    public void goToSecondActivity(View view){
        String text=((EditText) findViewById(R.id.editText)).getText().toString();//récuperation du contenue de l'editText
        ((EditText) findViewById(R.id.editText)).setText("");//on vide l'editText
        Intent intent= new Intent(this,secondScreen.class);//on creer un intent pour y mettre de data qu'on veut envoyer à l'ecran suivant

        intent.putExtra(EDIT_TEXT_CONTENT,text);//on insert la donné dans l'intent
        //ici MAIN_MESSAGE_HOLDER est un constant string qui sert de key dans l'intent de forme key=>value
        intent.putExtra(MAIN_MESSAGE_HOLDER,new MessageHolder("coucou"));//on peut mettre de objet dans l'intent, seulement si il implements serializable
        startActivityForResult(intent, REQUEST_CODE);//on démarre la seconde activit, mais on s'attend à obtenir un resultat.
                                                    // pour etre sur que c'est la seconde activité qui nous repond, on lui envoit un code
                                                    //que cette activité devra nous renvoyer pour s'identifier
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG_MAIN_ACTIVITY,"onSaveInstance");
        outState.putInt("nbClick",nbClick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE){
            super.onActivityResult(requestCode, resultCode, data);
            ((EditText) findViewById(R.id.editText)).setText(data.getStringExtra("EDIT_TEXT_CONTENT").toString());
        }
        Log.i(TAG_MAIN_ACTIVITY,"onActivityResult");
    }
}
