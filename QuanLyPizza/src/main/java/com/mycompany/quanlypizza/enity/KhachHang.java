package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class KhachHang implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MaKH", unique = true, nullable = false)
    private int maKH;
    
    private String ho;
    
    private String ten;
    
    @Column(name = "GioiTinh")
    private String gioiTinh;
    
    private String sdt;

    @Column(name = "TongChiTieu")
    private int tongChiTieu;
    
    @Column(name = "TinhTrang")
    private int tinhTrang;
    
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public KhachHang(int maKH, String ho, String ten, String gioiTinh, String sdt, int tongChiTieu) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.tongChiTieu = tongChiTieu;
    }
    

    public KhachHang() {
    }

    public KhachHang(int maKH, String ho, String ten, String gioiTinh, int tongChiTieu) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.tongChiTieu = tongChiTieu;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
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

    public int getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(int tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }


}
