## 关于
这是一个封装了登录注册，快捷登录，找回密码等一些与账户相关的发送验证码和提交按钮，
只需传入提交所需要的值，校验都会在封装中处理，并且会将传入的值返回到回调的方法。

## 使用

**验证码按钮**

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


**提交按钮**
setDataBytype
settype，爆出异常

```java

```

## 问题和Bug
同一个页面不能设置多个。
设置内容，必须哪找指定的顺序。