
package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class LoaiRespository {
    Session session = Connect.getFACTORY().openSession();
    
    public  List<LoaiSP> getDanhSachLoai(){
            Query query = session.createQuery("from LoaiSP");
            List<LoaiSP> getList = query.getResultList();
            return getList;
    }
    
    public Boolean themLoai(LoaiSP  loai){
        Transaction trans = null;
        try(Session session = Connect.getFACTORY().openSession()) {
             trans = session.beginTransaction();
            session.save(loai);
            trans.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public Boolean suaLoai(int maLoai, String ten){
        Transaction trans = null;
        LoaiSP loai = new LoaiSP();
        try(Session session = Connect.getFACTORY().openSession()) {
             trans = session.beginTransaction();
            Query  query = session.createQuery("update LoaiSP set tenLoai = ?1 where maLoai =?2");
            query.setParameter(1, loai.getTenLoai());
            query.setParameter(2, loai.getMaLoai());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public Boolean xoaLoai(int maLoai){
        Transaction trans = null;
        LoaiSP loai = new LoaiSP();
        try(Session session = Connect.getFACTORY().openSession()) {
             trans = session.beginTransaction();
            Query  query = session.createQuery("delete from LoaiSP where maLoai = ?1");
            query.setParameter(1, maLoai);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    
}
