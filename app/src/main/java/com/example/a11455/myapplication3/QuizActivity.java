package com.example.a11455.myapplication3;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
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
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextView;
    private static final String TAG=" QuizActivity";
    private static final String KEY_INDEX="index";
    private static final int REQUSET_CODE_CHEAT=0;
    private int score;
    private boolean mIsCheater;


    //定义问题集合
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
    //回传参数收集
    private int mCurrentIndex =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate(Bundle) called");
        //获得回传参数
        if (savedInstanceState!=null){
            mCurrentIndex =savedInstanceState.getInt(KEY_INDEX,0);
        }

        //textview点击跳转
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


        //选择正确的按钮以及点击事件
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

        //选择错误的按钮以及点击事件
        mFalsebtn= (Button) findViewById(R.id.false_btn);
        mFalsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(false);



            }
        });
        //选择下一个的按钮以及点击事件
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
             //   int question =mQuestionBank[mCurrentIndex].getmTextResId();
            //    mQuestionTextView.setText(question);
                mIsCheater=false;
                upDateQuestion();
            }
        });

        //选择上一个的按钮以及点击事件
        mBackButton=(ImageButton)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex!=0){
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                    mIsCheater=false;
                    getBackQuestion();
                }else {
                    mCurrentIndex=mQuestionBank.length;
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                    mIsCheater=false;
                    getBackQuestion();
                }


            }
        });

        //选择查看答案的按钮以及点击事件
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start cheatActivity
               // Intent intent = new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue =mQuestionBank[mCurrentIndex].ismAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
               // startActivity(intent);
                startActivityForResult(intent,REQUSET_CODE_CHEAT);
            }
        });



        upDateQuestion();



    }
    //更新问题方法
    private void upDateQuestion(){
        Log.d(TAG,"Updating question text",new Exception());
        int question =mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    //返回上一个问题方法
    private void getBackQuestion(){
        int question =mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    //选择正确的按钮以及点击事件
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue =mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageResId=0;
        if (mIsCheater){
            messageResId=R.string.judgment_toast;
        }else {



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
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
        Log.d(TAG,Integer.toString(mQuestionBank.length)+" "+Integer.toString(score));

        if (mCurrentIndex==mQuestionBank.length-1){
            Toast toast =  Toast.makeText(QuizActivity.this,R.string.showFen+score+R.string.number_fen,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
            score=0;
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

    //将当前的题号传给子活动
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    //接受答案确认方法，有错误
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode!= Activity.RESULT_OK){
            return;
        }
        if (requestCode==REQUSET_CODE_CHEAT){
            if (data==null){
                return;
            }
            mIsCheater =CheatActivity.wasAnswerShown(data);
        }
    }
}
