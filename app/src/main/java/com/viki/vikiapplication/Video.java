package com.viki.vikiapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Video extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;
    TextView tvnoexercice, tv_choiceexercice;
    Spinner spinner;
    String select;
    final String EXTRAT_VIDEO = "exempleExo";
    String idP;
    final String EXTRAT_IDP = "idP";
    ArrayAdapter<String> dataAdapter;
    String VideoURL;
    String[] ligne;
    int testDonnees;
    HashMap<String, String> exercice = new HashMap<String, String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //Récupération de l'id du patient et de la liste des dates à noter
        Intent i = getIntent();
        if(i != null){
            idP = i.getStringExtra(EXTRAT_IDP);
            select = i.getStringExtra(EXTRAT_VIDEO);
        }

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner_exercice);
        videoview = (VideoView) findViewById(R.id.myvideoview);
        videoview.setVisibility(View.INVISIBLE);
        tvnoexercice = (TextView) findViewById(R.id.tv_noexercice);
        tvnoexercice.setVisibility(View.INVISIBLE);
        tv_choiceexercice = (TextView) findViewById(R.id.tv_exercice);


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        String traitement = select.substring(11);
        ligne = traitement.split(";");
        testDonnees = traitement.length();
        if(testDonnees!=0)
        {
            for(int z=0; z<ligne.length; z++) {
                String[] list = ligne[z].split(",");
                categories.add(list[0]);
                exercice.put(list[0],list[1]);
            }
        }


        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }


    public void streamVideo(String urlVideo)
    {
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(Video.this);

        // Set progressbar title
        pDialog.setTitle("Video Streaming");

        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediaController = new MediaController(Video.this);

            mediaController.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(urlVideo);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                videoview.start();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        Spinner spinnerChoice = (Spinner) parent;
        if(spinnerChoice.getId() == R.id.spinner_exercice)
        {
            //do this
            // Showing selected spinner item
            if (item != "") {
                videoview.setVisibility(View.VISIBLE);

                VideoURL = exercice.get(item);

                this.streamVideo(VideoURL);
            } else {
                videoview.setVisibility(View.INVISIBLE);
                if(testDonnees==0)
                {
                    spinner.setVisibility(View.INVISIBLE);
                    tvnoexercice.setVisibility(View.VISIBLE);
                    tv_choiceexercice.setVisibility(View.INVISIBLE);
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
}