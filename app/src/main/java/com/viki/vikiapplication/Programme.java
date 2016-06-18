package com.viki.vikiapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Programme.this);
        builder.setMessage("Êtes-vous sur de vouloir vous déconnecter ?");

        builder.setCancelable(false);
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
                //Remise à zéro de l'application
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }


        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {

                dialog.cancel();

            }
        });
        builder.show();
    }
}

