package com.juanma.personality;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private String [] questionText = new String[30];
    private int[] AnswerArray = new int[20];



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mLinearLayout = findViewById(R.id.LinearLayScroll);
        setRadioGroups();
        createLayout(mLinearLayout,AnswerArray);

    }

    public void setRadioGroups() {

       String[] sAnswers = getResources().getStringArray(R.array.AnswerArray);
       String[] sQuestions = getResources().getStringArray(R.array.QuestionArray);

        for (int i = 0; i < sQuestions.length; i++) {

            final RadioGroup mNewQuestion = new RadioGroup(this);
            mNewQuestion.setId(i + 6); //ID is set to (i+6) b/c Listener causes button selection problem if id's of RadioGroup and RadioGButtons are same.
            setQuestionText(i, sQuestions[i]);  //Getting question text from array

            for (int j = 0; j < sAnswers.length; j++) {

                RadioButton button = new RadioButton(this);
                button.setId(j+1);
                button.setText(sAnswers[j]);
                mNewQuestion.addView(button);

            }

            radioGroups.add(mNewQuestion);
        }
    }

    public RadioGroup getRadioGroups(int id){
        return radioGroups.get(id);
    }

    public void setQuestionText(int id, String sQuestions){

        this.questionText[id] = sQuestions;

    }

    public String getQuestionText(int id){
        return questionText[id];
    }

    public void createLayout(final LinearLayout mLinearLayout, final int AnswerArray[] ){

        //Button
        final Button submitButton = new Button(this);
        submitButton.setText("Submit");
        submitButton.setTextSize(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,40,0,40);
        submitButton.setLayoutParams(layoutParams);

        //Answers

        final Answers FinalAnswer = Answers.getInstance(); // Getting the singleton

        //RadioButton Creation
        for (int i =0; i<radioGroups.size(); i++) {

            //Setting Question Text
            TextView questionTextView = new TextView(this);
            questionTextView.setText(i + 1 + " " + getQuestionText(i));
            questionTextView.setTypeface(null, Typeface.BOLD);
            questionTextView.setTextSize(16);
            //RadioGroup Question
            final  RadioGroup question = getRadioGroups(i);
            mLinearLayout.addView(questionTextView);
            mLinearLayout.addView(question);


            //Listener For RadioGroup/Button
            question.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    int GroupID = group.getId();
                    GroupID = GroupID - 6;//Remember we added 6 b/c of problem with listener?
                    AnswerArray[GroupID] = checkedId;

                }
            });

        }

            mLinearLayout.addView(submitButton);


        //Listener for Button

        submitButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                int extroversion = 0;
                int agreeableness = 1;
                int conscientousness = 2;
                int neuroticism = 3;
                int openness = 4;

                FinalAnswer.ScoreAnswers(extroversion , AnswerArray);
                FinalAnswer.ScoreAnswers(agreeableness  , AnswerArray);
                FinalAnswer.ScoreAnswers(conscientousness , AnswerArray);
                FinalAnswer.ScoreAnswers(neuroticism , AnswerArray);
                FinalAnswer.ScoreAnswers(openness , AnswerArray);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);



            }

        });


    }




}

