package com.example.techquiz;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private String username;
    TextView userid, usernameview, userscore;
    User currentuser;
    Button passquiz,rank,quit;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //--------
        Intent intent = getIntent();
        currentuser = (User) intent.getSerializableExtra("currentuser");
        userid = (TextView) findViewById(R.id.userid);
        usernameview = (TextView) findViewById(R.id.username);
        userscore = (TextView) findViewById(R.id.userscore);
        // show user data
        userid.setText("@"+currentuser.getUsername());
        usernameview.setText(currentuser.getUsername());
        userscore.setText(currentuser.getScore().toString());
        //pass quiz
        passquiz = (Button) findViewById(R.id.passquiz);
        passquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),activity_languagequiz.class);
                in.putExtra("currentuser",currentuser);
                startActivity(in);
            }
        });
        //rank
        rank = (Button) findViewById(R.id.ranking);
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Rank.class);
                in.putExtra("currentuser",currentuser);
                startActivity(in);
            }
        });
        //quit
        quit = (Button) findViewById(R.id.disconnect);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Login.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                finish();
            }
        });


    }
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(),Login.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


}