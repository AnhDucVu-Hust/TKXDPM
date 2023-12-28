package com.btl_tkxdpm.entity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NhanVien {

    private  String hoTen;

    private  String maNhanVien;
    private  String chucDanh;
    private String donVi;

    public NhanVien(String hoTen, String maNhanVien, String chucDanh, String donVi) {
        this.hoTen = hoTen;
        this.maNhanVien = maNhanVien;
        this.chucDanh = chucDanh;
        this.donVi = donVi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
}




