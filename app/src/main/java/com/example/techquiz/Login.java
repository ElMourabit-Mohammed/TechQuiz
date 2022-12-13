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


public class Login extends AppCompatActivity {

    EditText userName,password;
    TextView lSignup;
    Button lLogin;
    DBHelper DB;
    User currentuser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.Username);
        password = findViewById(R.id.password);
        lSignup = findViewById(R.id.lSignup);
        lLogin = findViewById(R.id.wlogin);
        DB = new DBHelper(this);
        currentuser = new User();

        lLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=userName.getText().toString();
                String mdp=password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(mdp))
                    Toast.makeText(Login.this,"All fields Required" , Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,mdp);
                    currentuser = DB.selectUser(user);
                    if(checkuserpass==true && currentuser!=null){
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("currentuser", currentuser);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        lSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }
}