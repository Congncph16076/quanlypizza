/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.HoaDon;
import java.util.List;

/**
 *
 * @author Vinh Kute
 */
public interface HoaDonService {
    List<HoaDon> getListHoaDon();
    int getMaHoaDonMoiNhat();
    Boolean addNew(int maKH, String nhanVien, int tongTien, String ghiChu);
    HoaDon getHoaDon(int maHD);
    List<HoaDon> getListHoaDonTheoGia(int min, int max);
    List<HoaDon> getListHoaDonTheoNgay(String min, String max);
}
