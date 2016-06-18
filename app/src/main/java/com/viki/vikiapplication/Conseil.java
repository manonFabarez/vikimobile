package com.viki.vikiapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conseil extends AppCompatActivity {

    WebView conseil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil);

        conseil = (WebView) findViewById(R.id.webView2);

        conseil.loadUrl("http://virtual-kine.ddns.net/mobile/conseil.php");

    }

    public void buttonDecoOnClick(View v) {
        Intent homeIntent = new Intent(getApplicationContext(), Connexion.class);
        //Remise à zéro de l'application
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}

