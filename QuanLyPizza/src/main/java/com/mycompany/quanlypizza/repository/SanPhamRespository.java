package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.util.Connect;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SanPhamRespository {

    Session session = Connect.getFACTORY().openSession();
    List<SanPham> listSP;

    public List<SanPham> getListSP() {
        try {
            Query query = session.createQuery("from SanPham order by maSP desc");
            listSP = new ArrayList<>();
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SanPham getSPByID(int ma) {
        try {
            SanPham sp = new SanPham();
            sp = session.load(SanPham.class, ma);
            return sp;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public LoaiSP getIDLoai(int maLoai) {
        try(Session session = Connect.getFACTORY().openSession();) {
            Query query = session.createQuery("from LoaiSP where maLoai = ?1");
            query.setParameter(1, maLoai);
            LoaiSP loaiSP = (LoaiSP) query.getSingleResult();
            System.out.println("ma Loai: "+maLoai);
            System.out.println("Loai: "+loaiSP);
            return loaiSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPham> getSPByCategory(int maloai) {
        listSP = new ArrayList<>();
        try {
            Query query = session.createQuery("from SanPham  sp where sp.loaiSP.maLoai = :maLoai  ");
            query.setParameter("maLoai", maloai);
            listSP = query.getResultList();
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public  String getAnh(int ma){
        try {
             Query query = session.createQuery("select sp.hinhAnh  from SanPham sp where sp.maSP = :maSP  ");
             query.setParameter("maSP", ma);
            String hinhAnh  = (String) query.getSingleResult();
            return hinhAnh;
        } catch (Exception e) {
        }
        return "";
    }
    
    
    public  Boolean  themSP(SanPham sp){
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            session.save(sp);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    public  Boolean suaSP(SanPham sp){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update SanPham set "
                    + "tenSP = :tenSP , loaiSP = : loaiSP ,soLuong = :soLuong ,donViTinh = :donViTinh"
                    + ",hinhAnh = :hinhAnh , donGia = :donGia "
                    + "where maSP = :maSP");
            query.setParameter("tenSP", sp.getTenSP());
            query.setParameter("loaiSP", sp.getLoaiSP());
            query.setParameter("soLuong", sp.getSoLuong());
            query.setParameter("donViTinh", sp.getDonViTinh());
            query.setParameter("hinhAnh", sp.getHinhAnh());
            query.setParameter("donGia", sp.getDonGia());
            query.setParameter("maSP", sp.getMaSP());
             query.executeUpdate();
             trans.commit();
             return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean nhapSanPhanTuExcel(SanPham sp){
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            session.save(sp);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    public  Boolean suaSoLuongSP(int ma, int soLuongMat){
        Transaction trans = null;
        SanPham sp = getSPByID(ma);
        int soLuong = sp.getSoLuong();
        sp.setSoLuong(soLuong + soLuongMat);
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update SanPham set "
                    + "soLuong = :soLuong "
                    + "where maSP = :maSP");
            query.setParameter("soLuong", sp.getSoLuong());
            query.setParameter("maSP", ma);
             query.executeUpdate();
             trans.commit();
             return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    
}



    
