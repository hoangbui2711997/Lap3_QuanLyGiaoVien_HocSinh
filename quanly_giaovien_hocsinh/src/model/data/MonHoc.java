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

public class MonHoc extends RecursiveTreeObject<MonHoc> {
    private SimpleIntegerProperty maMH;
    private SimpleStringProperty tenMH;
    private static SearchDB searchDB = SearchDB.getQueryDB();

    public MonHoc(String tenMH) {
        this.tenMH = new SimpleStringProperty(tenMH);
    }
    
    private MonHoc(int maMH, String tenMH){
        this.maMH = new SimpleIntegerProperty(maMH);
        this.tenMH = new SimpleStringProperty(tenMH);
    }
    
    public static MonHoc getInstance(int maMH, String tenMH){
        return new MonHoc(maMH, tenMH);
    }

    public int getMaMH() {
        return maMH.get();
    }

    public SimpleIntegerProperty maMHProperty() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH.get();
    }

    public SimpleStringProperty tenMHProperty() {
        return tenMH;
    }

    public void setMaMH(int maMH) {
        this.maMH.set(maMH);
    }

    public void setTenMH(String tenMH) {
        this.tenMH.set(tenMH);
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMH=" + maMH +
                ", tenMH=" + tenMH +
                '}';
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE MonHoc WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return NamHoc.Delete.where("MaMH = " + where);
        }
    }

    public static class Search{
        public Search() {
        }

        public static MonHoc where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("select * from MonHoc where " + where);
            resultSet.next();
            return searchDB.getMonHoc(resultSet);
        }

        public static List<MonHoc> getAll()throws SQLException{
            return searchDB.getDsMonHoc();
        }

        public static MonHoc whereId(String id) throws SQLException {
            return where("MaMH = " + id);
        }
    }

    static String statement = "";
    public static MonHoc Insert(MonHoc monHoc) throws SQLException{
        try {
            int id = InsertDB.getInstance().initInsert("MonHoc");

            statement = "INSERT INTO Monhoc(TenMH) VALUES " +
                    "(" +
//                    monHoc.getMaMH() + ", " +
                    "N'" + monHoc.getTenMH() + "'" +
                    ")";
            System.out.println(statement);
            InsertDB.getInstance().insertCommand(statement);
            return returnMonHoc(id, monHoc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static MonHoc returnMonHoc(int id, MonHoc monHoc) {
        return new MonHoc(id, monHoc.getTenMH());
    }
    
    public static class Update{
        /**
         * @param where
         * @param monHoc
         * @return
         * throws SqlException
         */
        public static Boolean where(int where, MonHoc monHoc) throws SQLException{
            try {
                statement = "UPDATE MonHoc " +
                        "SET " +
//                        "MaMH = " + monHoc.getMaMH() + ", " +
                        "TenMH = N'" + monHoc.getTenMH() + ", " +
                        "WHERE MaMH = " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
