package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "hoadon")
public class HoaDon implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaHD", unique = true, nullable = false)
    private int maHD;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaKH", referencedColumnName = "MaKH")
    private KhachHang khachHang;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    private NhanVien nhanVien;
    
    @Column(name = "NgayLap")
    private Date ngayLap;
    
    @Column(name = "TongTien")
    private int tongTien;
    
    @Column(name = "GhiChu")
    private String ghiChu;

    public HoaDon() {
    }

    public HoaDon(int maHD, KhachHang khachHang, NhanVien nhanVien, Date ngayLap, int tongTien, String ghiChu) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
}
