package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.NhaCungCap;
import java.util.List;

public interface NhaCungCapService {
    public  List<NhaCungCap> getListNCC();
    public  boolean themNCC(String tenNCC, String diaChi, String dienThoai);
    public  boolean suaNCC(String maNCC,String tenNCC, String diaChi, String dienThoai);
}
