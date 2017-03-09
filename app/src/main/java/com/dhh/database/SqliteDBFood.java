package com.dhh.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Environment;
import android.util.Log;

import com.dhh.object.DanhMuc;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import duong.ChucNangPhu;
import duong.sqlite.DuongSQLite;

/**
 * Created by Hong on 3/6/2017.
 */

public class SqliteDBFood {
    private Context context;
    private DuongSQLite duongSQLite;
    public static final String DB_NAME = "mon_ngon";
    public static final String TABLE_DANH_MUC = "danh_muc";
    public static final String TABLE_DANH_MUC_CON = "danh_muc_con";
    public static final String TABLE_MON_AN = "mon_an";
    public  static  String PATH="" ;
    public SqliteDBFood(Context context) {
        this.context = context;
        duongSQLite =new DuongSQLite(context);
//        PATH= "/data/data/"+context.getPackageName()+"/databases/mon_ngon.sqlite";
        PATH= Environment.getDataDirectory().getPath() +"/data/"+context.getPackageName()+"/databases/mon_ngon.sqlite";
        openDatabases();
    }
    public DuongSQLite getDuongSQLite() {
        return duongSQLite;
    }
    public boolean checkDB() {
        return duongSQLite.checkDataBase(PATH);
    }

    private void closeDatabases() {
        duongSQLite.cloneDatabase();
    }
//    public static final String PATH= Environment.getDataDirectory().getPath()
//            +"/data/com.htv.Dictionary/database/anh_viet.db";
    private void copyDatabase(Context context){
        try {
            InputStream inputStream=context.getAssets().open("anh_viet.db");
            File file=new File(PATH);
            if (!file.exists()){
                File parent=file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
                FileOutputStream outputStream=new FileOutputStream(file);
                byte[] b=new byte[1024];
                int count=inputStream.read(b);
                while (count!=-1){
                    outputStream.write(b,0,count);
                    count=inputStream.read(b);
                }
                outputStream.close();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openDatabases() {
        try {
//            duongSQLite.copyDataBase(context, PATH, DB_NAME+".sqlite");
       //     ChucNangPhu.showLog("fuck "+PATH);
            ChucNangPhu.showLog("is "+duongSQLite.checkDataBase(PATH));
            if (!duongSQLite.checkDataBase(PATH)){
                duongSQLite.copyDataBase(context, PATH, DB_NAME+".sqlite");
            }else  duongSQLite.openOrCreatDataBases(DB_NAME);
        } catch (Exception e) {
            Log.e("faker", "Exception openDatabases");
        }
    }

    public ArrayList<DanhMuc> getDanhMuc() {

        ArrayList<DanhMuc> danhMucs = new ArrayList<>();
        try {
            openDatabases();
            Cursor cursor = duongSQLite.getDatabase().query(TABLE_DANH_MUC, null, null, null, null, null, null);
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
            Cursor cursor = duongSQLite.selectByDK(query);
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
              //  Log.e("Database",danhMucCon.toString());
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
    public ArrayList<DanhMucCon> getDanhMucCon() {
        ArrayList<DanhMucCon> danhMucCons = new ArrayList<>();
        try {
            openDatabases();
            Cursor cursor = duongSQLite.getDatabase().query(TABLE_DANH_MUC_CON,null,null,null,null,null,null);
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
               // Log.e("Database",danhMucCon.toString());
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
    public ArrayList<MonAn> getMonAn(String id_danh_muc_con) {
        ArrayList<MonAn> monan= new ArrayList<>();
        String query = " Select * " + " From " + TABLE_MON_AN
                + " where " + "id_danh_muc_con" + " like '" + id_danh_muc_con + "'";
        try {
            openDatabases();
            Cursor cursor = duongSQLite.selectByDK(query);
            cursor.moveToFirst();
            int index_stt=cursor.getColumnIndex("stt");
//            int index_danh_muc_con = cursor.getColumnIndex("id");
            int index_id_mon_an=cursor.getColumnIndex("id_danh_muc_con");
            int index_link_img = cursor.getColumnIndex("link_img");
            int index_ten=cursor.getColumnIndex("ten");
            int index_des=cursor.getColumnIndex("des");
            int index_noi_dung=cursor.getColumnIndex("noi_dung_html");
            while (!cursor.isAfterLast()) {
                String stt=cursor.getString(index_stt);
//                String danh_muc_con=cursor.getString(index_danh_muc_con);
                String id = cursor.getString(index_id_mon_an);
                String link_img=cursor.getString(index_link_img);
                String ten =cursor.getString(index_ten);
                String des=cursor.getString(index_des);
                String noi_dung=cursor.getString(index_noi_dung);
               MonAn monAn =new MonAn(stt,"lol",id,link_img,ten,des,noi_dung);
                cursor.moveToNext();
                //ChucNangPhu.showLog(monAn.toString());
            }
            closeDatabases();
            return monan ;
        } catch (CursorIndexOutOfBoundsException e) {
            ChucNangPhu.showLog("CursorIndexOutOfBoundsException");
            return null;
        }
    }
    public ArrayList<MonAn> getMonAn() {
        ArrayList<MonAn> monan= new ArrayList<>();
        try {
            openDatabases();
            Cursor cursor = duongSQLite.getDatabase().query(TABLE_MON_AN,null,null,null,null,null,null);
            cursor.moveToFirst();
            int index_stt=cursor.getColumnIndex("stt");
//            int index_danh_muc_con = cursor.getColumnIndex("id");
            int index_id_mon_an=cursor.getColumnIndex("id_danh_muc_con");
            int index_link_img = cursor.getColumnIndex("link_img");
            int index_ten=cursor.getColumnIndex("ten");
            int index_des=cursor.getColumnIndex("des");
            int index_noi_dung=cursor.getColumnIndex("noi_dung_html");
            while (!cursor.isAfterLast()) {
                String stt=cursor.getString(index_stt);
//                String danh_muc_con=cursor.getString(index_danh_muc_con);
                String id = cursor.getString(index_id_mon_an);
                String link_img=cursor.getString(index_link_img);
                String ten =cursor.getString(index_ten);
                String des=cursor.getString(index_des);
                String noi_dung=cursor.getString(index_noi_dung);
                MonAn monAn =new MonAn(stt,"lol",id,link_img,ten,des,noi_dung);
                cursor.moveToNext();
               // ChucNangPhu.showLog(monAn.toString());
            }
            closeDatabases();
            return monan ;
        } catch (CursorIndexOutOfBoundsException e) {
            ChucNangPhu.showLog("CursorIndexOutOfBoundsException");
            return null;
        }
    }
}
