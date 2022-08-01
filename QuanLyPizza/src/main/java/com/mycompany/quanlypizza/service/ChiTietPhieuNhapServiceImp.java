package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.CTPhieuNhap;
import com.mycompany.quanlypizza.repository.ChiTietPhieuNhapRepository;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapServiceImp implements ChiTietPhieuNhapService{
    private  ChiTietPhieuNhapRepository chiTietPhieuNhapRepository = new ChiTietPhieuNhapRepository();
    List<CTPhieuNhap> listCTPhieuNhaps ;
    
    @Override
    public List<CTPhieuNhap> getListCTPhieuNhap() {
        listCTPhieuNhaps = chiTietPhieuNhapRepository.getListCTPhieuNhap();
        return listCTPhieuNhaps;
    }

    @Override
    public List<CTPhieuNhap> getListCTPhieuNhap(String maPN) {
        List<CTPhieuNhap> listCTPhieuNhap  = new ArrayList<>();
        int ma = Integer.parseInt(maPN);
        for (CTPhieuNhap ct : listCTPhieuNhap) {
            if (ct.getMaPN() ==  ma) {
                listCTPhieuNhap.add(ct);
            }
        }
        return listCTPhieuNhap;
    }

    @Override
    public Boolean luuCTPhieuNhap(CTPhieuNhap ctpn) {
        return  chiTietPhieuNhapRepository.addCTPhieuNhap(ctpn);
    }

}
