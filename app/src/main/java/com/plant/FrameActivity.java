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

public class FrameActivity extends FragmentActivity implements View.OnClickListener,FragmentChangeListener{
    /* view ****************************************/
    ImageView statusbar_home_btn;
    ImageView statusbar_realtime_btn;
    ImageView statusbar_conserve_btn;
    ImageView statusbar_conserve_confirm_btn;
    ImageView statusbar_more_btn;
    /************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        init();
    }

    /**init method******************************************************************************************/
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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentReplace, new IndexFragment())
                .commit();
    }
    //클릭 되어졌던 이미지를 초기화
    public void initImage(){
        statusbar_home_btn.setImageResource(R.drawable.statusbar_home_btn);
        statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_btn);
        statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_btn);
        statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_btn);
        statusbar_more_btn.setImageResource(R.drawable.statusbar_more_btn);
    }
    /************************************************************************************************/

    /**Override from implements******************************************************************************************/
    @Override
    public void onClick(View v) {
        initImage();
        switch(v.getId()){
            case R.id.statusbar_home_btn:
                statusbar_home_btn.setImageResource(R.drawable.statusbar_home_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new IndexFragment())
                        .commit();
                break;
            case R.id.statusbar_realtime_btn:
                statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new RealTimeFragment())
                        .commit();
                break;
            case R.id.statusbar_conserve_btn:
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationFragment())
                        .commit();
                break;
            case R.id.statusbar_conserve_confirm_btn:
                statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationCheckFragment())
                        .commit();
                break;
            case R.id.statusbar_more_btn:
                break;
        }
    }
    @Override
    public void makeChange(int number) {
        initImage();
        switch(number){
            case 1:
                statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new RealTimeFragment())
                        .commit();
                break;
            case 2:
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationFragment())
                        .commit();
                break;
            case 3:
                statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationCheckFragment())
                        .commit();
                break;
            case 4:
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationMakeFragment())
                        .commit();
                break;
        }
    }
    /************************************************************************************************/
}