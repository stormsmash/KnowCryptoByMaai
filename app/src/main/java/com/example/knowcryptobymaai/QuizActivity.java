package com.example.knowcryptobymaai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class QuizActivity extends MainActivity {
    public BottomNavigationView bottomNavigationView ;
    public Button answer1,answer2,answer3,answer4;
    public TextView score,question;
    public Questions mQuestions = new Questions();
    public String mAnswer; //เก็บคําตอบที่ถูก
    public Random r;
    public int mQuestionsLength = mQuestions.mQuestion.length;
    public int mScore = 0; //เก็บคะแนน
    public int count = 1; //จํานวนข้อที่ทํา
    public AlertDialog.Builder builder; //เรียกใช้ AlertDailog
    public ImageView imageshowresult;
    public MediaPlayer soundcorrect,soundwrong;
    CountDownTimer cdt;
    TextView tvTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        answer1 = (Button)findViewById(R.id.answer1);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);
        score = (TextView)findViewById(R.id.score);
        question = (TextView)findViewById(R.id.question);
        imageshowresult = findViewById(R.id.imageView2);
        tvTimer = (TextView)findViewById(R.id.tvTimer);
        soundcorrect = MediaPlayer.create(this,R.raw.correct);
        soundwrong = MediaPlayer.create(this,R.raw.fail);
        imageshowresult.setImageResource(0);
        r = new Random();
        updateQuestion(r.nextInt(mQuestionsLength));

        cdt = new CountDownTimer(40000, 50) {
            public void onTick(long millisUntilFinished) {
                String strTime = String.format("%.1f"
                        , (double)millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            public void onFinish() {
                tvTimer.setText("0");
                gameOver();
            }
        }.start();


        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_learn:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.action_quiz:
                        startActivity(new Intent(getApplicationContext(),QuizActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.action_game:
                        startActivity(new Intent(getApplicationContext(),GameActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
    public void updateQuestion(int num){
        question.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));
        mAnswer = mQuestions.getCorrectAnswer(num);
    }//ปิด method update Question
    public void ans1 (View view){
        if(count <5) {
            if (answer1.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"" );
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            } else {
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            }
        }//เช็ควำทำครบ 3 ข้อยัง
        else{
            if (answer1.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"");
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                updateQuestion(r.nextInt(mQuestionsLength));
                gameOver();
            }else{
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                updateQuestion(r.nextInt(mQuestionsLength));
                gameOver();
            }
        }
        count++;
    } //ปิ ด Method เลือก choice แรก
    public void ans2 (View view){
        if(count <5) {
            if (answer2.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"" );
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            } else {
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            }
        }//เช็คว่าทำครบ 3 ข้อยัง
        else{
            if (answer2.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"");
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                gameOver();
            }else{
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                gameOver();
            }
        }
        count++;
    } //ปิ ด Method เลือก choice สอง

    public void ans3 (View view){
        if(count <5) {
            if (answer3.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"" );
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            } else {
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            }
        }//เช็คว่ำท ำครบ 3 ข้อยัง
        else{
            if (answer3.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"");
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                gameOver();
            }else{
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                gameOver();
            }
        }
        count++;
    }//ปิ ด Method เลือก choice สำม
    public void ans4 (View view){
        if(count <5) {
            if (answer4.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"" );
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            } else {
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                updateQuestion(r.nextInt(mQuestionsLength));
            }
        }//เช็คว่ำท ำครบ 3 ข้อยัง
        else{
            if (answer4.getText().toString() == mAnswer) {
                mScore++;
                score.setText(mScore+"");
                imageshowresult.setImageResource(R.drawable.tick1);
                soundcorrect.start();
                gameOver();
            }else{
                imageshowresult.setImageResource(R.drawable.wrong1);
                soundwrong.start();
                gameOver();
            }
        }
        count++;
    }//ปิ ด Method เลือก choice สี่
    public void gameOver (){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("you score");
        builder.setMessage("Your done : " + count + " questions "+ "\n Scores : " +
                mScore);
        builder.setPositiveButton("new game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent refresh = new
                        Intent(getApplicationContext(),QuizActivity.class);
                startActivity(refresh);
            }
        });
        builder.setNegativeButton("exit", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }//ปิด method gameOver




}//ปิด class