
package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.PhieuNhap;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PhieuNhapRepository {
    Session session = Connect.getFACTORY().openSession();
    
    public  List<PhieuNhap> getListPhieuNhap(){
        Query query = session.createQuery("from PhieuNhap");
        List<PhieuNhap> lstPhieuNhap = query.getResultList();
        return lstPhieuNhap;
    }
    
    public  boolean  themPT(PhieuNhap pt){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.save(pt);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public  boolean  xoaPN(PhieuNhap pt){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.remove(pt);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    
    public  PhieuNhap getNhaCungCap(int maPN){
        try(Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("from PhieuNhap  where maPN =?1");
            query.setParameter(1, maPN);
            PhieuNhap  pt = (PhieuNhap) query.getSingleResult();
            return pt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean updatePN(PhieuNhap pt){
        try(Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("UPDATE PhieuNhap AS pt SET pt.maPN = ?1 , pt.nhaCungCap.maNCC = ?2 , pt.nhanVien.maNV = ?3 , pt.ngayLap = ?4 , pt.tongTien = ?5 "
                    + "where pt.maPN = ?6");
            query.setParameter(1, pt.getMaPN());
            query.setParameter(2, pt.getNhaCungCap().getMaNCC());
            query.setParameter(3, pt.getNhanVien().getMaNV());
            query.setParameter(4, pt.getNgayLap());
            query.setParameter(5, pt.getTongTien());
            query.setParameter(5, pt.getMaPN());
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public  int getLastID(){
        try(Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("select MAX(pt.maPN) from PhieuNhap AS pt ");
            int  pt = (int) query.getMaxResults();
            return pt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
