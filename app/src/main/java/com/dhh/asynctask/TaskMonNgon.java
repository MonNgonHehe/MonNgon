package com.dhh.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.dhh.database.SqliteDBFood;
import com.dhh.object.DanhMuc;
import com.dhh.object.MonAn;

import java.util.ArrayList;

/**
 * Created by Hong on 3/7/2017.
 */

public class TaskMonNgon extends AsyncTask<Void,Void,ArrayList<MonAn>> {
    private Context context;
    private Handler handler;

    public TaskMonNgon(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }


    @Override
    protected ArrayList<MonAn> doInBackground(Void... params) {
        SqliteDBFood duLieu=new SqliteDBFood(context);
        return duLieu.getMonAn();
    }
    @Override
    protected void onPostExecute(ArrayList<MonAn> monNgons) {
        Message message=new Message();
        message.obj=monNgons;
        handler.sendMessage(message);
    }
}
