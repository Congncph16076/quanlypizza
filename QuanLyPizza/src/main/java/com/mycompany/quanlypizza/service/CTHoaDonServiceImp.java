/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.CTHoaDon;
import com.mycompany.quanlypizza.repository.CTHoaDonRepo;
import com.mycompany.quanlypizza.repository.HoaDonRepo;
import com.mycompany.quanlypizza.repository.SanPhamRespository;
import com.mycompany.quanlypizza.service.CTHoaDonService;
import java.util.List;

/**
 *
 * @author Vinh Kute
 */
public class CTHoaDonServiceImp implements CTHoaDonService{
    private CTHoaDonRepo cthdRepo = new CTHoaDonRepo();
    private HoaDonRepo hdRepo = new HoaDonRepo();
    private SanPhamRespository sanPhamRespository = new SanPhamRespository();
    private HoaDonServiceImp hoaDonServiceImp = new HoaDonServiceImp();
    
    @Override
    public List<CTHoaDon> getList() {
        List<CTHoaDon> cthd =cthdRepo.getList();
        return cthd;
    }

    @Override
    public Boolean addNew(String maSP, String soLuong, String donGia, String thanhTien) {
        int ma = hoaDonServiceImp.getMaHoaDonMoiNhat();

        donGia = donGia.replace(",","");
        thanhTien = thanhTien.replace(",", "");

        CTHoaDon cthd = new CTHoaDon();

        cthd.setHoaDon(hdRepo.getById(ma));
        cthd.setSanPham(sanPhamRespository.getSPByID(Integer.parseInt(maSP)));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));
        return cthdRepo.save(cthd);
    }

    @Override
    public List<CTHoaDon> getListByMaHD(int maHD) {
        return cthdRepo.getByMaHD(maHD);
    }

    @Override
    public Boolean update(CTHoaDon cthd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CTHoaDon getById(int cthd) {
        return cthdRepo.getById(cthd);
    }
    
}
