package com.example.a11455.myapplication3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTruebtn;
    private Button mFalsebtn;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextView;


    private Question[] mQuestionBank= new Question[]{
        new Question(R.string.question_aili,true),
                new Question(R.string.question_xiaolian,true),
                new Question(R.string.question_xiana,true),
                new Question(R.string.question_chuyin,true),
                new Question(R.string.question_jiyue,true),
                new Question(R.string.question_nwl,true)
    };
    private int mCurrentIndex =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                upDateQuestion();
            }
        });
      //  int question =mQuestionBank[mCurrentIndex].getmTextResId();
      //  mQuestionTextView.setText(question);


        mTruebtn = (Button) findViewById(R.id.true_btn);
        mTruebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        Toast toast =Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.TOP,0,0);
//                        toast.show();
                checkAnswer(true);

                
            }
        });
        mFalsebtn= (Button) findViewById(R.id.false_btn);
        mFalsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(false);

            }
        });

        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
             //   int question =mQuestionBank[mCurrentIndex].getmTextResId();
            //    mQuestionTextView.setText(question);
                upDateQuestion();
            }
        });

        mBackButton=(ImageButton)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex!=0){
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                    getBackQuestion();
                }else {
                    mCurrentIndex=mQuestionBank.length;
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                    getBackQuestion();
                }


            }
        });


        upDateQuestion();



    }
    private void upDateQuestion(){
        int question =mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void getBackQuestion(){
        int question =mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue =mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageResId=0;
        if (userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;

        }else {
            messageResId=R.string.incorrect_toast;

        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }

}
