package com.btl_tkxdpm;

import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.HumanResourceDB.IHRSubSystem;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

public class CheckExcel {
    public static String checkErrorFile(String url, IHRSubSystem hrSubSystem, IAttendanceDB attendanceDB) throws IOException {
        String regex = "^I\\d+$";
        Pattern pattern = Pattern.compile(regex);

        try (FileInputStream inputStream = new FileInputStream(new File(url))) {
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
                Date day = row.getCell(1).getDateCellValue();
                String gio = row.getCell(2).getStringCellValue();
                String loaiChamCong = row.getCell(3).getStringCellValue();
                int ID = (int)row.getCell(4).getNumericCellValue();
                if (attendanceDB.getListAttendance().filtered(c->c.getId()==ID).size()>0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    System.out.println(ID);
                    alert.setTitle("Lỗi trùng mã id chấm công ");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Kiểm tra lại ID chấm công ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }
                if (!pattern.matcher(maNhanVien).matches()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi định dạng mã nhân viên");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Kiểm tra lại mã nhân viên ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }
                if (hrSubSystem.getNhanVienById(maNhanVien) ==null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi mã nhân viên");
                    alert.setHeaderText("WARNING");
                    alert.setContentText("Không tồn tại nhân viên ở hàng "+id);
                    // Show the alert
                    alert.showAndWait();
                    return null;
                }

                try {
                    LocalDate ngay =  row.getCell(1).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return "complete";
    }
}
