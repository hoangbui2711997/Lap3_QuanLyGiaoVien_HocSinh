package model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class KhoiHoc {
    private SimpleIntegerProperty MaKH;
    private SimpleStringProperty TenKH;

    private KhoiHoc(SimpleIntegerProperty maKH, SimpleStringProperty tenKH) {
        MaKH = maKH;
        TenKH = tenKH;
    }

    public static KhoiHoc getInstance(SimpleIntegerProperty maKH, SimpleStringProperty tenKH) {
        return new KhoiHoc(maKH, tenKH);
    }

    public KhoiHoc(SimpleStringProperty tenKH) {
        TenKH = tenKH;
    }

    public int getMaKH() {
        return MaKH.get();
    }

    public SimpleIntegerProperty maKHProperty() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH.get();
    }

    public SimpleStringProperty tenKHProperty() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        this.TenKH.set(tenKH);
    }

    @Override
    public String toString() {
        return "KhoiHoc{" +
                "MaKH=" + MaKH +
                ", TenKH=" + TenKH +
                '}';
    }
}