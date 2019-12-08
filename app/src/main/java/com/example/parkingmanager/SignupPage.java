package com.example.parkingmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignupPage extends AppCompatActivity {
    EditText fnameED,lnameED,emailED,mobileED,passwordED,cnfrmPasswordED;
    String fname,lname,email,mobile,password,cnfrmPassword;
    SQLiteHelper mSqLiteHelper;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mSqLiteHelper=new SQLiteHelper(this);

        initialize();
        findViewById(R.id.signButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validations();
            }
        });
        findViewById(R.id.signupLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(SignupPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
        else if (mobile.length()!= 10) {
            mobileED.setError("Enter 10 digit valid mobile number");
            mobileED.requestFocus();
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
        builder.setTitle("Close App?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //handle yes logic here
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //nothing here
            }
        });

        builder.show();
    }


    //Email check
    public static boolean isValidEmail(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        },2000);



    }
}

            

    
    

