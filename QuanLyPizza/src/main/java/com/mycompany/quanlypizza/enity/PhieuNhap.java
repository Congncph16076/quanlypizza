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
@Table(name = "phieunhap")
public class PhieuNhap implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaPN", unique = true, nullable = false)
    private int maPN;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNCC", referencedColumnName = "MaNCC")
    private NhaCungCap nhaCungCap;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    private NhanVien nhanVien;
    
    @Column(name = "NgayLap")
    private Date ngayLap;
    
    @Column(name = "TongTien")
    private int tongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(int maPN, NhaCungCap nhaCungCap, NhanVien nhanVien, Date ngayLap, int tongTien) {
        this.maPN = maPN;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }
    
    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
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

    
}
