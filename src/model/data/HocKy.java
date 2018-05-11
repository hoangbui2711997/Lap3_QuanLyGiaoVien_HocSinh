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

public class HocKy extends RecursiveTreeObject<HocKy> {
    private SimpleIntegerProperty maHK;
    private SimpleStringProperty tenHK;
    private SimpleIntegerProperty thangBatDau;
    private SimpleIntegerProperty ngayBatDau;
    private SimpleIntegerProperty thangKetThuc;
    private SimpleIntegerProperty ngayKetThuc;

    public int getNgayKetThuc() {
        return ngayKetThuc.get();
    }

    public static HocKy getInstance(int maHK, String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        return new HocKy(maHK, tenHK, thangBatDau, ngayBatDau, thangKetThuc, ngayKetThuc);
    }

    public HocKy(String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        this.tenHK = new SimpleStringProperty(tenHK);
        this.thangBatDau = new SimpleIntegerProperty(thangBatDau);
        this.ngayBatDau = new SimpleIntegerProperty(ngayBatDau);
        this.thangKetThuc = new SimpleIntegerProperty(thangKetThuc);
        this.ngayKetThuc = new SimpleIntegerProperty(ngayKetThuc);
    }

    private HocKy(int maHK, String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        this.maHK = new SimpleIntegerProperty(maHK);
        this.tenHK = new SimpleStringProperty(tenHK);
        this.thangBatDau = new SimpleIntegerProperty(thangBatDau);
        this.ngayBatDau = new SimpleIntegerProperty(ngayBatDau);
        this.thangKetThuc = new SimpleIntegerProperty(thangKetThuc);
        this.ngayKetThuc = new SimpleIntegerProperty(ngayKetThuc);
    }

    @Override
    public String toString() {
        return "HocKy{" +
                "maHK=" + maHK +
                ", tenHK=" + tenHK +
                ", thangBatDau=" + thangBatDau +
                ", ngayBatDau=" + ngayBatDau +
                ", thangKetThuc=" + thangKetThuc +
                ", ngayKetThuc=" + ngayKetThuc +
                '}';
    }

    public int getMaHK() {
        return maHK.get();
    }

    public SimpleIntegerProperty maHKProperty() {
        return maHK;
    }

    public void setMaHK(int maHK) {
        this.maHK.set(maHK);
    }

    public String getTenHK() {
        return tenHK.get();
    }

    public SimpleStringProperty tenHKProperty() {
        return tenHK;
    }

    public void setTenHK(String tenHK) {
        this.tenHK.set(tenHK);
    }

    public int getThangBatDau() {
        return thangBatDau.get();
    }

    public SimpleIntegerProperty thangBatDauProperty() {
        return thangBatDau;
    }

    public void setThangBatDau(int thangBatDau) {
        this.thangBatDau.set(thangBatDau);
    }

    public int getNgayBatDau() {
        return ngayBatDau.get();
    }

    public SimpleIntegerProperty ngayBatDauProperty() {
        return ngayBatDau;
    }

    public void setNgayBatDau(int ngayBatDau) {
        this.ngayBatDau.set(ngayBatDau);
    }

    public int getThangKetThuc() {
        return thangKetThuc.get();
    }

    public SimpleIntegerProperty thangKetThucProperty() {
        return thangKetThuc;
    }

    public void setThangKetThuc(int thangKetThuc) {
        this.thangKetThuc.set(thangKetThuc);
    }

    public int setNgayKetThuc() {
        return ngayKetThuc.get();
    }

    public SimpleIntegerProperty ngayKetThucProperty() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(int ngayKetThuc) {
        this.ngayKetThuc.set(ngayKetThuc);
    }

    static SearchDB searchDB = SearchDB.getQueryDB();

    public static class Search {
        private Search() {
        }

        public static HocKy where(String where) throws SQLException {

            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM HocKy WHERE " + where);
            resultSet.next();

            return searchDB.getHocKy(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<HocKy> getAll() throws SQLException {
            return searchDB.getDsHocKy();
        }
    }

    static String statement = "";

    public static HocKy Insert(HocKy hocKy) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("HocKy");

            statement = "INSERT INTO HocKy(MaHK,TenHK,ThangBatDau,NgayBatDau,ThangKetThuc,NgayKetThuc) VALUES (" +
//                    hocKy.getMa() + ", " +
                    id               + ", " +
                    "N'" + hocKy.getTenHK() + "', " +
                    hocKy.getThangBatDau() + ", " +
                    hocKy.getNgayBatDau() + ", " +
                    hocKy.getThangKetThuc() + ", " +
                    hocKy.getNgayKetThuc() +
                    ")";


            // wait form input
            // wait form input
            // wait form input

//            HocKy.Update.where("magd = " + id, new HocKy(id, diem.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return returnHocKy(id, hocKy);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static HocKy returnHocKy(int id, HocKy hocKy) {
        return new HocKy(id, hocKy.getTenHK(), hocKy.getThangBatDau(), hocKy.getNgayBatDau(), hocKy.getThangKetThuc(), hocKy.getNgayKetThuc());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE HocKy WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return HocKy.Delete.where("MaHK = " + where);
        }
    }

    public static class Update {


        /**
         * @param where    DK - update
         * @param newHocKy DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, HocKy newHocKy) throws SQLException {
            try {
                statement = "UPDATE HocKy " +
                        "SET " +
//                        "MaHS = N'" + newHocKy.getMaHS() + ", " +
//                        "MaPC = N'" + newHocKy.getMaPC() + ", " +
//                        "MaHK = " + newHocKy.getMaHK()         + ", " +
                        "TenHK = " + "N'" + newHocKy.getTenHK() + "', " +
                        "ThangBatDau = " + newHocKy.getThangBatDau()  + ", " +
                        "NgayBatDau = " + newHocKy.getNgayBatDau()   + ", " +
                        "ThangKetThuc = " + newHocKy.getThangKetThuc() + ", " +
                        "NgayKetThuc = " + newHocKy.getNgayKetThuc()  +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, HocKy hocKy) throws SQLException {
            return HocKy.Update.where("MaHK = " + where, hocKy);
        }
    }
}