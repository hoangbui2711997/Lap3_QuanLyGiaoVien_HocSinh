package model.repository;

import model.data.Diem;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryDiem {

    private static List<Diem> lstDiem;

    private RepositoryDiem() throws SQLException {

    }

    public synchronized static List<Diem> getAll() {
        try {
            if (lstDiem == null) {
                lstDiem = Diem.Search.getAll();
            }
        } catch (Exception e) {
            return null;
        } finally {
            return lstDiem;
        }
    }

    public synchronized static boolean add(Diem diem) {
        try {
            checkNull();

            lstDiem.add(diem);
            Diem.Insert(diem);
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    private static void checkNull() {
        if (lstDiem == null) {
            getAll();
        }
    }

    /**
     *
     * @param diem Đây là tham số điểm
     * @return
     */
    public synchronized static boolean del(Diem diem) {
        try {
            checkNull();

            // Giờ truyền tham số theo hàm vừa xong. Cái remove if này là dùng lambda @@! chịu khó tìm hiểu hoặc là làm theo
            // Sai cũng được :)) Miễn là ông giải thích được cho thầy
            // Tham số xóa là String maHS, String maPC

            boolean isRemoved = getAll().removeIf(
//                    Cái này có nghĩa là tìm những Điểm có Mã Học Sinh với Mã Phân công trùng với tham số điểm truyền vào ở trên
                    e -> e.getMaHS() == diem.getMaHS() && e.getMaPC().equals(diem.getMaPC())
            );

            if(isRemoved) {
                // 2 tham số giống ở trên - và theo thứ tự -- Chú ý là nếu có 2 khóa. Còn 1 khóa thì xóa như bình thường.
                // Bên Giáo viên có xóa 1 khóa rồi nhé
                Diem.Delete.whereId(diem.getMaHS() + "", diem.getMaPC() + "");
            }
//            System.out.println(getAll().size());
//Ok đến đây là xong phần Điểm Repository
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
//    public synchronized static Iterable<Diem> find(String id) {
//        checkNull();
//
//        Iterable<Diem> readOnlyCollection = lstDiem.stream()
//                .filter(gv -> (gv.getMaGV() + "").contains(id))
//                .collect(Collectors.toList());
//        if (((List<Diem>) readOnlyCollection).size() == 0) {
//            return null;
//        } else {
//            return readOnlyCollection;
//        }
//    }
//
//    public static boolean edit(Diem diem) {
//        try {
//            checkNull();
//
//            Diem.Update.where(diem.getMaGV() + "", diem);
//            Diem oldDiem =
//                    lstDiem
//                            .stream()
//                            .filter(e -> e.getMaGV() == diem.getMaGV())
//                            .findFirst()
//                            .get();
//
//            int index = lstDiem.indexOf(Diem);
//            lstDiem.set(index, diem);
//
//            Diem.Insert(diem);
//        } catch (Exception e) {
//            return false;
//        } finally {
//            return true;
//        }
//    }
}
