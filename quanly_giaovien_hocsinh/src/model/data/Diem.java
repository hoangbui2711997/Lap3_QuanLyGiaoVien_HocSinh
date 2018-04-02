package model.data;

import javafx.beans.property.SimpleIntegerProperty;

public class Diem {
    private SimpleIntegerProperty maHS, diemHS1, diemHS2, diemHS3;
    /**
     * maPC
     */
    private SimpleIntegerProperty maPC;

    public static Diem getInstance(SimpleIntegerProperty maHS, SimpleIntegerProperty diemHS1, SimpleIntegerProperty diemHS2, SimpleIntegerProperty diemHS3, SimpleIntegerProperty maPC) {
        return new Diem(maHS, diemHS1, diemHS2, diemHS3, maPC);
    }

    public Diem(SimpleIntegerProperty diemHS1, SimpleIntegerProperty diemHS2, SimpleIntegerProperty diemHS3, SimpleIntegerProperty maPC) {
        this.diemHS1 = diemHS1;
        this.diemHS2 = diemHS2;
        this.diemHS3 = diemHS3;
        this.maPC = maPC;
    }

    private Diem(SimpleIntegerProperty maHS, SimpleIntegerProperty diemHS1, SimpleIntegerProperty diemHS2, SimpleIntegerProperty diemHS3, SimpleIntegerProperty maPC) {
        this.maHS = maHS;
        this.diemHS1 = diemHS1;
        this.diemHS2 = diemHS2;
        this.diemHS3 = diemHS3;
        this.maPC = maPC;
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

    public int getMaPC() {
        return maPC.get();
    }

    public SimpleIntegerProperty maPCProperty() {
        return maPC;
    }

    public void setMaPC(int maPC) {
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
}