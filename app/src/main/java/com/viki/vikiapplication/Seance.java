package com.viki.vikiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Seance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    RelativeLayout rv_not;
    View imageSmiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        rv_not  = (RelativeLayout) findViewById(R.id.rv_notation);
        imageSmiley = findViewById(R.id.imageView2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        rv_not.setVisibility(View.VISIBLE);
        imageSmiley.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buttonDecoOnClick(View v){
        Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
        //Remise à zéro de l'application
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

}
