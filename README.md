## 关于
这是一个封装了登录注册，快捷登录，找回密码等一些与账户相关的发送验证码和提交按钮，
只需传入提交所需要的值，校验都会在封装中处理，并且会将传入的值返回到回调的方法。

## 使用

### 验证码按钮

设置数据
只需调用setUserEdit(EditText ed) 将输入框传入。

设置时间
调用setCountDownTime(int time) 方法，默认为60 秒，也可以自定义时间，单位为秒。
```xml
<com.loopeer.android.loginlib.view.CountDownView
    android:id="@+id/btn_captcha"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:text="@string/send"
    android:background="@drawable/button_selector"
    android:textColor="@color/selector_button_text"
    />
```

```java
EditText mEdEmail = (EditText) findViewById(R.id.email);
CountDownView mCountDownView = (CountDownView) findViewById(R.id.btn_captcha);
mCountDownView.setUserEdit(mEdEmail);
mCountDownView.setCountDownTime(60);
mCountDownView.setCaptchaListener(new LoginLoader.CaptchaListener() {
    @Override
    public void onPre() {

    }

    @Override
    public void onComplete(String phoneOrEmail) {
        ToastUtils.show(LoginActivity.this, "sendCaptcha: " + phoneOrEmail);
    }
});
```

### 提交按钮

**设置提交类型**
setSubmitType(SubmitType submitType);
如果不设置提交类型，则会报出**throw new IllegalArgumentException("SubmitType must not be null.")**;

提交类型有以下几种类型，登录，注册，快速登录，密码忘记等。

**设置内容**
setData(EditText.. eds);
可以传输随意的字段，然后会在SubmitListener 中的onComplete(String... strings) 返回相应的字符串，就可以直接提交API 了。


```java
public enum SubmitType {
    LOGIN, REGISTER, LOGIN_FAST, PASSWORD_FORGET, NORMAL
}
```

```xml
<com.loopeer.android.loginlib.view.LoginSubmit
    android:id="@+id/login_fast_submit"
    android:layout_width="match_parent"
    android:layout_height="?listPreferredItemHeightSmall"
    style="@style/Widget.AppCompat.Button.Small"
    android:text="@string/fast_login"
    />
```

```java
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
```

## 扩展
自定义校验
可以参考LoginSubmit，继承SubmitButton，重写checkInput(boolean isShowToast) 方法即可。

## 问题和Bug
注册按钮，必须按照用户名，验证码，密码先后顺序传入。

目前**提交按钮**只有固定的的几种提交类型，不能随意传入，如果有其他输入框（修改密码），则不能通用，正在考虑解决。