package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitButton;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestion = 0;
    String selectedAns = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionTextView = findViewById(R.id.totalQuestions);
        questionTextView = findViewById(R.id.questionLayout);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submitButton = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionTextView.setText("TOTAL QUESTION : " + totalQuestion);

        loadNewQues();

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() == R.id.submit){

            if(selectedAns.equals(QuestionAnswer.correctAns[currentQuestion])){
                score++;
            }

                currentQuestion++;
                loadNewQues();

        }else{
            selectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }

    }

    void loadNewQues(){

        if(currentQuestion == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestion]);
        ansA.setText(QuestionAnswer.choice[currentQuestion][0]);
        ansB.setText(QuestionAnswer.choice[currentQuestion][1]);
        ansC.setText(QuestionAnswer.choice[currentQuestion][2]);
        ansD.setText(QuestionAnswer.choice[currentQuestion][3]);


    }

    void finishQuiz(){
        String pass = "";
        if(score>2){
            pass = "PASS";
        }else{
            pass = "FAIL";
        }

        new AlertDialog.Builder(this)
                .setTitle(pass)
                .setMessage("SCORE IS " + score + " OUT OF " + totalQuestion)
                .setPositiveButton("RESTART",(dialogInterface, i) -> restartQuiz())
                .setCancelable(true)
                .show();
    }

    void restartQuiz(){
        score = 0;
        currentQuestion = 0;
        loadNewQues();
    }



}