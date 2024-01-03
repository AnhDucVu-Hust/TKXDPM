package com.btl_tkxdpm;
import com.btl_tkxdpm.AttendanceDB.IAttendanceDB;
import com.btl_tkxdpm.edit.EditController;
import com.btl_tkxdpm.entity.NhanVien;
import com.btl_tkxdpm.entity.NhanVienAttendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class EditControllerTest {

    private EditController editController;


    @Test
    public void testEditLoaiChamCong() {
        // Tạo dữ liệu cho việc kiểm thử
        NhanVienAttendance log = new NhanVienAttendance(1,new NhanVien("MNV001", "Nguyen Van A", "ChucDanhA","Trưởng phòng"), LocalDate.now(), LocalTime.now(), "CHECKIN");;


        // Gọi phương thức editAttendance để chỉnh sửa thông tin chấm công
        editController.tryEditLoaiChamCong(log,"CHECKOUT");

        // Kiểm tra xem thông tin đã được chỉnh sửa đúng chưa
        assertEquals(String.valueOf(log.getLoaiChamCong()), "CHECKOUT");


    }
}
