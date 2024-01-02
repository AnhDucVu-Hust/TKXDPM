package com.btl_tkxdpm.export;
import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.AttendanceDB.OnSiteAttendanceDB;
import com.btl_tkxdpm.SwitchScreener;
import com.btl_tkxdpm.entity.CongNhanThongKe;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import com.btl_tkxdpm.entity.NhanVienVanPhongThongKe;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;

public class ExportController implements Initializable {
    private IAttendanceDB attendanceDB;

    public IAttendanceDB getAttendanceDB() {
        return attendanceDB;
    }

    public void setAttendanceDB(IAttendanceDB attendanceDB) {
        this.attendanceDB = attendanceDB;
    }

    @FXML
    private ChoiceBox<String> donViSearch;

    @FXML
    private ChoiceBox<String> loaiNhanSu;


    @FXML
    private TableColumn<NhanVienVanPhongThongKe, String> tableDiMuonVeSom;
    @FXML
    private TableColumn<NhanVienVanPhongThongKe, String> tableTongSoBuoiLam;

    @FXML
    private TableColumn<NhanVienVanPhongThongKe, String> tableMaNV;

    @FXML
    private TableColumn<NhanVienVanPhongThongKe, String> tableTen;
    @FXML
    private TableColumn<NhanVienVanPhongThongKe, String> tableNgay;

    @FXML
    private TableView tableNhanVien;
    @FXML
    private TableView tableCongNhan;
    @FXML
    private TableColumn<CongNhanThongKe,String> tableTenCN;
    @FXML
    private TableColumn<CongNhanThongKe,String> tableMaNVCN;
    @FXML
    private TableColumn<CongNhanThongKe,String> tableTongGioLamCN;
    @FXML
    private TableColumn<CongNhanThongKe, String> tableTangCaCN;
    @FXML
    private ChoiceBox<String> thangSearch;


    @FXML
    void clickQuayLai(MouseEvent event) {
        SwitchScreener.switchScreen("/com/btl_tkxdpm/manHinhChinh.fxml");
    }

    @FXML
    void clickXacNhan(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Định dạng báo cáo");
        alert.setHeaderText("Bạn mốn xuất báo cáo dưới định dạng nào:");

        // Create two buttons
        ButtonType buttonTypeExcel=new ButtonType("Excel");
        ButtonType buttonTypeCSV = new ButtonType("CSV");

        // Set buttons in the alert
        alert.getButtonTypes().setAll(buttonTypeExcel, buttonTypeCSV);

        // Show the alert and wait for the user's response
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeCSV) {
                System.out.println("User clicked CSV");
                //String donVi = donViSearch.getValue().toString();
                String thang = thangSearch.getValue().toString();
                CsvExporter.exportToCsv(attendanceDB.getListAttendance(), "Báo cáo "+thang+".csv" );
                // Add your code for handling "Yes" option
            } else if (response == buttonTypeExcel) {
                System.out.println("User clicked No");
                // Add your code for handling "No" option
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->
        {ObservableList<CongNhanThongKe> listCongNhan = BangChamCongCongNhan.getBangThongKe(attendanceDB.getListCongNhanAttendance());
            Set<String> uniqueDonVi = attendanceDB.getListAttendance().stream()
                    .map(c -> c.getNhanVien().getDonVi())
                    .collect(Collectors.toSet());
            uniqueDonVi.add("Tất cả");
            donViSearch.setItems(FXCollections.observableArrayList(uniqueDonVi));
            donViSearch.setValue("Tất cả");
            loaiNhanSu.setOnAction(event -> {
            String selectedOption = loaiNhanSu.getValue();
            if (selectedOption.equals("Công nhân")){
                tableCongNhan.setVisible(true);
                tableNhanVien.setVisible(false);
            }
        });
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        tableCongNhan.setItems(listCongNhan);
        tableNhanVien.setItems(attendanceDB.getListNhanVienAttendace());
        tableTenCN.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHoTen()));
        //tableChucDanh.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNhanVien().getChucDanh()));
        tableMaNVCN.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMaNhanVien()));
        //tableNgay.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDay().format(dateFormatter)));
        thangSearch.setItems(FXCollections.observableArrayList(
                "Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        ));
        tableTongGioLamCN.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getThoiGianLam())));
        tableTangCaCN.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getThoiGianTangCa())));
        thangSearch.setValue("Tất cả");
        loaiNhanSu.setItems(FXCollections.observableArrayList(
                "Nhân viên văn phòng", "Công nhân"
        ));
        loaiNhanSu.setValue("Công nhân");
    });
    }
    @FXML
    void clickSearch(MouseEvent event){
        String thang = thangSearch.getValue();
        String donVi = donViSearch.getValue();
        if (thang.equals("Tất cả") && donVi.equals("Tất cả") ){
            System.out.println("Do nothing");
        }
        else if (donVi.equals("Tất cả")){
            Month month = Month.of(Integer.parseInt(thang));
           tableCongNhan.setItems(BangChamCongCongNhan.getBangThongKe(attendanceDB.getListAttendance().filtered(c -> c.getDay().getMonth() == month)));
        }
        else if (thang.equals("Tất cả")){
            tableCongNhan.setItems(BangChamCongCongNhan.getBangThongKe(attendanceDB.getListAttendance().filtered(c -> c.getNhanVien().getDonVi().equals(donVi))));
        }
        else{
            tableCongNhan.setItems(BangChamCongCongNhan.getBangThongKe(attendanceDB.getListAttendance().filtered(c -> c.getNhanVien().getDonVi().equals(donVi)).filtered(c -> c.getDay().getMonth() == Month.of(Integer.parseInt(thang)))));
        }

    }
}
