import model.data.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Test {

    @org.junit.jupiter.api.Test
    public void doTest1() throws SQLException {
        KhoiHoc kh  = new KhoiHoc("Test");
        KhoiHoc.Insert(kh);

    }

    @org.junit.jupiter.api.Test
    public void doTest() throws SQLException {
        // Thêm vào như tham số bên cạnh
        // Bây giờ xem mã học sinh
        // Không có học sinh nhé @@!
        // Thêm bừa 1 thằng test

//        LopHoc lopHoc = new LopHoc("Test", 52,4, 52);
//        LopHoc.Insert(lopHoc);
//
//
//
//
//        PhanCong phanCong = new PhanCong(52, 4, 52, 2);
//        PhanCong.Insert(phanCong);

        Diem diem = new Diem(979, 1,2,3, "524522");
        Diem.Insert(diem);


    }
}
