package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaNCC", unique = true, nullable = false)
    private int maNCC;
    
    @Column(name = "TenNCC")
    private String tenNCC;
    
    @Column(name = "DiaChi")
    private String diaChi;
    
    @Column(name = "DienThoai")
    private String dienThoai;

    public NhaCungCap() {
    }

    public NhaCungCap(int maNCC, String tenNCC, String diaChi, String dienThoai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
