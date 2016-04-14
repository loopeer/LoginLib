package com.loopeer.android.loginlib.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.loopeer.android.loginlib.utils.LoginLoader;


public class MainActivity extends AppCompatActivity {

    private LinearLayout mTopLayout;
    private String[] titles =
            new String[] {
                    "login", "register",
                    "fast login", "password forget",
                    "normal"};

    public static final String SUBMIT_TYPE = "submit_type";
    private LoginLoader.SubmitType[] mSubmitTypes =
            new LoginLoader.SubmitType[] {
                    LoginLoader.SubmitType.LOGIN, LoginLoader.SubmitType.REGISTER,
                    LoginLoader.SubmitType.LOGIN_FAST, LoginLoader.SubmitType.PASSWORD_FORGET,
                    LoginLoader.SubmitType.NORMAL};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Button button;
        for (int i = 0; i < 5; i++) {
            final int j = i;
            button = new Button(MainActivity.this);
            button.setId(i);
            button.setText(titles[i]);
            button.setOnClickListener(new ClickListener(j));
            mTopLayout.addView(button);
        }
    }

    class ClickListener implements View.OnClickListener {
        private int i;

        public ClickListener(int i) {
            this.i = i;
        }

        @Override
        public void onClick(View v) {
            toLoginActivity();
        }

        private void toLoginActivity() {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra(SUBMIT_TYPE, mSubmitTypes[i]);
            startActivity(intent);
        }
    }

    private void initView() {
        mTopLayout = (LinearLayout) findViewById(R.id.top_layout);
    }
}
