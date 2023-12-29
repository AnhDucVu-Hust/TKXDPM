package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class OnSiteAttendanceDB implements  IAttendanceDB{
    private ObservableList<NhanVienAttendance> listAttendance = FXCollections.observableArrayList();
    public void themNhanVien(NhanVienAttendance nhanVienAttendance) {
        listAttendance.add(nhanVienAttendance);
    }
    public OnSiteAttendanceDB(){
        themNhanVien(new NhanVienAttendance(
                new NhanVien("Vũ Anh Đức","00000","Trưởng đơn vị","Phòng Sản phẩm"),
                LocalDate.parse("2023-11-02"),
                LocalTime.parse("07:00:00"),
                "CHECKIN"

                )
        );
        themNhanVien(new NhanVienAttendance(
                        new NhanVien("Phạm Xuân Trường","00001","Trưởng đơn vị","Phòng Kĩ Thuật"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("17:30:00"),
                        "CHECKOUT"
                )
        );
        themNhanVien(new NhanVienAttendance(
                        new NhanVien("Vũ Văn Mạnh","00003","Kĩ sư","Nhân viên"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("07:00:00"),
                        "CHECKIN"
                )
        );
        themNhanVien(new NhanVienAttendance(
                        new NhanVien("Nguyễn Văn Mạnh","00004","Trưởng đơn vị","Nhân viên"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("17:30:00"),
                       "CHECKOUT"
                )
        );
    }
    public ObservableList<NhanVienAttendance> getListAttendance(){
        return listAttendance;
    }
}
