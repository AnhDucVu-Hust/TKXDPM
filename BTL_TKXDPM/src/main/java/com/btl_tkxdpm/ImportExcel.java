package com.btl_tkxdpm;

import com.btl_tkxdpm.HumanResourceDB.IHRSubSystem;
import com.btl_tkxdpm.Services;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

public class ImportExcel {
    public static ObservableList importExcel(String url, IHRSubSystem dsNhanVien) {
        ObservableList<NhanVien> listNhanVien = dsNhanVien.getListNhanVien();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ObservableList<NhanVienAttendance> listChamCong = FXCollections.observableArrayList();
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
                LocalTime gio = LocalTime.parse(row.getCell(2).getStringCellValue(),timeFormatter);
                int id = (int)(row.getCell(4).getNumericCellValue());
                NhanVien nhanvien = null;
                for (NhanVien nv: listNhanVien){
                    if (nv.getMaNhanVien().equals(maNhanVien)){
                        System.out.println(nv.getMaNhanVien());
                        nhanvien=nv;
                        System.out.println("Mã nhân viên khớp");
                        break;
                    }
                }
                String loaiChamCong = row.getCell(3).getStringCellValue();
                if (nhanvien!=null){
                    listChamCong.add(new NhanVienAttendance(id,nhanvien,day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),gio,loaiChamCong));
                }

                //Services.addNhanVien(new NhanVien(hoTen,maNhanVien,donVi,chucDanh));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChamCong;

    }
}