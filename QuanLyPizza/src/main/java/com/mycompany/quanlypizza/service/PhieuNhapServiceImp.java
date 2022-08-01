package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.PhieuNhap;
import com.mycompany.quanlypizza.repository.NhaCungCapRepository;
import com.mycompany.quanlypizza.repository.NhanVienRepository;
import com.mycompany.quanlypizza.repository.PhieuNhapRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuNhapServiceImp implements PhieuNhapService {

    PhieuNhapRepository phieuNhapRepository = new PhieuNhapRepository();
    NhaCungCapRepository nccRepository = new NhaCungCapRepository();
    NhanVienRepository nvRepository = new NhanVienRepository();
    List<PhieuNhap> listPhieuNhap;

    public PhieuNhapServiceImp() {
        getListPhieuNhap();
    }

    public void docDanhSach() {
        this.listPhieuNhap = phieuNhapRepository.getListPhieuNhap();
    }
    @Override
    public List<PhieuNhap> getListPhieuNhap() {
        listPhieuNhap = phieuNhapRepository.getListPhieuNhap();
        return listPhieuNhap;
    }

    @Override
    public Boolean themPhieuNhap(String nhaCungCap, String nhanVien, int tongTien) {
        String[] NCC = nhaCungCap.split(" - ");
        String[] NV = nhanVien.split(" - ");
        int maNCC = Integer.parseInt(NCC[0]);
        int maNV = Integer.parseInt(NV[0]);

        PhieuNhap pn = new PhieuNhap();
        pn.setNhaCungCap(nccRepository.getNhaCungCap(maNCC));
        pn.setNhanVien(nvRepository.getNV(maNV));
        pn.setTongTien(tongTien);
        pn.setNgayLap(new java.sql.Timestamp(new java.util.Date().getTime()));
        boolean flag = phieuNhapRepository.themPT(pn);
        if (flag) {
            new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);

        }
        return flag;
    }

    @Override
    public int getLastID() {
        return phieuNhapRepository.getLastID();
    }

    @Override
    public PhieuNhap timPhieuNhap(String maPN) {
        int ma = Integer.parseInt(maPN);
        for (PhieuNhap pn : listPhieuNhap) {
            if (pn.getMaPN() == ma) {
                return pn;
            }

        }
        return null;
    }

    @Override
    public List<PhieuNhap> getListPhieuNhapTheoGia(String giaThap, String giaCao) {
        try {
            int min = Integer.parseInt(giaThap);
            int max = Integer.parseInt(giaCao);

            if (max < min) {
                new MyDialogCommon("Khoảng nhập không phù hợp", MyDialogCommon.WARNING_DIALOG);
                return null;
            }
            List<PhieuNhap> lstPhieuNhap = new ArrayList<>();
            for (PhieuNhap pn : listPhieuNhap) {
                if (pn.getTongTien() <= max && pn.getTongTien() >= min) {
                    lstPhieuNhap.add(pn);
                }
            }
            return lstPhieuNhap;
        } catch (Exception e) {
            e.printStackTrace();
            new MyDialogCommon("Không phù hợp", MyDialogCommon.WARNING_DIALOG);

        }
        return null;
    }

    @Override
    public List<PhieuNhap> getListPhieuNhapTheoNgay(String tuNgay, String denNgay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(tuNgay);
            Date maxDate = sdf.parse(denNgay);
            if (maxDate.before(minDate)) {
                new MyDialogCommon("Khoảng nhập không phù hợp", MyDialogCommon.WARNING_DIALOG);
                return null;
            }
             List<PhieuNhap> lstPhieuNhap = new ArrayList<>();
             for (PhieuNhap pn : listPhieuNhap) {
                 if (pn.getNgayLap().after(minDate) && pn.getNgayLap().before(maxDate)) {
                     lstPhieuNhap.add(pn);
                 }
            }
             return lstPhieuNhap;
            
        } catch (Exception e) {
             e.printStackTrace();
            new MyDialogCommon("Không phù hợp", MyDialogCommon.WARNING_DIALOG);

        }
        return  null;
    }

}
