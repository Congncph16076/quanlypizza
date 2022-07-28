package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaTK", unique = true, nullable = false)
    private int maTK;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    private NhanVien nhanVien;
    
    @Column(name = "TenDangNhap")
    private String tenDangNhap;
    
    @Column(name = "MatKhau")
    private String matKhau;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Quyen", referencedColumnName = "Quyen")
    private PhanQuyen phanQuyen;
    
     @Column(name = "TrangThai")
    private String trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(int maTK, NhanVien nhanVien, String tenDangNhap, String matKhau, PhanQuyen phanQuyen, String trangThai) {
        this.maTK = maTK;
        this.nhanVien = nhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.trangThai = trangThai;
    }

    
    public TaiKhoan(int maTK, NhanVien nhanVien, String tenDangNhap, String matKhau, PhanQuyen phanQuyen) {
        this.maTK = maTK;
        this.nhanVien = nhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public PhanQuyen getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
