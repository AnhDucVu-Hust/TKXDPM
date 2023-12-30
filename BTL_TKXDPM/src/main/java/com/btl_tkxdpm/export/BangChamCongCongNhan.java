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
        return null;
        }

    }





