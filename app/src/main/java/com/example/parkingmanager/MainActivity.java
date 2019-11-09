package com.example.parkingmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.parkingmanager.SignupPage.isValidEmail;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText usernameED, passwordED;
    String username, password;
    SQLiteHelper mSqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSqLiteHelper = new SQLiteHelper(this);
        initilize();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();

            }
        });

    }


    private void initilize() {
        login = findViewById(R.id.login_button);
        usernameED = findViewById(R.id.username);
        passwordED = findViewById(R.id.password);
    }

    private void validation() {

        username = usernameED.getText().toString().toLowerCase();
        password = passwordED.getText().toString().toLowerCase();
        if (username.isEmpty()) {
            usernameED.setError("Enter username");
        } else if (!isValidEmail(username)) {
            usernameED.setError("Enter valid username");

        } else if (password.isEmpty()) {
            passwordED.setError("Enter password");

        } else {

            Cursor cursor = mSqLiteHelper.managerLogin(username, password);
            int row = cursor.getCount();
            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                for (int i = 1; i <= row; i++) {
                    String s0 = cursor.getString(0);   //id
                    String s1 = cursor.getString(1);//name
                    String s2 = cursor.getString(2);//email
                    String s3 = cursor.getString(3);//mobile
                    String s4 = cursor.getString(4);//password
                    // cursor.moveToNext();
                    //Toast.makeText(MainActivity.this, row2+" "+s0 + "ID : " + s1 + " " + s2 + " " + s3 + " " + s4, Toast.LENGTH_SHORT).show();
                    SharedPrefrenceUtilities.setSPboolean(MainActivity.this, SharedPrefrenceUtilities.spIsLoggedin, true);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(MainActivity.this, "Invalid username password", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void appCloseAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    public void onBackPressed()
    {
        super.onBackPressed();
        appCloseAlert();
    }
}