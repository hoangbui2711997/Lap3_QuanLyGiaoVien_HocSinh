package model.database;

import model.constract.ISearch;
import model.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDB implements ISearch {

    public Connection conn = null;
    private List<Diem> dsDiem;
    private List<GiaoVien> dsGiaoVien;
    private List<HocKy> dsHocKy;
    private List<KhoiHoc> dsKhoiHoc;
    private List<HocSinh> dsHocSinh;
    private List<NamHoc> dsNamHoc;
    private List<LopHoc> dsLopHoc;
    private List<MonHoc> dsMonHoc;
    private List<PhanCong> dsPhanCong;
    private List<XepLop> dsXepLop;
    private String statement = "";
    private static SearchDB instance = null;
    private static Object lock = new Object();

    /**
     * constructor cua SearchDB
     * @see "set" connection between database and java
     */
    private SearchDB() {
        this.conn = DB_Connection.getConnection();
    }

    public static SearchDB getQueryDB() {
        synchronized (lock) {
            if (instance == null) {
                return new SearchDB();
            }

            return instance;
        }
    }

    /**
     *
     * @param statement truyền vào câu lệnh qua biến statement và được execute thông qua searchCommand()
     * @return Trả về tập kết quả sau khi truy vấn (ResultSet)
     * @throws SQLException
     */
    // searchCommand thực thi câu lệnh statement và trả về kết quả
    public ResultSet searchCommand(String statement) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * @see  "Truyền" vào {@link ResultSet} và lấy về bộ dữ liệu tiếp theo của {@link ResultSet}
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng GiaoVien
     * @throws SQLException
     */
    // Lấy từng GiaoVien qua ResultSet
    public GiaoVien getGV(ResultSet resultSet) throws SQLException{
        try {
            Object maGV = resultSet.getObject(1);
            Object gioiTinh = resultSet.getObject(2);
            Object hoTen = resultSet.getObject(3);
            Object diaChi = resultSet.getObject(4);
            Object ngaySinh = resultSet.getObject(5);
            Object dienThoai = resultSet.getObject(6);
            Object CMND = resultSet.getObject(7);
            Object matKhau = resultSet.getObject(8);
            Object role = resultSet.getObject(9);


            return GiaoVien.getInstance((Integer) maGV, (String) gioiTinh,
                    (String) hoTen, (String) diaChi, ((Date) ngaySinh).toString(),
                    (String) dienThoai, (String) CMND, (String) matKhau, (Integer) role);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Diem
     * @throws SQLException
     */
    // Lấy từng Diem qua ResultSet
    public Diem getDiem(ResultSet resultSet) throws SQLException{
        try {
            Object maHS = resultSet.getObject(1);
            Object diemHS1 = resultSet.getObject(2);
            Object diemHS2 = resultSet.getObject(3);
//            Object masv = resultSet.getObject(4);
            Object diemHS3 = resultSet.getObject(4);
            Object maPC = resultSet.getObject(5);

            return Diem.getInstance(
                    (Integer) maHS,
                    (Integer) diemHS1,
                    (Integer) diemHS2,
                    (Integer) diemHS3,
//                    (Integer) masv,
                    (String) maPC
                    );
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Diem
     * @throws SQLException
     */
    // Lấy từng Diem qua ResultSet
    public HocKy getHocKy(ResultSet resultSet) throws SQLException{
        try {
            Object maHK = resultSet.getObject(1);
            Object tenHK = resultSet.getObject(2);
            Object thangBatDau = resultSet.getObject(3);
//            Object masv = resultSet.getObject(4);
            Object ngayBatDau = resultSet.getObject(4);
            Object thangKetThuc = resultSet.getObject(5);
            Object ngayKetThuc = resultSet.getObject(6);

            return HocKy.getInstance(
                    (Integer) maHK,
                    (String) tenHK,
                    (Integer) thangBatDau,
                    (Integer) ngayBatDau,
                    (Integer) thangKetThuc,
//                    (Integer) masv,
                    (Integer) ngayKetThuc
            );
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }


    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Diem
     * @throws SQLException
     */
    // Lấy từng HocSinh qua ResultSet
    public HocSinh getHocSinh(ResultSet resultSet) throws SQLException{
        try {
            Object maHS = resultSet.getObject(1);
            Object gioiTinh = resultSet.getObject(2);
            Object hoTen = resultSet.getObject(3);
            Object ngaySinh = resultSet.getObject(4);
            Object diaChi = resultSet.getObject(5);
            Object dienThoai = resultSet.getObject(6);

            return HocSinh.getInstance(
                    (Integer) maHS,
                    (String) gioiTinh,
                    (String) hoTen,
                    (String) ngaySinh,
                    (String) diaChi,
                    (String) dienThoai
            );
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Diem
     * @throws SQLException
     */
    // Lấy từng Diem qua ResultSet
    public KhoiHoc getKhoiHoc(ResultSet resultSet) throws SQLException{
        try {
            Object maKH = resultSet.getObject(1);
            Object tenKH = resultSet.getObject(2);


            return KhoiHoc.getInstance(
                    (Integer) maKH,
                    (String) tenKH
            );
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    public NamHoc getNamHoc(ResultSet resultSet) throws SQLException{
        try {
            Object maNH = resultSet.getObject(1);
            Object tenNH = resultSet.getObject(2);
            Object namBatDau = resultSet.getObject(3);
            Object namKetThuc = resultSet.getObject(4);


            return NamHoc.getInstance(
                    (Integer) maNH,
                    (String) tenNH,
                    (Integer) namBatDau,
                    (Integer) namKetThuc
            );
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    public LopHoc getLopHoc(ResultSet resultSet) throws SQLException{
        Object maLH = resultSet.getObject(1);
        Object tenLH = resultSet.getObject(2);
        Object maNH = resultSet.getObject(3);
        Object maGV = resultSet.getObject(4);
        Object maKH = resultSet.getObject(5);

        return LopHoc.getInstance(
                (Integer) maLH,
                (String) tenLH,
                (Integer) maNH,
                (Integer) maGV,
                (Integer) maKH
        );
    }

    public MonHoc getMonHoc(ResultSet resultSet) throws SQLException{
        Object maMH = resultSet.getObject(1);
        Object tenMH = resultSet.getObject(2);

        return MonHoc.getInstance(
                (Integer) maMH,
                (String) tenMH
        );
    }

    public PhanCong getPhanCong(ResultSet resultSet) throws SQLException{
        Object maPC = resultSet.getObject(1);
        Object maMH = resultSet.getObject(2);
        Object maGV = resultSet.getObject(3);
        Object maLH = resultSet.getObject(4);
        Object maHK = resultSet.getObject(5);

        return PhanCong.getInstance(
                (Integer) maPC,
                (Integer) maMH,
                (Integer) maGV,
                (Integer) maLH,
                (Integer) maHK
        );
    }

    public XepLop getXepLop(ResultSet resultSet) throws SQLException{
        Object maHS = resultSet.getObject(1);
        Object maLH = resultSet.getObject(2);

        return XepLop.getInstance(
                (Integer) maHS,
                (Integer) maLH
        );
    }

    /**
     *
     * @return Trả về câu lệnh hiện thời dùng để truy vấn
     */
    public String getStatement() {
        return statement;
    }

    /**
     *
     * @param statement - đặt câu lệnh cho biến statement
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<XepLop> getDsXepLop() throws SQLException{
        List<XepLop> dsxl = new ArrayList();
        ResultSet dsXepLop = searchCommand("SELECT * FROM XepLop");

        while(dsXepLop.next()) {
            XepLop xepLop = getXepLop(dsXepLop);
            dsxl.add(xepLop);
        }

        this.dsXepLop = dsxl;
        return dsxl;
    }

    public List<PhanCong> getDsPhanCong() throws SQLException{
        List<PhanCong> dspc = new ArrayList();
        ResultSet dsPhanCong = searchCommand("SELECT * FROM PHANCONG");

        while(dsPhanCong.next()) {
            PhanCong phanCong = getPhanCong(dsPhanCong);
            dspc.add(phanCong);
        }

        this.dsPhanCong = dspc;
        return dspc;
    }

    public List<MonHoc> getDsMonHoc() throws SQLException{
        List<MonHoc> dsmh = new ArrayList();
        ResultSet dsMonHoc = searchCommand("SELECT * FROM MONHOC");

        while(dsMonHoc.next()) {
            MonHoc monHoc = getMonHoc(dsMonHoc);
            dsmh.add(monHoc);
        }

        this.dsMonHoc = dsmh;
        return dsmh;
    }

    public List<LopHoc> getDsLopHoc() throws SQLException{
        List<LopHoc> dslh = new ArrayList();
        ResultSet dsNamHoc = searchCommand("SELECT * FROM LOPHOC");

        while(dsNamHoc.next()) {
            LopHoc lopHoc = getLopHoc(dsNamHoc);
            dslh.add(lopHoc);
        }

        this.dsLopHoc = dslh;
        return dslh;
    }

    public List<NamHoc> getDsNamHoc() throws SQLException{
        List<NamHoc> dsnh = new ArrayList();
        ResultSet dsNamHoc = searchCommand("SELECT * FROM NAMHOC");

        while(dsNamHoc.next()) {
            NamHoc namHoc = getNamHoc(dsNamHoc);
            dsnh.add(namHoc);
        }

        this.dsNamHoc = dsnh;
        return dsnh;
    }

    public List<Diem> getDsDiem() throws SQLException{
        List<Diem> dsdk = new ArrayList();
        ResultSet dsDiem = searchCommand("SELECT * FROM DIEM");

        while(dsDiem.next()) {
            Diem dk = getDiem(dsDiem);
            dsdk.add(dk);
        }

        this.dsDiem = dsdk;
        return dsdk;
    }

    public List<HocKy> getDsHocKy() throws SQLException{
        List<HocKy> danhSachHocKy = new ArrayList();
        ResultSet dsDiem = searchCommand("SELECT * FROM HOCKY");

        while(dsDiem.next()) {
            HocKy hk = getHocKy(dsDiem);
            danhSachHocKy.add(hk);
        }

        this.dsHocKy= danhSachHocKy;
        return danhSachHocKy;
    }

    public List<HocSinh> getDsHocSinh() throws SQLException{
        List<HocSinh> danhSachHocSinh = new ArrayList();
        ResultSet dsHocSinh = searchCommand("SELECT * FROM HOCSINH");

        while(dsHocSinh.next()) {
            HocSinh hk = getHocSinh(dsHocSinh);
            danhSachHocSinh.add(hk);
        }

        this.dsHocSinh= danhSachHocSinh;
        return danhSachHocSinh;
    }

    public List<KhoiHoc> getDsKhoiHoc() throws SQLException{
        List<KhoiHoc> danhSachKhoiHoc = new ArrayList();
        ResultSet dsKhoiHoc = searchCommand("SELECT * FROM KHOIHOC");

        while(dsKhoiHoc.next()) {
            KhoiHoc hk = getKhoiHoc(dsKhoiHoc);
            danhSachKhoiHoc.add(hk);
        }

        this.dsKhoiHoc = danhSachKhoiHoc;
        return danhSachKhoiHoc;
    }

    public List<GiaoVien> getDsGiaoVien() throws SQLException{
        List<GiaoVien> dsgv = new ArrayList();
        ResultSet dsGiaoVien = searchCommand("SELECT * FROM GIAOVIEN");

        while(dsGiaoVien.next()) {
            GiaoVien sv = getGV(dsGiaoVien);
            dsgv.add(sv);
        }

        this.dsGiaoVien = dsgv;
        return dsgv;
    }

    /**
     * Lock cho do bi race condition
     */
    Object anotherLock = new Object();
    public Integer getLastID(String nameObject) throws SQLException {
        synchronized (anotherLock) {
            String query = "SELECT IDENT_CURRENT('" + nameObject + "') as id";
            System.out.println(query);
            ResultSet rs = searchCommand(query);
            rs.next();
            Object id = rs.getObject(1);
            return (Integer.parseInt(id.toString()) + 2);
        }
    }
}
