package duong.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import duong.ChucNangPhu;

/**
 * Created by d on 17/01/2017.
 */

public class DuongSQLite {
    private Context context;

    public DuongSQLite(Context context) {
        this.context = context;
    }

    /**
     * đóng db khi đọc xong
     */
    public void cloneDatabase() {
        database.close();
    }
    /**
     * taoj bảng mới bằng các truyền câu lệnh query
     * @param query
     */
    public void createTable(String query) {
        database.execSQL(query);
    }
    private SQLiteDatabase database;

    /**
     * trả về đối tượng quản lý database
     * @return
     */
    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
    /**
     * tạo mới hoặc mở database
     * @param nameDatabases tên của database bỏ phần mở rộng .sqlite
     */
    public void openOrCreatDataBases(String nameDatabases) {
        database=context.openOrCreateDatabase(nameDatabases+".sqlite",Context.MODE_APPEND,null);

    }

    /**
     * chèn thêm 1 dòng vào databse bằng tên bảng tên cột cách nahu bằng dấu "," và dữ liệu tương ứng bằng đấu "," không có ràn buộc
     * @param tableName tên bảng
     * @param colums số trường cần chèn  VD: " `id`,`ten` "
     * @param values số dữ liệu tương ứng với số cột VD: " NULL,'dulieu text' "
     */
    public void insertByColumValue(String tableName,String colums, String values) {
        database.execSQL("INSERT INTO `"+tableName+"`("+colums+") VALUES ("+values+");");
    }

    /**
     * cập nhật dữ liệu thông qua (tên bảng , `tên`='values', diều kiện where `tên cột`='values')
     * @param tableName
     * @param columsAndValuesChange
     * @param where
     */
    public void updateByColumValue(String tableName,String columsAndValuesChange,String where) {
        database.execSQL("UPDATE `"+tableName+"` SET "+columsAndValuesChange+" WHERE "+where);
    }

    /**
     * xóa 1 dòng thông qua điều kiên ("tên bảng cần xóa", " `tên trường`='giá trị' ")
     * @param tableName
     * @param where
     */
    public void deleteRowById(String tableName,String where) {
        database.execSQL("DELETE FROM `"+tableName+"` WHERE "+where+";");
    }

    /**
     * lấy tất cả các dữ liệu trong bảng qua tên bảng
     * @param tableName " tên bảng "
     * @return Cursor đối tượng quản lý các dòng và cột dữ liệu
     */
    private Cursor selectAllByTableName(String tableName){
       return database.rawQuery("SELECT * FROM "+tableName, null);
    }
    /**
     * lấy tất cả các dữ liệu trong bảng qua điều kiện truyền vào
     * @param query " câu truy vấn "
     * @return Cursor đối tượng quản lý các dòng và cột dữ liệu
     */
    public Cursor selectByDK(String query){
        return database.rawQuery(query, null);
    }
    /**
     * xóa tất cả các dữ liệu trong bảng bằng tên bảng
     * @param tableName "tên bảng"
     */
    public void deleteTableByName(String tableName) {
        database.execSQL("delete from "+ tableName);
    }

    /**
     *
     * @param context
     * @param pathDB
     * @param nameDatabases
     * @throws IOException
     */
    public boolean copyDataBase(Context context, String pathDB, String nameDatabases){
        try {
            File file=new File(pathDB);
            Log.e("faker copyDataBase",pathDB);
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
                InputStream myInput = context.getAssets().open(nameDatabases);
                OutputStream myOutput = new FileOutputStream(pathDB);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer))>0){
                    myOutput.write(buffer, 0, length);
                }
                myOutput.close();
                myInput.close();
                Log.e("faker close",pathDB);
                return true;
            }
        }catch (Exception e){}
        return false;
    }

    /**
     * kiểm tra xem file sqlite đã tồn tại ## nhớ hỏi quyền
     * @param path đường dẫn file
     * @return đã tồn tại hoặc chưa true/false
     */
    public boolean checkDataBase(String path){
        try {
            ChucNangPhu.showLog("check "+path);
            return new File(path).exists();

        } catch (Exception e) {
            ChucNangPhu.showLog("checkDataBase E");

        }
        return false;
    }

    /**
     * xóa file thông qua đường đẫn
     * @param pathDatabase
     * @return đã xóa hoặc chưua xóa true/false
     */
    public boolean deleteDataBase(String pathDatabase){
        try {
            return new File(pathDatabase).delete();
        } catch (Exception e) {
            return false;
        }
    }

}
