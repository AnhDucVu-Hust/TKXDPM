package com.btl_tkxdpm.AttendanceDB;

import com.btl_tkxdpm.HumanResourceDB.OnSiteHRSubSystem;
import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

public class OnSiteAttendanceDB implements  IAttendanceDB{
    private ObservableList<NhanVienAttendance> listAttendance = FXCollections.observableArrayList();
    private ObservableList<NhanVien> listNhanVien = new OnSiteHRSubSystem().getListNhanVien();
    public void themNhanVien(NhanVienAttendance nhanVienAttendance) {
        listAttendance.add(nhanVienAttendance);
    }
    public OnSiteAttendanceDB() {
        String url = "/Users/vuanhduc/Downloads/TKXDPM/BTL_TKXDPM/src/main/java/com/btl_tkxdpm/cham_cong_data (2).xlsx";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try (FileInputStream inputStream = new FileInputStream(new File(url))) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            boolean isFirstRow = true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String maNhanVien = row.getCell(0).getStringCellValue();
                Date day = row.getCell(1).getDateCellValue();
                LocalTime gio = LocalTime.parse(row.getCell(2).getStringCellValue(), timeFormatter);
                double id = row.getCell(4).getNumericCellValue();
                NhanVien nhanvien = null;
                for (NhanVien nv : listNhanVien) {
                    if (nv.getMaNhanVien().equals(maNhanVien)) {
                        System.out.println(nv.getMaNhanVien());
                        nhanvien = nv;
                        System.out.println("Mã nhân viên khớp");
                        break;
                    }
                }
                String loaiChamCong = row.getCell(3).getStringCellValue();
                if (nhanvien != null) {
                    themNhanVien(new NhanVienAttendance((int) id, nhanvien, day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), gio, loaiChamCong));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
