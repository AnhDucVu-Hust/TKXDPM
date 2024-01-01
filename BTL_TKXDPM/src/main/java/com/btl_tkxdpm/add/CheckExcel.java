package com.btl_tkxdpm.add;

import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.regex.Pattern;

public class CheckExcel {
    public static String checkErrorFile(String url) throws IOException {
        String regex = "^I\\d+$";
        Pattern pattern = Pattern.compile(regex);

        try (InputStream inputStream = new URL(url).openStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            boolean isFirstRow = true;
            Integer id =0;
            while (rowIterator.hasNext()) {
                id = id+1;
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String maNhanVien = row.getCell(0).getStringCellValue();
                String day = row.getCell(1).getStringCellValue();
                String gio = row.getCell(2).getStringCellValue();
                String loaiChamCong = row.getCell(3).getStringCellValue();
                if (!pattern.matcher(maNhanVien).matches()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi định dạng mã nhân viên");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Kiểm tra lại mã nhân viên ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }
                String ngay =  row.getCell(1).getStringCellValue();
                try {
                    LocalDate.parse(ngay);
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi ngày");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Kiểm tra lại cột Ngày ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }
                try {
                    LocalTime.parse(gio);
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi giờ");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Kiểm tra lại cột Giờ ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }

            }
        }
        return null;
    }
}
