package com.viki.vikiapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    WebView graphique, general;
    Spinner spinner;
    ArrayAdapter<String> dataAdapter;
    String select;
    final String EXTRAT_RESULTAT = "resultatSeance";
    String idP ;
    final String EXTRAT_IDP = "idP";

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
            idP = i.getStringExtra(EXTRAT_IDP);
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
            graphique = (WebView) findViewById(R.id.webView);

            String chxr="";
            String note="";
            String chm="";
            String traitement = select.substring(15);
            Log.v("CHRTEST",select);
            String[] ligne = traitement.split(";");
            if(ligne.length>1){
                for(int z=0; z<ligne.length; z++) {

                    String[] list = ligne[z].split(",");

                    if (!list[1].equals("99")) {
                        chxr = chxr + list[0].substring(0, 10) + "|";
                        note += list[1] + ",";
                        chm += "d,4d89f9,0," + z + ",12,0" + "|";
                    }

                }
            }


            //do this
            // Showing selected spinner item
            if (item == "Evolution de la douleur") {

                //general = (WebView) findViewById(R.id.webView);

                //general.loadUrl("http://virtual-kine.ddns.net/mobile/resultatDouleur.php?idP="+idP);

                if(note.length()>0 && chm.length()>0 && chxr.length()>0)
                {
                    note = note.substring(0, note.length()-1);
                    chxr = chxr.substring(0, chxr.length()-1);
                    chm = chm.substring(0, chm.length()-1);
                }


                Log.v("CHRTEST", "note finale : "+note);
                Log.v("CHRTEST","chxr : "+chxr);
                Log.v("CHRTEST", "chm : "+chm);
                graphique.setVisibility(View.VISIBLE);
                graphique.loadUrl("http://chart.apis.google.com/chart?cht=lc&chs=550x430&chd=t:"+note+
                        "&chxr=1,0,10&chds=0,10&" +
                        "chco=4d89f9&" +
                        "chxt=x,y&chxl=0:|"+chxr+"&" +
                        "chls=3,1,0&" +
                        "chm="+chm+"&chg=0,6.67,5,5&chdl=Note par séance");
            } else {

                general = (WebView) findViewById(R.id.webView);

                general.loadUrl("http://virtual-kine.ddns.net/mobile/resultatGeneral.php?idP="+idP);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buttonDecoOnClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Resultat.this);
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

