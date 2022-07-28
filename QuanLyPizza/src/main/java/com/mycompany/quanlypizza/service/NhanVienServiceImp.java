package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.repository.NhanVienRepository;
import java.util.ArrayList;
import java.util.List;

public class NhanVienServiceImp implements NhanVienService {

    private NhanVienRepository nvRepository;
    List<NhanVien> listNV;

    @Override
    public List<NhanVien> getDSNV() {
        nvRepository = new NhanVienRepository();
        listNV = nvRepository.getListNV();
        return listNV;
    }

    @Override
    public Boolean themNV(String ho, String ten, String gioiTinh, String chucVu) {
        nvRepository = new NhanVienRepository();
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ho.equals("")) {
            new MyDialogCommon("Họ không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialogCommon("Tên không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialogCommon("Chức vụ không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }

        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvRepository.themNV(nv);
        if (!flag) {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        } else {
            new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        }
    }

    @Override
    public Boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu) {
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvRepository.nhapNVTuExcel(nv);
        return flag;
    }

    @Override
    public Boolean suaNV(String ma, String ho, String ten, String gioiTinh, String chucVu) {
        nvRepository = new NhanVienRepository();
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ho.equals("")) {
            new MyDialogCommon("Họ không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialogCommon("Tên không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialogCommon("Chức vụ không được để trống!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }

        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvRepository.suaNV(nv);
        if (!flag) {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        } else {
            new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        }
    }

    @Override
    public Boolean xoaNV(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            NhanVien nv = nvRepository.getNV(maNV);
            MyDialogCommon dlg = new MyDialogCommon("Bạn có chắc chắn muốn xoá?", MyDialogCommon.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialogCommon.OK_OPTION) {
                flag = nvRepository.xoaNV(nv);
                if (flag) {
                    new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
                } else {
                    new MyDialogCommon("Xoá thất bại!", MyDialogCommon.ERROR_DIALOG);
                }
            }

        } catch (Exception e) {
            new MyDialogCommon("Chưa chọn nhân viên!", MyDialogCommon.ERROR_DIALOG);
        }
        return false;
    }

    @Override
    public Boolean getNV(NhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhanVien> timNV(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        List<NhanVien> dsnv = new ArrayList<>();
        listNV = nvRepository.getListNV();
        for (NhanVien nv : listNV) {

            if (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa)
                    || nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
//            while (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa)
//                    || nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa)) {
//               dsnv.add(nv);
//                
//            }
            return dsnv;
        }
        return null;
    }

}
