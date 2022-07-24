
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.GiamGia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface GiamGiaService {
    List<GiamGia> getDanhSachMaGiam();
    
    boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT);
    
    boolean suaMaGiam(String ma,String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT);
    
    
}
