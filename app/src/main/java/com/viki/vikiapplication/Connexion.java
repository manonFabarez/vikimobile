package com.viki.vikiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
    }

    public void buttonOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), Menu.class);
        startActivity(i);
    }
}


