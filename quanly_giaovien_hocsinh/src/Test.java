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
        NamHoc nh = new NamHoc(LocalDate.now().getYear(), LocalDate.now().getYear() + 1, "Test");
        NamHoc.Insert(nh);

        HocSinh hs = new HocSinh("Nam", "I am Test",
                Date.valueOf(LocalDate.now()).toString(), "Dia chi Test", "123456789");
        HocSinh.Insert(hs);

        HocKy hocKy = new HocKy("Hoc Ky 1", 1,1,2,2);
        HocKy.Insert(hocKy);

        MonHoc mh = new MonHoc("Test");
        MonHoc.Insert(mh);
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
