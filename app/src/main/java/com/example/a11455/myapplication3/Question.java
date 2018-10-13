package com.example.a11455.myapplication3;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId,boolean answerTrue){
        mAnswerTrue=answerTrue;
        mTextResId=textResId;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean answerTrue) {
        this.mAnswerTrue = answerTrue;
    }
}
