package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Diem {
    private SimpleIntegerProperty maHS, diemHS1, diemHS2, diemHS3;
    /**
     * maPC
     */
    private SimpleIntegerProperty maPC;

    public static Diem getInstance(int maHS, int diemHS1, int diemHS2, int diemHS3, int maPC) {
        return new Diem(maHS, diemHS1, diemHS2, diemHS3, maPC);
    }

    public Diem(int diemHS1, int diemHS2, int diemHS3, int maPC) {
        this.diemHS1 = new SimpleIntegerProperty(diemHS1);
        this.diemHS2 = new SimpleIntegerProperty(diemHS2);
        this.diemHS3 = new SimpleIntegerProperty(diemHS3);
        this.maPC = new SimpleIntegerProperty(maPC);
    }

    private Diem(int maHS, int diemHS1, int diemHS2, int diemHS3, int maPC) {
        this.maHS = new SimpleIntegerProperty(maHS);
        this.diemHS1 = new SimpleIntegerProperty(diemHS1);
        this.diemHS2 = new SimpleIntegerProperty(diemHS2);
        this.diemHS3 = new SimpleIntegerProperty(diemHS3);
        this.maPC = new SimpleIntegerProperty(maPC);
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

    public int getDiemHS1() {
        return diemHS1.get();
    }

    public SimpleIntegerProperty diemHS1Property() {
        return diemHS1;
    }

    public void setDiemHS1(int diemHS1) {
        this.diemHS1.set(diemHS1);
    }

    public int getDiemHS2() {
        return diemHS2.get();
    }

    public SimpleIntegerProperty diemHS2Property() {
        return diemHS2;
    }

    public void setDiemHS2(int diemHS2) {
        this.diemHS2.set(diemHS2);
    }

    public int getDiemHS3() {
        return diemHS3.get();
    }

    public SimpleIntegerProperty diemHS3Property() {
        return diemHS3;
    }

    public void setDiemHS3(int diemHS3) {
        this.diemHS3.set(diemHS3);
    }

    public int getMaPC() {
        return maPC.get();
    }

    public SimpleIntegerProperty maPCProperty() {
        return maPC;
    }

    public void setMaPC(int maPC) {
        this.maPC.set(maPC);
    }

    @Override
    public String toString() {
        return "Diem{" +
                "maHS=" + maHS +
                ", diemHS1=" + diemHS1 +
                ", diemHS2=" + diemHS2 +
                ", diemHS3=" + diemHS3 +
                ", maPC=" + maPC +
                '}';
    }

    public static class Search {
        private Search() {
        }

        public static Diem where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM GIANGDUONG WHERE " + where);
            resultSet.next();

            return searchDB.getDiem(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<Diem> getAll() throws SQLException {
            return searchDB.getDsDiem();
        }
    }

    public static Diem Insert(Diem diem) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("DIEM");

            statement = "INSERT INTO DIEM(tengd) VALUES" +
                    "(" +
//                    diem.getMa() + ", " +
                    "N'" + diem.getTen() + "'" +
                    ")";


            // wait form input
            // wait form input
            // wait form input

//            Diem.Update.where("magd = " + id, new Diem(id, diem.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return new Diem(id, diem.getTen());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE GIANGDUONG WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return Delete.where("magd = " + where);
        }
    }

    public static class Update {


        /**
         * @param where         DK - update
         * @param newDiem DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Diem newDiem) throws SQLException {
            try {
                statement = "UPDATE GIANGDUONG " +
                        "SET " +
                        "tengd = N'" + newDiem.getTen() + "' " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, Diem gd) throws SQLException {
            return Update.where("magd = " + where, gd);
        }
    }
}