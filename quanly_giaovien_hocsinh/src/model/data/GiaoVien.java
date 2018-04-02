package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GiaoVien {
    private SimpleIntegerProperty maGV;
    private SimpleStringProperty gioiTinh;
    private SimpleStringProperty hoTen;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty ngaySinh;
    private SimpleStringProperty dienThoai;
    private SimpleStringProperty CMND;
    private SimpleStringProperty matkhau;

    public static GiaoVien getInstance(int maGV, String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau) {
        return new GiaoVien(maGV, gioiTinh, hoTen, diaChi, ngaySinh, dienThoai, CMND, matkhau);
    }

    public GiaoVien(String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau) {
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.dienThoai = new SimpleStringProperty(dienThoai);
        this.CMND = new SimpleStringProperty(CMND);
        this.matkhau = new SimpleStringProperty(matkhau);
    }

    private GiaoVien(int maGV, String gioiTinh, String hoTen, String diaChi, String ngaySinh, String dienThoai, String CMND, String matkhau) {
        this.maGV = new SimpleIntegerProperty(maGV);
        this.gioiTinh = new SimpleStringProperty(gioiTinh);
        this.hoTen = new SimpleStringProperty(hoTen);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
        this.dienThoai = new SimpleStringProperty(dienThoai);
        this.CMND = new SimpleStringProperty(CMND);
        this.matkhau = new SimpleStringProperty(matkhau);
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "MaGV=" + MaGV +
                ", GioiTinh=" + GioiTinh +
                ", HoTen=" + HoTen +
                ", DiaChi=" + DiaChi +
                ", NgaySinh=" + NgaySinh +
                ", DienThoai=" + DienThoai +
                ", CMND=" + CMND +
                ", matkhau=" + matkhau +
                '}';
    }

    public int getMaGV() {
        return MaGV.get();
    }

    public SimpleIntegerProperty maGVProperty() {
        return MaGV;
    }

    public void setMaGV(int maGV) {
        this.MaGV.set(maGV);
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

    public String getDiaChi() {
        return DiaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi.set(diaChi);
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

    public String getDienThoai() {
        return DienThoai.get();
    }

    public SimpleStringProperty dienThoaiProperty() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.DienThoai.set(dienThoai);
    }

    public String getCMND() {
        return CMND.get();
    }

    public SimpleStringProperty CMNDProperty() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND.set(CMND);
    }

    public String getMatkhau() {
        return matkhau.get();
    }

    public SimpleStringProperty matkhauProperty() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau.set(matkhau);
    }
}