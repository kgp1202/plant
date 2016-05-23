package com.plant;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class Login_Activity extends Activity implements View.OnClickListener{
    /************* activity *************/
    ImageView btnTw;
    ImageView btnFb;
    userData userData;
    Animation anim1;
    Animation anim2;
    /************* activity *************/

    com.plant.Kakao.KakaoBtnLayout btnKko;
    private SessionCallback callback;

    public void init(){
        btnTw=(ImageView)findViewById(R.id.btnTW);
        btnFb=(ImageView)findViewById(R.id.btnFB);
        btnKko=(com.plant.Kakao.KakaoBtnLayout)findViewById(R.id.btnKKO);

        btnTw.setOnClickListener(this);
        btnFb.setOnClickListener(this);

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
//        final Intent intent = new Intent(this, com.plant.Kakao.KakaoSignUp.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
//        finish();
        requestMe();
    }
    public void kakaoInit(){
        /*
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Log.d("test","logout success");
            }
        });
        */
        callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }
    protected void requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {} // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                //여기서 user 데이터를 자기가 원하는 모양으로 입력
                String kakaoID = String.valueOf(userProfile.getId()); // userProfile에서 ID값을 가져옴
                String kakaoNickname = userProfile.getNickname();     // Nickname 값을 가져옴
                Log.d("test","onSuccess Login");
                userData=new userData();
                userData.loginFrom= com.plant.userData.KAKAO;
                userData.name=userProfile.getNickname();
                userData.profilePath=userProfile.getProfileImagePath();
                userData.ID=userProfile.getId();
                Log.d("test",":"+userData.getUserDataJson());
                redirectMainActivity(userData); // 로그인 성공시 MainActivity로
            }
        });
    }
    private void redirectMainActivity(userData input) {
        /**
         *
         * 로그인 성공시 여기를 바꿔 줘야됨
         *
         * */
        Intent intent=new Intent(this,FrameActivity.class);
        startActivity(intent);
        finish();
    }
    protected void redirectLoginActivity() {
//        Log.d("test","session close or anything");
//        final Intent intent = new Intent(this, com.plant.Login_Activity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
        finish();
    }
    /************* KAKAO Class & Function END *************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        init(); //actvity init
        kakaoInit();//kakao Init;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
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
                        Intent intent=new Intent(Login_Activity.this,FrameActivity.class);
                        startActivity(intent);
                        finish();
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
}
