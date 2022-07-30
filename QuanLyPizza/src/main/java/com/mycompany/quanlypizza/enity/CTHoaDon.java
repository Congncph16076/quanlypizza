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
@Table(name = "cthoadon")
public class CTHoaDon implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaCTHD", unique = true, nullable = false)
    private int maCTHD;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaHD", referencedColumnName = "MaHD")
    private HoaDon hoaDon;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaSP", referencedColumnName = "MaSP")
    private SanPham sanPham;
    
    @Column(name = "SoLuong")
    private int soLuong;
    
    @Column(name = "DonGia")
    private int donGia;
    
    @Column(name = "ThanhTien")
    private int thanhTien;

    public CTHoaDon() {
    }

    public CTHoaDon(int maCTHD, HoaDon hoaDon, SanPham sanPham, int soLuong, int donGia, int thanhTien) {
        this.maCTHD = maCTHD;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    
}
