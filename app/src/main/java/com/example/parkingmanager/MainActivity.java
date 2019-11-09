package com.example.parkingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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



    }


    private void initilize()
    {
        login = findViewById(R.id.login_button);
        usernameED=findViewById(R.id.username);
        passwordED=findViewById(R.id.password);
    }
    private void validation()
    {

        username=usernameED.getText().toString().toLowerCase();
        password=passwordED.getText().toString().toLowerCase();
    }

}
