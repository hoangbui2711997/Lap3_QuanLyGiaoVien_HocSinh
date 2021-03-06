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

public class PhanCong extends RecursiveTreeObject<PhanCong> {
    private SimpleStringProperty maPC;
    private SimpleIntegerProperty maMH;
    private SimpleIntegerProperty maGV;
    private SimpleIntegerProperty maLH;
    private SimpleIntegerProperty maHK;
    public static SearchDB searchDB = SearchDB.getQueryDB();

    private PhanCong(String maPC, int maMH, int maGV, int maLH, int maHK){
        this.maPC = new SimpleStringProperty(maPC);
        this.maMH = new SimpleIntegerProperty(maMH);
        this.maGV = new SimpleIntegerProperty(maGV);
        this.maLH = new SimpleIntegerProperty(maLH);
        this.maHK = new SimpleIntegerProperty(maHK);
    }

    public PhanCong(int maMH, int maGV, int maLH, int maHK){
        this.maMH = new SimpleIntegerProperty(maMH);
        this.maGV = new SimpleIntegerProperty(maGV);
        this.maLH = new SimpleIntegerProperty(maLH);
        this.maHK = new SimpleIntegerProperty(maHK);
    }

    public static PhanCong getInstance(String maPC, int maMH, int maGV, int maLH, int maHK){
        return new PhanCong(maPC, maMH, maGV, maLH, maHK);
    }

    public String getMaPC() {
        return maPC.get();
    }

    public SimpleStringProperty maPCProperty() {
        return maPC;
    }

    public int getMaMH() {
        return maMH.get();
    }

    public SimpleIntegerProperty maMHProperty() {
        return maMH;
    }

    public int getMaGV() {
        return maGV.get();
    }

    public SimpleIntegerProperty maGVProperty() {
        return maGV;
    }

    public int getMaLH() {
        return maLH.get();
    }

    public SimpleIntegerProperty maLHProperty() {
        return maLH;
    }

    public int getMaHK() {
        return maHK.get();
    }

    public SimpleIntegerProperty maHKProperty() {
        return maHK;
    }

    public void setMaPC(String maPC) {
        this.maPC.set(maPC);
    }

    public void setMaMH(int maMH) {
        this.maMH.set(maMH);
    }

    public void setMaGV(int maGV) {
        this.maGV.set(maGV);
    }

    public void setMaLH(int maLH) {
        this.maLH.set(maLH);
    }

    public void setMaHK(int maHK) {
        this.maHK.set(maHK);
    }

    @Override
    public String toString() {
        return "phancong{" +
                "maPC=" + maPC +
                ", maMH=" + maMH +
                ", maGV=" + maGV +
                ", maLH=" + maLH +
                ", maHK=" + maHK +
                '}';
    }

    public static class Search{
        public Search() {
        }

        public static PhanCong where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("select * from phancong where " + where);
            resultSet.next();
            return searchDB.getPhanCong(resultSet);
        }

        public static List<PhanCong> getAll()throws SQLException{
            return searchDB.getDsPhanCong();
        }
    }

    static String statement = "";
    public static PhanCong Insert(PhanCong phanCong) throws SQLException{
        try {
//            int id = InsertDB.getInstance().initInsert("phancong");
            MonHoc mh = MonHoc.Search.whereId(phanCong.getMaMH() + "");
            GiaoVien gv = GiaoVien.Search.whereId(phanCong.getMaGV() + "");
            LopHoc lh = LopHoc.Search.whereId(phanCong.getMaLH() + "");
            HocKy hk = HocKy.Search.whereId(phanCong.getMaHK() + "");

            String maPC = (mh.getMaMH() + "" + gv.getMaGV() + "" + lh.getMaLH() + "" + hk.getMaHK());

            statement = "INSERT INTO PhanCong(MaPC, MaMH, MaGV, MaLH, MaHK) VALUES " +
                    "(" +
                    "N'" + maPC + "', " +
                    phanCong.getMaMH() + ", " +
                    phanCong.getMaGV() + ", " +
                    phanCong.getMaLH() + ", " +
                    phanCong.getMaHK() +
                    ")";

            System.out.println(statement);
            InsertDB.getInstance().insertCommand(statement);
            return returnPhanCong(maPC , phanCong);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static PhanCong returnPhanCong(String id, PhanCong phanCong) {
        return new PhanCong(id, phanCong.getMaMH(), phanCong.getMaGV(), phanCong.getMaLH(), phanCong.getMaHK());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE PHANCONG WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return LopHoc.Delete.where("MaLH = " + where);
        }
    }

    public static class Update{
        /**
         * @param where
         * @param phanCong
         * @return
         * throws SqlException
         */
        public static Boolean where(String where, PhanCong phanCong) throws SQLException{
            try {
                statement = "UPDATE phancong " +
                        "SET " +
                        "MaPC = N'" + phanCong.getMaPC() + "', " +
                        "MaMH = " + phanCong.getMaMH() + ", " +
                        "MaGV = " + phanCong.getMaGV() + ", " +
                        "MaLH = " + phanCong.getMaLH() + ", " +
                        "MaHK = " + phanCong.getMaHK() +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, PhanCong phanCong) throws SQLException{
            return where("MaPC = " + where, phanCong);
        }
    }
}
