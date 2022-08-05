/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.HoaDon;
import com.mycompany.quanlypizza.repository.HoaDonRepo;
import com.mycompany.quanlypizza.repository.KhachHangRespository;
import com.mycompany.quanlypizza.repository.NhanVienRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vinh Kute
 */
public class HoaDonServiceImp implements HoaDonService{
    private HoaDonRepo hoaDonRepo = new HoaDonRepo();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private KhachHangRespository khachHangRespository = new KhachHangRespository();
    
    @Override
    public List<HoaDon> getListHoaDon() {
        List<HoaDon> lhd = hoaDonRepo.getList();
        return lhd;
    }

    @Override
    public int getMaHoaDonMoiNhat() {
        return hoaDonRepo.getMaHoaDonMoiNhat();
    }

    @Override
    public Boolean addNew(int maKH, String nhanVien, int tongTien, String ghiChu) {
        HoaDon hd = new HoaDon();
        String[] arrNV = nhanVien.split(" - ");
        int maNV = Integer.parseInt(arrNV[0]);
        hd.setNhanVien(nhanVienRepository.getNV(maNV));
        hd.setKhachHang(khachHangRespository.getKhachHang(maKH));
        hd.setGhiChu(ghiChu);
        hd.setTongTien(tongTien);
        return hoaDonRepo.save(hd);
    }

    @Override
    public HoaDon getHoaDon(int maHD) {
        return hoaDonRepo.getById(maHD);
    }

    @Override
    public List<HoaDon> getListHoaDonTheoGia(int min, int max) {
        try {
            List<HoaDon> listHoaDon = hoaDonRepo.getList();
            List<HoaDon> dshd = new ArrayList<>();
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() > min && hd.getTongTien() < max)
                    dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            new MyDialogCommon("Hãy nhập khoảng giá hợp lệ", MyDialogCommon.ERROR_DIALOG);
        }
        return null;
    }

    @Override
    public List<HoaDon> getListHoaDonTheoNgay(String min, String max) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(min);
            Date maxDate = sdf.parse(max);

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            List<HoaDon> dshd = hoaDonRepo.getListTheoNgay(dateMin, dateMax);
            return dshd;
        } catch (Exception e) {
            new MyDialogCommon("Hãy nhập khoảng ngày hợp lệ!", MyDialogCommon.ERROR_DIALOG);
        }
        return null;
    }
    
}
