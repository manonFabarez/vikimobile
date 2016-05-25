package com.viki.vikiapplication;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void buttonOnClick(View V){

        switch(V.getId()){
            case R.id.bt_menu_programme :
                Intent i = new Intent(getApplicationContext(),Programme.class);
                startActivity(i);
                break;
            case R.id.bt_menu_video :
                Intent j = new Intent(getApplicationContext(),Video.class);
                startActivity(j);
                break;
            case R.id.bt_menu_seance :
                Intent k = new Intent(getApplicationContext(),Seance.class);
                startActivity(k);
                break;
            case R.id.bt_menu_resultat :
                Intent l = new Intent(getApplicationContext(),Resultat.class);
                startActivity(l);
                break;
            case R.id.bt_menu_conseil :
                Intent m = new Intent(getApplicationContext(),Conseil.class);
                startActivity(m);
                break;
            case R.id.bt_menu_notif :
                Intent n = new Intent(getApplicationContext(),Notification.class);
                startActivity(n);
                break;
        }
    }
}
