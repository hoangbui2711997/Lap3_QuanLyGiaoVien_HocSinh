package model.repository;

import model.data.MonHoc;

import java.sql.SQLException;
import java.util.List;

public class RepositoryMonHoc {
    private static List<MonHoc> lstMonHoc;

    private RepositoryMonHoc() throws SQLException {

    }

    public synchronized static List<MonHoc> getAll() {
        try {
            if (lstMonHoc == null) {
                lstMonHoc = MonHoc.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstMonHoc;
        }
    }

    public synchronized static boolean add(MonHoc monHoc) {
        try {
            checkNull();

            lstMonHoc.add(monHoc);
            MonHoc.Insert(monHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstMonHoc == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(MonHoc monHoc) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(e -> e.getMaMH() == monHoc.getMaMH());

            if(isRemoved) {
                MonHoc.Delete.whereId(monHoc.getMaMH() + "");
            }
//            System.out.println(getAll().size());

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }
}
