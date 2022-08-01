
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.PhieuNhap;
import java.util.List;

public interface PhieuNhapService {
    public List<PhieuNhap> getListPhieuNhap();
    public Boolean themPhieuNhap(String nhaCungCap, String nhanVien, int tongTien);
    public  int getLastID();
    public PhieuNhap timPhieuNhap(String maPN);
    public  List<PhieuNhap> getListPhieuNhapTheoGia(String giaThap, String giaCao);
    public  List<PhieuNhap> getListPhieuNhapTheoNgay(String tuNgay, String denNgay);
    
    
}
