package com.example.techquiz;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText username,password,repassword;
    TextView rSignin;
    Button rRegister;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.rpassword);
        repassword = findViewById(R.id.rrepassword);
        rRegister = findViewById(R.id.rRegister);
        rSignin = findViewById(R.id.rSignin);
        DB =  new DBHelper(this);

        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mdp = password.getText().toString();
                String rmdp = repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(mdp) || TextUtils.isEmpty(rmdp))
                    Toast.makeText(Register.this,"ALL fields Required", Toast. LENGTH_SHORT).show();
                else {
                    if(mdp.equals(rmdp)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,mdp);
                            if(insert==true){
                                Toast.makeText(Register.this,"Registereted Succesufully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this,"User already Exists",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this , "Passwords are not matching" , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        rSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

}