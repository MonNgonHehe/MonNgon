package com.dhh.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Environment;
import android.util.Log;

import com.dhh.object.DanhMuc;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.io.IOException;
import java.util.ArrayList;

import duong.ChucNangPhu;
import duong.sqlite.DuongSQLite;

/**
 * Created by Hong on 3/6/2017.
 */

public class SqliteDBFood {
    private Context context;
    private DuongSQLite sqliteManager;
    public static final String DB_NAME = "mon_ngon";
    public static final String TABLE_DANH_MUC = "danh_muc";
    public static final String TABLE_DANH_MUC_CON = "danh_muc_con";
    public static final String PATH =
            "/data/com.dhh.activity/database/mon_ngon.sqlite";

    public SqliteDBFood(Context context) {
        this.context = context;
        openDatabases();
    }

    public boolean checkDB() {
        return sqliteManager.checkDataBase(PATH);
    }

    private void closeDatabases() {
        sqliteManager.cloneDatabase();
    }

    private void openDatabases() {
        try {
//            sqliteManager.copyDataBase(context, PATH, DB_NAME+".sqlite");
            if (sqliteManager.checkDataBase(PATH))
                sqliteManager.copyDataBase(context, PATH, DB_NAME+".sqlite");
              else  sqliteManager.openOrCreatDataBases(DB_NAME);
        } catch (Exception e) {
            Log.e("faker", "openDatabases");
        }
    }

    public ArrayList<DanhMuc> getDanhMuc() {

        ArrayList<DanhMuc> danhMucs = new ArrayList<>();
        try {
            openDatabases();
            Cursor cursor = sqliteManager.getDatabase().query(TABLE_DANH_MUC, null, null, null, null, null, null);
            cursor.moveToFirst(); // di chuyển con trỏ đến dòng đầu tiền trong bảng
            int stt = cursor.getColumnIndex("stt");
            int id_danh_muc = cursor.getColumnIndex("id");
            int index_ten_danhmuc = cursor.getColumnIndex("ten");
            while (!cursor.isAfterLast()) {
                int stt1 = cursor.getInt(stt);
                int id = cursor.getInt(id_danh_muc);
                String tenDanhMuc = cursor.getString(index_ten_danhmuc);
                DanhMuc danhMuc = new DanhMuc(stt1, id, tenDanhMuc);
                danhMucs.add(danhMuc);
                cursor.moveToNext();
            }
            closeDatabases();
            return danhMucs;
        } catch (CursorIndexOutOfBoundsException e) {
            ChucNangPhu.showLog("CursorIndexOutOfBoundsException");
            return null;
        }
    }

    public ArrayList<DanhMucCon> getDanhMucCon(String id_danh_muc) {
        ArrayList<DanhMucCon> danhMucCons = new ArrayList<>();
        String query = " Select * " + " From " + TABLE_DANH_MUC_CON
                + " where " + "id_danh_muc" + " like '" + id_danh_muc + "'";
        try {
            openDatabases();
            Cursor cursor = sqliteManager.selectByDK(query);
            cursor.moveToFirst();
            int index_stt=cursor.getColumnIndex("stt");
            int index_id=cursor.getColumnIndex("id");
            int  index_danh_muc = cursor.getColumnIndex("id_danh_muc");
            int index_ten_danhmuc = cursor.getColumnIndex("ten");
            while (!cursor.isAfterLast()) {
                int stt=cursor.getInt(index_stt);
                String id =cursor.getString(index_id);
                String danh_muc=cursor.getString(index_danh_muc);
                String tenDanhMuc = cursor.getString(index_ten_danhmuc);
                DanhMucCon danhMucCon = new DanhMucCon(stt,id,danh_muc, tenDanhMuc);
                Log.e("Database",danhMucCon.toString());
                danhMucCons.add(danhMucCon);
                cursor.moveToNext();
            }
            closeDatabases();
            return danhMucCons;
        } catch (CursorIndexOutOfBoundsException e) {
            ChucNangPhu.showLog("CursorIndexOutOfBoundsException");
            return null;
        }
    }
    public ArrayList<MonAn> getDanhMonAn(int id_danh_muc_con) {
        ArrayList<MonAn> monan= new ArrayList<>();
        String query = " Select * " + " From " + TABLE_DANH_MUC_CON
                + " where " + "id" + " like '" + id_danh_muc_con + "'";
        try {
            openDatabases();
            Cursor cursor = sqliteManager.selectByDK(query);
            cursor.moveToFirst();
            int index_stt=cursor.getColumnIndex("stt");
            int index_danh_muc_con = cursor.getColumnIndex("id");
            int index_id_mon_an=cursor.getColumnIndex("id_danh_muc_con");
            int index_link_img = cursor.getColumnIndex("link_img");
            int index_ten=cursor.getColumnIndex("ten");
            int index_des=cursor.getColumnIndex("des");
            int index_noi_dung=cursor.getColumnIndex("noi_dung_html");
            while (!cursor.isAfterLast()) {
                int stt=cursor.getInt(index_stt);
                String danh_muc_con=cursor.getString(index_danh_muc_con);
                String id = cursor.getString(index_id_mon_an);
                String link_img=cursor.getString(index_link_img);
                String ten =cursor.getString(index_ten);
                String des=cursor.getString(index_des);
                String noi_dung=cursor.getString(index_noi_dung);
               MonAn monAn =new MonAn(stt,danh_muc_con,id,link_img,ten,des,noi_dung);
                cursor.moveToNext();
            }
            closeDatabases();
            return monan ;
        } catch (CursorIndexOutOfBoundsException e) {
            ChucNangPhu.showLog("CursorIndexOutOfBoundsException");
            return null;
        }
    }
}
