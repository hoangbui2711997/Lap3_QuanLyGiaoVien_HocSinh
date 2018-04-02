package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class KhoiHoc {
    private SimpleIntegerProperty maKH;
    private SimpleStringProperty tenKH;

    private KhoiHoc(int maKH, String tenKH) {
        this.maKH = new SimpleIntegerProperty(maKH);
        this.tenKH = new SimpleStringProperty(tenKH);
    }

    public static KhoiHoc getInstance(int maKH, String tenKH) {
        return new KhoiHoc(maKH, tenKH);
    }

    public KhoiHoc(SimpleStringProperty tenKH) {
        this.tenKH = tenKH;
    }

    public int getMaKH() {
        return maKH.get();
    }

    public SimpleIntegerProperty maKHProperty() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH.get();
    }

    public SimpleStringProperty tenKHProperty() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH.set(tenKH);
    }

    @Override
    public String toString() {
        return "KhoiHoc{" +
                "maKH=" + maKH +
                ", tenKH=" + tenKH +
                '}';
    }
}