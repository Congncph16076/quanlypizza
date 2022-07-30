/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.KhachHang;
import com.mycompany.quanlypizza.repository.KhachHangRespository;
import com.mycompany.quanlypizza.util.Connect;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class KhachHangServiceImp implements KhachHangService {

    Session sesion = Connect.getFACTORY().openSession();
    List<KhachHang> listKhachHang;
    KhachHangRespository khachHangRepo = new KhachHangRespository();

    @Override
    public void docDanhSach() {
        this.listKhachHang = khachHangRepo.getListKhachHang();
    }

    @Override
    public List<KhachHang> getListKhachHang() {
        if (listKhachHang == null)
            docDanhSach();
//        listKhachHang = khachHangRepo.getListKhachHang();
        return listKhachHang;
    }

    @Override
    public List<KhachHang> timKiemKhachHang(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        for (KhachHang kh : listKhachHang) {
            String ho = kh.getHo().toLowerCase();
            String ten = kh.getTen().toLowerCase();
            String gioiTinh = kh.getGioiTinh().toLowerCase();
            if (ho.contains(tuKhoa) || ten.contains(tuKhoa) || gioiTinh.contains(tuKhoa)) {
                dskh.add(kh);
            }
        }
        return dskh;
    }

    @Override
    public List<KhachHang> timKiemKhachHang(String txtMin, String txtMax) {
        if (txtMax.trim().equals("") && txtMin.trim().equals("")) {
            return (List<KhachHang>) listKhachHang;
        }
        try {
            ArrayList<KhachHang> dskh = new ArrayList<>();
            txtMin = txtMin.replace(",", "");
            txtMax = txtMax.replace(",", "");
            int min = Integer.parseInt(txtMin);
            int max = Integer.parseInt(txtMax);
            for (KhachHang kh : listKhachHang) {
                if (kh.getTongChiTieu() >= min && kh.getTongChiTieu() <= max) {
                    dskh.add(kh);
                }
            }
            return dskh;
        } catch (Exception e) {
            new MyDialogCommon("Hãy nhập giá trị nguyên phù hợp!", MyDialogCommon.ERROR_DIALOG);
        }
        return null;
    }

    @Override
    public boolean themKhachHang(String ho, String ten, String sdt, String gioiTinh) {
        if (ten.trim().equals("")) {
            new MyDialogCommon("Không được để trống tên!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chọn giới tính")) {
            new MyDialogCommon("Hãy chọn giới tính!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setSdt(sdt);
        kh.setGioiTinh(gioiTinh);
        kh.setTongChiTieu(0);
        boolean flag = khachHangRepo.addKhachHang(kh);
        if (flag) {
            new MyDialogCommon("Thêm thành công!", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Thêm thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
    }

    @Override
    public boolean suaKhachHang(String ma, String ho, String ten, String sdt, String gioiTinh) {
        int maKH = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        if (ten.trim().equals("")) {
            new MyDialogCommon("Không được để trống tên!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chọn giới tính")) {
            new MyDialogCommon("Hãy chọn giới tính!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        KhachHang kh = new KhachHang();
        kh.setMaKH(maKH);
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setSdt(sdt);
        kh.setGioiTinh(gioiTinh);
        boolean flag = khachHangRepo.updateKhachHang(kh);
        if (flag) {
            new MyDialogCommon("Sửa thành công!", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Sửa thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
    }

    @Override
    public boolean xoaKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            MyDialogCommon dlg = new MyDialogCommon("Bạn có chắc chắn muốn xoá?", MyDialogCommon.WARNING_DIALOG);
            if (dlg.getAction() == MyDialogCommon.CANCEL_OPTION) {
                return false;
            }
            flag = khachHangRepo.deleteKhachHang(maKH);
        } catch (Exception e) {
            new MyDialogCommon("Chưa chọn khách hàng!", MyDialogCommon.ERROR_DIALOG);
        }
        if (flag) {
            new MyDialogCommon("Xoá thành công!", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Xoá thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
    }
}
