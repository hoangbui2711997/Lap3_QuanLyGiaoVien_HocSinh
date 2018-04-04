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

public class Diem {
    private SimpleIntegerProperty maHS, diemHS1, diemHS2, diemHS3;
    /**
     * maPC
     */
    private SimpleStringProperty maPC;

    public static Diem getInstance(int maHS, int diemHS1, int diemHS2, int diemHS3, String maPC) {
        return new Diem(maHS, diemHS1, diemHS2, diemHS3, maPC);
    }

    public Diem(int id, int maHS, int diemHS1, int diemHS2, int diemHS3, String maPC) {
        this.diemHS1 = new SimpleIntegerProperty(diemHS1);
        this.diemHS2 = new SimpleIntegerProperty(diemHS2);
        this.diemHS3 = new SimpleIntegerProperty(diemHS3);
        this.maPC = new SimpleStringProperty(maPC);
    }

    private Diem(int maHS, int diemHS1, int diemHS2, int diemHS3, String maPC) {
        this.maHS = new SimpleIntegerProperty(maHS);
        this.diemHS1 = new SimpleIntegerProperty(diemHS1);
        this.diemHS2 = new SimpleIntegerProperty(diemHS2);
        this.diemHS3 = new SimpleIntegerProperty(diemHS3);
        this.maPC = new SimpleStringProperty(maPC);
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

    public String getMaPC() {
        return maPC.get();
    }

    public SimpleStringProperty maPCProperty() {
        return maPC;
    }

    public void setMaPC(String maPC) {
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
    static SearchDB searchDB = SearchDB.getQueryDB();
    public static class Search {
        private Search() {
        }

        public static Diem where(String where) throws SQLException {

            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM Diem WHERE " + where);
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
    static String statement = "";
    public static Diem Insert(Diem diem) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("Diem");

            statement = "INSERT INTO Diem(MaHS, MaPC, DiemHS1, DiemHS2, DiemHS3) VALUES " +
                    "(" +
//                    diem.getMa() + ", " +
                    diem.getMaHS() + ", " +
                    "N'" + diem.getMaPC() + "', " +
                    diem.getDiemHS1() + ", " +
                    diem.getDiemHS2() + ", " +
                    diem.getDiemHS3() +
                    ")";


            // wait form input
            // wait form input
            // wait form input

//            Diem.Update.where("magd = " + id, new Diem(id, diem.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return returnDiem(id, diem);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Diem returnDiem(int id, Diem diem) {
        return new Diem(id, diem.getMaHS(), diem.getDiemHS1(), diem.getDiemHS2(), diem.getDiemHS3(), diem.getMaPC());
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE Diem WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String maHS, String maPC) {
            return Delete.where("MaHS = " + maHS
            + " and MaPC = " + maPC);
        }
    }

    /**
     * Chi update duoc diem HS1 -> HS3 ngoai ra khong update duoc gi nua
     */
    public static class Update {


        /**
         * @param where         DK - update
         * @param newDiem DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Diem newDiem) throws SQLException {
            try {
                statement = "UPDATE Diem " +
                        "SET " +
//                        "MaHS = N'" + newDiem.getMaHS() + ", " +
//                        "MaPC = N'" + newDiem.getMaPC() + ", " +
                        "DiemHS1 = " + newDiem.getDiemHS1() + ", " +
                        "DiemHS2 = " + newDiem.getDiemHS2() + ", " +
                        "DiemHS3 = " + newDiem.getDiemHS3() + " " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        /**
         *
         * @param maHS ma hoc sinh
         * @param maPC ma phan cong
         * @param diem diem
         * @return
         * @throws SQLException
         */
        public static Boolean whereId(int maHS, String maPC, Diem diem) throws SQLException {
            return Update.where("MaHS = " + maHS
                    + " and MaPC = " + maPC, diem);
        }
    }
}