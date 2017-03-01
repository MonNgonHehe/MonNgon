package com.dhh.database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import duong.sqlite.DuongSQLite;

/**
 * Created by D on 28/02/2017.
 */

public class DataBaseMonNgon {
    private  Context context;
    private DuongSQLite duongSQLite;
    private final static String QUERY_CREATE_TB_DANH_MUC="CREATE TABLE IF NOT EXISTS `danh_muc` (" +
            "`stt`INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`id`INTEGER ," +
            "`ten`TEXT NOT NULL" +
            ");";
    private final static String QUERY_CREATE_TB_DANH_MUC_COM="CREATE TABLE  IF NOT EXISTS `danh_muc_con` (" +
            "`stt`INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`id`INTEGER ," +
            "`id_danh_muc`INTEGER NOT NULL," +
            "`ten`TEXT NOT NULL" +
            ");";
    private final static String QUERY_CREATE_TB_MON_AN="CREATE TABLE IF NOT EXISTS `mon_an` (" +
            "`stt`INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`id`INTEGER ," +
            "`id_danh_muc_con`INTEGER NOT NULL," +
            "`ten`TEXT NOT NULL," +
            "`nguyen_lieu`TEXT NOT NULL" +
            ");";
    private final static String QUERY_CREATE_TB_CACH_LAM="CREATE TABLE IF NOT EXISTS  `cach_lam` (" +
            "`stt`INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`id_mon_an`INTEGER NOT NULL," +
            "`buoc_lam`INTEGER NOT NULL," +
            "`noi_dung`TEXT NOT NULL," +
            "`anh`TEXT" +
            ");";
    public DataBaseMonNgon(Context context) {
        this.context=context;
        duongSQLite=new DuongSQLite(context);
        duongSQLite.openOrCreatDataBases("mon_ngon");
        duongSQLite.createTable(QUERY_CREATE_TB_DANH_MUC);
        duongSQLite.createTable(QUERY_CREATE_TB_DANH_MUC_COM);
        duongSQLite.createTable(QUERY_CREATE_TB_MON_AN);
        duongSQLite.createTable(QUERY_CREATE_TB_CACH_LAM);
        ParserNotiDTTC parserNotiDTTC=new ParserNotiDTTC(null,duongSQLite);
        parserNotiDTTC.execute();
    }
    class Flag{
        private ArrayList<DanhMuc> danhMucs;
        private ArrayList<DanhMucCon> danhMucConss;
        private ArrayList<MonAn> monAns;
        private ArrayList<CachLam> cachLams;

        public Flag(ArrayList<DanhMuc> danhMucs, ArrayList<DanhMucCon> danhMucConss, ArrayList<MonAn> monAns, ArrayList<CachLam> cachLams) {
            this.danhMucs = danhMucs;
            this.danhMucConss = danhMucConss;
            this.monAns = monAns;
            this.cachLams = cachLams;
        }

        public ArrayList<DanhMuc> getDanhMucs() {

            return danhMucs;
        }

        public ArrayList<DanhMucCon> getDanhMucConss() {
            return danhMucConss;
        }

        public void setDanhMucConss(ArrayList<DanhMucCon> danhMucConss) {
            this.danhMucConss = danhMucConss;
        }

        public ArrayList<MonAn> getMonAns() {
            return monAns;
        }

        public void setMonAns(ArrayList<MonAn> monAns) {
            this.monAns = monAns;
        }

        public ArrayList<CachLam> getCachLams() {
            return cachLams;
        }

        public void setCachLams(ArrayList<CachLam> cachLams) {
            this.cachLams = cachLams;
        }

        public void setDanhMucs(ArrayList<DanhMuc> danhMucs) {

            this.danhMucs = danhMucs;
        }
    }
    class DanhMucCon{
        private String ten;
        private String idDanhMuc;
        private String id;

        @Override
        public String toString() {
            return "DanhMucCon{" +
                    "ten='" + ten + '\'' +
                    ", idDanhMuc='" + idDanhMuc + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public String getIdDanhMuc() {
            return idDanhMuc;
        }

        public void setIdDanhMuc(String idDanhMuc) {
            this.idDanhMuc = idDanhMuc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DanhMucCon(String ten, String idDanhMuc, String id) {

            this.ten = ten;
            this.idDanhMuc = idDanhMuc;
            this.id = id;
        }
    }
    class DanhMuc{
        private String ten;
        private String id;

        @Override
        public String toString() {
            return "DanhMuc{" +
                    "ten='" + ten + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public DanhMuc(String ten, String id) {

            this.ten = ten;
            this.id = id;
        }
    }
    class MonAn{
        String id;
        String ten;
        String nguyenLieu;
        String idDanhMucCon;

        @Override
        public String toString() {
            return "MonAn{" +
                    "id='" + id + '\'' +
                    ", ten='" + ten + '\'' +
                    ", nguyenLieu='" + nguyenLieu + '\'' +
                    ", idDanhMucCon='" + idDanhMucCon + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public String getNguyenLieu() {
            return nguyenLieu;
        }

        public void setNguyenLieu(String nguyenLieu) {
            this.nguyenLieu = nguyenLieu;
        }

        public String getIdDanhMucCon() {
            return idDanhMucCon;
        }

        public void setIdDanhMucCon(String idDanhMucCon) {
            this.idDanhMucCon = idDanhMucCon;
        }

        public MonAn(String id, String ten, String nguyenLieu, String idDanhMucCon) {

            this.id = id;
            this.ten = ten;
            this.nguyenLieu = nguyenLieu;
            this.idDanhMucCon = idDanhMucCon;
        }
    }
    class CachLam{
        String id;
        String buocLam;
        String noiDung;
        String anh;

        @Override
        public String toString() {
            return "CachLam{" +
                    "id='" + id + '\'' +
                    ", buocLam='" + buocLam + '\'' +
                    ", noiDung='" + noiDung + '\'' +
                    ", anh='" + anh + '\'' +
                    '}';
        }

        public String getBuocLam() {
            return buocLam;
        }

        public void setBuocLam(String buocLam) {
            this.buocLam = buocLam;
        }

        public String getNoiDung() {
            return noiDung;
        }

        public void setNoiDung(String noiDung) {
            this.noiDung = noiDung;
        }

        public String getAnh() {
            return anh;
        }

        public void setAnh(String anh) {
            this.anh = anh;
        }

        public String getId() {

            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CachLam(String id, String buocLam, String noiDung, String anh) {

            this.id = id;
            this.buocLam = buocLam;
            this.noiDung = noiDung;
            this.anh = anh;
        }
    }
    class LoadMonAn{
        private String idDanhMucCon;
        private String link;

        @Override
        public String toString() {
            return "LoadMonAn{" +
                    "idDanhMucCon='" + idDanhMucCon + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }

        public String getIdDanhMucCon() {
            return idDanhMucCon;
        }

        public void setIdDanhMucCon(String idDanhMucCon) {
            this.idDanhMucCon = idDanhMucCon;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public LoadMonAn(String idDanhMucCon, String link) {

            this.idDanhMucCon = idDanhMucCon;
            this.link = link;
        }
    }
    class ParserNotiDTTC extends AsyncTask<Void, Void, Flag> {
        private DuongSQLite duongSQLite;
        private Handler handler;
        public ParserNotiDTTC(Handler handler, DuongSQLite duongSQLite) {
            this.handler=handler;
            this.duongSQLite=duongSQLite;
        }
        @Override
        protected Flag doInBackground(Void... params) {
            String link = "http://afamily.vn";
            ArrayList<DanhMuc> danhMucs=new ArrayList<>();
             ArrayList<DanhMucCon> danhMucCons=new ArrayList<>();
            ArrayList<LoadMonAn> loadMonans=new ArrayList<>();
            Flag flag=null;
            try {
                Document doc = Jsoup.connect(link).get();
                Elements elements= doc.select("div.menu-boxchild-wrap").first().select("div.menu-boxchild");
                Elements spans=elements.select("span");
                Collections.reverse(spans);

                Elements uls=elements.select("ul");

                Collections.reverse(uls);
                /**
                 * lấy dc các tab to
                 */
                for (Element element:spans) {
                    Log.e("faker",""+element.text()+" "+spans.indexOf(element));
//                    duongSQLite.insertByColumValue("danh_muc","`ten`","'"+element.text()+"'");
                }
                /**
                 * llấy dc các tab con + id tab con uls.indexOf(element)+lis.indexOf(el) , id tab to uls.indexOf(element), ten tab con el.text()
                 */
                for (Element element:uls) {
                    Elements lis=uls.get(uls.indexOf(element)).select("li");
                    for (Element el:lis) {
                        Log.e("faker",el.text()+" "+uls.indexOf(element)+" "+uls.indexOf(element)+lis.indexOf(el)+" "+el.select("a").attr("href"));
                        String str=el.select("a").attr("href");
                        str=str.replace(".htm","/trang-1.htm");
                        loadMonans.add(new LoadMonAn(uls.indexOf(element)+""+lis.indexOf(el),str));
                        Log.e("faker","-----------------------------------------------------" );
                    }
//                    duongSQLite.insertByColumValue("danh_muc","`ten`","'"+element.text()+"'");
                }//keytool -list -v -keystore mystore.keystore
                /**
                 * lấy nội dung là list item từ các tab con và id tab con
                 *
                 * ngày mai sẽ truy cập vào trang và lấy dữ liệu các item từ tab con và vào từng item lấy thông tin và lưu vào database
                 *
                 * - tìm hiểu cách lưu ảnh trên server hoặc dùng firebase để lưu ảnh
                 */
                for (LoadMonAn loadMonAn:loadMonans) {
                    Log.e("faker",loadMonAn.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return flag;
        }

        @Override
        protected void onPostExecute(Flag itemNotiDTTCs) {

        }

    }

}
