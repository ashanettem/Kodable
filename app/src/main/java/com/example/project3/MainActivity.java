package com.example.project3;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button startEasy;
    Button startHard;
    Button rtrn;


    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //mediaPlayer.stop();

        // playing background music


            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);


        // assigning values to the buttons
        startEasy = (Button)findViewById(R.id.Easy);
        startHard = (Button)findViewById(R.id.Hard);
        rtrn = (Button)findViewById(R.id.login);

        startEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtGameE();
            }
        });

        startHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtGameH();
            }
        });

        rtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rtrnLogin();
            }
        });


    }


    public void strtGameE(){
        Intent intent = new Intent(this, Easy1.class);
        startActivity(intent);
    }

    public void strtGameH(){
        Intent intent = new Intent(this, Hard1.class);
        startActivity(intent);
    }

    public void rtrnLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}