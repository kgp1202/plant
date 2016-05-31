package com.plant;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ReservationFragment extends Fragment {

    View mainView;
    FragmentChangeListener mCallback;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mCallback=(FragmentChangeListener)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_reservation, container, false);
        init();

        return mainView;
    }

    public void init(){
        Button button = (Button) mainView.findViewById(R.id.makeRoomBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.makeChange(4);
            }
        });
    }
}
