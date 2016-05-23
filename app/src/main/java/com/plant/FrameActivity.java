package com.plant;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FrameActivity extends FragmentActivity implements View.OnClickListener{

    ImageView statusbar_home_btn;
    ImageView statusbar_realtime_btn;
    ImageView statusbar_conserve_btn;
    ImageView statusbar_conserve_confirm_btn;
    ImageView statusbar_more_btn;

    int fragmentNum;
    IndexFragment indexFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        init();
    }

    public void init(){
        statusbar_home_btn = (ImageView) findViewById(R.id.statusbar_home_btn);
        statusbar_realtime_btn = (ImageView) findViewById(R.id.statusbar_realtime_btn);
        statusbar_conserve_btn = (ImageView) findViewById(R.id.statusbar_conserve_btn);
        statusbar_conserve_confirm_btn = (ImageView) findViewById(R.id.statusbar_conserve_confirm_btn);
        statusbar_more_btn = (ImageView) findViewById(R.id.statusbar_more_btn);

        statusbar_home_btn.setOnClickListener(this);
        statusbar_realtime_btn.setOnClickListener(this);
        statusbar_conserve_btn.setOnClickListener(this);
        statusbar_conserve_confirm_btn.setOnClickListener(this);
        statusbar_more_btn.setOnClickListener(this);

        fragmentNum = 1;
        indexFragment = new IndexFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentReplace, indexFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.statusbar_home_btn:
                if(fragmentNum == 1) {

                    break;
                }
                initImage();
                statusbar_home_btn.setImageResource(R.drawable.statusbar_home_clicked_btn);
                fragmentNum = 1;
                if(indexFragment == null)
                    indexFragment = new IndexFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, indexFragment)
                        .commit();

                break;
            case R.id.statusbar_realtime_btn:
                if(fragmentNum == 2) {

                    break;
                }
                initImage();
                statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_clicked_btn);
                fragmentNum = 2;
                Toast.makeText(this,"realTime", Toast.LENGTH_SHORT).show();
                break;
            case R.id.statusbar_conserve_btn:
                if(fragmentNum == 3) {

                    break;
                }
                initImage();
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                fragmentNum = 3;
                Toast.makeText(this,"conserve", Toast.LENGTH_SHORT).show();
                break;
            case R.id.statusbar_conserve_confirm_btn:
                if(fragmentNum == 4) {

                    break;
                }
                initImage();
                statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_clicked_btn);
                fragmentNum = 4;
                Toast.makeText(this,"conserve_confirm", Toast.LENGTH_SHORT).show();
                break;
            case R.id.statusbar_more_btn:
                if(fragmentNum == 5) {

                    break;
                }
                initImage();
                statusbar_more_btn.setImageResource(R.drawable.statusbar_more_clicked_btn);
                fragmentNum = 5;
                Toast.makeText(this,"more", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //클릭 되어졌던 이미지를 초기화
    public void initImage(){
        switch(fragmentNum){
            case 1:statusbar_home_btn.setImageResource(R.drawable.statusbar_home_btn);
                break;
            case 2:statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_btn);
                break;
            case 3:statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_btn);
                break;
            case 4:statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_btn);
                break;
            case 5:statusbar_more_btn.setImageResource(R.drawable.statusbar_more_btn);
                break;
        }
    }
}