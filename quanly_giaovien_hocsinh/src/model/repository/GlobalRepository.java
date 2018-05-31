package model.repository;

import java.sql.SQLException;

public class GlobalRepository {

    public static void synToDataBase() throws SQLException {
        RepositoryDiem.updateUnSynchronizedDataBase();
        RepositoryGiaoVien.updateUnSynchronizedDataBase();
        RepositoryXepLop.updateUnSynchronizedDataBase();
        RepositoryNamHoc.updateUnSynchronizedDataBase();
        RepositoryKhoiHoc.updateUnSynchronizedDataBase();
        RepositoryHocKy.updateUnSynchronizedDataBase();
        RepositoryMonHoc.updateUnSynchronizedDataBase();
        RepositoryLopHoc.updateUnSynchronizedDataBase();
        RepositoryPhanCong.updateUnSynchronizedDataBase();
        RepositoryHocSinh.updateUnSynchronizedDataBase();
    }
}
