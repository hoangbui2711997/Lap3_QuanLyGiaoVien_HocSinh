package model;

import model.database.DB_Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKe {

    private static Connection connection = DB_Connection.getConnection();

    private static int getResult(String statement) throws SQLException {
        ResultSet resultSet = connection.prepareCall(statement).executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static int laySoLuongHocSinhChuaDatNam(int nam) throws SQLException {
        String statement = "select [dbo].func_LaySoLuongHocSinhChuaDatNamXXXX(" + nam + ") as 'Soluong'";
        return getResult(statement);
    }

    public static int laySoLuongHocSinhGioiNam(int nam) throws SQLException {
        String statement = "select [dbo].func_LaySoLuongHocSinhGioiNamXXXX(" + nam + ") as 'Soluong'";
        return getResult(statement);
    }

    public static int laySoLuongHocKhaDatNam(int nam) throws SQLException {
        String statement = "select [dbo].func_LaySoLuongHocSinhKhaNamXXXX(" + nam + ") as 'Soluong'";
        return getResult(statement);
    }

    public static int laySoLuongHocTrungBinhNam(int nam) throws SQLException {
        String statement = "select [dbo].func_LaySoLuongHocSinhTrungBinhNamXXXX(" + nam + ") as 'Soluong'";
        return getResult(statement);
    }

    public static int laySoLuongHocNam(int nam) throws SQLException {
        String statement = "select [dbo].func_LaySoLuongHocSinhNamXXXX(" + nam + ") as 'Soluong'";
        return getResult(statement);
    }
}
