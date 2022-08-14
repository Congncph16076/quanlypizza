package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import com.mycompany.quanlypizza.repository.NhanVienRepository;
import com.mycompany.quanlypizza.repository.PhanQuyenRepository;
import com.mycompany.quanlypizza.repository.TaiKhoanRespository;

public class TaiKhoanServiceImp implements TaiKhoanService {

    private TaiKhoanRespository taiKhoanRespository = new TaiKhoanRespository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private PhanQuyenRepository quyenRepository = new PhanQuyenRepository();

    @Override
    public String getTenDNTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanRespository.getTenDangNhapTheoMa(maNV);
    }

    @Override
    public String getQuyen(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanRespository.getQuyenTheoMa(maNV);
    }

    @Override
    public void datLaiMK(String ma, String tenDN) {
        TaiKhoan tk = new TaiKhoan();
        int maNV = Integer.parseInt(ma);
        tk.setNhanVien(nhanVienRepository.getNV(maNV));
        tk.setMatKhau(tenDN);
        boolean flag = taiKhoanRespository.datLaiMK(tk);
        if (flag) {
            new MyDialogCommon("Đặt lại thành công! Mật khẩu mới là: " + tenDN, MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Đặt lại thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
    }

    @Override
    public void datLaiQuyen(String ma, String quyen) {
        TaiKhoan tk = new TaiKhoan();
        int maNV = Integer.parseInt(ma);
        tk.setNhanVien(nhanVienRepository.getNV(maNV));
        tk.setPhanQuyen(quyenRepository.getQuyen(quyen));
        boolean flag = taiKhoanRespository.datLaiQuyen(tk);
        if (flag) {
            new MyDialogCommon("Đặt lại thành công! ", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Đặt lại thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
    }

    @Override
    public Boolean kiemTraTrungTenDangNhap(String tenDN) {
        return taiKhoanRespository.kiemTraTrungTenDangNhap(tenDN);
    }

    @Override
    public Boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            new MyDialogCommon("Không được để trống Tên đăng nhập!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            MyDialogCommon dlg = new MyDialogCommon("Tên đang nhập trùng hoặc bị khóa , thực hiện mở khóa?", MyDialogCommon.WARNING_DIALOG);
            if (dlg.getAction() == MyDialogCommon.SUCCESS_DIALOG) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        TaiKhoan tk = new TaiKhoan();
        tk.setNhanVien(nhanVienRepository.getNV(maNV));
        tk.setTenDangNhap(tenDangNhap);
        tk.setPhanQuyen(quyenRepository.getQuyen(quyen));
        tk.setMatKhau(tenDangNhap);
        tk.setTrangThai("1");
        boolean flag = taiKhoanRespository.themTaiKhoan(tk);

        if (flag) {
            new MyDialogCommon("Cấp tài khoản thành công! Mật khẩu là " + tenDangNhap, MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Cấp tài khoản thất bại! Tài khoản đã tồn tại", MyDialogCommon.ERROR_DIALOG);
        }
        return flag;
    }

    @Override
    public void khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        TaiKhoan tk = new TaiKhoan();
        tk.setNhanVien(nhanVienRepository.getNV(maNV));
        boolean flag = taiKhoanRespository.khoaTaiKhoan(tk);
        if (flag) {
            new MyDialogCommon("Khoá tài khoản thành công! ", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Khóa tài khoản thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
    }

    @Override
    public void moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        TaiKhoan tk = new TaiKhoan();
        tk.setNhanVien(nhanVienRepository.getNV(maNV));
        boolean flag = taiKhoanRespository.moKhoaTaiKhoan(tk);
        if (flag) {
            new MyDialogCommon(" Mở khoá tài khoản thành công! ", MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("Mở khóa tài khoản thất bại!", MyDialogCommon.ERROR_DIALOG);
        }
    }

    @Override
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if (!matKhauMoi.equals(nhapLaiMatKhau)) {
            new MyDialogCommon("Mật khẩu không khớp ! ", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanRespository.doiMK(matKhauCu, matKhauMoi);
        if (flag) {
            new MyDialogCommon("Đổi thành công \n Mật khẩu mới của bạn là: " + matKhauMoi, MyDialogCommon.SUCCESS_DIALOG);
        } else {
            new MyDialogCommon("thất bại", MyDialogCommon.ERROR_DIALOG);
        }
        //Chưa xong
        return flag;
    }

    @Override
    public int getTrangThai(int maNV) {
//        int ma = Integer.parseInt(maNV);
        return taiKhoanRespository.getTrangThai(maNV);
    }

}
