package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.CTPhieuNhap;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChiTietPhieuNhapRepository {

    Session session = Connect.getFACTORY().openSession();

    public List<CTPhieuNhap> getListCTPhieuNhap() {
        Query query = session.createQuery("from CTPhieuNhap");
        List<CTPhieuNhap> lstCTPhieuNhap = query.getResultList();
        return lstCTPhieuNhap;
    }

    public List<CTPhieuNhap> getListCTPhieuNhapByMaPN(int maPN) {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("from CTPhieuNhap where maPN = ?1");
            query.setParameter(1, maPN);
            List<CTPhieuNhap> lstCTPhieuNhap = query.getResultList();
            return lstCTPhieuNhap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CTPhieuNhap> getListCTPhieuNhapByMaSP(int maSP) {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("from CTPhieuNhap where maSP = ?1");
            query.setParameter(1, maSP);
            List<CTPhieuNhap> lstCTPhieuNhap = query.getResultList();
            return lstCTPhieuNhap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            //update số lượng tồn trong kho
            Query query = session.createQuery("update SanPham set SoLuong = ?1 where maSP = ?2");
            query.setParameter(1, ctpn.getSoLuong());
            query.setParameter(2, ctpn.getMaSP());
            query.executeUpdate();

            //thêm số lượng mới vào kho
            session.save(ctpn);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public  boolean  deleteCTPhieuNhapByMaPN(CTPhieuNhap ctpn){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM CTPhieuNhap AS ctpn WHERE ctpn.maPN = ?1");
            query.setParameter(1, ctpn.getMaPN());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public  boolean  deleteCTPhieuNhapByMaPNVaMaSP(CTPhieuNhap ctpn){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM CTPhieuNhap AS ctpn WHERE ctpn.maPN = ?1 AND ctpn.maSP = ?2");
            query.setParameter(1, ctpn.getMaPN());
            query.setParameter(2, ctpn.getMaSP());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
//    public  boolean  updateCTPN(CTPhieuNhap ctpn){
//        Transaction trans = null;
//        try(Session session = Connect.getFACTORY().openSession()) {
//            trans = session.beginTransaction();
//            Query query = session.createQuery("UPDATE CTPhieuNhap AS ctpn SET ctpn.maSP - ?1 , ctpn.");
//            query.setParameter(1, ctpn.getMaPN());
//            query.setParameter(2, ctpn.getMaSP());
//            query.executeUpdate();
//            trans.commit();
//            return true;
//        } catch (Exception e) {
//            trans.rollback();
//            e.printStackTrace();
//        }
//        return false;
//    }

}
