package com.viki.vikiapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class Menu extends AppCompatActivity {

    String idP;
    final String EXTRAT_IDP = "idP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Récupération de l'id du patient
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
        }
    }

    public void buttonOnClick(View V){

        switch(V.getId()){
            case R.id.bt_menu_programme :
                Intent i = new Intent(getApplicationContext(),Programme.class);
                startActivity(i);
                break;
            case R.id.bt_menu_video :
                Intent j = new Intent(getApplicationContext(),Video.class);
                startActivity(j);
                break;
            case R.id.bt_menu_seance :
                //Passement des infos dans une tâche en arrière plan : class BackGroundTask
                String method = "seance";
                BackgroundTask backgroundTask = new BackgroundTask(this);

                //Exécution tache en arrière plan + paramètres necessaires à la taches
                backgroundTask.execute(method,idP);

                break;
            case R.id.bt_menu_resultat :
                Intent l = new Intent(getApplicationContext(),Resultat.class);
                startActivity(l);
                break;
            case R.id.bt_menu_conseil :
                Intent m = new Intent(getApplicationContext(),Conseil.class);
                startActivity(m);
                break;
            case R.id.bt_menu_notif :
                Intent n = new Intent(getApplicationContext(),Notification.class);
                startActivity(n);
                break;
            case R.id.bt_deconnexion :
                Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
                //Remise à zéro de l'application
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }

    public void onBackPressed()
    {

            AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
            builder.setMessage("Êtes-vous sur de vouloir vous déconnecter ?");

            builder.setCancelable(false);
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();
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
