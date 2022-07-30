package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.repository.GiamGiaRespository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiamGiaServiceImp implements GiamGiaService {

    private GiamGiaRespository giamGiaRespository;
    private List<GiamGia> listGiamGia = null;
    @Override
    public List<GiamGia> getDanhSachMaGiam() {
        giamGiaRespository = new GiamGiaRespository();
        listGiamGia =giamGiaRespository.getList();
        return listGiamGia;
    }

    @Override
    public boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        giamGiaRespository = new GiamGiaRespository();
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        boolean flag = false;
        if (ten.equals("")) {
            new MyDialogCommon("Vui lòng nhập tên chương trình khuyến mại", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialogCommon("Ngày kết thúc không hợp lệ", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        try {
            int phamTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phamTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);
            flag = giamGiaRespository.save(gg);

        } catch (Exception e) {
            new MyDialogCommon("Vui lòng nhập số nguyên hợp lệ", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Thất bại", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
    }

    @Override
    public boolean suaMaGiam(String ma,String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        giamGiaRespository = new GiamGiaRespository();
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        boolean flag = false;
        if (ma.equals("")) {
            new MyDialogCommon("Vui lòng chọn chương trình giảm giá", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialogCommon("Vui lòng nhập tên chương trình khuyến mại", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialogCommon("Ngày kết thúc không hợp lệ", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        try {
            int maGiam = Integer.parseInt(ma);
            int phamTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setMaGiam(maGiam);
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phamTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);
            flag = giamGiaRespository.suaMaGiamGia(gg);

        } catch (Exception e) {
            new MyDialogCommon("Vui lòng nhập số nguyên hợp lệ", MyDialogCommon.ERROR_DIALOG);
            e.printStackTrace();
            return false;
        }
        if (flag) {
            new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Thất bại", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
        
        
    }

}
