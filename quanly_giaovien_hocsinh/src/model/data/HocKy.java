package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HocKy {
    private SimpleIntegerProperty maHK;
    private SimpleStringProperty tenHK;
    private SimpleIntegerProperty thangBatDau;
    private SimpleIntegerProperty ngayBatDau;
    private SimpleIntegerProperty thangKetThuc;
    private SimpleIntegerProperty ngayKetThuc;

    public static HocKy getInstance(int maHK, String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        return new HocKy(maHK, tenHK, thangBatDau, ngayBatDau, thangKetThuc, ngayKetThuc);
    }

    public HocKy(String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        this.tenHK = new SimpleStringProperty(tenHK);
        this.thangBatDau = new SimpleIntegerProperty(thangBatDau);
        this.ngayBatDau = new SimpleIntegerProperty(ngayBatDau);
        this.thangKetThuc = new SimpleIntegerProperty(thangKetThuc);
        this.ngayKetThuc = new SimpleIntegerProperty(ngayKetThuc);
    }

    private HocKy(int maHK, String tenHK, int thangBatDau, int ngayBatDau, int thangKetThuc, int ngayKetThuc) {
        this.maHK = new SimpleIntegerProperty(maHK);
        this.tenHK = new SimpleStringProperty(tenHK);
        this.thangBatDau = new SimpleIntegerProperty(thangBatDau);
        this.ngayBatDau = new SimpleIntegerProperty(ngayBatDau);
        this.thangKetThuc = new SimpleIntegerProperty(thangKetThuc);
        this.ngayKetThuc = new SimpleIntegerProperty(ngayKetThuc);
    }

    @Override
    public String toString() {
        return "HocKy{" +
                "maHK=" + maHK +
                ", tenHK=" + tenHK +
                ", thangBatDau=" + thangBatDau +
                ", ngayBatDau=" + ngayBatDau +
                ", thangKetThuc=" + thangKetThuc +
                ", ngayKetThuc=" + ngayKetThuc +
                '}';
    }

    public int getMaHK() {
        return maHK.get();
    }

    public SimpleIntegerProperty maHKProperty() {
        return maHK;
    }

    public void setMaHK(int maHK) {
        this.maHK.set(maHK);
    }

    public String getTenHK() {
        return tenHK.get();
    }

    public SimpleStringProperty tenHKProperty() {
        return tenHK;
    }

    public void setTenHK(String tenHK) {
        this.tenHK.set(tenHK);
    }

    public int getThangBatDau() {
        return thangBatDau.get();
    }

    public SimpleIntegerProperty thangBatDauProperty() {
        return thangBatDau;
    }

    public void setThangBatDau(int thangBatDau) {
        this.thangBatDau.set(thangBatDau);
    }

    public int getNgayBatDau() {
        return ngayBatDau.get();
    }

    public SimpleIntegerProperty ngayBatDauProperty() {
        return ngayBatDau;
    }

    public void setNgayBatDau(int ngayBatDau) {
        this.ngayBatDau.set(ngayBatDau);
    }

    public int getThangKetThuc() {
        return thangKetThuc.get();
    }

    public SimpleIntegerProperty thangKetThucProperty() {
        return thangKetThuc;
    }

    public void setThangKetThuc(int thangKetThuc) {
        this.thangKetThuc.set(thangKetThuc);
    }

    public int setNgayKetThuc() {
        return ngayKetThuc.get();
    }

    public SimpleIntegerProperty ngayKetThucProperty() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(int ngayKetThuc) {
        this.ngayKetThuc.set(ngayKetThuc);
    }
}