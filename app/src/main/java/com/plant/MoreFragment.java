package com.plant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;


public class MoreFragment extends Fragment implements View.OnClickListener {
    UserData userData;
    Context mContext;
    Button logout_btn;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //UI 설정
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);
        logout_btn = (Button) rootView.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(this);
        mContext = getContext();

        //데이터 받기
        Bundle extra = getArguments();
        userData = (UserData) extra.getSerializable("UserData");
        Log.d("UserData", userData.getUserDataJSONString());

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(userData.loginFrom) {
            case UserData.KAKAO:
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(mContext, Login_Activity.class);
                        startActivity(intent);
                    }
                });
                break;
            case UserData.NAVER:
                OAuthLogin.getInstance().logoutAndDeleteToken(getContext());
                Intent intent = new Intent(mContext, Login_Activity.class);
                startActivity(intent);
                break;
            default:
                Log.d("Error", "UserData.loginFrom is not defined");
                break;
        }

        //SharedPreference에 저장되어 있던 정보 삭제
        SharedPreferences pref = getContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}