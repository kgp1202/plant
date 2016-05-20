package com.plant;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
    Animation anim1;
    Animation anim2;
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
    public void onClick(final View v) {
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                switch(v.getId()){
                    case R.id.btnFB:
                    startActivity(new Intent(getApplicationContext(), IndexActivity.class));
                        break;
                    case R.id.btnKKO:
                        break;
                    case R.id.btnTW:
                        break;
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        v.startAnimation(anim1);
    }

    public void init(){
        btnTw=(ImageView)findViewById(R.id.btnTW);
        btnFb=(ImageView)findViewById(R.id.btnFB);
        btnKko=(com.plant.Kakao.KakaoBtnLayout)findViewById(R.id.btnKKO);

        btnTw.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnKko.setOnClickListener(this);

        anim1= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.scale_up);
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
        }                                              // 로그인화면을 다시 불러옴
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
