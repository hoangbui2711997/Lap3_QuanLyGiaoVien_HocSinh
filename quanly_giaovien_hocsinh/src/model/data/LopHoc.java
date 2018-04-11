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

public class LopHoc extends RecursiveTreeObject<LopHoc> {
    private SimpleIntegerProperty maLH;
    private SimpleIntegerProperty maNH;
    private SimpleIntegerProperty maGV;
    private SimpleIntegerProperty maKH;
    private SimpleStringProperty tenLH;
    public static SearchDB searchDB = SearchDB.getQueryDB();

    public LopHoc(String tenLH, int maNH, int maGV, int maKH) {
        this.maGV = new SimpleIntegerProperty(maGV);
        this.maKH = new SimpleIntegerProperty(maKH);
        this.maLH = new SimpleIntegerProperty(maNH);
        this.tenLH = new SimpleStringProperty(tenLH);
    }

    private LopHoc(int maLH, String tenLH, int maNH, int maGV, int maKH) {
        this.maLH = new SimpleIntegerProperty(maLH);
        this.tenLH = new SimpleStringProperty(tenLH);
        this.maGV = new SimpleIntegerProperty(maNH);
        this.maKH = new SimpleIntegerProperty(maGV);
        this.maKH = new SimpleIntegerProperty(maKH);
    }

    public static LopHoc getInstance(int maLH, String tenLH, int maNH, int maGV, int maKH){
        return new LopHoc(maLH, tenLH, maNH, maGV, maKH);
    }


    public int getMaLH() {
        return maLH.get();
    }

    public SimpleIntegerProperty maLHProperty() {
        return maLH;
    }

    public int getMaNH() {
        return maNH.get();
    }

    public SimpleIntegerProperty maNHProperty() {
        return maNH;
    }

    public int getMaGV() {
        return maGV.get();
    }

    public SimpleIntegerProperty maGVProperty() {
        return maGV;
    }

    public int getMaKH() {
        return maKH.get();
    }

    public SimpleIntegerProperty maKHProperty() {
        return maKH;
    }

    public String getTenLH() {
        return tenLH.get();
    }

    public SimpleStringProperty tenLHProperty() {
        return tenLH;
    }

    public void setMaLH(int maLH) {
        this.maLH.set(maLH);
    }

    public void setMaNH(int maNH) {
        this.maNH.set(maNH);
    }

    public void setMaGV(int maGV) {
        this.maGV.set(maGV);
    }

    public void setMaKH(int maKH) {
        this.maKH.set(maKH);
    }

    public void setTenLH(String tenLH) {
        this.tenLH.set(tenLH);
    }

    @Override
    public String toString() {
        return "LopHoc{" +
                "maLH=" + maLH +
                ", maNH=" + maNH +
                ", maGV=" + maGV +
                ", maKH=" + maKH +
                ", tenLH=" + tenLH +
                '}';
    }

    public static class Search{
        public Search() {
        }

        public static LopHoc where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("select * from LopHoc where " + where);
            resultSet.next();
            return searchDB.getLopHoc(resultSet);
        }

        public static List<LopHoc> getAll()throws SQLException{
            return searchDB.getDsLopHoc();
        }
    }

    static String statement = "";
    public static LopHoc Insert(LopHoc lopHoc) throws SQLException{
        try {
            int id = InsertDB.getInstance().initInsert("LopHoc");

            statement = "INSERT INTO LopHoc(MaLH, TenLH, MaNH, MaGV, MaKh) VALUES " +
                    "(" +
                    lopHoc.getMaLH() + ", " +
                    "N'" + lopHoc.getTenLH() + "', " +
                    lopHoc.getMaNH() + ", " +
                    lopHoc.getMaGV() + ", " +
                    lopHoc.getMaKH() +
                    ")";

            InsertDB.getInstance().insertCommand(statement);
            return returnLopHoc(id, lopHoc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static LopHoc returnLopHoc(int id, LopHoc lopHoc) {
        return new LopHoc(id, lopHoc.getTenLH(), lopHoc.getMaNH(), lopHoc.getMaGV(), lopHoc.getMaKH());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE LopHoc WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

//        public static Boolean whereId(String where) {
//            return LopHoc.Delete.where("MaLH = " + where);
//        }
    }

    public static class Update{
        /**
         * @param where
         * @param lopHoc
         * @return
         * throws SqlException
         */
        public static Boolean where(int where, LopHoc lopHoc) throws SQLException{
            try {
                statement = "UPDATE LopHoc " +
                        "SET " +
                        "MaLH = " + lopHoc.getMaLH() + ", " +
                        "TenLH = N'" + lopHoc.getTenLH() + "', " +
                        "MaNH = " + lopHoc.getMaNH() + ", " +
                        "MaGV = " + lopHoc.getMaGV() + ", " +
                        "MaKH = " + lopHoc.getMaKH() +
                        "WHERE MaLH = " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
