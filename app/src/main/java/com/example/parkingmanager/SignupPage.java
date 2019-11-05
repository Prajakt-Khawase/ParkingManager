package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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

        } else if(email.isEmpty())
        {
            emailED.setError("Enter email");

        } else if(!isValidEmail(email))
        {
            emailED.setError("Enter valid email");

        }else if(mobile.isEmpty())
        {
            mobileED.setError("Enter mobile number");

        }
        else if(password.isEmpty())
        {
            passwordED.setError("Enter password");

        }else if(cnfrmPassword.isEmpty())
        {
            cnfrmPasswordED.setError("Re-enter password");

        }else if(!password.equals(cnfrmPassword)) {
            passwordED.setError("Password mismatch");

        }
        else {
            String name = fname + " " + lname;

            mSqLiteHelper.insertRecord(name, email, mobile, password);
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupPage.this, MainActivity.class));
            finish();

        }
    }

    public void appCloseAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupPage.this);

    }
    //Email check
    public static boolean isValidEmail(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    
}
