package com.example.a11455.myapplication3;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId,boolean answerTrue){
        mAnswerTrue=answerTrue;
        mTextResId=textResId;
    }

    //获得题目id
    public int getmTextResId() {
        return mTextResId;
    }

    //设置题目id
    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    //获得答案是否正确
    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    //设置答案是否正确
    public void setmAnswerTrue(boolean answerTrue) {
        this.mAnswerTrue = answerTrue;
    }
}
