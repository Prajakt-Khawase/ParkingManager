package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupPage extends AppCompatActivity {
    EditText fnameED,lnameED,emailED,mobileED,passwordED,cnfrmPasswordED;
    String fname,lname,email,mobile,password,cnfrmPassword;
    SQLiteHelper mSqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mSqLiteHelper=new SQLiteHelper(this);
    }
    public void signUp(View v){
        startActivity(new Intent(SignupPage.this, MainActivity.class));

    }

    private void initialize() {


        fnameED=findViewById(R.id.fnameSignup);
        lnameED=findViewById(R.id.lnameSignup);
        emailED=findViewById(R.id.emailSignup);
        mobileED=findViewById(R.id.mobileSignup);
        passwordED=findViewById(R.id.passwordSignup);
        cnfrmPasswordED=findViewById(R.id.confirm_passwordSignup);
        
    }
    private void validations()
    {
        fname=fnameED.getText().toString().toLowerCase();
        lname=lnameED.getText().toString().toLowerCase();
        email=emailED.getText().toString().toLowerCase();
        mobile=mobileED.getText().toString().toLowerCase();
        password=passwordED.getText().toString().toLowerCase();
        cnfrmPassword=cnfrmPasswordED.getText().toString().toLowerCase();

        if(fname.isEmpty())
        {
            fnameED.setError("Enter first name");

        } else if(lname.isEmpty())
        {
            lnameED.setError("Enter last name");

        }
    }
    
}
