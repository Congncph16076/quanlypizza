/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.repository.LoaiRespository;
import java.util.List;

public class LoaiServiceImp implements LoaiService {

    private LoaiRespository loaiRespository = null;
    private List<LoaiSP> listLoai = null;

    @Override
    public List<LoaiSP> getDanhSach() {
        try {
            loaiRespository = new LoaiRespository();
            listLoai = loaiRespository.getDanhSachLoai();
            return listLoai;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean themLoai(int maLoai, String tenLoai) {
        loaiRespository = new LoaiRespository();
        if (tenLoai.trim().equals("")) {
            new MyDialogCommon("Không được để trống tên loại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        maLoai += 1;
        LoaiSP loai = new LoaiSP(maLoai, tenLoai);
        if (loaiRespository.themLoai(loai)) {
            new MyDialogCommon("Thêm thành công!", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialogCommon("Thêm thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }

    }

    @Override
    public Boolean suaLoai(String maLoai, String ten) {
        loaiRespository = new LoaiRespository();
        if (ten.trim().equals("")) {
            new MyDialogCommon("Không được để trống tên loại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
        int ma = Integer.parseInt(maLoai);
        if (loaiRespository.suaLoai(ma, ten)) {
            new MyDialogCommon(" Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
    }

    @Override
    public Boolean xoaLoai(String ma) {
        loaiRespository = new LoaiRespository();
        if (ma.trim().equals("")) {
            new MyDialogCommon("Chưa chọn loại để xoá!", MyDialogCommon.SUCCESS_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (loaiRespository.xoaLoai(maLoai)) {
            new MyDialogCommon(" Thành công!", MyDialogCommon.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialogCommon("Thất bại!", MyDialogCommon.ERROR_DIALOG);
            return false;
        }
    }

}
