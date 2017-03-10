package com.dhh.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.dhh.database.SqliteDBFood;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;

import duong.ChucNangPhu;

/**
 * Created by Hong on 3/7/2017.
 */

public class TaskMonAn extends AsyncTask<String,Void,ArrayList<MonAn>> {
    private final ArrayList<DanhMucCon> danhMucCons;
    private Context context;
    private Handler handler;

    public TaskMonAn(Context context, Handler handler, ArrayList<DanhMucCon> danhMucCons) {
        this.context = context;
        this.handler = handler;
        this.danhMucCons = danhMucCons;

    }


    @Override
    protected ArrayList<MonAn> doInBackground(String... params) {
        ArrayList<MonAn> monAns=new ArrayList<>();
        SqliteDBFood duLieu=new SqliteDBFood(context);
        for (DanhMucCon danhMucCon:danhMucCons) {
            if (danhMucCon.getId_danh_muc().equals(params[0]))
                monAns.addAll(duLieu.getMonAn(danhMucCon.getId()));
        }
        return monAns;
    }

    @Override
    protected void onPostExecute(ArrayList<MonAn> monAns) {
        Message message=new Message();
        message.obj=monAns;
        handler.sendMessage(message);
        ChucNangPhu.showLog("onPostExecute "+monAns.size());
    }
}
