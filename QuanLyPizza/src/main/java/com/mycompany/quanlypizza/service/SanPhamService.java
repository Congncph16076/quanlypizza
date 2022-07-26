
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.SanPham;
import java.util.List;


public interface SanPhamService {
    public  List<SanPham> getListSP();
    public  SanPham getListSPByID(String ma);
    public  List<SanPham> getListSPByCategory(String maloai);
    public  List<SanPham> getListSPByName(String tenSP);
    String getAnh(int ma);
    Boolean themSP(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia);
    Boolean nhapSPTuExcel(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia);
    Boolean suaSP(String ma,
            String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia);
    Boolean xoaSP(String ma );
}
