package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Programme extends AppCompatActivity {

    String select;
    final String EXTRAT_RETOUR = "retourProgramme";
    String idP;
    final String EXTRAT_IDP = "idP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);

        //Récupération de l'id du patient et de la liste des dates à noter
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
            select = i.getStringExtra(EXTRAT_RETOUR);
        }


    }
}
