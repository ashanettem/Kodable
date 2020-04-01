package com.example.project3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Easy1 extends AppCompatActivity {

    Button upButton;
    ImageView kody;
    Button go;
    Button downButton;
    Button leftButton;
    Button rightButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Animation animation;

    TextView tv;
    Drawable clear;
    MyStartDragListener startDrag;
    endDragListener endDrag;

    MediaPlayer mediaPlayer;

    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor2;

    String person;
    String stLogin;
    int key;

    int score = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy1);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // playing background music

        startDrag = new MyStartDragListener();
        endDrag = new endDragListener();
        go = (Button)findViewById(R.id.go);




        upButton = (Button)findViewById(R.id.upBtn);
        downButton = (Button)findViewById(R.id.downBtn);
        leftButton = (Button)findViewById(R.id.leftBtn);
        rightButton = (Button)findViewById(R.id.rightBtn);
        button1  = (Button)findViewById(R.id.Btn1);
        button2  = (Button)findViewById(R.id.Btn2);
        button3 = (Button)findViewById(R.id.Btn3);
        button4  = (Button)findViewById(R.id.Btn4);

        clear = button1.getBackground();

        upButton.setOnLongClickListener(startDrag);
        downButton.setOnLongClickListener(startDrag);
        leftButton.setOnLongClickListener(startDrag);
        rightButton.setOnLongClickListener(startDrag);

        button1.setOnDragListener(endDrag);
        button2.setOnDragListener(endDrag);
        button3.setOnDragListener(endDrag);
        button4.setOnDragListener(endDrag);

        kody = (ImageView)findViewById(R.id.kody);


        tv = (TextView)findViewById(R.id.tv1);

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
                if(button1.getBackground() == rightButton.getBackground()
                        && button2.getBackground() == downButton.getBackground()) { //821a49d


                    tv.setText("Good Job!\nYour Score Is: " + score);


                    ObjectAnimator a1 = ObjectAnimator.ofFloat(kody, "translationX", 750f);
                    a1.setDuration(1500);
                    ObjectAnimator a12 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a12.setDuration(3000);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(kody, "translationY", 800f);
                    a2.setDuration(1500);
                    ObjectAnimator a22 = ObjectAnimator.ofFloat(kody, "rotation", 0f, 720f);
                    a22.setDuration(2000);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(a1,a12);
                    set.playTogether(a2,a22);
                    set.playSequentially(a1, a2);
                    set.start();

                    SharedPreferences sharedPref = getSharedPreferences("high score", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("score", score);
                    //editor.putInt("high score", 0);
                    editor.commit();


                    person = currentAccount.getString("Main Account", "");

                    for (int i = 1; i <= key; i++) {
                        stLogin = sharedPref2.getString(("Login" + i), "").toString();
                        if ((person.equals(stLogin.toLowerCase()))) {

                            editor2.putBoolean("Easy1"+i, true);

                            editor2.putInt("account", i);
                            editor2.apply();
                            //return;
                            break;
                        }

                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Easy1.this, Easy2.class);
                            intent.putExtra("ttlscore", score);
                            startActivity(intent);
                            finish();
                        }
                    }, 6000);



                }
                else {
                    tv.setText("Try Again");
                    button1.setBackground(clear);
                    button2.setBackground(clear);
                    button3.setBackground(clear);
                    button4.setBackground(clear);
                    if (score > 0){score -= 10;}
                }

            }
        });

    }

    private class MyStartDragListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view){
            WithDragShadow shadow = new WithDragShadow(view);
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
