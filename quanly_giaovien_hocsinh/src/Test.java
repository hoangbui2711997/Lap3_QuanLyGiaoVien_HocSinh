import model.data.*;
import model.repository.RepositoryGiaoVien;
import model.repository.RepositoryHocSinh;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Test {

    @org.junit.jupiter.api.Test
    public void doTest1() throws SQLException {
//        KhoiHoc kh  = new KhoiHoc("Test");
//        KhoiHoc.Insert(kh);
//        randomGiaoVien();
//        updateNgaySinhHS();
        Random rd = new Random();

        for (HocSinh hs : RepositoryHocSinh.getAll()) {
            int ngay = rd.nextInt(28) + 1;
            String ngayX = ngay < 10 ? ("0" + ngay) : ngay + "";
            int thang = rd.nextInt(12) + 1;
            boolean track = true;

            int nam = rd.nextInt(4) + Integer.parseInt(hs.getNgaySinh().substring(0, 4)) + 11;
//            int nam = rd.nextInt(20) + 2018 - 11 - 20;

            if(track == false)
            {
                continue;
            }

            String date = nam + "-"+ thang + "-" + ngayX;
//            System.out.println();
            System.out.println("Update HocSinh SET ngayNhapHoc = '" + date + "' WHERE MaHS = " + hs.getMaHS()
            );

        }
    }

    private void updateNgaySinhHS() {
        Random rd = new Random();

        for (HocSinh hs : RepositoryHocSinh.getAll()) {
            int ngay = rd.nextInt(28) + 1;
            String ngayX = ngay < 10 ? ("0" + ngay) : ngay + "";
            int thang = rd.nextInt(12) + 1;
            boolean track = true;

//            int nam = rd.nextInt(4) + Integer.parseInt(hs.getNgaySinh().substring(0, 4)) + 11;
            int nam = rd.nextInt(20) + 2018 - 11 - 20;

            if(track == false)
            {
                continue;
            }

            String date = nam + "-"+ thang + "-" + ngayX;
//            System.out.println();
            System.out.println("Update HocSinh SET NgaySinh = '" + date + "' WHERE MaHS = " + hs.getMaHS()
            );

        }
    }

    private void randomGiaoVien() {
        Random rd = new Random();
        for (GiaoVien gv : RepositoryGiaoVien.getAll()) {
            int ngay = rd.nextInt(28) + 1;
            String ngayX = ngay < 10 ? ("0" + ngay) : ngay + "";
            int thang = rd.nextInt(12) + 1;
            boolean track = true;
            int nam = 2019;
            while (nam > 2018) {
                if(Integer.parseInt(gv.getNgaySinh().substring(0, 4)) + 18 > 2018) {
                    track = false;
                    break;
                }
                nam = rd.nextInt(20) + Integer.parseInt(gv.getNgaySinh().substring(0, 4)) + 18;
            }

            if(track == false)
            {
                continue;
            }

            String date = nam + "-"+ thang + "-" + ngayX;
//            System.out.println();
            System.out.println("Update GiaoVien SET ngayGiaNhap = '" + date + "' WHERE MaGV = " + gv.getMaGV()
            );

        }
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
        PhanCong phanCong = new PhanCong(52, 4, 52, 2);
        PhanCong.Insert(phanCong);

//        Diem diem = new Diem(979, 1,2,3, "524522");
//        Diem.Insert(diem);


    }
}
