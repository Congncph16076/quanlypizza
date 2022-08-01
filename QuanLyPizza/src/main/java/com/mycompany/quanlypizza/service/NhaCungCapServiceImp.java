package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.NhaCungCap;
import com.mycompany.quanlypizza.repository.NhaCungCapRepository;
import java.util.List;
import java.util.regex.Pattern;

public class NhaCungCapServiceImp implements NhaCungCapService {
    
    private NhaCungCapRepository nhaCungCapRepository = new NhaCungCapRepository();
    private List<NhaCungCap> listNhaCungCaps;
    
    @Override
    public List<NhaCungCap> getListNCC() {
        listNhaCungCaps = nhaCungCapRepository.getListNhaCungCap();
        return listNhaCungCaps;
    }
    
    @Override
    public boolean themNCC(String tenNCC, String diaChi, String dienThoai) {
        if (tenNCC.trim().equals("")) {
            new MyDialogCommon("Nhập tên nhà cung cấp!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialogCommon("Nhập địa chỉ!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (!pattern.matcher(dienThoai).matches()) {
            new MyDialogCommon("Nhập   số điện thoại nhà cung cấp!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setDienThoai(dienThoai);
        boolean flag = nhaCungCapRepository.themNCC(ncc);
        if (flag) {
            new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            
        } else {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            
        }
        return flag;
    }
    
    @Override
    public boolean suaNCC(String maNCC, String tenNCC, String diaChi, String dienThoai) {
       int ma = Integer.parseInt(maNCC);
        if (tenNCC.trim().equals("")) {
            new MyDialogCommon("Nhập tên nhà cung cấp!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialogCommon("Nhập địa chỉ!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (!pattern.matcher(dienThoai).matches()) {
            new MyDialogCommon("Nhập   số điện thoại nhà cung cấp!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(ma);
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setDienThoai(dienThoai);
        boolean flag = nhaCungCapRepository.updateNCC(ncc);
        if (flag) {
            new MyDialogCommon("Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            
        } else {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            
        }
        return flag;
    }
    
}
