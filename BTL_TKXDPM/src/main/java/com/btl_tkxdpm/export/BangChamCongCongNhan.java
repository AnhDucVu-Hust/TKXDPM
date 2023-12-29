package com.btl_tkxdpm.export;

import com.btl_tkxdpm.DBConnection;
import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.entity.CongNhanThongKe;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BangChamCongCongNhan {
    public static ObservableList<CongNhanThongKe> getBangThongKe(int month,int year){
        ObservableList<NhanVienAttendance> bangChamCong = Services.queryChamCongCongNhan(month,year);
        Map<String,Float> soGioDiMuonVeSom = new ;
        Map<String, Integer> soBuoiDiLam = bangChamCong.stream()
                .collect(Collectors.groupingBy(nhanVienAttendance -> nhanVienAttendance.getNhanVien().getMaNhanVien(), Collectors.mapping(NhanVienAttendance::getDay, Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
        for (NhanVienAttendance nhanVienAttendance : bangChamCong){
            if (nhanVienAttendance.getLoaiChamCong().equals("CHECKIN")){
                float soGioDiMuon = Services.SubtractLocalTime(nhanVienAttendance.getGioVao(), LocalTime.of(8,0,0));
                
            }
        }

    }



    }

