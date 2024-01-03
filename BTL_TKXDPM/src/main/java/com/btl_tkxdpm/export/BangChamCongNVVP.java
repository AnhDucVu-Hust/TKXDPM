package com.btl_tkxdpm.export;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.entity.CongNhanThongKe;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import com.btl_tkxdpm.entity.NhanVienVanPhongThongKe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BangChamCongNVVP {
    public static ObservableList<NhanVienVanPhongThongKe> getBangThongKe(ObservableList<NhanVienAttendance> attendanceDB) {
        Map<String, NhanVienVanPhongThongKe> thongKeMap = new HashMap<>();
        for (NhanVienAttendance attendance : attendanceDB) {
            String maNV = attendance.getNhanVien().getMaNhanVien();
            thongKeMap.computeIfAbsent(maNV, k -> new NhanVienVanPhongThongKe(attendance.getNhanVien().getMaNhanVien()));
            NhanVienVanPhongThongKe thongKe =  thongKeMap.get(maNV);
            thongKe.setHoTen(attendance.getNhanVien().getHoTen());
            thongKe.addNhungNgayDiLam(attendance.getDay());
            if (attendance.getLoaiChamCong().equals("CHECKOUT")){
                if (attendance.getGioVao().isBefore(LocalTime.of(17,30))){
                    double duration = Services.SubtractLocalTime(LocalTime.of(17,30),attendance.getGioVao());
                    thongKe.setSoGioDiMuonVeSom(thongKe.getSoGioDiMuonVeSom()-duration);
                }
            }
            else {
                if (attendance.getGioVao().isAfter(LocalTime.of(8,0))){
                    double duration = Services.SubtractLocalTime(LocalTime.of(8,0),attendance.getGioVao());
                    thongKe.setSoGioDiMuonVeSom(thongKe.getSoGioDiMuonVeSom()+duration);
                }

            }
        }
        return FXCollections.observableArrayList(thongKeMap.values());
    }
}
