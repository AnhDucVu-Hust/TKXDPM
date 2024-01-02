package com.btl_tkxdpm.employee;

import com.btl_tkxdpm.HumanResourceDB.IHRSubSystem;
import com.btl_tkxdpm.HumanResourceDB.OnSiteHRSubSystem;
import com.btl_tkxdpm.Services;
import com.btl_tkxdpm.SwitchScreener;
import com.btl_tkxdpm.entity.NhanVien;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    private IHRSubSystem hrSubSystem;
    @FXML
    private Button donViSearch;

    @FXML
    private TextField donViTextField;

    @FXML
    private Button maNVSearch;

    @FXML
    private TextField maNVTextField;

    @FXML
    private TableColumn<NhanVien, String> tableChucDanh;

    @FXML
    private TableColumn<NhanVien, String> tableDonVi;

    @FXML
    private TableColumn<NhanVien, String> tableMaNV;

    @FXML
    private TableView<NhanVien> tableNhanVien;

    @FXML
    private TableColumn<NhanVien, String> tableTen;

    @FXML
    private ImageView tenSearch;

    @FXML
    private TextField tenTextField;

    @FXML
    void clickQuayLai(MouseEvent event) {
        SwitchScreener.switchScreen("manHinhChinh.fxml");
    }

    @FXML
    void clickXacNhan(MouseEvent event) {

    }
    @FXML
    void clickMaNVSearch(MouseEvent event){
        String maNV = maNVSearch.getText();
        ObservableList listnhanVien = Services.queryNhanVienBangMa(maNV);


    }
    @FXML
    void clickTenSearch(MouseEvent event){

    }
    @FXML
    void clickDonViSearch(MouseEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hrSubSystem = new OnSiteHRSubSystem();
        tableNhanVien.setItems(hrSubSystem.getListNhanVien());
        tableTen.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHoTen()));
        tableChucDanh.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getChucDanh()));
        tableMaNV.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMaNhanVien()));
        tableDonVi.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDonVi()));
    }

}
