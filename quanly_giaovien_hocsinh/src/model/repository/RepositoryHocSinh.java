package model.repository;

import model.data.HocSinh;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryHocSinh {

    private static List<HocSinh> lstHocSinh;

    private RepositoryHocSinh() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstHocSinh = HocSinh.Search.getAll();
    }

    public static List<HocSinh> getAll() {
        try {
            if (lstHocSinh == null) {
                lstHocSinh = HocSinh.Search.getAll(); }
        } catch (Exception e) {
            return null;
        } finally {
            return lstHocSinh;
        }
    }

    public synchronized static boolean add(HocSinh hocSinh) {
        try {
            checkNull();

            lstHocSinh.add(hocSinh);
            HocSinh.Insert(hocSinh);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstHocSinh == null) {
            getAll();
        }
    }

    // Đây là xóa của hocsinh - 1 khóa
    public synchronized static boolean del(HocSinh hocSinh) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(e -> e.getMaHS() == hocSinh.getMaHS());

            if(isRemoved) {
               HocSinh.Delete.whereId(hocSinh.getMaHS() + "");
            }
//            System.out.println(getAll().size());

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

//    /**
//     * @param id nhap id muon tim kiem
//     *
//     * @return Trả về danh sách các đối tượng tìm thấy qua id đã đưa
//     */
//    public synchronized static Iterable<HocSinh> find(String id) {
//        checkNull();
//
//        Iterable<HocSinh> readOnlyCollection = lstHocSinh.stream()
//                .filter(gv -> (gv.getMaGV() + "").contains(id))
//                .collect(Collectors.toList());
//        if (((List<GiaoVien>) readOnlyCollection).size() == 0) {
//            return null;
//        } else {
//            return readOnlyCollection;
//        }
//    }
//
//    public static boolean edit(GiaoVien giaoVien) {
//        try {
//            checkNull();
//
//            GiaoVien.Update.where(giaoVien.getMaGV() + "", giaoVien);
//            GiaoVien oldGiaoVien =
//                    lstGiaoVien
//                            .stream()
//                            .filter(e -> e.getMaGV() == giaoVien.getMaGV())
//                            .findFirst()
//                            .get();
//
//            int index = lstGiaoVien.indexOf(oldGiaoVien);
//            lstGiaoVien.set(index, giaoVien);
//
//            GiaoVien.Insert(giaoVien);
//        } catch (Exception e) {
//            return false;
//        } finally {
//            return true;
//        }
//    }
}
