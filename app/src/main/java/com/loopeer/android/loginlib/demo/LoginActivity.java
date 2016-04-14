package com.loopeer.android.loginlib.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.loopeer.android.loginlib.utils.LoginLoader;
import com.loopeer.android.loginlib.utils.ToastUtils;
import com.loopeer.android.loginlib.view.CountDownView;
import com.loopeer.android.loginlib.view.LoginSubmit;

public class LoginActivity extends AppCompatActivity {

    private EditText mEdEmail;
    private EditText mEdCode;
    private EditText mEdPassword;
    private CountDownView mCountDownView;
    private LoginLoader.SubmitType mSubmitType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parseIntent();
        initViews();
    }

    private void parseIntent() {
        mSubmitType = (LoginLoader.SubmitType) getIntent().getSerializableExtra(MainActivity.SUBMIT_TYPE);
    }

    private void initViews() {
        mEdEmail = (EditText) findViewById(R.id.email);
        mEdCode = (EditText) findViewById(R.id.code);
        mEdPassword = (EditText) findViewById(R.id.password);
        mCountDownView = (CountDownView) findViewById(R.id.btn_captcha);

        sendCaptcha();
        sendSubmit();
    }

    private void sendCaptcha() {
        mCountDownView.setUserEdit(mEdEmail);
        mCountDownView.setCaptchaListener(new LoginLoader.CaptchaListener() {
            @Override
            public void onPre() {

            }

            @Override
            public void onComplete(String phoneOrEmail) {
                ToastUtils.show(LoginActivity.this, "sendCaptcha: " + phoneOrEmail);
            }
        });
    }

    private void sendSubmit() {
        LoginSubmit fastLoginSubmit = (LoginSubmit) findViewById(R.id.login_fast_submit);
//        fastLoginSubmit.setSubmitType(mSubmitType);
        fastLoginSubmit.setText(mSubmitType.toString());
        setDataByType(mSubmitType, fastLoginSubmit);
        fastLoginSubmit.setSubmitListener(new LoginLoader.SubmitListener() {
            @Override
            public void onComplete(String... strings) {
                for (String string : strings) {
                    Log.d("LoginActivity", "submit onComplete: " + string);
                }

                ToastUtils.show(LoginActivity.this, "fast login"
                        + " : " + strings[0]
                        + " : " + strings[1]);

            }
        });
    }

    private void sendSubmit2() {
        LoginSubmit loginSubmit = (LoginSubmit) findViewById(R.id.login_fast_submit);
        loginSubmit.setSubmitType(LoginLoader.SubmitType.LOGIN);
        loginSubmit.setData(mEdEmail, mEdPassword);
        loginSubmit.setSubmitListener(new LoginLoader.SubmitListener() {
            @Override
            public void onComplete(String... strings) {
                for (String string : strings) {
                    Log.d("LoginActivity", "submit onComplete: " + string);
                }
            }
        });
    }

    private void setDataByType(LoginLoader.SubmitType submitType, LoginSubmit loginSubmit) {
        if (loginSubmit == null) return;
        if (submitType == null) return;
        loginSubmit.setSubmitType(submitType);
        switch (submitType) {
            case LOGIN:
                loginSubmit.setData(mEdEmail, mEdPassword);
                return;
            case REGISTER:
                loginSubmit.setData(mEdEmail, mEdCode, mEdPassword);
                return;
            case LOGIN_FAST:
            case PASSWORD_FORGET:
                loginSubmit.setData(mEdEmail, mEdCode);
                return;
            case NORMAL:
            default:
                break;
        }
    }

}
