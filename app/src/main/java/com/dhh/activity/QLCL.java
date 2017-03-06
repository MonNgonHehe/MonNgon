package com.dhh.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import duong.sqlite.DuongSQLite;

import static com.dhh.activity.QLCL.TaskQLCL.tbSinhVien;

/**
 * Created by D on 01/03/2017.
 */

public class QLCL extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSinhVien(41,11);

        Handler handler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };

        TaskQLCL taskQLCL=new TaskQLCL(handler,null,this);
        taskQLCL.execute();



    }
    int index=0;
    public void getSinhVien(int he, int khoaHienTai) {
        for (String khoa:countDownKhoa(khoaHienTai))
            for (String nganh:getNganh())
                for (int i =0; i < 1000; i++){
                    String msv="";
                    if (i<10) msv="00"+i;
                    else if (i<100) msv="0"+i;
                    else msv=""+i;

                    Log.e("faker",""+index++);
                    Log.e("faker",""+khoa+""+he+nganh+msv);
                }

    }

    private ArrayList<String> countDownKhoa(int i) {
        ArrayList<String> strings=new ArrayList<>();
        for (int j = i; j >0; j--) {
            if (j<10) strings.add("0"+j);
            else strings.add(""+j);
        }
        return strings;
    }

    public ArrayList<String> getNganh() {
        ArrayList<String> strings=new ArrayList<>();
        for (int i = 10; i < 1000; i+=10) {
            if (i<100) strings.add("0"+i);
            else strings.add(""+i);
        }
        return strings;
    }
    class SinhVien{
        private String msv;
        private String ten;
        private String lop;
        private String khoa;
        private String svNam;
        private String tbttl;

        @Override
        public String toString() {
            return "SinhVien{" +
                    "msv='" + msv + '\'' +
                    ", ten='" + ten + '\'' +
                    ", lop='" + lop + '\'' +
                    ", khoa='" + khoa + '\'' +
                    ", svNam='" + svNam + '\'' +
                    ", tbttl='" + tbttl + '\'' +
                    '}';
        }

        public String getMsv() {
            return msv;
        }

        public void setMsv(String msv) {
            this.msv = msv;
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public String getLop() {
            return lop;
        }

        public void setLop(String lop) {
            this.lop = lop;
        }

        public String getKhoa() {
            return khoa;
        }

        public void setKhoa(String khoa) {
            this.khoa = khoa;
        }

        public String getSvNam() {
            return svNam;
        }

        public void setSvNam(String svNam) {
            this.svNam = svNam;
        }

        public String getTbttl() {
            return tbttl;
        }

        public void setTbttl(String tbttl) {
            this.tbttl = tbttl;
        }

        public SinhVien(String msv, String ten, String lop, String khoa, String svNam, String tbttl) {

            this.msv = msv;
            this.ten = ten;
            this.lop = lop;
            this.khoa = khoa;
            this.svNam = svNam;
            this.tbttl = tbttl;
        }
    }
    class TaskQLCL extends AsyncTask<String, Void,SinhVien> {
        public static final String tbSinhVien="CREATE TABLE IF NOT EXISTS `sinhvien` (" +
                "`stt`INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`masv`TEXT," +
                "`ten`TEXT," +
                "`lop`TEXT," +
                "`khoa`TEXT," +
                "`tl`TEXT," +
                "`nam`TEXT" +
                ");";
        private  Context context;
        private DuongSQLite duongSQLite;
        private Handler handler;
        public TaskQLCL(Handler handler, DuongSQLite duongSQLite, Context context) {
            this.handler=handler;
            this.duongSQLite=duongSQLite;
            this.context=context;
        }
        private ArrayList<String> countDownKhoa(int i) {
            ArrayList<String> strings=new ArrayList<>();
            for (int j = i; j >0; j--) {
                if (j<10) strings.add("0"+j);
                else strings.add(""+j);
            }
            return strings;
        }

        public ArrayList<String> getNganh() {
            ArrayList<String> strings=new ArrayList<>();
            for (int i = 10; i < 1000; i+=10) {
                if (i<100) strings.add("0"+i);
                else strings.add(""+i);
            }
            return strings;
        }
        @Override
        protected SinhVien doInBackground(String... params) {
            DataBaseMonNgon duongSQLite=new DataBaseMonNgon(context);

                int khoaHienTai=2;
                String he="31";
            int loi=0;
                for (String khoa:countDownKhoa(khoaHienTai))
                    for (String nganh:getNganh())
                        for (int i =0; i < 600; i++){
                            String msv="";
                            if (i<10) msv="00"+i;
                            else if (i<100) msv="0"+i;
                            else msv=""+i;
//                            Log.e("faker",""+index++);
//                            Log.e("faker",""+khoa+""+he+nganh+msv);
                            String ma=""+khoa+""+he+nganh+msv;
                            String link="https://dttc.haui.edu.vn/vn/s/sinh-vien/bang-mon-bat-buoc?action=p1&p=1&ps=500&exp=rownb&dir=1&s=" +
                                    ma+
                                    "&osk=774fd5732173ddd410660a22bbcc0efc&idx=-1&view=sinh-vien%252Fbang-mon-bat-buoc";
                            SinhVien sinhVien=null;

                            Document doc = null;
                            try {
                                doc = Jsoup.connect(link).get();
                            } catch (Exception e) {
                                Log.e("faker","IOException");
                                continue;
                            }
                            if (doc.select("b.sName").isEmpty()){
                                loi=loi+1;
                                Log.e("faker","null "+ma+"break");
                                if (loi>200){
                                    loi=0;
                                    break;
                                }
                                continue;
                            }
                            sinhVien=new SinhVien(ma,
                                        doc.select("b").get(0).text(),
                                        doc.select("b").get(1).text(),
                                        doc.select("b").get(2).text(),
                                        doc.select("b").get(5).text(),
                                        doc.select("b").get(3).text());
                            Log.e("faker",sinhVien.toString());
                            duongSQLite.getDuongSQLite().insertByColumValue("sinhvien","`ten`,`lop`,`khoa`,`tl`,`nam`,`masv` ",
                                    "'"+sinhVien.getTen()+"',"+
                                            "'"+sinhVien.getLop()+"',"+
                                            "'"+sinhVien.getKhoa()+"',"+
                                            "'"+sinhVien.getTbttl()+"',"+
                                            "'"+sinhVien.getSvNam()+"',"+
                                            "'"+sinhVien.getMsv()+"'");
                        }

            return null;
        }

        @Override
        protected void onPostExecute(SinhVien itemNotiDTTCs) {
            Log.e("faker","onPostExecute");
        }

    }

    public class DataBaseMonNgon {
        private Context context;
        private DuongSQLite duongSQLite;
        public DataBaseMonNgon(Context context) {
            this.context=context;
            duongSQLite=new DuongSQLite(context);
            duongSQLite.openOrCreatDataBases("qlcl");
            duongSQLite.createTable(tbSinhVien);
//            TaskQLCL parserNotiDTTC=new TaskQLCL(null,duongSQLite);
//            parserNotiDTTC.execute();
        }

        public DuongSQLite getDuongSQLite() {
            return duongSQLite;
        }
    }

}
