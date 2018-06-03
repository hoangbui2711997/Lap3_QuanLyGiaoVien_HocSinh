import model.data.*;
import model.database.DB_Connection;
import model.repository.*;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class Test {

    @org.junit.jupiter.api.Test
    public void doTest1() throws SQLException {
//        KhoiHoc kh  = new KhoiHoc("Test");
//        KhoiHoc.Insert(kh);
//        randomGiaoVien();
//        updateNgaySinhHS();
//        updateNgayNhapHocHS();
//        for (int i = 1; i <= 35; i++) {
//            NamHoc.Insert(new NamHoc(2017, 2018, "Năm thứ 36"));
//        }
//        addLopHoc();
//        addXepLop();
//        addMonHoc();
//        addPhanCong();
//        addDiem();
//        testThongKe(nam);
//        addDiem();
    }

    private int testThongKe(String nam) throws SQLException {
        CallableStatement callableStatement1 = DB_Connection.getConnection()
                .prepareCall("select [dbo].func_LaySoLuongHocSinhChuaDatNamXXXX("+ 1976 +") as 'Soluong'");

        CallableStatement callableStatement2 = DB_Connection.getConnection()
                .prepareCall("select [dbo].func_LaySoLuongHocSinhGioiNamXXXX("+ nam +") as 'Soluong'");

        CallableStatement callableStatement3 = DB_Connection.getConnection()
                .prepareCall("select [dbo].func_LaySoLuongHocSinhKhaNamXXXX("+ nam +") as 'Soluong'");

        CallableStatement callableStatement4 = DB_Connection.getConnection()
                .prepareCall("select [dbo].func_LaySoLuongHocSinhTrungBinhNamXXXX("+ nam +") as 'Soluong'");

        ResultSet resultSet = callableStatement1.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    private void addDiem() throws SQLException {
        List<HocSinh> hocSinhList = RepositoryHocSinh.getAll();
        List<PhanCong> phanCongList = RepositoryPhanCong.getAll();
        Random rd = new Random();


        for (int i = 0; i < 30000; i++) {
            int maHS = hocSinhList.get(rd.nextInt(hocSinhList.size())).getMaHS();
            String maPC = phanCongList.get(rd.nextInt(phanCongList.size())).getMaPC();

            int diemHS1 = rd.nextInt(10) + 1;
            int diemHS2 = rd.nextInt(10) + 1;
            int diemHS3 = rd.nextInt(10) + 1;

            Diem diem = new Diem(maHS, diemHS1, diemHS2, diemHS3, maPC);
            Diem.Insert(diem);
        }
    }

    private void addPhanCong() throws SQLException {
        List<MonHoc> monHocList = RepositoryMonHoc.getAll();
        List<GiaoVien> giaoVienList = RepositoryGiaoVien.getAll();
        List<LopHoc> lopHocList = RepositoryLopHoc.getAll();
        List<HocKy> hocKyList = RepositoryHocKy.getAll();

        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            int maMH = monHocList.get(
                    random.nextInt(monHocList.size())
            ).getMaMH();

            int maGV = giaoVienList.get(
                    random.nextInt(giaoVienList.size())
            ).getMaGV();

            int maLopHoc = lopHocList.get(
                    random.nextInt(lopHocList.size())
            ).getMaLH();

            int maHocKy = hocKyList.get(
                    random.nextInt(hocKyList.size())
            ).getMaHK();

            PhanCong phanCong = new PhanCong(maMH, maGV, maLopHoc, maHocKy);
            PhanCong.Insert(phanCong);
        }
    }

    private void addMonHoc() throws SQLException {
        MonHoc monHoc = new MonHoc("Tin Học 10 cơ bản");
        MonHoc monHoc1 = new MonHoc("Tin Học 10 nâng cao");
        MonHoc monHoc2 = new MonHoc("Tin Học 11 cơ bản");
        MonHoc monHoc3 = new MonHoc("Tin Học 11 nâng cao");
        MonHoc monHoc4 = new MonHoc("Tin Học 12 cơ bản");
        MonHoc monHoc5 = new MonHoc("Tin Học 12 nâng cao");

        MonHoc.Insert(monHoc);
        MonHoc.Insert(monHoc1);
        MonHoc.Insert(monHoc2);
        MonHoc.Insert(monHoc3);
        MonHoc.Insert(monHoc4);
        MonHoc.Insert(monHoc5);
    }

    private void addXepLop() throws SQLException {
        List<HocSinh> hocSinhs = RepositoryHocSinh.getAll();
        List<LopHoc> lopHocs = RepositoryLopHoc.getAll();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            XepLop xepLop = new XepLop(
                    hocSinhs.get(random.nextInt(hocSinhs.size())).getMaHS(),
                    lopHocs.get(random.nextInt(lopHocs.size())).getMaLH()
            );
            XepLop.Insert(xepLop);
        }
    }

    private void addLopHoc() throws SQLException {
        char a = 'C';
        List<NamHoc> namHocs = RepositoryNamHoc.getAll();
        List<GiaoVien> giaoViens = RepositoryGiaoVien.getAll();
        List<KhoiHoc> khoiHocs = RepositoryKhoiHoc.getAll();

        Random random = new Random();

        for (int i = 10; i < 12; i++) {
            for (int j = 1; j < 33; j++) {
                String tenLop = "Lớp " + i + a + j;
                int maNamHoc = namHocs.get(random.nextInt(namHocs.size())).getMaNH();
                int maGiaoVien = giaoViens.get(random.nextInt(giaoViens.size())).getMaGV();
                int maKhoiHoc = khoiHocs.get(random.nextInt(khoiHocs.size())).getMaKH();
                LopHoc lopHoc = new LopHoc(tenLop, maNamHoc, maGiaoVien, maKhoiHoc);
                LopHoc.Insert(lopHoc);
            }
            a++;
        }
    }

    private void updateNgayNhapHocHS() {
        Random rd = new Random();

        for (HocSinh hs : RepositoryHocSinh.getAll()) {
            int ngay = rd.nextInt(28) + 1;
            String ngayX = ngay < 10 ? ("0" + ngay) : ngay + "";
            int thang = rd.nextInt(12) + 1;
            boolean track = true;

            int nam = rd.nextInt(4) + Integer.parseInt(hs.getNgaySinh().substring(0, 4)) + 11;
//            int nam = rd.nextInt(20) + 2018 - 11 - 20;

            if (track == false) {
                continue;
            }

            String date = nam + "-" + thang + "-" + ngayX;
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

            if (track == false) {
                continue;
            }

            String date = nam + "-" + thang + "-" + ngayX;
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
                if (Integer.parseInt(gv.getNgaySinh().substring(0, 4)) + 18 > 2018) {
                    track = false;
                    break;
                }
                nam = rd.nextInt(20) + Integer.parseInt(gv.getNgaySinh().substring(0, 4)) + 18;
            }

            if (track == false) {
                continue;
            }

            String date = nam + "-" + thang + "-" + ngayX;
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

//        lophoc lopHoc = new lophoc("Test", 52,4, 52);
//        lophoc.Insert(lopHoc);
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
