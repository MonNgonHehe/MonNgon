package com.dhh.object;

/**
 * Created by Hong on 3/6/2017.
 */

public class MonAn {
    private String stt;
    private String id;
    private String id_danhmuccon;
    private String link_img;
    private String ten;
    private String des;
    private String content_html;

    public MonAn(String stt, String id, String id_danhmuccon, String link_img, String ten, String des, String content_html) {
        this.stt = stt;
        this.id = id;
        this.id_danhmuccon = id_danhmuccon;
        this.link_img = link_img;
        this.ten = ten;
        this.des = des;
        this.content_html = content_html;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "stt=" + stt +
                ", id='" + id + '\'' +
                ", id_danhmuccon='" + id_danhmuccon + '\'' +
                ", link_img='" + link_img + '\'' +
                ", ten='" + ten + '\'' +
                ", des='" + des + '\'' +
                ", content_html='" + content_html + '\'' +
                '}';
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_danhmuccon() {
        return id_danhmuccon;
    }

    public void setId_danhmuccon(String id_danhmuccon) {
        this.id_danhmuccon = id_danhmuccon;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContent_html() {
        return content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }
}
