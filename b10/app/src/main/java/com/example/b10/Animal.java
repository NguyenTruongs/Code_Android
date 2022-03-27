package com.example.b10;

public class Animal {
    private int id;
    private String tensv;
    private String hinh;
    private String gia;
    private String noidung;

    public Animal() {
    }

    public Animal(int id, String tensv, String hinh, String gia, String noidung) {
        this.id = id;
        this.tensv = tensv;
        this.hinh = hinh;
        this.gia = gia;
        this.noidung = noidung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
