package com.example.project3;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    Button submit;
    EditText emailaddress;
    String login = "";
    String password = "";
    Boolean isparent;
    EditText fname, lname, pass;
    TextView mTvDate;
    TextView childEmailLabel;
    EditText childEmail;
    CheckBox parent;
    String child;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    int key;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    SharedPreferences currentAccount;
    SharedPreferences.Editor currentAccountEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        emailaddress = (EditText)findViewById(R.id.eTEmail);
        submit = (Button)findViewById(R.id.rButton);
        fname = (EditText)findViewById(R.id.eTFN);
        lname =  (EditText)findViewById(R.id.eTLN);
        pass = (EditText)findViewById(R.id.eTPWD);
        mTvDate = (TextView) findViewById(R.id.tvDate);
        childEmail = (EditText)findViewById(R.id.eTChildEmail1);
        childEmailLabel = (TextView)findViewById(R.id.tVEA3);
        parent = (CheckBox)findViewById(R.id.checkbox);


        currentAccount = getSharedPreferences("Parent", MODE_PRIVATE);
        //isparent = parent.isChecked();

        //if(isparent){
           // childEmail.setVisibility(View.VISIBLE);
           // childEmailLabel.setVisibility(View.VISIBLE);

        //}



        mTvDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Register.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                        mDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mTvDate.setText(date);
            }
        };

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        key = sharedPref.getInt("counter", 1);


        // Intent to move back to previous page upon successful validation
        final Intent success = new Intent(this, Login.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //created a charsequence for function to validate email
                CharSequence login = emailaddress.getText().toString();

                //check if any fields are empty
                if (emailaddress.getText().toString().isEmpty() || fname.getText().toString().isEmpty()
                        || lname.getText().toString().isEmpty() || pass.getText().toString().isEmpty()
                        || mTvDate.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "No missing fields allowed",
                            Toast.LENGTH_LONG);
                    toast.show();
                }

                //check email validation
                else if (!isValidEmail(login)){
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Please provide accurate email",
                            Toast.LENGTH_LONG);
                    toast3.show();
                }

                // Validate the length of the name fields
                else if (fname.getText().toString().length() < 3 || lname.getText().toString().length() < 3){
                    Toast toast4 = Toast.makeText(getApplicationContext(), "Name must be at least 3 letters",
                            Toast.LENGTH_LONG);
                    toast4.show();
                }

                // if everything is valid, call the intent to move back to previous page
                else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Account Created",
                            Toast.LENGTH_LONG);

                    login = emailaddress.getText().toString();
                    password = pass.getText().toString();
                    child = childEmail.getText().toString();



                    //else{isparent = false;}

                    // saves the login credentials to shared preferences so it can be seen on SignInPage
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("AccountInfo",0);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Login"+key, login.toString());
                    editor.putString("Password"+key, password);
                    editor.putString("FirstName"+key, fname.getText().toString());
                    editor.putString("LastName"+key, lname.getText().toString());
                    editor.putBoolean("Parent"+key, isparent);
                    editor.putString("Child"+key, child);
                    editor.putBoolean("Easy1"+key, false);
                    editor.putBoolean("Easy2"+key, false);
                    editor.putBoolean("Easy3"+key, false);
                    editor.putBoolean("Hard1"+key, false);
                    editor.putBoolean("Hard1"+key, false);
                    editor.putBoolean("Hard2"+key, false);
                    editor.putBoolean("Hard3"+key, false);

                    key++;
                    editor.putInt("counter", key);
                    editor.apply();
                    toast1.show();
                    startActivity(success);

                }
            }
        });
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

    public void checkParent(View view) {

            isparent = true;
            childEmail.setVisibility(View.VISIBLE);
            childEmailLabel.setVisibility(View.VISIBLE);


    }
}