package model.repository;

import model.data.PhanCong;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryPhanCong {
    private static List<PhanCong> lstPhanCong;

    private RepositoryPhanCong() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstPhanCong = PhanCong.Search.getAll();
    }

    public static List<PhanCong> getAll() {
        try {
            if (lstPhanCong == null) {
                lstPhanCong = PhanCong.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstPhanCong;
        }
    }

    public synchronized static boolean add(PhanCong phanCong) {
        try {
            checkNull();

            lstPhanCong.add(phanCong);
            PhanCong.Insert(phanCong);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstPhanCong == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(PhanCong phanCong) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaPC() == phanCong.getMaPC());

            if(isRemoved) {
                PhanCong.Delete.whereId(phanCong.getMaPC() + "");
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
    public synchronized static Iterable<PhanCong> find(String id) {
        checkNull();

        Iterable<PhanCong> readOnlyCollection = lstPhanCong.stream()
                .filter(gv -> (gv.getMaPC() + "").contains(id))
                .collect(Collectors.toList());
        if (((List<PhanCong>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

    public static boolean edit(PhanCong phanCong) {
        try {
            checkNull();

            PhanCong.Update.whereId(phanCong.getMaPC(), phanCong);
            PhanCong oldPhanCong =
                    lstPhanCong
                            .stream()
                            .filter(e -> e.getMaPC() == phanCong.getMaPC())
                            .findFirst()
                            .get();

            int index = lstPhanCong.indexOf(oldPhanCong);
            lstPhanCong.set(index, phanCong);

//            phancong.Insert(phanCong);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }
}
