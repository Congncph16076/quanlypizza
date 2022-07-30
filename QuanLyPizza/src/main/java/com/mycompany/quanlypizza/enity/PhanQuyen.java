package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phanquyen")
public class PhanQuyen implements Serializable{
    @Id
    @Column(name = "Quyen", unique = true, nullable = false)
    private String quyen;
    
    @Column(name = "NhapHang")
    private int nhapHang;
    
    @Column(name = "QlSanPham")
    private int qlSanPham;
    
    @Column(name = "QlNhanVien")
    private int qlNhanVien;
    
    @Column(name = "QlKhachHang")
    private int qlKhachHang;
    
    @Column(name = "ThongKe")
    private int thongKe;

    public PhanQuyen() {
    }

    public PhanQuyen(String quyen, int nhapHang, int qlSanPham, int qlNhanVien, int qlKhachHang, int thongKe) {
        this.quyen = quyen;
        this.nhapHang = nhapHang;
        this.qlSanPham = qlSanPham;
        this.qlNhanVien = qlNhanVien;
        this.qlKhachHang = qlKhachHang;
        this.thongKe = thongKe;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public int getNhapHang() {
        return nhapHang;
    }

    public void setNhapHang(int nhapHang) {
        this.nhapHang = nhapHang;
    }

    public int getQlSanPham() {
        return qlSanPham;
    }

    public void setQlSanPham(int qlSanPham) {
        this.qlSanPham = qlSanPham;
    }

    public int getQlNhanVien() {
        return qlNhanVien;
    }

    public void setQlNhanVien(int qlNhanVien) {
        this.qlNhanVien = qlNhanVien;
    }

    public int getQlKhachHang() {
        return qlKhachHang;
    }

    public void setQlKhachHang(int qlKhachHang) {
        this.qlKhachHang = qlKhachHang;
    }

    public int getThongKe() {
        return thongKe;
    }

    public void setThongKe(int thongKe) {
        this.thongKe = thongKe;
    }

}
