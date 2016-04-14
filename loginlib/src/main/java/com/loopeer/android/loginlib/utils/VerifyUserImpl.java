package com.loopeer.android.loginlib.utils;

public class VerifyUserImpl extends VerifyUser {
    @Override
    public boolean verifyPhoneOrEmail(String phoneOrEmail) {
       return StringUtils.isValidEmail(phoneOrEmail)
               || StringUtils.isValidPhoneNumber(phoneOrEmail);
    }

    @Override
    public boolean verifyPassword(String password) {
        return StringUtils.isValidPassword(password);
    }

    @Override
    public boolean verifyCaptcha(String captcha) {
        return StringUtils.isValidCaptcha(captcha);
    }

    @Override
    public boolean checkUser(LoginLoader.SubmitType inputType, String... strings) {
        if (strings == null) return false;
        switch (inputType) {
            case LOGIN:
                return verifyPhoneOrEmail(strings[0])
                        && verifyPassword(strings[1]);
            case REGISTER:
                return verifyPhoneOrEmail(strings[0])
                        && verifyPassword(strings[1])
                        && verifyCaptcha(strings[2]);
            case LOGIN_FAST:
            case PASSWORD_FORGET:
                return verifyPhoneOrEmail(strings[0])
                        && verifyCaptcha(strings[1]);
            case NORMAL:
            default:
                break;
        }
        return false;
    }

    @Override
    public void setIsShowToast(boolean isShowToast) {

    }

}
