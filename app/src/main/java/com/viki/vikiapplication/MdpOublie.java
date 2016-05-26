package com.viki.vikiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MdpOublie extends AppCompatActivity {

    //Variables
    EditText ET_MAIL, ET_DATENAIS;
    String email, datenais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdp_oublie);

        //Récupération des valeurs
        ET_MAIL = (EditText)findViewById(R.id.et_MdpO_email);
        ET_DATENAIS = (EditText)findViewById(R.id.et_MdpO_dateNais);
    }

    public void btValMDPOonClik(View V) {
        //Récupération de l'info dans un string
        email = ET_MAIL.getText().toString();
        datenais = ET_DATENAIS.getText().toString();

        //Passement des infos dans une tâche en arrière plan : class BackGroundTask
        String method = "mdpo";
        BackgroundTask backgroundTask = new BackgroundTask(this);

        //Exécution tache en arrière plan + paramètres necessaires à la taches
        backgroundTask.execute(method,email,datenais);

    }
}
