package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupPage extends AppCompatActivity {
    EditText fnameED,lnameED,emailED,mobileED,passwordED,cnfrmPasswordED;
    String fname,lname,email,mobile,password,cnfrmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page); 
    }
    public void signUp(View v){
        startActivity(new Intent(SignupPage.this, MainActivity.class));

    }
}
