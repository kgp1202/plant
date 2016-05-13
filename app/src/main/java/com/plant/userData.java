package com.plant;

import android.graphics.Bitmap;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by angks on 2016-05-13.
 */
public class userData {
    public static int FACEBOOK=1;
    public static int KAKAO=2;
    public static int TWITTER=3;
    public int ID;
    public int loginFrom;
    public Bitmap profile;
    public ArrayList<Integer> realTimeRoomID;
    public int point;
    public String name;
    public JSONObject getUserDataJson(){
        Gson makeString=new Gson();;
        JSONObject returnV=null;
        try {
            returnV=new JSONObject(makeString.toJson(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnV;
    }
    public void setUserDataFromJson(JSONObject input){
        Gson makeClasee=new Gson();
        userData temp=new userData();
        temp=makeClasee.fromJson(input.toString(),userData.class);
        this.ID=temp.ID;
        this.loginFrom=temp.loginFrom;
        this.profile=temp.profile;
        this.point=temp.point;
        this.realTimeRoomID=temp.realTimeRoomID;
        this.point=temp.point;
        this.name=temp.name;
    }
}
