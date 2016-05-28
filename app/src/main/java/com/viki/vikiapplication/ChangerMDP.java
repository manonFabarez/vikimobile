package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ChangerMDP extends AppCompatActivity {

    //Variables
    EditText ET_OLD, ET_NEW, ET_CONF;
    String old_mdp, new_mdp, conf_new_mdp;
    String idP;
    final String EXTRAT_IDP = "idP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_mdp);

        //Récupération de l'id du patient
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
        }
    }

    public void valider_chgerMdp_OnClick(View V) {

        //Récupération des valeurs
        ET_OLD = (EditText)findViewById(R.id.et_ChgerMdp_AncienMDP);
        ET_NEW = (EditText)findViewById(R.id.et_ChgerMdp_NewMDP);
        ET_CONF = (EditText)findViewById(R.id.et_ChgerMDP_Confirmation);

        //Récupération de l'info dans un string
        old_mdp = ET_OLD.getText().toString();
        new_mdp = ET_NEW.getText().toString();
        conf_new_mdp = ET_CONF.getText().toString();

        //Passement des infos dans une tâche en arrière plan : class BackGroundTask
        String method = "newMdp";
        BackgroundTask backgroundTask = new BackgroundTask(this);

        //Exécution tache en arrière plan + paramètres necessaires à la taches
        backgroundTask.execute(method,old_mdp,new_mdp,conf_new_mdp,idP);

    }

}
