package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Parent extends AppCompatActivity {

    ImageView easy1;
    ImageView easy2;
    ImageView easy3;
    ImageView hard1;
    ImageView hard2;
    ImageView hard3;

    Boolean easy1complete;
    Boolean easy2complete;
    Boolean easy3complete;
    Boolean hard1complete;
    Boolean hard2complete;
    Boolean hard3complete;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    int key;

    String stLogin;
    String stPass;
    String child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        easy1 = (ImageView) findViewById(R.id.checkEasy1);
        easy2 = (ImageView) findViewById(R.id.checkEasy2);
        easy3 = (ImageView) findViewById(R.id.checkEasy3);
        hard1 = (ImageView) findViewById(R.id.checkHard1);
        hard2 = (ImageView) findViewById(R.id.checkHard2);
        hard3 = (ImageView) findViewById(R.id.checkHard3);

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor = sharedPref.edit();
        key = sharedPref.getInt("counter", 1);
        //editor.putInt("account", 0);
        editor.apply();


        currentAccount = getSharedPreferences("Current Account", MODE_PRIVATE);
        child = currentAccount.getString("Child Account", "");

        for (int i = 1; i <= key; i++) {
            stLogin = sharedPref.getString(("Login" + i), "").toString();
            stPass = sharedPref.getString(("Password" + i), "").toString();
            if ((child.equals(stLogin.toLowerCase()))) {

                easy1complete = sharedPref.getBoolean("Easy1" + i, false);
                if(easy1complete){easy1.setVisibility(View.VISIBLE); }

                easy2complete = sharedPref.getBoolean("Easy2" + i, false);
                if(easy2complete){easy2.setVisibility(View.VISIBLE); }

                easy3complete = sharedPref.getBoolean("Easy3" + i, false);
                if(easy3complete){easy3.setVisibility(View.VISIBLE); }

                hard1complete = sharedPref.getBoolean("Hard1" + i, false);
                if(hard1complete){hard1.setVisibility(View.VISIBLE); }

                hard2complete = sharedPref.getBoolean("Hard2" + i, false);
                if(hard2complete){hard2.setVisibility(View.VISIBLE); }

                hard3complete = sharedPref.getBoolean("Hard3" + i, false);
                if(hard3complete){hard3.setVisibility(View.VISIBLE); }

                editor.putInt("account", i);
                editor.apply();
                return;
            }

        }





    }

    public void exitApp(View view) {
        finish();
        System.exit(0);
    }
}
