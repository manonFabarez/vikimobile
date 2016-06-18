package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class Programme extends AppCompatActivity {

    WebView programme;

    String idP ;
    final String EXTRAT_IDP = "idP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);
        programme = (WebView) findViewById(R.id.programme);

        //Récupération de l'id du patient
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
        }

        programme.loadUrl("http://virtual-kine.ddns.net/mobile/programme.php?idP="+idP);
    }

    public void buttonDecoOnClick(View v) {
        Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
        //Remise à zéro de l'application
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}

