package model.data;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HocSinh extends RecursiveTreeObject<HocSinh> {
    private SimpleIntegerProperty maHS;
    private SimpleStringProperty gioiTinh;
    private SimpleStringProperty hoTen;
    private SimpleStringProperty ngaySinh;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty dienThoai;
    private SimpleStringProperty ngayTotNghiep;
    private SimpleStringProperty ngayNhapHoc;

    public SimpleStringProperty dienThoaiProperty() {
        return dienThoai;
    }

    public String getNgayTotNghiep() {
        return ngayTotNghiep.get();
    }

    public SimpleStringProperty ngayTotNghiepProperty() {
        return ngayTotNghiep;
    }

    public void setNgayTotNghiep(String ngayTotNghiep) {
        this.ngayTotNghiep.set(ngayTotNghiep);
    }

    public String getNgayNhapHoc() {
        return ngayNhapHoc.get();
    }

    public SimpleStringProperty ngayNhapHocProperty() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(String ngayNhapHoc) {
        this.ngayNhapHoc.set(ngayNhapHoc);
    }

    @Override
    public String toString() {
        return "HocSinh{" +
                "maHS=" + maHS +
                ", gioiTinh=" + gioiTinh +
                ", hoTen=" + hoTen +
                ", ngaySinh=" + ngaySinh +
                ", diaChi=" + diaChi +
                ", dienThoai=" + dienThoai +
                ", ngayTotNghiep=" + ngayTotNghiep +
                ", ngayNhapHoc=" + ngayNhapHoc +
                '}';
    }

    public static HocSinh getInstance(int maHS, String gioiTinh, String hoTen, String ngaySinh, String diaChi,
                                      String dienThoai, String ngayNhapHoc, String ngayTotNghiep) {
        return new HocSinh(maHS, gioiTinh, hoTen, ngaySinh, diaChi, dienThoai, ngayNhapHoc, ngayTotNghiep);
    }

    public HocSinh(String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.dienThoai = new SimpleStringProperty(dienThoai);
    }

    private HocSinh(int maHS, String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai,
                    String ngayNhapHoc, String ngayTotNghiep) {
        this.maHS = new SimpleIntegerProperty(maHS);
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.dienThoai = new SimpleStringProperty(dienThoai);
        this.ngayNhapHoc = new SimpleStringProperty(ngayNhapHoc);
        this.ngayTotNghiep = new SimpleStringProperty(ngayTotNghiep);
    }

    public int getMaHS() {
        return maHS.get();
    }

    public SimpleIntegerProperty maHSProperty() {
        return maHS;
    }

    public String getGioiTinh() {
        return gioiTinh.get();
    }

    public SimpleStringProperty gioiTinhProperty() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh.set(gioiTinh);
    }

    public String getHoTen() {
        return hoTen.get();
    }

    public SimpleStringProperty hoTenProperty() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public String getNgaySinh() {
        return ngaySinh.get();
    }

    public SimpleStringProperty ngaySinhProperty() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public String getDienThoai() {
        return dienThoai.get();
    }

    public SimpleStringProperty dienTHoaiProperty() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai.set(dienThoai);
    }

    static SearchDB searchDB = SearchDB.getQueryDB();
    public static class Search {
        private Search() {
        }

        public static HocSinh where(String where) throws SQLException {

            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM HocSinh WHERE " + where);
            resultSet.next();

            return searchDB.getHocSinh(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<HocSinh> getAll() throws SQLException {
            return searchDB.getDsHocSinh();
        }
    }
    static String statement = "";
    public static HocSinh Insert(HocSinh hocSinh) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("HocSinh");//? cái id này để tự tăng hay là điền????

            statement = "INSERT INTO HocSinh(GioiTinh, HoTen, NgaySinh, DiaChi, DienThoai) VALUES " +
                    "(" +
//                    hocSinh.getMa() + ", " +
//                    id + ", " +
                    "N'" + hocSinh.getGioiTinh() + "', " +
                    "'N" + hocSinh.getHoTen() + "', " +
                    "'" + hocSinh.getNgaySinh() + "', " +
                    "N'" + hocSinh.getDiaChi() + "', " +
                    "'" + hocSinh.getDienThoai() + "'" +
                    ")";
            System.out.println(statement);

            // wait form input
            // wait form input
            // wait form input

//            HocSinh.Update.where("MaHS = " + id, new HocSinh(id, hocSinh.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return returnHocSinh(id, hocSinh);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static HocSinh returnHocSinh(int id, HocSinh hocSinh) {
        return new HocSinh(id, hocSinh.getGioiTinh(), hocSinh.getHoTen(), hocSinh.getNgaySinh(),
                hocSinh.getDiaChi(), hocSinh.getDienThoai(), hocSinh.getNgayNhapHoc(), hocSinh.getNgayTotNghiep());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE HocSinh WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return HocSinh.Delete.where("MaHS = " + where);
        }
    }

    public static class Update {


        /**
         * @param where         DK - update
         * @param newHocSinh DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, HocSinh newHocSinh) throws SQLException {
            try {
                statement = "UPDATE HocSinh " +
                        "SET " +
//                        "MaHS = N'" + newHocSinh.getMaHS() + ", " +
//                        "MaPC = N'" + newHocSinh.getMaPC() + ", " +
                        "GioiTinh = N'" + newHocSinh.getGioiTinh() + "', " +
                        "HoTen = 'N" + newHocSinh.getHoTen() + "', " +
                        "NgaySinh = '" + newHocSinh.getNgaySinh() + "', " +
                        "DiaChi = N'" + newHocSinh.getDiaChi() + "', " +
                        "DienThoai = '" + newHocSinh.getDienThoai() +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String id, HocSinh gd) throws SQLException {
            return HocSinh.Update.where("MaHS = " + id, gd);
        }

        public static void updateNgayTotNghiep(String id, String ngayTotNghiep) {
            statement = "UPDATE HocSinh " +
                    "SET " +
//                        "MaHS = N'" + newHocSinh.getMaHS() + ", " +
//                        "MaPC = N'" + newHocSinh.getMaPC() + ", " +

                    "ngayTotNghiep = '" + ngayTotNghiep + "' " +
                    "WHERE MaHS = " + id;
        }
    }
}