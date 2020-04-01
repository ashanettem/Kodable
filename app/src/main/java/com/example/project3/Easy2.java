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

public class Easy2 extends AppCompatActivity {


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

    TextView tv;
    Drawable clear;

    Easy2.MyStartDragListener startDrag;
    Easy2.endDragListener endDrag;

    MediaPlayer mediaPlayer;

    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor2;



    String person;
    String stLogin;
    int key;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy2);
        // playing background music

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // gets score from previous activity
        Intent i = getIntent();
        score = i.getIntExtra("ttlscore", 0);

        startDrag = new Easy2.MyStartDragListener();
        endDrag = new Easy2.endDragListener();
        go = (Button)findViewById(R.id.go);

        upbtn = (Button)findViewById(R.id.lv2upBtn);
        downbtn = (Button)findViewById(R.id.lv2downBtn);
        leftbtn = (Button)findViewById(R.id.lv2leftBtn);
        rightbtn = (Button)findViewById(R.id.lv2rightBtn);
        btn1  = (Button)findViewById(R.id.lv2Btn1);
        btn2  = (Button)findViewById(R.id.lv2Btn2);
        btn3 = (Button)findViewById(R.id.lv2Btn3);
        btn4  = (Button)findViewById(R.id.lv2Btn4);

        clear = btn1.getBackground();

        upbtn.setOnLongClickListener(startDrag);
        downbtn.setOnLongClickListener(startDrag);
        leftbtn.setOnLongClickListener(startDrag);
        rightbtn.setOnLongClickListener(startDrag);

        btn1.setOnDragListener(endDrag);
        btn2.setOnDragListener(endDrag);
        btn3.setOnDragListener(endDrag);
        btn4.setOnDragListener(endDrag);

        kody = (ImageView)findViewById(R.id.kody);

        tv = (TextView)findViewById(R.id.lv2tv1);

        currentAccount = getSharedPreferences("Current Account", MODE_PRIVATE);
        currentAccountEditor = currentAccount.edit();

        sharedPref2 = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor2 = sharedPref2.edit();

        key = sharedPref2.getInt("counter", 1);
        //editor.putInt("account", 0);
        editor2.apply();

        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(btn1.getBackground() == rightbtn.getBackground()
                        && btn2.getBackground() == upbtn.getBackground()) { //821a49d

                    SharedPreferences sharedPref = getSharedPreferences("high score", MODE_PRIVATE);
                    score = sharedPref.getInt("score", 0);
                    score = 3000;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("score", score);
                    editor.commit();

                    tv.setText("Good Job!\nYour Score Is: " + score);

                    ObjectAnimator a1 = ObjectAnimator.ofFloat(kody, "translationX", 650f);
                    a1.setDuration(1500);
                    ObjectAnimator a12 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a12.setDuration(3000);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(kody, "translationY", -700f);
                    a2.setDuration(1500);
                    ObjectAnimator a22 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a22.setDuration(2000);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(a1,a12);
                    set.playTogether(a2, a22);
                    set.playSequentially(a1, a2);
                    set.start();


                    person = currentAccount.getString("Main Account", "");

                    for (int i = 1; i <= key; i++) {
                        stLogin = sharedPref2.getString(("Login" + i), "").toString();
                        if ((person.equals(stLogin.toLowerCase()))) {

                            editor2.putBoolean("Easy2"+i, true);

                            editor2.putInt("account", i);
                            editor2.apply();
                            //return;
                            break;
                        }

                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Easy2.this, Easy3.class);
                            intent.putExtra("ttlscore", score);
                            startActivity(intent);
                            finish();
                        }
                    }, 6000);


                }
                else {
                    tv.setText("Try Again");
                    btn1.setBackground(clear);
                    btn2.setBackground(clear);
                    btn3.setBackground(clear);
                    btn4.setBackground(clear);
                    if (score > 0){score -= 10;}
                }

            }
        });

    }

    private class MyStartDragListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view){
            Easy2.WithDragShadow shadow = new Easy2.WithDragShadow(view);
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