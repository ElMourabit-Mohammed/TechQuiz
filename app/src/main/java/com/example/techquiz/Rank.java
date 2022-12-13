package com.example.techquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class Rank extends AppCompatActivity {
    DBHelper DB;
    ArrayList<User> usersList;
    ListView listView;
    User currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        Intent intent = getIntent();
        currentuser = (User) intent.getSerializableExtra("currentuser");
        DB = new DBHelper(this);
        usersList = DB.selectAllUsersList();

        listView = (ListView) findViewById(R.id.listViewUsersRank);
        RankUserAdapter ranklistUserAdapter = new RankUserAdapter(getApplicationContext(), usersList);
        listView.setAdapter(ranklistUserAdapter);

    }
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(),Profile.class);
        in.putExtra("currentuser",currentuser);
        startActivity(in);
        finish();
    }
}