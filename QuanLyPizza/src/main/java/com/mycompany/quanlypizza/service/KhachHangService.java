/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.KhachHang;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public interface KhachHangService {
    public void docDanhSach();
    public List<KhachHang> getListKhachHang();
    public List<KhachHang> timKiemKhachHang(String tuKhoa);
    public List<KhachHang> timKiemKhachHang(String txtMin, String txtMax);
    public boolean themKhachHang(String ho, String ten,String sdt, String gioiTinh);
    public boolean suaKhachHang(String ma, String ho, String ten,String sdt, String gioiTinh);
    public boolean xoaKhachHang(String ma);
}
