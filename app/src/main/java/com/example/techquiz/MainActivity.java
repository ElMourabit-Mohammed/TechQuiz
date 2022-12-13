package com.example.techquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button wLogin,wRegistration,waboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wLogin = (Button) findViewById(R.id.wLogin);
        wRegistration = (Button) findViewById(R.id.wRegister);
        waboutus = (Button) findViewById(R.id.waboutus);

        wLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() ,Login.class);
                startActivity(intent);
            }
        });

        wRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() ,Register.class);
                startActivity(intent);
            }
        });

        waboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Aboutus.class);
                startActivity(intent);
            }
        });

    }
}