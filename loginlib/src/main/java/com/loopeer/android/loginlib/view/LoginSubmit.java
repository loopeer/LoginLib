package com.loopeer.android.loginlib.view;

import android.content.Context;
import android.util.AttributeSet;


public class LoginSubmit extends SubmitButton {

    public LoginSubmit(Context context) {
        this(context, null);
    }

    public LoginSubmit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginSubmit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean checkInput(boolean isShowToast) {
        return super.checkInput(isShowToast);
    }

}
