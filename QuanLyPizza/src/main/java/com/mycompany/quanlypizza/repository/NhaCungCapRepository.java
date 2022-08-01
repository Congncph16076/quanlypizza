package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.NhaCungCap;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class NhaCungCapRepository {
    Session session = Connect.getFACTORY().openSession();
    
    public  List<NhaCungCap> getListNhaCungCap(){
        Query query = session.createQuery("from NhaCungCap");
        List<NhaCungCap> lstNCC = query.getResultList();
        return lstNCC;
    }
    public  NhaCungCap getNhaCungCap(int maNCC){
        try(Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("from NhaCungCap  where maNCC =?1");
            query.setParameter(1, maNCC);
            NhaCungCap  nCC = (NhaCungCap) query.getSingleResult();
            return nCC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public  boolean  themNCC(NhaCungCap ncc){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.save(ncc);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public  boolean  xoaNCC(NhaCungCap ncc){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.remove(ncc);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }
    
    public  boolean  updateNCC(NhaCungCap  ncc){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query  query = session.createQuery("update NhaCungCap set tenNCC = ?1,diaChi = ?2,dienThoai = ?3 where maNCC = ?4");
            query.setParameter(1, ncc.getTenNCC());
            query.setParameter(2, ncc.getDiaChi());
            query.setParameter(3, ncc.getDienThoai());
            query.setParameter(4, ncc.getMaNCC());
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
