package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Resultat extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    WebView graphique;
    Spinner spinner;
    ArrayAdapter<String> dataAdapter;
    TextView tvProgramme, tvDateProg, tvNbJours;
    EditText etNomProgramme, etDateDebProg;
    String select;
    String dateProg;
    final String EXTRAT_RESULTAT = "resultatSeance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner_graphique);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Informations Générales");
        categories.add("Evolution de la douleur");

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //Récupération des éléments de la bdd
        Intent i = getIntent();
        if(i != null){
            select = i.getStringExtra(EXTRAT_RESULTAT);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        Spinner spinnerChoice = (Spinner) parent;
        if(spinnerChoice.getId() == R.id.spinner_graphique)
        {
            //recuperation des valeurs
            tvProgramme = (TextView) findViewById(R.id.tv_programme);
            graphique = (WebView) findViewById(R.id.webView);
            etNomProgramme = (EditText) findViewById(R.id.et_nomProgramme);
            tvDateProg = (TextView) findViewById(R.id.tv_dateProg);
            etDateDebProg = (EditText) findViewById(R.id.et_DateDebProg);
            tvNbJours = (TextView) findViewById(R.id.tv_nbJours);


            //Visibilité
            tvProgramme.setVisibility(View.INVISIBLE);
            graphique.setVisibility(View.INVISIBLE);
            etNomProgramme.setVisibility(View.INVISIBLE);
            tvDateProg.setVisibility(View.INVISIBLE);
            etDateDebProg.setVisibility(View.INVISIBLE);
            tvNbJours.setVisibility(View.INVISIBLE);

            String chxr="";
            String note="";
            String chm="";
            String traitement = select.substring(15);
            Log.v("CHRTEST",select);
            String[] ligne = traitement.split(";");
            for(int z=0; z<ligne.length; z++) {

                String[] list = ligne[z].split(",");
                etNomProgramme.setText(list[1]);
                etDateDebProg.setText(list[2].substring(0,10));
                dateProg = list[2].substring(0,10);
                if(z==ligne.length-1){
                    chxr+=list[3].substring(0,10);
                    note+=list[4];
                    chm+="d,4d89f9,0,"+z+",12,0";
                }else{
                    chxr = chxr + list[3].substring(0,10)+"|";
                    note+=list[4]+",";
                    chm+="d,4d89f9,0,"+z+",12,0"+"|";
                }

            }

            //do this
            // Showing selected spinner item
            if (item == "Evolution de la douleur") {
                graphique.setVisibility(View.VISIBLE);
                graphique.loadUrl("http://chart.apis.google.com/chart?cht=lc&chs=450x330&chd=t:"+note+
                        "&chxr=1,0,10&chds=0,10&" +
                        "chco=4d89f9&" +
                        "chxt=x,y&chxl=0:|"+chxr+"&" +
                        "chls=3,1,0&" +
                        "chm="+chm+"&chg=0,6.67,5,5&chdl=Note par séance");
            } else {

                //Mise en forme de la date de début du programme pour pouvoir calculer le nombre de jours entre celle-ci et aujourd'hui
                String[] dateProgSub = dateProg.split("-");
                int mYear = Integer.parseInt(dateProgSub[0]);
                int mMonth = Integer.parseInt(dateProgSub[1]);
                int mDay = Integer.parseInt(dateProgSub[2]);
                Date dateBase = new Date(mYear, mMonth, mDay);
                String comparaison = String.valueOf(this.compareDate(dateBase));
                tvNbJours.setText("Vous êtes soigné depuis "+comparaison+" jours.");

                tvProgramme.setVisibility(View.VISIBLE);
                etNomProgramme.setVisibility(View.VISIBLE);
                tvDateProg.setVisibility(View.VISIBLE);
                etDateDebProg.setVisibility(View.VISIBLE);
                tvNbJours.setVisibility(View.VISIBLE);

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

    public double compareDate(Date theEarlierDate){
        // Obtient la date actuelle
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        Date theLaterDate = new Date(mYear, mMonth, mDay);

        //Calcul du nombre de jours entre le début du programme et la date du jour
        double result = Double.POSITIVE_INFINITY;
        if (theEarlierDate != null && theLaterDate != null) {
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            Calendar aCal = Calendar.getInstance();
            aCal.setTime(theEarlierDate);
            long aFromOffset = aCal.get(Calendar.DST_OFFSET);
            aCal.setTime(theLaterDate);
            long aToOffset = aCal.get(Calendar.DST_OFFSET);
            long aDayDiffInMili = (theLaterDate.getTime() + aToOffset) - (theEarlierDate.getTime() + aFromOffset);
            result = ((double) aDayDiffInMili / MILLISECONDS_PER_DAY);
        }

        return result;
    }
}
