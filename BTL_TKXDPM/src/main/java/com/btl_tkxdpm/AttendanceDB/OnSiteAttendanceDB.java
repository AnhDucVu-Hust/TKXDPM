package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class OnSiteAttendanceDB implements  IAttendanceDB{
    private ObservableList<NhanVienAttendance> listAttendance = FXCollections.observableArrayList();
    private ObservableList<NhanVien> listNhanVien;
    public void themNhanVien(NhanVienAttendance nhanVienAttendance) {
        listAttendance.add(nhanVienAttendance);
    }
    public OnSiteAttendanceDB(){
        themNhanVien(new NhanVienAttendance(1,
                new NhanVien("Vũ Anh Đức","00000","Nhân viên văn phòng","Phòng Sản phẩm"),
                LocalDate.parse("2023-11-02"),
                LocalTime.parse("07:00:00"),
                "CHECKIN"

                )
        );
        themNhanVien(new NhanVienAttendance(2,
                        new NhanVien("Phạm Xuân Trường","00001","Công nhân","Phòng Kĩ Thuật"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("17:30:00"),
                        "CHECKOUT"
                )
        );
        themNhanVien(new NhanVienAttendance(3,
                        new NhanVien("Vũ Văn Mạnh","00003","Công nhân","Nhân viên"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("07:00:00"),
                        "CHECKIN"
                )
        );
        themNhanVien(new NhanVienAttendance(4,
                        new NhanVien("Nguyễn Văn Mạnh","00004","Nhân viên văn phòng","Nhân viên"),
                        LocalDate.parse("2023-11-02"),
                        LocalTime.parse("17:30:00"),
                       "CHECKOUT"
                )
        );
    }
    public ObservableList<NhanVienAttendance> getListAttendance(){
        return listAttendance;
    }

    @Override
    public ObservableList<NhanVienAttendance> getListCongNhanAttendance() {
        return listAttendance.filtered(c -> c.getNhanVien().getChucDanh().equals("Công nhân"));
    }

    @Override
    public ObservableList<NhanVienAttendance> getListNhanVienAttendace() {
        return listAttendance.filtered(c -> c.getNhanVien().getChucDanh().equals("Nhân viên văn phòng"));
    }

    @Override
    public void addAttendance(ObservableList<NhanVienAttendance> listChamCong) {
        listAttendance.addAll(listChamCong);
    }

    @Override
    public void editAttendance(NhanVienAttendance editedAttendance) {
        int id = editedAttendance.getId();
        for (NhanVienAttendance attendance: listAttendance){
            if (attendance.getId() == id){
                attendance = editedAttendance;
            }
        }

    }

    @Override
    public ObservableList<NhanVienAttendance> queryByTenOrID(ObservableList<NhanVienAttendance> listChamCong, String query) {
        if (query.equals("")){
            return listChamCong;
        }
        ObservableList<NhanVienAttendance> listFiltered = listChamCong.filtered(c -> (c.getNhanVien().getHoTen().contains(query) || c.getNhanVien().getMaNhanVien().contains(query)));
       return listFiltered;
    }



}
