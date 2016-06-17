package com.viki.vikiapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {

    //Variables
    EditText ET_IDENT, ET_PASS;
    String login_ident, login_pass;
    SharedPreferences sp;

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
        finish();
        Intent n = new Intent(getApplicationContext(),MdpOublie.class);
        startActivity(n);
    }
}


