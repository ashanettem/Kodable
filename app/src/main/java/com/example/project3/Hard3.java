package com.example.project3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Hard3 extends AppCompatActivity {

    //variable declarations
    ImageView kody;
    Button go;
    Button upbtn;
    Button downbtn;
    Button leftbtn;
    Button rightbtn;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    MediaPlayer mediaPlayer;

    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    String person;
    String stLogin;
    int key;

    TextView tv;
    Drawable clear;

    Hard3.MyStartDragListener startDrag;
    Hard3.endDragListener endDrag;

    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;

    SharedPreferences sharedPref3;
    SharedPreferences.Editor editor3;


    int scoreSave;
    int accountNum;
    int counter;
    int scoreCheck;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard3);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // playing background music


        // gets score from previous activity
        Intent i = getIntent();
        score = i.getIntExtra("ttlscore", 0);

        // Assigning values to all the variables
        startDrag = new Hard3.MyStartDragListener();
        endDrag = new Hard3.endDragListener();
        go = (Button)findViewById(R.id.go);
        kody = (ImageView)findViewById(R.id.kody);
        tv = (TextView)findViewById(R.id.lv6tv1);

        upbtn = (Button)findViewById(R.id.lv6upBtn);
        downbtn = (Button)findViewById(R.id.lv6downBtn);
        leftbtn = (Button)findViewById(R.id.lv6leftBtn);
        rightbtn = (Button)findViewById(R.id.lv6rightBtn);
        btn1  = (Button)findViewById(R.id.lv6Btn1);
        btn2  = (Button)findViewById(R.id.lv6Btn2);
        btn3 = (Button)findViewById(R.id.lv6Btn3);
        btn4  = (Button)findViewById(R.id.lv6Btn4);

        //setting the background so I can call it when the buttons are cleared
        clear = btn1.getBackground();

        // calling class to begin dragging the object
        upbtn.setOnLongClickListener(startDrag);
        downbtn.setOnLongClickListener(startDrag);
        leftbtn.setOnLongClickListener(startDrag);
        rightbtn.setOnLongClickListener(startDrag);

        // calling class to end dragging event
        btn1.setOnDragListener(endDrag);
        btn2.setOnDragListener(endDrag);
        btn3.setOnDragListener(endDrag);
        btn4.setOnDragListener(endDrag);

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        accountNum = sharedPref.getInt("account", 0);

        sharedPref2 = getSharedPreferences(getString(R.string.pref_file_key2),
                Context.MODE_PRIVATE);
        editor2 = sharedPref2.edit();
        counter = sharedPref2.getInt("HardScoreCounter", 1);

        scoreCheck = sharedPref2.getInt("Score_Hard_"+accountNum+"_"+(counter-1), -1);

        if (scoreCheck == -1){
            counter = 1;
        }

        currentAccount = getSharedPreferences("Current Account", MODE_PRIVATE);
        currentAccountEditor = currentAccount.edit();


        sharedPref3 = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor3 = sharedPref3.edit();

        key = sharedPref3.getInt("counter", 1);
        //editor.putInt("account", 0);
        editor3.apply();

        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // checking if the correct arrows were placed in the correct buttons by
                // comparing their background bitimage values
                if(btn1.getBackground() == rightbtn.getBackground()
                        && btn2.getBackground() == upbtn.getBackground()
                        && btn3.getBackground() == rightbtn.getBackground()
                        && btn4.getBackground() == upbtn.getBackground()){

                    SharedPreferences sharedPref = getSharedPreferences("high score", MODE_PRIVATE);
                    score = sharedPref.getInt("score", 0);
                    score = 10000;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("score", score);
                    editor.commit();

                    tv.setText("Good Job!\nYour Score So Is: " + score); // displaying the score

                    // Animating the guy to complete the track
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(kody, "translationX", 350f);
                    a1.setDuration(1500);
                    ObjectAnimator a12 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a12.setDuration(3000);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(kody, "translationY", -500f);
                    a2.setDuration(1500);
                    ObjectAnimator a22 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a22.setDuration(3000);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(kody, "translationX", 825f);
                    a3.setDuration(1500);
                    ObjectAnimator a32 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a32.setDuration(3000);
                    ObjectAnimator a4 = ObjectAnimator.ofFloat(kody, "translationY", -900f);
                    a4.setDuration(1500);
                    ObjectAnimator a42 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a42.setDuration(2000);
                    // using AnimatorSet to have the animations occur in sequence
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(a1,a12);
                    set.playTogether(a2,a22);
                    set.playTogether(a3,a32);
                    set.playTogether(a4,a42);
                    set.playSequentially(a1, a2, a3,a4);
                    set.start();


                    person = currentAccount.getString("Main Account", "");

                    for (int i = 1; i <= key; i++) {
                        stLogin = sharedPref3.getString(("Login" + i), "").toString();
                        if ((person.equals(stLogin.toLowerCase()))) {

                            editor3.putBoolean("Hard3"+i, true);

                            editor3.putInt("account", i);
                            editor3.apply();
                            //return;
                            break;

                        }

                    }


                    // moving on to the next page after a delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Hard3.this, Score.class);
                            sharedPref2 = getApplicationContext().getSharedPreferences("Scores",0);
                            editor2 = sharedPref2.edit();
                            //editor2.putInt("ScoreAccount"+accountNum, accountNum);
                            editor2.putInt("Score_Hard_"+accountNum + "_" + counter, score);
                            counter++;
                            editor2.putInt("HardScoreCounter", counter);
                            editor2.apply();

                            intent.putExtra("hardScore", score); // passing score to next page
                            startActivity(intent);
                            finish();
                        }
                    }, 9000);


                }
                else {
                    // clearing out the buttons for incorrect answers
                    tv.setText("Try Again");
                    btn1.setBackground(clear);
                    btn2.setBackground(clear);
                    btn3.setBackground(clear);
                    btn4.setBackground(clear);
                    if (score > 0){score -= 10;} // decrementing the score
                }

            }
        });

    }

    private class MyStartDragListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view){
            Hard3.WithDragShadow shadow = new Hard3.WithDragShadow(view);
            ClipData data= ClipData.newPlainText("","");
            view.startDrag(data, shadow,view,0);
            return false;
        }
    }

    private class endDragListener implements View.OnDragListener{
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onDrag(View view, DragEvent dragEvent){
            if(dragEvent.getAction()==DragEvent.ACTION_DROP){
                ((Button)view).setBackground(
                        ((Button)dragEvent.getLocalState()).getBackground());
            }
            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder{
        public WithDragShadow(View v){
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas){
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint){
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

    }


}