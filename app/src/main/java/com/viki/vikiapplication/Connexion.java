package com.viki.vikiapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Connexion extends Activity {

    //Variables
    EditText ET_IDENT, ET_PASS;
    String login_ident, login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //Récupération des valeurs
        ET_IDENT = (EditText)findViewById(R.id.user_ident);
        ET_PASS = (EditText)findViewById(R.id.user_pass);
    }

    public void buttonOnClick(View V) {
        //Récupération de l'info dans un string
        login_ident = ET_IDENT.getText().toString();
        login_pass = ET_PASS.getText().toString();

        //Passement des infos dans une tâche en arrière plan : class BackGroundTask
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);

        //Exécution tache en arrière plan + paramètres necessaires à la taches
        backgroundTask.execute(method,login_ident,login_pass);

    }

    public void forgetOnClick(View V) {
        Intent n = new Intent(getApplicationContext(),MdpOublie.class);
        startActivity(n);
    }
}


