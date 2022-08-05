package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.ThongKe;
import com.mycompany.quanlypizza.repository.ThongKeRepository;
import java.util.List;

public class ThongKeServiceImp implements ThongKeService{
    private ThongKeRepository thongKeRepository = new ThongKeRepository();
    private  List<Double> doanhThuThang;
    
    @Override
    public ThongKe thongKe(int nam) {
            return thongKeRepository.ThongKeRepository(nam);
    }

    @Override
    public double getDoanhThuThang(int thang, int nam) {
        return thongKeRepository.getDoanhThuThang(thang, nam);
    }
    
}
