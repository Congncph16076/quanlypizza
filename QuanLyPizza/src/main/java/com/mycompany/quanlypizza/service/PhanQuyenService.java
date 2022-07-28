package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.enity.PhanQuyen;
import java.util.List;


public interface PhanQuyenService {
     List<PhanQuyen> getDanhSachQuyen();
     Boolean themQuyen(String tenQuyen);
     Boolean suaQuyen(String tenQuyen
             , int nhapHang, int sanPham
             , int nhanVien, int khachHang
             , int thongKe);
     Boolean xoaQuyen(String tenQuyen);
     PhanQuyen Quyen(String tenQuyen);
}
