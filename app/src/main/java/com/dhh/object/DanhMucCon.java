package com.dhh.object;

/**
 * Created by Hong on 3/6/2017.
 */

public class DanhMucCon{
    private int stt;
    private String id,id_danh_muc,ten_danh_muc;

    public DanhMucCon(int stt, String id, String id_danh_muc, String ten_danh_muc) {
        this.stt = stt;
        this.id = id;
        this.id_danh_muc = id_danh_muc;
        this.ten_danh_muc = ten_danh_muc;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_danh_muc() {
        return id_danh_muc;
    }

    public void setId_danh_muc(String id_danh_muc) {
        this.id_danh_muc = id_danh_muc;
    }

    public String getTen_danh_muc() {
        return ten_danh_muc;
    }

    public void setTen_danh_muc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }

    @Override
    public String toString() {
        return "DanhMucCon{" +
                "stt=" + stt +
                ", id='" + id + '\'' +
                ", id_danh_muc='" + id_danh_muc + '\'' +
                ", ten_danh_muc='" + ten_danh_muc + '\'' +
                '}';
    }
}
