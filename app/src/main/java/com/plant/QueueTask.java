package com.plant;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by angks on 2016-05-25.
 */
class QueueTask extends AsyncTask<Void, Void, Void> {
    ActivityMakeDarker myTrigger;
    public QueueTask(ActivityMakeDarker input){
        myTrigger=input;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... params) {
        while(!isCancelled()){

        }
        return null;
    }
    @Override
    protected void onPostExecute(Void params) {
        super.onPostExecute(params);
        myTrigger.makeDarker(false);
    }
    @Override
    public void onCancelled(Void params) {
        super.onCancelled(params);
        Log.d("cancelled","test");
        myTrigger.makeDarker(false);
    }
};