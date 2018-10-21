package com.example.a11455.myapplication3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE="com.example.a11455.myapplication3.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN="com.example.a11455.myapplication3.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private int seeAnswerNum=3;
    private String TAG = "CheatActivity";
    private String tip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);



        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView)findViewById(R.id.answer_text_view);

        mShowAnswerButton =(Button)findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tip=getApplicationContext().getResources().getString(R.string.seeAnswer)
                        +Integer.toString(seeAnswerNum-1)
                        +getApplicationContext().getResources().getString(R.string.number_ci);

                Toast toast =  Toast.makeText(CheatActivity.this,tip,Toast.LENGTH_LONG);
                toast.show();
                if(seeAnswerNum<=3&&seeAnswerNum>0){
                    if (mAnswerIsTrue){
                        mAnswerTextView.setText(R.string.true_button);
                    }else{
                        mAnswerTextView.setText(R.string.false_button);
                    }
                    setAnswerShownResult(true);
                    seeAnswerNum--;
                    Log.d(TAG,Integer.toString(seeAnswerNum));
                }else {
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    int cx =mShowAnswerButton.getWidth()/2;
                    int cy =mShowAnswerButton.getHeight()/2;
                    float radius =mShowAnswerButton.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton,cx,cy,radius,0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }else {
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }
                }









            }
        });
    }

    public static Intent newIntent(Context packageContext ,boolean answerIsTrue){
        Intent intent =new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);

    }
}
