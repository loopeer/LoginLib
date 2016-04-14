package com.loopeer.android.loginlib.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopeer.android.loginlib.utils.LoginLoader;
import com.loopeer.android.loginlib.utils.VerifyUser;
import com.loopeer.android.loginlib.utils.VerifyUserLogImpl;

public abstract class SubmitButton extends Button implements View.OnClickListener {
    public SubmitButton(Context context) {
        this(context, null);
    }

    public SubmitButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubmitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setOnClickListener(this);
    }

    private void init() {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setEnabled(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected static LoginLoader.SubmitType mSubmitType;

    public void setSubmitType(LoginLoader.SubmitType submitType) {
        mSubmitType = submitType;
    }

    LoginLoader.SubmitListener mSubmitListener;

    public void setSubmitListener(LoginLoader.SubmitListener submitListener) {
        mSubmitListener = submitListener;
    }

    private void doOnSubmit(View view) {
        if (mSubmitListener != null) {
            mSubmitListener.onComplete(inputs);
        }
    }

    public boolean checkInput(boolean isShowToast) {
        if (mSubmitType == null) {
            throw new IllegalArgumentException("SubmitType must not be null.");
        }
        VerifyUser verifyUserLog = new VerifyUserLogImpl(getContext(), isShowToast);
        return verifyUserLog.checkUser(mSubmitType, inputs);
    }

    private void textChangedAfter(Editable editable, int length, int index) {
        for (int i = 0; i < length; i++) {
            if (i == index) inputs[i] = editable.toString();
        }
    }

    public void setData(EditText... editText) {
        inputs = new String[editText.length];
        setForm(editText);
    }

    String[] inputs;
    private void setForm(EditText... editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].addTextChangedListener(new SubmitTextWatcher(editTexts.length, i));
        }
    }

    class SubmitTextWatcher implements TextWatcher {
        int index;
        int length;

        public SubmitTextWatcher(int length, int index) {
            this.length = length;
            this.index = index;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            textChangedAfter(s, length, index);
            if (checkInput(false)) {
                setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!checkInput(true)) return;
        doOnSubmit(v);
    }
}
