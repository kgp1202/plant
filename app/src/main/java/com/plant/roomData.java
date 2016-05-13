package com.plant;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by angks on 2016-05-13.
 */
public class roomData {
    public int ID;
    public ArrayList<Integer> userID;
    public long durationTime;
    public int userNum;
    public int maxUser;
    public int startingPoint;
    public int destPoint;
    public boolean round;//왕복
    public long roomStartTime;//출발 날짜
    public int roomObject;//목적
    public String content;
    public JSONObject getRoomDataJson(){
        Gson makeJson=new Gson();
        JSONObject returnV=null;
        try {
            returnV=new JSONObject(makeJson.toJson(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnV;
    }
    public void setRoomDataFromJson(JSONObject input){
        Gson makeClasee=new Gson();
        roomData temp=new roomData();
        temp=makeClasee.fromJson(input.toString(),roomData.class);
        this.ID=temp.ID;
        this.userID=temp.userID;
        this.durationTime=temp.durationTime;
        this.userNum=temp.userNum;
        this.maxUser=temp.maxUser;
        this.startingPoint=temp.startingPoint;
        this.destPoint=temp.destPoint;
        this.round=temp.round;
        this.roomStartTime=temp.roomStartTime;
        this.roomObject=temp.roomObject;
        this.content=temp.content;
    }
}
