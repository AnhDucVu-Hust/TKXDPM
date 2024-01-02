package com.btl_tkxdpm.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NhanVienVanPhongThongKe {
    private String maNhanVien;
    private String hoTen;
    private String donVi;
    private String thang;
    private int soBuoiDiLam=0;
    private double soGioDiMuonVeSom=0;
    public Set<LocalDate> getNhungNgayDiLam() {
        return nhungNgayDiLam;
    }

    public void addNhungNgayDiLam(LocalDate ngayDiLam) {
        this.nhungNgayDiLam.add(ngayDiLam);
    }

    private Set<LocalDate> nhungNgayDiLam = new HashSet<LocalDate>();

    public NhanVienVanPhongThongKe(String maNhanVien, String hoTen, String donVi, String thang, int soBuoiDiLam, double soGioDiMuonVeSom) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.donVi = donVi;
        this.thang = thang;
        this.soBuoiDiLam = soBuoiDiLam;
        this.soGioDiMuonVeSom = soGioDiMuonVeSom;
    }
    public NhanVienVanPhongThongKe(String maNhanVien){
        this.maNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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

    public int getSoBuoiDiLam() {
        return soBuoiDiLam;
    }

    public void setSoBuoiDiLam(int soBuoiDiLam) {
        this.soBuoiDiLam = soBuoiDiLam;
    }

    public double getSoGioDiMuonVeSom() {
        return soGioDiMuonVeSom;
    }

    public void setSoGioDiMuonVeSom(double soGioDiMuonVeSom) {
        this.soGioDiMuonVeSom = soGioDiMuonVeSom;
    }
}
