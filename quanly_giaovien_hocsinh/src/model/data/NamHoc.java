package model.data;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NamHoc extends RecursiveTreeObject<NamHoc> {
    private SimpleIntegerProperty maNH;
    private SimpleIntegerProperty Nambatdau;
    private SimpleIntegerProperty Namketthuc;
    private SimpleStringProperty tenNH;
    public static SearchDB searchDB = SearchDB.getQueryDB();

    public NamHoc(int nambatdau, int namketthuc, String tenNH) {
        Nambatdau = new SimpleIntegerProperty(nambatdau);
        Namketthuc = new SimpleIntegerProperty(namketthuc);
        this.tenNH = new SimpleStringProperty(tenNH);
    }

    private NamHoc(int maNH, String tenNH, int nambatdau, int namketthuc) {
        this.maNH = new SimpleIntegerProperty(maNH);
        Nambatdau = new SimpleIntegerProperty(nambatdau);
        Namketthuc = new SimpleIntegerProperty(namketthuc);
        this.tenNH = new SimpleStringProperty(tenNH);
    }

    public static NamHoc getInstance(int maNH, String tenNH, int nambatdau, int namketthuc) {
        return new NamHoc(maNH, tenNH, nambatdau, namketthuc);
    }

    public int getMaNH() {
        return maNH.get();
    }

    public SimpleIntegerProperty maNHProperty() {
        return maNH;
    }

    public int getNambatdau() {
        return Nambatdau.get();
    }

    public SimpleIntegerProperty nambatdauProperty() {
        return Nambatdau;
    }

    public int getNamketthuc() {
        return Namketthuc.get();
    }

    public SimpleIntegerProperty namketthucProperty() {
        return Namketthuc;
    }

    public String getTenNH() {
        return tenNH.get();
    }

    public SimpleStringProperty tenNHProperty() {
        return tenNH;
    }

    public void setMaNH(int maNH) {
        this.maNH.set(maNH);
    }

    public void setNambatdau(int nambatdau) {
        this.Nambatdau.set(nambatdau);
    }

    public void setNamketthuc(int namketthuc) {
        this.Namketthuc.set(namketthuc);
    }

    public void setTenNH(String tenNH) {
        this.tenNH.set(tenNH);
    }

    @Override
    public String toString() {
        return "NamHoc{" +
                "maNH=" + maNH +
                ", Nambatdau=" + Nambatdau +
                ", Namketthuc=" + Namketthuc +
                ", tenNH=" + tenNH +
                '}';
    }

    public static class Search {
        public Search() {

        }

        public static NamHoc where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("select * from NamHoc where " + where);
            resultSet.next();
            return searchDB.getNamHoc(resultSet);
        }

        public static List<NamHoc> getAll()throws SQLException{
            return searchDB.getDsNamHoc();
        }
    }

    static String statement = "";
    public static NamHoc Insert(NamHoc namHoc) throws SQLException{
        try {
            int id = InsertDB.getInstance().initInsert("NamHoc");

            statement = "INSERT INTO NamHoc(TenNH, Nambatdau, Namketthuc) VALUES " +
                    "(" +
//                    namHoc.getMaNH() + ", " +
                    "N'" + namHoc.getTenNH() + "', " +
                    namHoc.getNambatdau() + ", " +
                    namHoc.getNamketthuc() +
                    ")";
            System.out.println(statement);
            InsertDB.getInstance().insertCommand(statement);
            return returnNamHoc(id, namHoc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static NamHoc returnNamHoc(int id, NamHoc namHoc) {
        return new NamHoc(id, namHoc.getTenNH(), namHoc.getNambatdau(), namHoc.getNamketthuc());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE NamHoc WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return NamHoc.Delete.where("MaNH = " + where);
        }
    }

    public static class Update{
        /**
         * @param where
         * @param namHoc
         * @return
         * throws SqlException
         */
        public static Boolean where(String where, NamHoc namHoc) throws SQLException{
            try {
                statement = "UPDATE NamHoc " +
                        "SET " +
//                        "MaNH = " + namHoc.getMaNH() + ", " +
                        "TenNH = N'" + namHoc.getTenNH() + "', " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(int where, NamHoc namHoc) throws SQLException{
            try {
                statement = "UPDATE NamHoc " +
                        "SET " +
                        "MaNH = " + namHoc.getMaNH() + ", " +
                        "TenNH = N'" + namHoc.getTenNH() + "', " +
                        "WHERE MaNH = " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}

