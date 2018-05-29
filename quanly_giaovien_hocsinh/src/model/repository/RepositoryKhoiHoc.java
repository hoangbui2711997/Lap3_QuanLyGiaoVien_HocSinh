package model.repository;

import model.data.KhoiHoc;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryKhoiHoc {
    private static List<KhoiHoc> lstKhoiHoc;

    private RepositoryKhoiHoc() throws SQLException {

    }

    public synchronized static List<KhoiHoc> getAll() {
        try {
            if (lstKhoiHoc == null) {
                lstKhoiHoc = KhoiHoc.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstKhoiHoc;
        }
    }

    public synchronized static boolean add(KhoiHoc khoiHoc) {
        try {
            checkNull();

            lstKhoiHoc.add(khoiHoc);
            KhoiHoc.Insert(khoiHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstKhoiHoc == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(KhoiHoc khoiHoc) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaKH() == khoiHoc.getMaKH());

            if(isRemoved) {
                KhoiHoc.Delete.whereId(khoiHoc.getMaKH() + "");
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
    public synchronized static Iterable<KhoiHoc> find(String id) {
        checkNull();

        Iterable<KhoiHoc> readOnlyCollection = lstKhoiHoc.stream()
                .filter(gv -> (gv.getMaKH() + "").contains(id))
                .collect(Collectors.toList());
        if (((List<KhoiHoc>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

    public static boolean edit(KhoiHoc khoiHoc) {
        try {
            checkNull();

            KhoiHoc.Update.whereId(khoiHoc.getMaKH() + "", khoiHoc);
            KhoiHoc oldKhoiHoc =
                    lstKhoiHoc
                            .stream()
                            .filter(e -> e.getMaKH() == khoiHoc.getMaKH())
                            .findFirst()
                            .get();

            int index = lstKhoiHoc.indexOf(oldKhoiHoc);
            lstKhoiHoc.set(index, khoiHoc);

//            KhoiHoc.Insert(khoiHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

}
