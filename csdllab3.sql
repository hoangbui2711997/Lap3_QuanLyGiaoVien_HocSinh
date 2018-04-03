create database QLHocSinhVaGiaoVien
GO

use master
drop database QLHocSinhVaGiaoVien

use QLHocSinhVaGiaoVien
GO

create table HocSinh(
	MaHS int primary key identity(1,1),
	GioiTinh nchar(4) check (GioiTinh IN (N'Nam',N'Nu')),
	HoTen nvarchar(50),
	NgaySinh date,
	DiaChi nvarchar(50),
	DienThoai varchar(15),
)
go

create table GiaoVien(
	MaGV int primary key identity(1, 1),
	GioiTinh nchar(4) check (GioiTinh IN (N'Nam',N'Nu')),
	HoTen nvarchar(50),
	DiaChi nvarchar(50),
	NgaySinh date,
	DienThoai varchar(15),
	CMND varchar(15),
	matkhau nvarchar(20),
	role int
)
go

create table NamHoc(
	MaNH int primary key identity(1, 1),
	TenNH nvarchar(20),
	Nambatdau int,
	Namketthuc int,
)
go

create table HocKy(
	MaHK int primary key identity(1, 1),
	TenHK nvarchar(20),
	ThangBatDau int,
	NgayBatDau int,
	ThangKetThuc int,
	NgayKetThuc int,
)
go

create table KhoiHoc(
	MaKH int primary key identity(1, 1),
	TenKH nvarchar(50),
)
go

create table LopHoc(
	MaLH int primary key identity(1, 1),
	TenLH nvarchar(50),
	MaNH int references NamHoc(MaNH),
	MaGV int references GiaoVien(MaGV),
	MaKH int references KhoiHoc(MaKH),
)
go

create table MonHoc(
	MaMH int primary key identity(1, 1),
	TenMH nvarchar(50),
)
go

create table PhanCong(
	MaPC nvarchar(20) primary key,
	MaMH int references MonHoc(MaMH),
	MaGV int references GiaoVien(MaGV),
	MaLH int references LopHoc(MaLH),
	MaHK int references HocKy(MaHK)
)
go

create table XepLop(
    MaHS int references HocSinh(MaHS),
    MaLH int references LopHoc(MaLH),
    primary key(MaHS, MaLH)
)
go

create table Diem(
	MaHS int references HocSinh(MaHS),
	MaPC nvarchar(20) references PhanCong(MaPC),
	DiemHS1 int check(DiemHS1 >= 0),
	DiemHS2 int check(DiemHS2 >= 0),
	DiemHS3 int check(DiemHS3 >= 0),
	primary key(MaHS, MaPC)
)
go
