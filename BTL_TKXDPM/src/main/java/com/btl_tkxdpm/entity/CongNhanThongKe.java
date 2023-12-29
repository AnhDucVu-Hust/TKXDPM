package com.btl_tkxdpm.entity;

public class CongNhanThongKe {
    private String maNhanVien;
    private String hoTen;
    private String donVi;
    private String thang;
    private double thoiGianLam;
    private double thoiGianTangCa;

    public CongNhanThongKe(String maNhanVien, String hoTen, String donVi, String thang, double thoiGianLam, double thoiGianTangCa) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.donVi = donVi;
        this.thang = thang;
        this.thoiGianLam = thoiGianLam;
        this.thoiGianTangCa = thoiGianTangCa;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String id) {
        this.maNhanVien = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public double getThoiGianLam() {
        return thoiGianLam;
    }

    public void setThoiGianLam(double thoiGianLam) {
        this.thoiGianLam = thoiGianLam;
    }

    public double getThoiGianTangCa() {
        return thoiGianTangCa;
    }

    public void setThoiGianTangCa(double thoiGianTangCa) {
        this.thoiGianTangCa = thoiGianTangCa;
    }
}
