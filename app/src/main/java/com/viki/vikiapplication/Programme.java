package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Programme extends AppCompatActivity {

    WebView programme;

    String idP ;
    final String EXTRAT_IDP = "idP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);
        programme = (WebView) findViewById(R.id.webView);

        //Récupération de l'id du patient
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
            Log.v("ident",idP);
        }

        programme.loadUrl("http://virtual-kine.ddns.net/mobile/programme.php?idP=1");
    }
}
