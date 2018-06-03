package model.repository;

import model.data.GiaoVien;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryGiaoVien {

    private static List<GiaoVien> lstGiaoVien;

    private RepositoryGiaoVien() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstGiaoVien = GiaoVien.Search.getAll();
    }

    public static List<GiaoVien> getAll() {
        try {
            if (lstGiaoVien == null) {
                lstGiaoVien = GiaoVien.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstGiaoVien;
        }
    }

    public synchronized static boolean add(GiaoVien giaoVien) {
        try {
            checkNull();

            lstGiaoVien.add(giaoVien);
            GiaoVien.Insert(giaoVien);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstGiaoVien == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(GiaoVien giaoVien) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaGV() == giaoVien.getMaGV());

            if(isRemoved) {
                GiaoVien.Delete.whereId(giaoVien.getMaGV() + "");
            }
//            System.out.println(getAll().size());

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    /**
     * @param id nhap id muon tim kiem
     *
     * @return Trả về danh sách các đối tượng tìm thấy qua id đã đưa
     */
    public synchronized static Iterable<GiaoVien> find(String id) {
        checkNull();

        Iterable<GiaoVien> readOnlyCollection = lstGiaoVien.stream()
                .filter(gv -> (gv.getMaGV() + "").contains(id))
                .collect(Collectors.toList());
        if (((List<GiaoVien>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

    public static boolean edit(GiaoVien giaoVien) {
        try {
            checkNull();

            GiaoVien.Update.whereId(giaoVien.getMaGV() + "", giaoVien);
            GiaoVien oldGiaoVien =
                    lstGiaoVien
                            .stream()
                            .filter(e -> e.getMaGV() == giaoVien.getMaGV())
                            .findFirst()
                            .get();

            int index = lstGiaoVien.indexOf(oldGiaoVien);
            lstGiaoVien.set(index, giaoVien);

//            giaovien.Insert(giaoVien);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }
}
