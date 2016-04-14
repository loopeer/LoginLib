package com.loopeer.android.loginlib.utils;

import android.text.TextUtils;

/**
 * 策略模式，可以替换自己的判断方式，参考Retrofit setAdapter
 */
public class StringUtils {

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    public static final boolean isValidPassword(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return target.length() >= 6;
        }
    }

    public static final boolean isValidCaptcha(CharSequence target) {
        return isValidPassword(target);
    }


}
