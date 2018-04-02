package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HocSinh {
    private SimpleIntegerProperty maHS;
    private SimpleStringProperty gioiTinh;
    private SimpleStringProperty hoTen;
    private SimpleStringProperty ngaySinh;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty dienThoai;

    @Override
    public String toString() {
        return "HocSinh{" +
                "maHS=" + maHS +
                ", gioiTinh=" + gioiTinh +
                ", hoTen=" + hoTen +
                ", ngaySinh=" + ngaySinh +
                ", diaChi=" + diaChi +
                ", dienThoai=" + dienThoai +
                '}';
    }

    public static HocSinh getInstance(int maHS, String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        return new HocSinh(maHS, gioiTinh, hoTen, ngaySinh, diaChi, dienThoai);
    }

    public HocSinh(String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.dienThoai = new SimpleStringProperty(dienThoai);
    }

    private HocSinh(int maHS, String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        this.maHS = new SimpleIntegerProperty(maHS);
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.dienThoai = new SimpleStringProperty(dienThoai);
    }

    public int getMaHS() {
        return maHS.get();
    }

    public SimpleIntegerProperty maHSProperty() {
        return maHS;
    }

    public String getGioiTinh() {
        return gioiTinh.get();
    }

    public SimpleStringProperty gioiTinhProperty() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh.set(gioiTinh);
    }

    public String getHoTen() {
        return hoTen.get();
    }

    public SimpleStringProperty hoTenProperty() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public String getNgaySinh() {
        return ngaySinh.get();
    }

    public SimpleStringProperty ngaySinhProperty() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public String getDienThoai() {
        return dienThoai.get();
    }

    public SimpleStringProperty dienTHoaiProperty() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai.set(dienThoai);
    }
}