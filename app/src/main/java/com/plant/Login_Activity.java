package com.plant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class Login_Activity extends Activity implements View.OnClickListener{
    /************* activity *************/
    ImageView btnTw;
    ImageView btnFb;
    com.plant.Kakao.KakaoBtnLayout btnKko;
    /************* activity *************/

    /************* activity *************/
    private SessionCallback callback;

    /************* activity *************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        init(); //actvity init
        kakaoInit();//kakao Init;
    }
    @Override
    public void onClick(View v) {
        Animation anim1= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        Animation anim2=AnimationUtils.loadAnimation(this,R.anim.scale_up);
        v.startAnimation(anim1);
        v.startAnimation(anim2);
        switch (v.getId()){
            case R.id.btnFB:
                break;
            case R.id.btnKKO:
                break;
            case R.id.btnTW:
                break;
        }
    }
    public void init(){
        btnTw=(ImageView)findViewById(R.id.btnTW);
        btnFb=(ImageView)findViewById(R.id.btnFB);
        btnKko=(com.plant.Kakao.KakaoBtnLayout)findViewById(R.id.btnKKO);

        btnTw.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnKko.setOnClickListener(this);
    }

    /************* KAKAO extend class  and function *************/
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login_); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }
    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, com.plant.Kakao.KakaoSignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    public void kakaoInit(){
        callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);
    }
    /************* KAKAO Class & Function END *************/
}
