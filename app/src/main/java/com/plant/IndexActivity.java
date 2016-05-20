package com.plant;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class IndexActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    ImageView index_realtime_btn;
    ImageView index_conserve_btn;
    ImageView index_conserve_confirm_btn;
    Animation size_down_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();
    }

    public void init(){
        index_realtime_btn = (ImageView) findViewById(R.id.index_realtime_btn);
        index_conserve_btn = (ImageView) findViewById(R.id.index_conserve_btn);
        index_conserve_confirm_btn = (ImageView) findViewById(R.id.index_conserve_confirm_btn);

        index_realtime_btn.setOnClickListener(this);
        index_conserve_btn.setOnClickListener(this);
        index_conserve_confirm_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.index_realtime_btn:
                break;
            case R.id.index_conserve_btn:
                break;
            case R.id.index_conserve_confirm_btn:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.startAnimation(size_down_anim);

        return false;
    }
}
