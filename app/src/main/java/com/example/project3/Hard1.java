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

public class Hard1 extends AppCompatActivity {

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

    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor2;

    TextView tv;
    Drawable clear;

    Hard1.MyStartDragListener startDrag;
    Hard1.endDragListener endDrag;

    MediaPlayer mediaPlayer;

    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    String person;
    String stLogin;
    int key;

    int score = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard1);

        // playing background music
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // Assigning values to all the variables
        startDrag = new Hard1.MyStartDragListener();
        endDrag = new Hard1.endDragListener();
        go = (Button)findViewById(R.id.go);
        kody = (ImageView)findViewById(R.id.kody);
        tv = (TextView)findViewById(R.id.lv4tv1);

        upbtn = (Button)findViewById(R.id.lv4upBtn);
        downbtn = (Button)findViewById(R.id.lv4downBtn);
        leftbtn = (Button)findViewById(R.id.lv4leftBtn);
        rightbtn = (Button)findViewById(R.id.lv4rightBtn);
        btn1  = (Button)findViewById(R.id.lv4Btn1);
        btn2  = (Button)findViewById(R.id.lv4Btn2);
        btn3 = (Button)findViewById(R.id.lv4Btn3);
        btn4  = (Button)findViewById(R.id.lv4Btn4);

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

        sharedPref2 = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor2 = sharedPref2.edit();

        key = sharedPref2.getInt("counter", 1);
        //editor.putInt("account", 0);
        editor2.apply();

        currentAccount = getSharedPreferences("Current Account", MODE_PRIVATE);
        currentAccountEditor = currentAccount.edit();


        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // checking if the correct arrows were placed in the correct buttons by
                // comparing their background bitimage values
                if(btn1.getBackground() == rightbtn.getBackground()
                        && btn2.getBackground() == downbtn.getBackground()
                        && btn3.getBackground() == leftbtn.getBackground()
                        && btn4.getBackground() == downbtn.getBackground()) {

                    SharedPreferences sharedPref = getSharedPreferences("high score", MODE_PRIVATE);
                    score = sharedPref.getInt("score", 0);
                    score = 7000;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("score", score);
                    editor.commit();

                    tv.setText("Good Job!\nYour Score Is: " + score); // displaying the score

                    // Animating the guy to complete the track
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(kody, "translationX", 640f);
                    a1.setDuration(1500);
                    ObjectAnimator a12 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a12.setDuration(3000);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(kody, "translationY", 700f);
                    a2.setDuration(1500);
                    ObjectAnimator a22 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a22.setDuration(3000);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(kody, "translationX", 125f);
                    a3.setDuration(1500);
                    ObjectAnimator a32 = ObjectAnimator.ofFloat(kody, "rotation", 0f, -720f);
                    a32.setDuration(3000);
                    ObjectAnimator a4 = ObjectAnimator.ofFloat(kody, "translationY", 1030f);
                    a4.setDuration(1500);
                    ObjectAnimator a42 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a42.setDuration(2000);
                    // using AnimatorSet to have the animations occur in sequence
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(a1,a12);
                    set.playTogether(a2,a22);
                    set.playTogether(a3,a32);
                    set.playTogether(a4,a42);
                    set.playSequentially(a1, a2, a3, a4);
                    set.start();


                    person = currentAccount.getString("Main Account", "");

                    for (int i = 1; i <= key; i++) {
                        stLogin = sharedPref2.getString(("Login" + i), "").toString();
                        if ((person.equals(stLogin.toLowerCase()))) {

                            editor2.putBoolean("Hard1"+i, true);

                            editor2.putInt("account", i);
                            editor2.apply();
                            //freturn;
                            break;

                        }

                    }

                    // moving on to the next page after a delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Hard1.this, Hard2.class);
                            intent.putExtra("ttlscore", score); // passing score to next page
                            startActivity(intent);
                            finish();
                        }
                    }, 8000);


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
            Hard1.WithDragShadow shadow = new Hard1.WithDragShadow(view);
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