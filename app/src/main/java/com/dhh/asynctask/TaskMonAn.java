package com.dhh.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.dhh.database.SqliteDBFood;
import com.dhh.object.MonAn;

import java.util.ArrayList;

/**
 * Created by Hong on 3/7/2017.
 */

public class TaskMonAn extends AsyncTask<String,Void,ArrayList<MonAn>> {
    private Context context;
    private Handler handler;

    public TaskMonAn(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }


    @Override
    protected ArrayList<MonAn> doInBackground(String... params) {
        SqliteDBFood duLieu=new SqliteDBFood(context);
        return duLieu.getMonAn(params[0]);
    }
    @Override
    protected void onPostExecute(ArrayList<MonAn> monAns) {
        Message message=new Message();
        message.obj=monAns;
        handler.sendMessage(message);
    }
}
