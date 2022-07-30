
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.TaiKhoan;

public interface TaiKhoanService {
    public  String getTenDNTheoMa(String ma);
    public  String getQuyen(String ma);
    public  void datLaiMK(String ma, String tenDN);
    public  void datLaiQuyen(String ma, String quyen);
    public  Boolean kiemTraTrungTenDangNhap(String tenDN);
    public  Boolean themTaiKhoan(String ma, String tenDangNhap, String quyen);
    public  void khoaTaiKhoan(String ma);
    public  void moKhoaTaiKhoan(String ma);
    public  boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau);
    public  int getTrangThai(int ma);
}
