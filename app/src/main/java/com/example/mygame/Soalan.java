package com.example.mygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Soalan extends AppCompatActivity {

    private TextView questionLabel;
    private TextView countLabel;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray  = new ArrayList<>();

    String quizData[][] = {
            {"Dimensi dalam konsep Melayu, Kecuali","Kemanusiaan","Ras Melayu","Bahasa Melayu","Kebudayaan Melayu"},
            {"Pada tahun berapa masyarakat Melayu bertapak di Asia Tenggara?","5000 Tahun","6000 Tahun","3000 Tahun","8000 Tahun"},
            {"Apakah pengaruh yang mempengaruhi pembentukan Melayu Purba","Kerajaan Sri vijaya","Kerajaan Majapahit",
                    "Kerajaan Chih Tu","Kerajaan Angkor"},
            {"Kerajaan berikut pernah wujud di Kepulauan Melayu Nusantara kecuali","Ryukyu","Tun Sun","KLangkasuka",
                    "Majapahit"},
            {"Orang Melayu yang berhijrah keluar dari rantau Kepulauan Melayu digelar sebagai...","Melayu Diaspora","Melayu Perantau","Melayu Pelayar",
                   "Melayu Diasambiguasi"}


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soalan);

        questionLabel = findViewById(R.id.soalan);
        countLabel = findViewById(R.id.countLabel);

        answer1 = findViewById(R.id.answer1btn);
        answer2 = findViewById(R.id.answer2btn);
        answer3 = findViewById(R.id.answer3btn);
        answer4 = findViewById(R.id.answer4btn);




        for(int i = 0;i<quizData.length; i++)
        {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);


            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz()
    {
        countLabel.setText("Q" + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);

        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));
        answer4.setText(quiz.get(3));

        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view)
    {
        Button answerBtn = findViewById(view.getId());

        String btnText  = answerBtn.getText().toString();
        String alertTitle;

        if(btnText.equals(rightAnswer)){
        alertTitle = "Tahniah Anda Betul";
        rightAnswerCount++;
        }else
        {
        alertTitle = "Maaf !";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Jawapannya  : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show Result.
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}