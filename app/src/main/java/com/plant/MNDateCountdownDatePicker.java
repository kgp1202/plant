package com.plant;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class MNDateCountdownDatePicker extends DatePicker {

    Context mcontext;



    public MNDateCountdownDatePicker(Context context) {
        super(context);
        mcontext = context;
        init();
    }

    public MNDateCountdownDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;

        init();
    }

    public MNDateCountdownDatePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mcontext = context;
        init();
    }

    private void init() {

        // 현재 폰트 색을 제너럴세팅의 색으로 변경
        LinearLayout linearLayout = (LinearLayout)getChildAt(0);
        if (linearLayout != null) {
            linearLayout = (LinearLayout) linearLayout.getChildAt(0);
            if (linearLayout != null) {
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    NumberPicker numberPicker = (NumberPicker)linearLayout.getChildAt(i);
                    if (numberPicker != null) {
                        Log.d("here", "aa");
                        numberPicker.getDividerDrawable();
                        numberPicker.setDividerDrawable(ContextCompat.getDrawable(mcontext, R.drawable.dialog_divider));
                        numberPicker.setDividerPadding(50);
                    }
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }

}