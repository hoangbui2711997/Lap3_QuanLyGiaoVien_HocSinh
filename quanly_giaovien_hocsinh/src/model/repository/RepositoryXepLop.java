package model.repository;

import model.data.XepLop;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryXepLop {
    private static List<XepLop> lstXepLop;

    private RepositoryXepLop() throws SQLException {

    }

    static void updateUnSynchronizedDataBase() throws SQLException {
        lstXepLop = XepLop.Search.getAll();
    }

    public static List<XepLop> getAll() {
        try {
            if (lstXepLop == null) {
                lstXepLop = XepLop.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstXepLop;
        }
    }

    public synchronized static boolean add(XepLop xepLop) {
        try {
            checkNull();

            lstXepLop.add(xepLop);
            XepLop.Insert(xepLop);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstXepLop == null) {
            getAll();
        }
    }

    // Đây là xóa của giáo viên - 1 khóa
    public synchronized static boolean del(XepLop xepLop) {
        try {
            checkNull();

            boolean isRemoved = getAll().removeIf(gv1 -> gv1.getMaHS() == xepLop.getMaHS());

            if(isRemoved) {
                XepLop.Delete.whereId(xepLop.getMaHS() + "", xepLop.getMaLH() + "");
            }
//            System.out.println(getAll().size());

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    /**
     * @param maHocSinh nhap id muon tim kiem
     * @param maLopHoc nhap id muon tim kiem
     * @return Trả về danh sách các đối tượng tìm thấy qua id đã đưa
     */
    public synchronized static Iterable<XepLop> find(String maHocSinh, String maLopHoc) {
        checkNull();

        Iterable<XepLop> readOnlyCollection = lstXepLop.stream()
                .filter(gv -> (gv.getMaHS() + "").contains(maHocSinh)
                        && (gv.getMaLH() + "").contains(maLopHoc)
                )
                .collect(Collectors.toList());
        if (((List<XepLop>) readOnlyCollection).size() == 0) {
            return null;
        } else {
            return readOnlyCollection;
        }
    }

//    public static boolean edit(XepLop xepLop) {
//        try {
//            checkNull();
//
//            XepLop.Update.whereId(xepLop.getMaHS(), xepLop);
//            XepLop oldXepLop =
//                    lstXepLop
//                            .stream()
//                            .filter(e -> e.getMaHS() == xepLop.getMaHS())
//                            .findFirst()
//                            .get();
//
//            int index = lstXepLop.indexOf(oldXepLop);
//            lstXepLop.set(index, xepLop);
//
////            XepLop.Insert(xepLop);
//        } catch (Exception e) {
//            return false;
//        } finally {
//            return true;
//        }
//    }

}
