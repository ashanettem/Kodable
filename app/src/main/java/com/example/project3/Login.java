package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button register;
    Button signIn;
    EditText email;
    EditText pass;
    CheckBox parentChecked;



    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;
    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor2;


    int key;

    String stLogin;
    String stPass;
    Boolean isParent;
    String child;
    String person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = (EditText)findViewById(R.id.eTUN);
        pass = (EditText)findViewById(R.id.eTPW);
        register = (Button) findViewById(R.id.Hard);
        signIn = (Button) findViewById(R.id.Easy);


        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        key = sharedPref.getInt("counter", 1);
        //editor.putInt("account", 0);
        editor.apply();


        currentAccount = getSharedPreferences("Current Account", MODE_PRIVATE);
        currentAccountEditor = currentAccount.edit();

        // button that calls the function to open the registration page from signing in
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationForm();
            }
        });




        signIn = (Button) findViewById(R.id.Easy);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence username = email.getText().toString();

                //validating if the fields have correct values in them


                if (isValidEmail(username) && pass.getText().toString().length() > 3 ) {
                    for(int i = 1; i <= key; i++) {
                        stLogin = sharedPref.getString(("Login"+i), "").toString();
                        stPass = sharedPref.getString(("Password"+i), "").toString();
                        if ((email.getText().toString().toLowerCase().equals(stLogin.toLowerCase()))
                                && (pass.getText().toString().equals(stPass))) {

                            isParent = sharedPref.getBoolean("Parent"+i, false);

                            openSignInPage();

                            editor.putInt("account", i);


                            if (isParent) {

                                child = sharedPref.getString("Child"+i, "");
                                currentAccountEditor.putString("Child Account", child);
                                currentAccountEditor.commit();

                            }

                            if (!isParent){
                                person = sharedPref.getString("Login"+i, "");
                                currentAccountEditor.putString("Main Account", person);
                                currentAccountEditor.commit();
                            }

                            editor.apply();
                            return;
                        }
                        //else{

                        //}

                    }
                    Toast toast = Toast.makeText(getApplicationContext(), "Username and/or Password not Registered",
                            Toast.LENGTH_LONG);
                    toast.show();
                }

                //notifying if fields arent valid
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid email",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }


        });
    }

    //opens the registration page
    private void openRegistrationForm() {

            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }

    //opens the sign in page if the email address is valid
    private void openSignInPage() {
        if(isParent == true) {
            Intent intent = new Intent(this, Parent.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    // function that checks if email is correct using android's own pattern utility
    public final static boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target))
        {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}