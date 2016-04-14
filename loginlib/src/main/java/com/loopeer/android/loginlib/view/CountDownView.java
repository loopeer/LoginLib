package com.loopeer.android.loginlib.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopeer.android.loginlib.utils.LoginLoader;
import com.loopeer.android.loginlib.utils.VerifyUserLogImpl;

public class CountDownView extends Button implements View.OnClickListener {

    private MyCountTimer mMyCountTimer;
    private static int DEFAULT_COUNT_DOWN_TIME = 60;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    private String mUser;

    public void setUserEdit(EditText edUser) {
        edUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mUser = s.toString();
            }
        });
    }

    public static void setCountDownTime(int defaultCountDownTime) {
        DEFAULT_COUNT_DOWN_TIME = defaultCountDownTime;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    boolean flag = true;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mMyCountTimer != null) {
            mMyCountTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        if (!checkUser()) return;
        if (mMyCountTimer == null) {
            mMyCountTimer = new MyCountTimer(DEFAULT_COUNT_DOWN_TIME * 1000, 1000);
        }

        if (flag) {
            mMyCountTimer.start();
            onPre();
        }
    }

    public boolean checkUser() {
        boolean isVerify = new VerifyUserLogImpl(getContext(), true).verifyPhoneOrEmail(mUser);
        return isVerify;

        /*if (TextUtils.isEmpty(mUser)) {
            ToastUtils.show(getContext(), "用户名不能为空");
            return true;
        }

        if (!Patterns.PHONE.matcher(mUser).matches()
                && !Patterns.EMAIL_ADDRESS.matcher(mUser).matches()) {
            ToastUtils.show(getContext(), "格式错误");
            return true;
        }
        return false;*/
    }

    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long s) {
            flag = false;
            setText(s / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            flag = true;
            setText("重新发送");
            CountDownView.this.onComplete(mUser);
        }
    }

    private LoginLoader.CaptchaListener mCaptchaListener;

    public void setCaptchaListener(LoginLoader.CaptchaListener captchaListener) {
        mCaptchaListener = captchaListener;
    }

    public void onPre() {
        if (mCaptchaListener != null) {
            mCaptchaListener.onPre();
            setEnabled(false);
        }
    }

    public void onComplete(String user) {
        if (mCaptchaListener != null) {
            mCaptchaListener.onComplete(user);
            setEnabled(true);
        }
    }
}
