package com.plant.Kakao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

/**
 * Created by angks on 2016-05-17.
 */
public class KakaoSignUp extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
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
                Logger.d("UserProfile : " + userProfile);
                redirectMainActivity(); // 로그인 성공시 MainActivity로
            }
        });
    }

    private void redirectMainActivity() {
        /**
         *
         * 로그인 성공시 여기를 바꿔 줘야됨
         *
         * */
        startActivity(new Intent(this, com.plant.Login_Activity.class));
        finish();
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, com.plant.Login_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}
