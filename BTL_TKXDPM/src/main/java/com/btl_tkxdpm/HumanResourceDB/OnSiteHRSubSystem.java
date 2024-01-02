package com.btl_tkxdpm.HumanResourceDB;

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

public class OnSiteHRSubSystem implements IHRSubSystem{
    private ObservableList<NhanVien> listNhanVien = FXCollections.observableArrayList();
    public OnSiteHRSubSystem(){
        addNhanVien("/Users/vuanhduc/Downloads/TKXDPM/BTL_TKXDPM/NhanVien.xlsx");
    }
    @Override
    public ObservableList<NhanVien> getListNhanVien() {
        return listNhanVien;
    }

    public void addNhanVien(String url) {
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
                listNhanVien.add(new NhanVien(hoTen,maNhanVien,chucDanh,donVi));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public NhanVien getNhanVienById(String maNhanVien){
        for (NhanVien nv : listNhanVien){
            if (nv.getMaNhanVien().equals(maNhanVien)){
                return  nv;
            }
        }
        return null;
    }
}
