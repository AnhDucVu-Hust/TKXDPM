package com.btl_tkxdpm.export;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.DBConnection;
import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.entity.CongNhanThongKe;
import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BangChamCongCongNhan {
    public static ObservableList<CongNhanThongKe> getBangThongKe(ObservableList<NhanVienAttendance> attendanceDB) {
        ObservableList<CongNhanThongKe> listCongNhanThongKe = null;
        Map<String, CongNhanThongKe> thongKeMap = new HashMap<>();
        for (NhanVienAttendance attendance : attendanceDB) {
            String maNV = attendance.getNhanVien().getMaNhanVien();
            thongKeMap.computeIfAbsent(maNV, k -> new CongNhanThongKe(attendance.getNhanVien().getMaNhanVien()));
            CongNhanThongKe thongKe =  thongKeMap.get(maNV);
            thongKe.setHoTen(attendance.getNhanVien().getHoTen());
            if (attendance.getLoaiChamCong().equals("CHECKOUT")){
                LocalTime checkin_time = attendance.getGioVao();
                ObservableList<NhanVienAttendance> checkin_day = attendanceDB.filtered(c -> c.getDay()==attendance.getDay()).filtered(c -> c.getLoaiChamCong().equals("CHECKIN"));
                if (checkin_day != null && checkin_day.size()>0){
                    checkin_time = checkin_day.get(0).getGioVao();
                }
                thongKe.setThoiGianLam(thongKe.getThoiGianLam()+Services.SubtractLocalTime(attendance.getGioVao(),checkin_time));
            }
        }
        return FXCollections.observableArrayList(thongKeMap.values());
    }
}





