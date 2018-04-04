package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GiaoVien {
    private SimpleIntegerProperty maGV;
    private SimpleStringProperty gioiTinh;
    private SimpleStringProperty hoTen;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty ngaySinh;
    private SimpleStringProperty dienThoai;
    private SimpleStringProperty CMND;
    private SimpleStringProperty matkhau;
    private SimpleIntegerProperty role;

    public int getRole() {
        return role.get();
    }

    public SimpleIntegerProperty roleProperty() {
        return role;
    }

    public void setRole(int role) {
        this.role.set(role);
    }

    public static GiaoVien getInstance(int maGV, String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau, int role) {
        return new GiaoVien(maGV, gioiTinh, hoTen, diaChi, ngaySinh, dienThoai, CMND, matkhau, role);
    }

    public GiaoVien(String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau, int role) {
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.dienThoai = new SimpleStringProperty(dienThoai);
        this.CMND = new SimpleStringProperty(CMND);
        this.matkhau = new SimpleStringProperty(matkhau);
        this.role = new SimpleIntegerProperty(role);
    }

    private GiaoVien(int maGV, String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau, int role) {
        this.maGV = new SimpleIntegerProperty(maGV);
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.dienThoai = new SimpleStringProperty(dienThoai);
        this.CMND = new SimpleStringProperty(CMND);
        this.matkhau = new SimpleStringProperty(matkhau);
        this.role = new SimpleIntegerProperty(role);
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "maGV=" + maGV +
                ", gioiTinh=" + gioiTinh +
                ", HoTen=" + hoTen +
                ", DiaChi=" + diaChi +
                ", NgaySinh=" + ngaySinh +
                ", DienThoai=" + dienThoai +
                ", CMND=" + CMND +
                ", matkhau=" + matkhau +
                ", role=" + role +
                '}';
    }

    public int getMaGV() {
        return maGV.get();
    }

    public SimpleIntegerProperty maGVProperty() {
        return maGV;
    }

    public void setMaGV(int maGV) {
        this.maGV.set(maGV);
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

    public String getDiaChi() {
        return diaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
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

    public String getDienThoai() {
        return dienThoai.get();
    }

    public SimpleStringProperty dienThoaiProperty() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai.set(dienThoai);
    }

    public String getCMND() {
        return CMND.get();
    }

    public SimpleStringProperty CMNDProperty() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND.set(CMND);
    }

    public String getMatkhau() {
        return matkhau.get();
    }

    public SimpleStringProperty matkhauProperty() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau.set(matkhau);
    }

    static SearchDB searchDB = SearchDB.getQueryDB();
    public static class Search {
        private Search() {
        }

        public static GiaoVien where(String where) throws SQLException {

            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM GiaoVien WHERE " + where);
            resultSet.next();

            return searchDB.getGV(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<GiaoVien> getAll() throws SQLException {
            return searchDB.getDsGiaoVien();
        }
    }
    static String statement = "";
    public static GiaoVien Insert(GiaoVien giaoVien) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("GiaoVien");

            statement = "INSERT INTO GiaoVien(maGV, gioiTinh, HoTen, DiaChi, NgaySinh, DienThoai, CMND, matkhau, role) VALUES " +
                    "(" +
                    giaoVien.getMaGV() + ", " +
                    "N'" + giaoVien.getGioiTinh() + "', " +
                    "N'" + giaoVien.getHoTen() + "', " +
                    "N'" + giaoVien.getDiaChi() + "', " +
                    "'" + giaoVien.getNgaySinh() + "', " +
                    "'" + giaoVien.getDienThoai() + "', " +
                    "'" + giaoVien.getCMND() + "', " +
                    "N'" + giaoVien.getMatkhau() + "', " +
                    giaoVien.getRole() +
                    ")";


            // wait form input
            // wait form input
            // wait form input

//            GiaoVien.Update.where("magd = " + id, new GiaoVien(id, diem.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return returnGiaoVien(id, giaoVien);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static GiaoVien returnGiaoVien(int id, GiaoVien giaoVien) {
        // int maGV, String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau, int role
        return new GiaoVien(id, giaoVien.getGioiTinh(), giaoVien.getHoTen(), giaoVien.getDiaChi(),
                giaoVien.getNgaySinh(), giaoVien.getDienThoai(), giaoVien.getCMND(), giaoVien.getMatkhau(),
                giaoVien.getRole());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE GIAOVIEN WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return GiaoVien.Delete.where("MaGV = " + where);
        }
    }

    public static class Update {


        /**
         * @param where         DK - update
         * @param newGiaoVien DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, GiaoVien newGiaoVien) throws SQLException {
            try {
                statement = "UPDATE GiaoVien " +
                        "SET " +
//                        "MaHS = N'" + newGiaoVien.getMaHS() + ", " +
//                        "MaPC = N'" + newGiaoVien.getMaPC() + ", " +
                        "MaGV = " + newGiaoVien.getMaGV() + ", " +
                        "GioiTinh = N'" + newGiaoVien.getMaGV() + "', " +
                        "HoTen = N'" + newGiaoVien.getHoTen() + "', " +
                        "DiaChi = N'" + newGiaoVien.getDiaChi() + "', " +
                        "NgaySinh = '" + newGiaoVien.getNgaySinh() + "', " +
                        "DienThoai = '" + newGiaoVien.getDienThoai() + "', " +
                        "CMND = '" + newGiaoVien.getCMND() + "', " +
                        "matkhau = N'" + newGiaoVien.getMatkhau() + "', " +
                        "role = " + newGiaoVien.getRole() + " " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, GiaoVien gv) throws SQLException {
            return GiaoVien.Update.where("MaGV = " + where, gv);
        }
    }
}