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
@Table(name = "sanpham")
public class SanPham implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaSP", unique = true, nullable = false)
    private int maSP;
    
    @Column(name = "TenSP")
    private String tenSP;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaLoai", referencedColumnName = "MaLoai")
    private LoaiSP loaiSP;
    
    @Column(name = "SoLuong")
    private int soLuong;
    
    @Column(name = "DonViTinh")
    private String donViTinh;
    
    @Column(name = "HinhAnh")
    private String hinhAnh;
    
    @Column(name = "DonGia")
    private int donGia;

    public SanPham() {

    }

    public SanPham(int maSP, String tenSP, LoaiSP loaiSP, int soLuong, String donViTinh, String hinhAnh, int donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public LoaiSP getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(LoaiSP loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    
}
