package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HocKy {
    private SimpleIntegerProperty MaHK;
    private SimpleStringProperty TenHK;
    private SimpleIntegerProperty ThangBatDau;
    private SimpleIntegerProperty NgayBatDau;
    private SimpleIntegerProperty ThangKetThuc;
    private SimpleIntegerProperty NgayKetThuc;

    public static HocKy getInstance(SimpleIntegerProperty maHK, SimpleStringProperty tenHK, SimpleIntegerProperty thangBatDau, SimpleIntegerProperty ngayBatDau, SimpleIntegerProperty thangKetThuc, SimpleIntegerProperty ngayKetThuc) {
        return new HocKy(maHK, tenHK, thangBatDau, ngayBatDau, thangKetThuc, ngayKetThuc);
    }

    public HocKy(SimpleStringProperty tenHK, SimpleIntegerProperty thangBatDau, SimpleIntegerProperty ngayBatDau, SimpleIntegerProperty thangKetThuc, SimpleIntegerProperty ngayKetThuc) {
        TenHK = tenHK;
        ThangBatDau = thangBatDau;
        NgayBatDau = ngayBatDau;
        ThangKetThuc = thangKetThuc;
        NgayKetThuc = ngayKetThuc;
    }

    private HocKy(SimpleIntegerProperty maHK, SimpleStringProperty tenHK, SimpleIntegerProperty thangBatDau, SimpleIntegerProperty ngayBatDau, SimpleIntegerProperty thangKetThuc, SimpleIntegerProperty ngayKetThuc) {
        MaHK = maHK;
        TenHK = tenHK;
        ThangBatDau = thangBatDau;
        NgayBatDau = ngayBatDau;
        ThangKetThuc = thangKetThuc;
        NgayKetThuc = ngayKetThuc;
    }

    @Override
    public String toString() {
        return "HocKy{" +
                "MaHK=" + MaHK +
                ", TenHK=" + TenHK +
                ", ThangBatDau=" + ThangBatDau +
                ", NgayBatDau=" + NgayBatDau +
                ", ThangKetThuc=" + ThangKetThuc +
                ", NgayKetThuc=" + NgayKetThuc +
                '}';
    }

    public int getMaHK() {
        return MaHK.get();
    }

    public SimpleIntegerProperty maHKProperty() {
        return MaHK;
    }

    public void setMaHK(int maHK) {
        this.MaHK.set(maHK);
    }

    public String getTenHK() {
        return TenHK.get();
    }

    public SimpleStringProperty tenHKProperty() {
        return TenHK;
    }

    public void setTenHK(String tenHK) {
        this.TenHK.set(tenHK);
    }

    public int getThangBatDau() {
        return ThangBatDau.get();
    }

    public SimpleIntegerProperty thangBatDauProperty() {
        return ThangBatDau;
    }

    public void setThangBatDau(int thangBatDau) {
        this.ThangBatDau.set(thangBatDau);
    }

    public int getNgayBatDau() {
        return NgayBatDau.get();
    }

    public SimpleIntegerProperty ngayBatDauProperty() {
        return NgayBatDau;
    }

    public void setNgayBatDau(int ngayBatDau) {
        this.NgayBatDau.set(ngayBatDau);
    }

    public int getThangKetThuc() {
        return ThangKetThuc.get();
    }

    public SimpleIntegerProperty thangKetThucProperty() {
        return ThangKetThuc;
    }

    public void setThangKetThuc(int thangKetThuc) {
        this.ThangKetThuc.set(thangKetThuc);
    }

    public int getNgayKetThuc() {
        return NgayKetThuc.get();
    }

    public SimpleIntegerProperty ngayKetThucProperty() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(int ngayKetThuc) {
        this.NgayKetThuc.set(ngayKetThuc);
    }
}