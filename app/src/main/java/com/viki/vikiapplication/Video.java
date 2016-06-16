package com.viki.vikiapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class Video extends AppCompatActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,View.OnTouchListener {

    private VideoView mVV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

    /*   voir code : https://www.youtube.com/watch?v=SiD77g9KfhA */

        int fileRes=0;
        Bundle e = getIntent().getExtras();
        if (e!=null) {
            fileRes = e.getInt("fileRes");
        }

        mVV = (VideoView)findViewById(R.id.myvideoview);
        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);
        mVV.setOnTouchListener(this);

        if (!playFileRes(fileRes)) return;

        mVV.start();


    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int fileRes = 0;
        Bundle e = getIntent().getExtras();
        if (e != null) {
            fileRes = e.getInt("fileRes");
        }
        playFileRes(fileRes);
    }

    private boolean playFileRes(int fileRes) {
        if (fileRes==0) {
            stopPlaying();
            return false;
        } else {
            //mVV.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + fileRes));
            mVV.setVideoURI(Uri.parse("C:\\Users\\manou\\Desktop\\videoflexioncoude.mp4"));
            return true;
        }
    }

    public void stopPlaying() {
        mVV.stopPlayback();
        this.finish();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        stopPlaying();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }
}