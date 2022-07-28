package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.NhanVien;
import java.util.List;

public interface NhanVienService {
    public List<NhanVien> getDSNV ();
    public Boolean themNV(String ho, String ten, String gioiTinh, String chucVu);
    public Boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu);
    public Boolean suaNV(String ma,String ho, String ten, String gioiTinh, String chucVu);
    public Boolean xoaNV(String ma);
    public Boolean getNV(NhanVien nv);
    public List<NhanVien> timNV(String tuKhoa);
    
}
