package com.btl_tkxdpm.add;

import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ImportExcel {
    public static ObservableList importExcel(String url) {
        ObservableList<NhanVien> listNhanVien = FXCollections.observableArrayList();
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
                String hoTen = row.getCell(1).getStringCellValue();
                String donVi = row.getCell(2).getStringCellValue();
                String chucDanh = row.getCell(3).getStringCellValue();
                listNhanVien.add(new NhanVien(hoTen,maNhanVien,donVi,chucDanh));
                Services.addNhanVien(new NhanVien(hoTen,maNhanVien,donVi,chucDanh));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;

    }
}