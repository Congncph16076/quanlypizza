package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class NhanVien implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaNV", unique = true, nullable = false)
    private int maNV;
    
    private String ho;
    
    private String ten;
    
    @Column(name = "GioiTinh")
    private String gioiTinh;
    
    @Column(name = "ChucVu")
    private String chucVu;

    public NhanVien() {
    }

    public NhanVien(int maNV, String ho, String ten, String gioiTinh, String chucVu) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

}
