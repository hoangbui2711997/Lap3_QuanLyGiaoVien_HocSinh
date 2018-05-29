package model.repository;

import model.data.HocKy;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryHocKy {
    private static List<HocKy> lstHocKy;

    private RepositoryHocKy() throws SQLException {

    }

    public synchronized static List<HocKy> getAll() {
        try {
            if (lstHocKy == null) {
                lstHocKy = HocKy.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstHocKy;
        }
    }

    public synchronized static boolean add(HocKy hocKy) {
        try {
            checkNull();

            lstHocKy.add(hocKy);
            HocKy.Insert(hocKy);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstHocKy == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(HocKy hocKy) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaHK() == hocKy.getMaHK());

            if(isRemoved) {
                HocKy.Delete.whereId(hocKy.getMaHK() + "");
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
    public synchronized static Iterable<HocKy> find(String id) {
        checkNull();

        Iterable<HocKy> readOnlyCollection = lstHocKy.stream()
                .filter(gv -> (gv.getMaHK() + "").contains(id))
                .collect(Collectors.toList());
        if (((List<HocKy>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

    public static boolean edit(HocKy hocKy) {
        try {
            checkNull();

            HocKy.Update.whereId(hocKy.getMaHK() + "", hocKy);
            HocKy oldHocKy =
                    lstHocKy
                            .stream()
                            .filter(e -> e.getMaHK() == hocKy.getMaHK())
                            .findFirst()
                            .get();

            int index = lstHocKy.indexOf(oldHocKy);
            lstHocKy.set(index, hocKy);

//            HocKy.Insert(hocKy);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }
}
