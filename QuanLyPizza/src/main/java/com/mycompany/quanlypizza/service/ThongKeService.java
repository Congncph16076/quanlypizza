
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.ThongKe;

public interface ThongKeService {
    public ThongKe thongKe(int nam);
    public  double  getDoanhThuThang(int thang, int nam);
}
