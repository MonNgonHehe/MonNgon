package com.dhh.object;

import java.io.Serializable;

/**
 * Created by Hong on 3/6/2017.
 */

public class DanhMuc implements Serializable {
    private  int stt;
    private int idDanhMuc;
    private String tenDanhMuc;

    public DanhMuc(int stt, int idDanhMuc, String tenDanhMuc) {
        this.stt = stt;
        this.idDanhMuc = idDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
