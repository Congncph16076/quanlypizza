/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.CTHoaDon;
import java.util.List;

/**
 *
 * @author Vinh Kute
 */
public interface CTHoaDonService {
    List<CTHoaDon> getList();

    Boolean addNew(String maSP, String soLuong, String donGia, String thanhTien);

    List<CTHoaDon> getListByMaHD(int maHD);

    Boolean update(CTHoaDon cthd);

    CTHoaDon getById(int cthd);
}
