package com.plant;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FrameActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        fragmentReplace(1);
    }
    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;

        //Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);
        newFragment = new RealTimeFragment();
        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragmentReplace, newFragment);
        // Commit the transaction
        transaction.commit();
    }

}
