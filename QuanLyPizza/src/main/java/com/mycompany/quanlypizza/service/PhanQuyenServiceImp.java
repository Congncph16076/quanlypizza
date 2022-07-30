/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.enity.PhanQuyen;
import com.mycompany.quanlypizza.repository.PhanQuyenRepository;
import java.util.ArrayList;
import java.util.List;

public class PhanQuyenServiceImp  implements PhanQuyenService{

    public static PhanQuyen quyenTK = null;
    private PhanQuyenRepository pQuyenRepository;
    private List<PhanQuyen> lstPQ;
    
    @Override
    public List<PhanQuyen> getDanhSachQuyen() {
        try {
            pQuyenRepository = new PhanQuyenRepository();
            lstPQ = pQuyenRepository.getDanhSach();
            return lstPQ;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Boolean themQuyen(String tenQuyen) {
        pQuyenRepository = new PhanQuyenRepository();
        if (tenQuyen == null || tenQuyen.trim().equals("")) {
            return false;
        }
        if (kiemTraTonTaiQuyen(tenQuyen)) {
            new MyDialogCommon("Thất bại! Quyền đã tồn tại", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        PhanQuyen pq = new PhanQuyen(tenQuyen, 0, 0, 0, 0, 0);
        Boolean flag = pQuyenRepository.themQuyen(pq);
        if (flag) {
            new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        }else{
             new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        
    }

    @Override
    public Boolean suaQuyen(String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
        pQuyenRepository = new PhanQuyenRepository();
        PhanQuyen pq = new PhanQuyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        Boolean flag = pQuyenRepository.suaQuyen(pq);
         if (flag) {
            new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        }else{
             new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
    }

    @Override
    public Boolean xoaQuyen(String tenQuyen) {
        Boolean flag = pQuyenRepository.xoaQuyen(tenQuyen);
         if (flag) {
            new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        }else{
             new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
    }

    @Override
    public PhanQuyen Quyen(String tenQuyen) {
        pQuyenRepository = new PhanQuyenRepository();
        quyenTK = pQuyenRepository.getQuyen(tenQuyen);
        return quyenTK;
    }
    
    private  boolean kiemTraTonTaiQuyen(String quyen){
        getDanhSachQuyen();
        lstPQ = new ArrayList<>();
        for (PhanQuyen q : lstPQ) {
            if (q.getQuyen().equalsIgnoreCase(quyen)) {
                return true;
            }
        }
        return false;
    }
    
}
