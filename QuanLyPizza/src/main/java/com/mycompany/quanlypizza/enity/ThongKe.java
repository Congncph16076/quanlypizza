/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.enity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author User
 */
public class ThongKe implements Serializable{
    public BigInteger soLuongSP;
    public BigInteger soLuongKH;
    public BigInteger soLuongNV;
    public int[] tongThuQuy;
    public List<SanPham> topSanPhamBanChay;

    public ThongKe() {
    }

    public ThongKe(BigInteger soLuongSP, BigInteger soLuongKH, BigInteger soLuongNV, int[] tongThuQuy, ArrayList<SanPham> topSanPhamBanChay) {
        this.soLuongSP = soLuongSP;
        this.soLuongKH = soLuongKH;
        this.soLuongNV = soLuongNV;
        this.tongThuQuy = tongThuQuy;
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    public BigInteger getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(BigInteger soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public BigInteger getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(BigInteger soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public BigInteger getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(BigInteger soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int getTongThuQuy(int quy) {
        return tongThuQuy[quy - 1];
    }

    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }
    
    public int getTongDoanhThu() {
        int tong = 0;
        for (int i = 0; i < 4; i++) {
            tong += tongThuQuy[i];
        }
        return tong;
    }

    public List<SanPham> getTopSanPhamBanChay() {
        return topSanPhamBanChay;
    }

    public void setTopSanPhamBanChay(List<SanPham> topSanPhamBanChay) {
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    
}
