package com.viki.vikiapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class Seance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner listNote, spinner;
    RelativeLayout rv_not;
    String select;
    final String EXTRAT_RETOUR = "retourSeance";
    String idP;
    TextView tvnoseance, tvchoiceseance;
    int testDonnees;
    final String EXTRAT_IDP = "idP";
    ArrayAdapter<String> dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);

        //Récupération de l'id du patient et de la liste des dates à noter
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
            select = i.getStringExtra(EXTRAT_RETOUR);
        }

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        rv_not = (RelativeLayout) findViewById(R.id.rv_notation);
        rv_not.setVisibility(View.INVISIBLE);
        listNote = (Spinner) findViewById(R.id.listeDouleur);
        tvnoseance = (TextView) findViewById(R.id.tv_noseance);
        tvnoseance.setVisibility(View.INVISIBLE);
        tvchoiceseance = (TextView) findViewById(R.id.tv_choixSeance);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        String traitement = select.substring(13);
        String[] ligne = traitement.split(";");
        testDonnees = traitement.length();
        if(testDonnees!=0)
        {
            for(int z=0; z<ligne.length; z++) {
                String[] list = ligne[z].split(",");
                categories.add(list[1].substring(0,10));
            }
        }



        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //LISTE DEROULANTE DE LA DOULEUR

        // Spinner click listener
        listNote.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<Integer> douleur = new ArrayList<>();
        for(int z=0; z<11; z++) {
            douleur.add(z);
        }

        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapterNote = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, douleur);

        // Drop down layout style - list view with radio button
        dataAdapterNote.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        listNote.setAdapter(dataAdapterNote);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        Spinner spinnerChoice = (Spinner) parent;
        if(spinnerChoice.getId() == R.id.spinner)
        {
            //do this
            // Showing selected spinner item
            if (item != "") {
                rv_not.setVisibility(View.VISIBLE);
            } else {
                rv_not.setVisibility(View.INVISIBLE);
                if(testDonnees==0)
                {
                    spinner.setVisibility(View.INVISIBLE);
                    tvnoseance.setVisibility(View.VISIBLE);
                    tvchoiceseance.setVisibility(View.INVISIBLE);

                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buttonDecoOnClick(View v) {
        Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
        //Remise à zéro de l'application
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    public void buttonValiderNotationOnClick(View v){
        String dateSeance = spinner.getSelectedItem().toString();
        String noteSeance = listNote.getSelectedItem().toString();
        EditText commentaire = (EditText) findViewById(R.id.et_commentaireSeance);
        String commentaireSeance = commentaire.getText().toString().replace("'","''");

        //Passement des infos dans une tâche en arrière plan : class BackGroundTask
        String method = "noterseance";
        BackgroundTask backgroundTask = new BackgroundTask(this);

        //Exécution tache en arrière plan + paramètres necessaires à la taches
        backgroundTask.execute(method,idP, dateSeance, noteSeance, commentaireSeance);

        listNote.setSelection(0);
        rv_not.setVisibility(View.INVISIBLE);
        dataAdapter.remove(dateSeance);
        spinner.setSelection(0);
        if(dataAdapter.getCount()==1)
        {
                spinner.setVisibility(View.INVISIBLE);
                tvnoseance.setVisibility(View.VISIBLE);
                tvchoiceseance.setVisibility(View.INVISIBLE);
        }

    }



}
