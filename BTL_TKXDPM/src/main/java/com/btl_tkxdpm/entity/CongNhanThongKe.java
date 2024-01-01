package com.btl_tkxdpm.entity;

import java.time.LocalTime;

public class CongNhanThongKe {
    private String maNhanVien;
    private String hoTen;
    private String donVi;
    private String thang;
    private double thoiGianLam;
    private double thoiGianTangCa;

    public LocalTime getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(LocalTime lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    private LocalTime lastCheckinTime=LocalTime.of(0,0,0);

    public CongNhanThongKe(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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
