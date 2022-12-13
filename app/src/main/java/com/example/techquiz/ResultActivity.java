package com.example.techquiz;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2, tv3;
    Button btnBack, trainButton;
    ImageView languageImage;
    String languageName;
    int quizscore;
    User currentuser;
    DBHelper DB;

    String languageTrainingList[] = {
            "JavaScript","https://www.w3schools.com/js/default.asp",
            "Java","https://www.w3schools.com/java/default.asp",
            "C","https://www.w3schools.com/c/index.php",
            "C++","https://www.w3schools.com/cpp/default.asp",
            "CSS","https://www.w3schools.com/css/default.asp",
            ".Net","https://www.w3schools.com/cs/index.php",
            "HTML","https://www.w3schools.com/html/default.asp",
            "PHP","https://www.w3schools.com/php/default.asp",
            "Python","https://www.w3schools.com/python/default.asp",
            "SQL","https://www.w3schools.com/sql/default.asp"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        currentuser = (User) intent.getSerializableExtra("currentuser");
        tv = (TextView)findViewById(R.id.tvres);
        tv2 = (TextView)findViewById(R.id.tvres2);
        tv3 = (TextView)findViewById(R.id.tvres3);
        trainButton = (Button) findViewById(R.id.btnTrain);
        btnBack = (Button) findViewById(R.id.btnRestart);
        languageImage = (ImageView) findViewById(R.id.resultImg);
        languageName = QuestionsActivity.languagename;
        quizscore = QuestionsActivity.marks;

        DB = new DBHelper(this);
        DB.insertScore(currentuser.getUsername(),languageName,quizscore);
        currentuser = DB.selectUser(currentuser.getUsername());

        StringBuffer sb = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Correct answers: " + QuestionsActivity.correct);
        sb3.append("Wrong Answers: " + QuestionsActivity.wrong);
        if(QuestionsActivity.correct == 10) {
            sb.append("Congratulations you have just get the " + languageName + " badge");
            languageImage.setImageResource(R.drawable.badge);
            trainButton.setVisibility(View.GONE);
        }
        else{
            sb.append("You should train more");
            languageImage.setImageResource(R.drawable.prepare);
            trainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = -1;
                    for (int i=0;i<languageTrainingList.length;i++) {
                        if (languageTrainingList[i].equals(languageName)) {
                            index = i;
                            break;
                        }
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(languageTrainingList[index+1]));
                    startActivity(intent);
                }
            });
        }
        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);

        QuestionsActivity.correct=0;
        QuestionsActivity.wrong=0;

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Profile.class);
                in.putExtra("currentuser",currentuser);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(),Profile.class);
        in.putExtra("currentuser",currentuser);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


}
