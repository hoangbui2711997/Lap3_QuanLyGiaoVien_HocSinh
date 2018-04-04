package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class XepLop {
    private SimpleIntegerProperty maHS;
    private SimpleIntegerProperty maLH;
    public static SearchDB searchDB;

    private XepLop(int maHS, int maLH){
        this.maHS = new SimpleIntegerProperty(maHS);
        this.maLH = new SimpleIntegerProperty(maLH);
    }

    public XepLop(int maLH){
        this.maLH = new SimpleIntegerProperty(maLH);
    }

    public static XepLop getInstance(int maHS, int maLH){
        return new XepLop(maHS, maLH);
    }

    public int getMaHS() {
        return maHS.get();
    }

    public SimpleIntegerProperty maHSProperty() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS.set(maHS);
    }

    public int getMaLH() {
        return maLH.get();
    }

    public SimpleIntegerProperty maLHProperty() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH.set(maLH);
    }

    @Override
    public String toString() {
        return "XepLop{" +
                "maHS=" + maHS +
                ", maLH=" + maLH +
                '}';
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE XEPLOP WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

//        public static Boolean whereId(String where) {
//            return NamHoc.Delete.where("MaNH = " + where);
//        }
    }

    public static class Search{
        public Search() {
        }

        public static XepLop where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("select * from XepLop where " + where);
            resultSet.next();
            return searchDB.getXepLop(resultSet);
        }

        public static List<XepLop> getAll()throws SQLException{
            return searchDB.getDsXepLop();
        }
    }

    static String statement = "";
    public static XepLop Insert(XepLop xepLop) throws SQLException{
        try {
            int id = InsertDB.getInstance().initInsert("XepLop");

            statement = "INSERT INTO XepLop(MaHS, MaLH) VALUES " +
                    "(" +
                    xepLop.getMaHS() + ", " +
                    xepLop.getMaLH() +
                    ")";

            InsertDB.getInstance().insertCommand(statement);
            return returnXepLop(id, xepLop);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static XepLop returnXepLop(int id, XepLop xepLop) {
        return new XepLop(id, xepLop.getMaLH());
    }

    public static class Update{
        /**
         * @param where
         * @param xepLop
         * @return
         * throws SqlException
         */
        public static Boolean where(int where, XepLop xepLop) throws SQLException{
            try {
                statement = "UPDATE XepLop " +
                        "SET " +
                        "MaHS = " + xepLop.getMaHS() + ", " +
                        "MaLH = " + xepLop.getMaLH() +
                        "WHERE MaHS = " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
