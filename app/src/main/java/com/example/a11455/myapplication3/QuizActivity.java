package com.example.a11455.myapplication3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG=" QuizActivity";
    private static final String KEY_INDEX="index";
    private int score;


    private Question[] mQuestionBank= new Question[]{
                new Question(R.string.question_aiLi,true),
                new Question(R.string.question_xiaoLian,true),
                new Question(R.string.question_xiaNa,true),
                new Question(R.string.question_chuYin,true),
                new Question(R.string.question_jiYue,true),
                new Question(R.string.question_Nwl,true),
                new Question(R.string.question_deLiSha,true),
                new Question(R.string.question_xueEr,true),
                new Question(R.string.question_Alkxya,true),
                new Question(R.string.question_xiaoAi,true)
    };
    private int mCurrentIndex =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate(Bundle) called");
        if (savedInstanceState!=null){
            mCurrentIndex =savedInstanceState.getInt(KEY_INDEX,0);
        }

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
            score=score+10;
            mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
            //   int question =mQuestionBank[mCurrentIndex].getmTextResId();
            //    mQuestionTextView.setText(question);
            upDateQuestion();

        }else {
            messageResId=R.string.incorrect_toast;
            mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
            //   int question =mQuestionBank[mCurrentIndex].getmTextResId();
            //    mQuestionTextView.setText(question);
            upDateQuestion();


        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
        Log.d(TAG,Integer.toString(mQuestionBank.length)+" "+Integer.toString(score));

        if (mCurrentIndex==mQuestionBank.length-1){
            Toast toast =  Toast.makeText(QuizActivity.this,"您的得分："+score+"分",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG," onPause() called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG," onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG," onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }
}
