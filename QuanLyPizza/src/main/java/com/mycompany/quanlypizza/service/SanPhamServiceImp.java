package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.repository.SanPhamRespository;
import java.util.ArrayList;
import java.util.List;

public class SanPhamServiceImp implements SanPhamService {

    private SanPhamRespository sanPhamRespository;
    List<SanPham> listSP;

    @Override
    public List<SanPham> getListSP() {
        sanPhamRespository = new SanPhamRespository();
        listSP = sanPhamRespository.getListSP();
        return listSP;
    }

    @Override
    public SanPham getListSPByID(String ma) {
        listSP = new ArrayList<>();
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSP) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<SanPham> getListSPByCategory(String maloai) {
        listSP = new ArrayList<>();
        if (!maloai.trim().equals("")) {
            try {
                for (SanPham sp : listSP) {
                    int loaiSP1 = Integer.parseInt(maloai);
                    if (loaiSP1 == sp.getMaSP()) {
                        listSP.add(sp);
                    }
                }
                return listSP;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public List<SanPham> getListSPByName(String tenSP) {
        List<SanPham> listSP1 = new ArrayList<>();
        listSP = sanPhamRespository.getListSP();
        if (!tenSP.trim().equals("")) {
            try {
                for (SanPham sp : listSP) {
                    String tenSP1 = sp.getTenSP().toLowerCase();
                    if (tenSP1.toLowerCase().contains(tenSP.toLowerCase())) {
                        listSP1.add(sp);
                    }
                }
                return listSP1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAnh(int ma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean themSP(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) {
        sanPhamRespository = new SanPhamRespository();
        if (ten.trim().equals("")) {
            new MyDialogCommon("Tên SP không được để rỗng!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (donViTinh.trim().equals("")) {
            new MyDialogCommon("Vui lòng điền Đơn vị tính!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        try {
            String[] loaiTmp = loai.split("-");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            if (maLoai == 0) {
                new MyDialogCommon("Vui lòng chọn sản phẩm!", MyDialogCommon.ERROR_DIALOG);
                return false;
            }
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setLoaiSP(sanPhamRespository.getIDLoai(maLoai));
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);
            if (sanPhamRespository.themSP(sp)) {
                new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            new MyDialogCommon("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialogCommon.ERROR_DIALOG);

        }
        return false;
    }

    @Override
    public Boolean nhapSPTuExcel(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) {
        sanPhamRespository = new SanPhamRespository();
        try {
            String[] loaiTmp = loai.split("-");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setLoaiSP(sanPhamRespository.getIDLoai(maLoai));
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);
            sanPhamRespository.nhapSanPhanTuExcel(sp);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Boolean suaSP(String ma,
            String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) {
        sanPhamRespository = new SanPhamRespository();
        if (ten.trim().equals("")) {
            new MyDialogCommon("Tên SP không được để rỗng!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (donViTinh.trim().equals("")) {
            new MyDialogCommon("Vui lòng điền Đơn vị tính!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        try {
            String[] loaiTmp = loai.split("-");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int maSP = Integer.parseInt(ma);
            soLuong = soLuong.replace(",", "");
            int soLuongSP = Integer.parseInt(soLuong);

            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            if (maLoai == 0) {
                new MyDialogCommon("Vui lòng chọn sản phẩm!", MyDialogCommon.ERROR_DIALOG);
                return false;
            }
            SanPham sp = new SanPham();

            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setLoaiSP(sanPhamRespository.getIDLoai(maLoai));
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);
            if (sanPhamRespository.suaSP(sp)) {
                new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            new MyDialogCommon("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialogCommon.ERROR_DIALOG);

        }
        return false;
    }

    @Override
    public Boolean xoaSP(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
