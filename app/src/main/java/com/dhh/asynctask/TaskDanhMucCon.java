package com.dhh.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.dhh.database.SqliteDBFood;
import com.dhh.object.DanhMuc;
import com.dhh.object.DanhMucCon;

import java.util.ArrayList;

/**
 * Created by Hong on 3/7/2017.
 */

public class TaskDanhMucCon extends AsyncTask<Void,Void,ArrayList<DanhMucCon>> {
    private Context context;
    private Handler handler;

    public TaskDanhMucCon(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }


    @Override
    protected ArrayList<DanhMucCon> doInBackground(Void... params) {
        SqliteDBFood duLieu=new SqliteDBFood(context);
        return duLieu.getDanhMucCon();
    }
    @Override
    protected void onPostExecute(ArrayList<DanhMucCon> danhMucCons) {
        Message message=new Message();
        message.obj=danhMucCons;
        handler.sendMessage(message);
    }
}
