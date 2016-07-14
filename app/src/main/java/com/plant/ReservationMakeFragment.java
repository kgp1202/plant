package com.plant;

import android.support.v7.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kakao.usermgmt.response.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.Inflater;

public class ReservationMakeFragment extends Fragment {

    roomData room = new roomData();
    View mainView;
    Spinner UserNumSpin;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    EditText destination_editText;

    ImageView onetwoWay_btn[] = new ImageView[2];
    ImageView goal_btn[] = new ImageView[3];

    TextView reservation_make_month_textView;
    TextView reservation_make_day_textView;
    TextView reservation_make_hour_textView;
    TextView reservation_make_minute_textView;
    LinearLayout dayLaout;
    LinearLayout timeLayout;

    ImageView send_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_reservation_make, container, false);
        init();

        return mainView;
    }

    public void init(){
        room = new roomData();
        destination_editText = (EditText) mainView.findViewById(R.id.destination_editText);
        UserNumSpin = (Spinner) mainView.findViewById(R.id.UserNumSpin);
        reservation_make_month_textView = (TextView) mainView.findViewById(R.id.reservation_make_month_textView);
        reservation_make_day_textView = (TextView) mainView.findViewById(R.id.reservation_make_day_textView);
        reservation_make_hour_textView = (TextView) mainView.findViewById(R.id.reservation_make_hour_textView);
        reservation_make_minute_textView = (TextView) mainView.findViewById(R.id.reservation_make_minute_textView);
        dayLaout = (LinearLayout) mainView.findViewById(R.id.dayLayout);
        timeLayout = (LinearLayout) mainView.findViewById(R.id.timeLayout);
        (onetwoWay_btn[0] = (ImageView) mainView.findViewById(R.id.oneway_btn)).setOnClickListener(onetwoWayListener);
        (onetwoWay_btn[1] = (ImageView) mainView.findViewById(R.id.twoway_btn)).setOnClickListener(onetwoWayListener);
        (goal_btn[0] = (ImageView) mainView.findViewById(R.id.goal_btn0)).setOnClickListener(goalListener);
        (goal_btn[1] = (ImageView) mainView.findViewById(R.id.goal_btn1)).setOnClickListener(goalListener);
        (goal_btn[2] = (ImageView) mainView.findViewById(R.id.goal_btn2)).setOnClickListener(goalListener);
        send_btn = (ImageView) mainView.findViewById(R.id.send_btn);

        //roomData의 Default값 설정
        room.goal = 0;
        room.round = false;

        //현재 시간을 입력한다.
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day= calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        reservation_make_month_textView.setText("" + (month + 1));
        reservation_make_day_textView.setText("" + day);
        reservation_make_hour_textView.setText("" + hour);
        reservation_make_minute_textView.setText("" + minute);

        dayLaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = (DatePickerDialog) YearMonthDayPicker();
                datePickerDialog.show();
            }
        });
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, timePickerListener, hour, minute, false);
                timePickerDialog.show();
            }
        });

        //전송 버튼 클릭시
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destination_editText.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "목적지를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(((String)UserNumSpin.getSelectedItem()).equals("인원")){
                    Toast.makeText(getContext(), "인원을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd hh mm ss");
                    String monthStr = "" + month;
                    String dayStr = "" + day;
                    String hourStr = "" + hour;
                    String minuteStr = "" + minute;
                    if(monthStr.length() == 1)  monthStr = "0" + monthStr;
                    if(dayStr.length() == 1)  dayStr = "0" + dayStr;
                    if(hourStr.length() == 1)  hourStr = "0" + hourStr;
                    if(minuteStr.length() == 1)  minuteStr = "0" + minuteStr;
                    String timeStr = "" + year + " " + monthStr + " " + dayStr + " " + hourStr + " " + minuteStr + " 00";
                    Log.d("aa", "" + year + " " + monthStr + " " + dayStr + " " + hourStr + " " + minuteStr + " 00");
                    try {
                        Date date = format.parse(timeStr);
                        room.roomStartTime = date.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public Dialog YearMonthDayPicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, datePickerListener, year, month, day);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        return datePickerDialog;
    }

    //왕복, 편도 버튼 리스너
    View.OnClickListener onetwoWayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.oneway_btn:
                    if(room.round == false) break;
                    room.round = false;
                    onetwoWay_btn[0].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_oneway_clicked_btn));
                    onetwoWay_btn[1].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_twoway_btn));
                    break;
                case R.id.twoway_btn:
                    if(room.round == true)  break;
                    room.round = true;
                    onetwoWay_btn[0].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_oneway_btn));
                    onetwoWay_btn[1].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_twoway_clicked_btn));
                    break;
                default:
                    Log.d("error", "onetwoWayListener");
            }
        }
    };

    //목적(자격증, 영어, 기타) 버튼 리스너
    //자격증 = 0, 영어 = 1, 기타 = 2;
    View.OnClickListener goalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goal_btn[0].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option1));
            goal_btn[1].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option2));
            goal_btn[2].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option3));
            switch (v.getId()){
                case R.id.goal_btn0:
                    goal_btn[0].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option1_clicked));
                    room.goal = 0;
                    break;
                case R.id.goal_btn1:
                    goal_btn[1].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option2_clicked));
                    room.goal = 1;
                    break;
                case R.id.goal_btn2:
                    goal_btn[2].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.reserve_box2_option3_clicked));
                    room.goal = 2;
                    break;
            }
        }
    };

    //DatePicker 리스너
    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year_here, int monthOfYear, int dayOfMonth) {
            year = year_here;
            month = monthOfYear;
            day = dayOfMonth;
            reservation_make_month_textView.setText("" + (monthOfYear + 1));
            reservation_make_day_textView.setText("" + dayOfMonth);
        }
    };

    //TimePicker 리스너
    TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute_here) {
            hour = hourOfDay;
            minute = minute_here;
            reservation_make_hour_textView.setText("" + hourOfDay);
            reservation_make_minute_textView.setText("" + minute_here);
        }
    };
}
