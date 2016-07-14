package com.plant;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FrameActivity extends FragmentActivity implements View.OnClickListener, FragmentChangeListener, ActivityMakeDarker, View.OnTouchListener {
    QueueTask myQueueTask;

    /* view ****************************************/
    RelativeLayout mView;
    ImageView statusbar_home_btn;
    ImageView statusbar_realtime_btn;
    ImageView statusbar_conserve_btn;
    ImageView statusbar_conserve_confirm_btn;
    ImageView statusbar_more_btn;
    AnimationDrawable frameAnimation;
    RelativeLayout addedView;
    ImageView wheel;
    Fragment fragment;
    /************************************************/

    //UserData와 RoomData
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        init();
    }

    /**
     * init method
     ******************************************************************************************/
    public void init() {
        //intent를 통해서 넘어온 데이터를 UserData에 넣는다.
        userData = (UserData) getIntent().getSerializableExtra("UserData");

        mView = (RelativeLayout) findViewById(R.id.mView);

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
    public void initImage() {
        statusbar_home_btn.setImageResource(R.drawable.statusbar_home_btn);
        statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_btn);
        statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_btn);
        statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_btn);
        statusbar_more_btn.setImageResource(R.drawable.statusbar_more_btn);
    }
    /************************************************************************************************/

    /**
     * Override from implements
     ******************************************************************************************/
    @Override
    public void onClick(View v) {
        initImage();
        switch (v.getId()) {
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
                statusbar_more_btn.setImageResource(R.drawable.statusbar_more_clicked_btn);

                Fragment testFragment = new MoreFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("UserData", userData);
                testFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, testFragment)
                        .commit();
                break;
        }
    }

    @Override
    public void makeChange(int number) {
        initImage();
        switch (number) {
            case 1:
                statusbar_realtime_btn.setImageResource(R.drawable.statusbar_realtime_clicked_btn);
                fragment = new RealTimeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, fragment)
                        .commit();
                break;
            case 2:
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                fragment = new ReservationFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, fragment)
                        .commit();
                break;
            case 3:
                statusbar_conserve_confirm_btn.setImageResource(R.drawable.statusbar_conserve_confirm_clicked_btn);
                fragment = new ReservationCheckFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, fragment)
                        .commit();
                break;
            case 4:
                statusbar_conserve_btn.setImageResource(R.drawable.statusbar_conserve_clicked_btn);
                fragment = new ReservationMakeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentReplace, new ReservationMakeFragment())
                        .commit();
                break;
        }
    }

    @Override
    public void makeDarker(boolean input) {
        if (input) {//set view
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            addedView = (RelativeLayout) inflater.inflate(R.layout.queuing_layout, null);
            addedView.setOnTouchListener(this);
            addedView.setClickable(true);
            mView.addView(addedView);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addedView.setLayoutParams(layoutParams);
            wheel = (ImageView) addedView.findViewById(R.id.wheelImage);
            wheel.setBackgroundResource(R.drawable.wheel_list);
            frameAnimation = (AnimationDrawable) wheel.getBackground();
            frameAnimation.start();
            myQueueTask=new QueueTask(this);
            myQueueTask.execute();
        } else {
            frameAnimation.stop();
            mView.removeViewAt(1);
        }
    }

    float mThouchX;
    float baseX;
    boolean isMove=false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mThouchX = event.getX();
                baseX = v.getX();
                isMove=false;
                break;
            case MotionEvent.ACTION_UP:
                if (isMove) {
                    if (event.getRawX() - baseX > 200) {
                        myQueueTask.cancel(true);
                    } else
                        v.setX(0);
                }
                else
                    v.setX(0);
                break;
            case MotionEvent.ACTION_MOVE:
                isMove = true;
                if (event.getRawX() - mThouchX > 0) {
                    v.setX(v.getX() + event.getX() - mThouchX);
                }
                break;
        }
        return false;
    }
    /************************************************************************************************/
}