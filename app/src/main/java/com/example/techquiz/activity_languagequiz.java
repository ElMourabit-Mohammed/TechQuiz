package com.example.techquiz;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_languagequiz extends AppCompatActivity {
    String languageList[] = {"JavaScript","Java","C","C++","CSS",".Net","HTML","PHP","Python","SQL"};
    int languageImage[] = {R.drawable.javascript,R.drawable.java,R.drawable.c,R.drawable.cpp,R.drawable.css,R.drawable.dotnet,R.drawable.html,R.drawable.php,R.drawable.python,R.drawable.sql};
    ListView listView;
    User currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languagequiz);

        Intent intent = getIntent();
        currentuser = (User) intent.getSerializableExtra("currentuser");
        listView = (ListView) findViewById(R.id.listViewLanguages);
        LanguageQuizAdapter languageQuizAdapter = new LanguageQuizAdapter(getApplicationContext(),languageList,languageImage);
        listView.setAdapter(languageQuizAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent startQuestions = new Intent(getApplicationContext(), QuestionsActivity.class);
                startQuestions.putExtra("LanguageName",languageList[position]);
                startQuestions.putExtra("currentuser", currentuser);
                startActivity(startQuestions);
            }
        });
    }
}