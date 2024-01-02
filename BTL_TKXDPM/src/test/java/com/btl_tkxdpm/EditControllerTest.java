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

    @BeforeEach
    public void setUp() {
        // Tạo một đối tượng EditController để kiểm thử
        editController = new EditController();

        // Sử dụng Mockito để tạo mock cho IAttendanceDB
        IAttendanceDB mockAttendanceDB = mock(IAttendanceDB.class);

        // Thiết lập mockAttendanceDB cho editController
        editController.setAttendanceDB(mockAttendanceDB);
    }

    @Test
    public void testEditAttendance() {
        // Tạo dữ liệu cho việc kiểm thử
        NhanVienAttendance log = new NhanVienAttendance(1,new NhanVien("MNV001", "Nguyen Van A", "ChucDanhA","Trưởng phòng"), LocalDate.now(), LocalTime.now(), "ChamCongA");;
        LocalDate ngay = LocalDate.now();
        LocalTime gio = LocalTime.now();
        String loaiChamCong = "ChamCongType";

        // Gọi phương thức editAttendance để chỉnh sửa thông tin chấm công
        editController.editAttendance(log, ngay, gio, loaiChamCong);

        // Kiểm tra xem thông tin đã được chỉnh sửa đúng chưa
        assertEquals(ngay, log.getDay());
        assertEquals(gio, log.getGioVao());
        assertEquals(loaiChamCong, log.getLoaiChamCong());

    }
}
