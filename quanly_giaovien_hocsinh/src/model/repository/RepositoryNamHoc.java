package model.repository;

import model.data.NamHoc;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryNamHoc {
    private static List<NamHoc> lstNamHoc;

    private RepositoryNamHoc() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstNamHoc = NamHoc.Search.getAll();
    }

    public static List<NamHoc> getAll() {
        try {
            if (lstNamHoc == null) {
                lstNamHoc = NamHoc.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstNamHoc;
        }
    }

    public synchronized static boolean add(NamHoc namHoc) {
        try {
            checkNull();

            lstNamHoc.add(namHoc);
            NamHoc.Insert(namHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstNamHoc == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(NamHoc namHoc) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaNH() == namHoc.getMaNH());

            if(isRemoved) {
                NamHoc.Delete.whereId(namHoc.getMaNH() + "");
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
    public synchronized static Iterable<NamHoc> find(String id) {
        checkNull();

        Iterable<NamHoc> readOnlyCollection = lstNamHoc.stream()
                .filter(gv -> (gv.getMaNH() + "").contains(id))
                .collect(Collectors.toList());
        if (((List<NamHoc>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

    public static boolean edit(NamHoc namHoc) {
        try {
            checkNull();

            NamHoc.Update.whereId(namHoc.getMaNH(), namHoc);
            NamHoc oldNamHoc =
                    lstNamHoc
                            .stream()
                            .filter(e -> e.getMaNH() == namHoc.getMaNH())
                            .findFirst()
                            .get();

            int index = lstNamHoc.indexOf(oldNamHoc);
            lstNamHoc.set(index, namHoc);

//            NamHoc.Insert(namHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

}
