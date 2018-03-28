package model.objets;

public class HocSinh {
    private String maHocSinh, gioiTinh, hoTen, ngaySinh, diaChi, dienThoai;

    public static HocSinh getInstance(String maHocSinh, String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        return new HocSinh(maHocSinh, gioiTinh, hoTen, ngaySinh, diaChi, dienThoai);
    }

    private HocSinh(String maHocSinh, String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        this.maHocSinh = maHocSinh;
        this.gioiTinh = gioiTinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    @Override
    public String toString() {
        return "HocSinh{" +
                "maHocSinh='" + maHocSinh + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", dienThoai='" + dienThoai + '\'' +
                '}';
    }

    public HocSinh(String gioiTinh, String hoTen, String ngaySinh, String diaChi, String dienThoai) {
        this.gioiTinh = gioiTinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    public String getMaHocSinh() {
        return maHocSinh;
    }

    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
