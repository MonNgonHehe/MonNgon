package com.dhh.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.dhh.database.SqliteDBFood;
import com.dhh.object.DanhMuc;

import java.util.ArrayList;

/**
 * Created by Hong on 3/7/2017.
 */

public class TaskDanhMuc extends AsyncTask<Void,Void,ArrayList<DanhMuc>> {
    private Context context;
    private Handler handler;

    public TaskDanhMuc(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }


    @Override
    protected ArrayList<DanhMuc> doInBackground(Void... params) {
        SqliteDBFood duLieu=new SqliteDBFood(context);
        return duLieu.getDanhMuc();
    }
    @Override
    protected void onPostExecute(ArrayList<DanhMuc> danhMucs) {
        Message message=new Message();
        message.obj=danhMucs;
        handler.sendMessage(message);
    }
}
