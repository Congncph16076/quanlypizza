
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.LoaiSP;
import java.util.List;


public interface LoaiService {
    List<LoaiSP> getDanhSach();
    Boolean themLoai(int maLoai, String tenLoai);
    Boolean suaLoai(String maLoai , String ten);
    Boolean xoaLoai(String maLoai);
}
