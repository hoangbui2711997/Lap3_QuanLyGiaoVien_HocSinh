package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HocSinh {
    private SimpleIntegerProperty MaHS;
    private SimpleStringProperty GioiTinh;
    private SimpleStringProperty HoTen;
    private SimpleStringProperty NgaySinh;
    private SimpleStringProperty DiaChi;
    private SimpleStringProperty DienTHoai;

    @Override
    public String toString() {
        return "HocSinh{" +
                "MaHS=" + MaHS +
                ", GioiTinh=" + GioiTinh +
                ", HoTen=" + HoTen +
                ", NgaySinh=" + NgaySinh +
                ", DiaChi=" + DiaChi +
                ", DienTHoai=" + DienTHoai +
                '}';
    }

    public static HocSinh getInstance(SimpleIntegerProperty maHS, SimpleStringProperty gioiTinh, SimpleStringProperty hoTen, SimpleStringProperty ngaySinh, SimpleStringProperty diaChi, SimpleStringProperty dienTHoai) {
        return new HocSinh(maHS, gioiTinh, hoTen, ngaySinh, diaChi, dienTHoai);
    }

    public HocSinh(SimpleStringProperty gioiTinh, SimpleStringProperty hoTen, SimpleStringProperty ngaySinh, SimpleStringProperty diaChi, SimpleStringProperty dienTHoai) {
        GioiTinh = gioiTinh;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        DienTHoai = dienTHoai;
    }

    private HocSinh(SimpleIntegerProperty maHS, SimpleStringProperty gioiTinh, SimpleStringProperty hoTen, SimpleStringProperty ngaySinh, SimpleStringProperty diaChi, SimpleStringProperty dienTHoai) {
        MaHS = maHS;
        GioiTinh = gioiTinh;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        DienTHoai = dienTHoai;
    }

    public int getMaHS() {
        return MaHS.get();
    }

    public SimpleIntegerProperty maHSProperty() {
        return MaHS;
    }

    public String getGioiTinh() {
        return GioiTinh.get();
    }

    public SimpleStringProperty gioiTinhProperty() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.GioiTinh.set(gioiTinh);
    }

    public String getHoTen() {
        return HoTen.get();
    }

    public SimpleStringProperty hoTenProperty() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen.set(hoTen);
    }

    public String getNgaySinh() {
        return NgaySinh.get();
    }

    public SimpleStringProperty ngaySinhProperty() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.NgaySinh.set(ngaySinh);
    }

    public String getDiaChi() {
        return DiaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi.set(diaChi);
    }

    public String getDienTHoai() {
        return DienTHoai.get();
    }

    public SimpleStringProperty dienTHoaiProperty() {
        return DienTHoai;
    }

    public void setDienTHoai(String dienTHoai) {
        this.DienTHoai.set(dienTHoai);
    }
}