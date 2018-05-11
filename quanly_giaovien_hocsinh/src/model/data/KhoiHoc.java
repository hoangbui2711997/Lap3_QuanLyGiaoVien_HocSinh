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

public class KhoiHoc extends RecursiveTreeObject<KhoiHoc> {
    private SimpleIntegerProperty maKH;
    private SimpleStringProperty tenKH;

    private KhoiHoc(int maKH, String tenKH) {
        this.maKH = new SimpleIntegerProperty(maKH);
        this.tenKH = new SimpleStringProperty(tenKH);
    }

    public static KhoiHoc getInstance(int maKH, String tenKH) {
        return new KhoiHoc(maKH, tenKH);
    }

    public KhoiHoc(String tenKH) {
        this.tenKH = new SimpleStringProperty(tenKH);;
    }

    public int getMaKH() {
        return maKH.get();
    }

    public SimpleIntegerProperty maKHProperty() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH.get();
    }

    public SimpleStringProperty tenKHProperty() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH.set(tenKH);
    }

    @Override
    public String toString() {
        return "KhoiHoc{" +
                "maKH=" + maKH +
                ", tenKH=" + tenKH +
                '}';
    }

    static SearchDB searchDB = SearchDB.getQueryDB();
    public static class Search {
        private Search() {
        }

        public static KhoiHoc where(String where) throws SQLException {

            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM KhoiHoc WHERE " + where);
            resultSet.next();

            return searchDB.getKhoiHoc(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<KhoiHoc> getAll() throws SQLException {
            return searchDB.getDsKhoiHoc();
        }
    }
    static String statement = "";
    public static KhoiHoc Insert(KhoiHoc khoiHoc) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("KhoiHoc");

            statement = "INSERT INTO KhoiHoc(TenKH) VALUES " +
                    "(" +
//                    khoiHoc.getMa() + ", " +
//                    khoiHoc.getMaKH() + ", " +
//                    id                + ", " +
                    "N'" + khoiHoc.getTenKH() + "' " +
                    ")";

            System.out.println(statement);
            // wait form input
            // wait form input
            // wait form input

            InsertDB.getInstance().insertCommand(statement);
            return returnKhoiHoc(id, khoiHoc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static KhoiHoc returnKhoiHoc(int id, KhoiHoc khoiHoc) {
        return new KhoiHoc(id, khoiHoc.getTenKH());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE KhoiHoc WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return KhoiHoc.Delete.where("MaKH = " + where);
        }
    }

    public static class Update {


        /**
         * @param where         DK - update
         * @param newKhoiHoc DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, KhoiHoc newKhoiHoc) throws SQLException {
            try {
                statement = "UPDATE KhoiHoc " +
                        "SET " +
//                        "MaKH = N'" + newKhoiHoc.getMaKH() + ", " +
                        "TenKH = N'" + newKhoiHoc.getTenKH() + "' " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, KhoiHoc khoiHoc) throws SQLException {
            return KhoiHoc.Update.where("MaKH = " + where, khoiHoc);
        }
    }
}