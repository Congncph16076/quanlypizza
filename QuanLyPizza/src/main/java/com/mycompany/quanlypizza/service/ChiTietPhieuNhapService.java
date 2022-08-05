package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.CTPhieuNhap;
import java.util.List;


public interface ChiTietPhieuNhapService {
    public  List<CTPhieuNhap> getListCTPhieuNhap();
    public  List<CTPhieuNhap> getListCTPhieuNhap(String maPN);
    public  Boolean luuCTPhieuNhap(CTPhieuNhap ctpn);
}
