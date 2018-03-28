package model.objets;

public class KhoiHoc
{
    private String maKhoiHoc, tenKhoiHoc;

    public KhoiHoc(String maKhoiHoc, String tenKhoiHoc) {
        this.maKhoiHoc = maKhoiHoc;
        this.tenKhoiHoc = tenKhoiHoc;
    }

    public static KhoiHoc getInstance(String maKhoiHoc, String tenKhoiHoc) {
        return new KhoiHoc(maKhoiHoc, tenKhoiHoc);
    }

    public KhoiHoc(String tenKhoiHoc) {
        this.tenKhoiHoc = tenKhoiHoc;
    }

    public String getMaKhoiHoc() {
        return maKhoiHoc;
    }

    public void setMaKhoiHoc(String maKhoiHoc) {
        this.maKhoiHoc = maKhoiHoc;
    }

    public String getTenKhoiHoc() {
        return tenKhoiHoc;
    }

    public void setTenKhoiHoc(String tenKhoiHoc) {
        this.tenKhoiHoc = tenKhoiHoc;
    }

    @Override
    public String toString() {
        return "KhoiHoc{" +
                "maKhoiHoc='" + maKhoiHoc + '\'' +
                ", tenKhoiHoc='" + tenKhoiHoc + '\'' +
                '}';
    }
}
