package com.plant;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Kim on 2016-05-17.
 */
public class StatusBar extends FrameLayout {

    ImageView statusbar_home_btn,statusbar_realtime_btn, statusbar_conserve_btn, statusbar_conserve_confirm_btn, statusbar_more_btn;
    public StatusBar(Context context) {
        super(context);
    }
    public StatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public StatusBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            inflate(getContext(), R.layout.statusbar, this);
            statusbar_home_btn = (ImageView) findViewById(R.id.statusbar_home_btn);
            statusbar_realtime_btn = (ImageView) findViewById(R.id.statusbar_realtime_btn);
            statusbar_conserve_btn = (ImageView) findViewById(R.id.statusbar_conserve_btn);
            statusbar_conserve_confirm_btn = (ImageView) findViewById(R.id.statusbar_conserve_confirm_btn);
            statusbar_more_btn = (ImageView) findViewById(R.id.statusbar_more_btn);

            statusbar_home_btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ContextSwitch
                }
            });
    }
}
