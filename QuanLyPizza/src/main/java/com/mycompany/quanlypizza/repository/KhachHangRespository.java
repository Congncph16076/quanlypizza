/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.repository;

import org.hibernate.Session;
import com.mycompany.quanlypizza.util.Connect;
import com.mycompany.quanlypizza.enity.KhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Transaction;
/**
 *
 * @author Admin
 */
public class KhachHangRespository {
    Session session = Connect.getFACTORY().openSession();
    List<KhachHang> listKhachHang;
    
    public List<KhachHang> getListKhachHang() {
        try {
            Query query = session.createQuery("from KhachHang order by maKH desc");
            listKhachHang = new ArrayList<>();
            listKhachHang = query.getResultList();
            return  listKhachHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang getKhachHang(int maKH) {
        try {
            KhachHang kh = new KhachHang();
            kh = session.load(KhachHang.class, maKH);
            return kh;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addKhachHang(KhachHang kh) {
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            session.save(kh);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }

    public boolean deleteKhachHang(int maKH) {
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            KhachHang kh = this.getKhachHang(maKH);
            trans = session.beginTransaction();
            session.remove(kh);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }

    public boolean updateKhachHang(KhachHang kh) {
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update KhachHang set ho = :ho , ten = :ten ,gioiTinh = :gioiTinh ,sdt = :sdt"
                    + " where maKH = :maKH");
            query.setParameter("ho", kh.getHo());
            query.setParameter("ten", kh.getTen());
            query.setParameter("gioiTinh", kh.getGioiTinh());
            query.setParameter("sdt", kh.getSdt());
            query.setParameter("maKH", kh.getMaKH());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            KhachHang khachhang = this.getKhachHang(maKH);
            trans = session.beginTransaction();
            Query query = session.createQuery("update KhachHang set TongChiTieu = :tongChiTieu where MaKH = :maKH");
            query.setParameter("tongChiTieu", tongChiTieu);
            query.setParameter("maKH", maKH);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    ///////////////////
    
}
