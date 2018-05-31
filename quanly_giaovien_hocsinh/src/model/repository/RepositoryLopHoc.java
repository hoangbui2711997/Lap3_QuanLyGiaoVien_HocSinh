package model.repository;


import model.data.LopHoc;

import java.sql.SQLException;
import java.util.List;

public class RepositoryLopHoc {
    private static List<LopHoc> lstLopHoc;

    private RepositoryLopHoc() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstLopHoc = LopHoc.Search.getAll();
    }

    public static List<LopHoc> getAll() {
        try {
            if (lstLopHoc == null) {
                lstLopHoc = LopHoc.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstLopHoc;
        }
    }

    public synchronized static boolean add(LopHoc lopHoc) {
        try {
            checkNull();

            lstLopHoc.add(lopHoc);
            LopHoc.Insert(lopHoc);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstLopHoc == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(LopHoc lopHoc) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(e -> e.getMaLH() == lopHoc.getMaLH());

            if(isRemoved) {
                LopHoc.Delete.whereId(lopHoc.getMaLH() + "");
            }
//            System.out.println(getAll().size());

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }
}

