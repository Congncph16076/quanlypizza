package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.PhanQuyen;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PhanQuyenRepository {

    Session session = Connect.getFACTORY().openSession();

    public List<PhanQuyen> getDanhSach() {
        Query query = session.createQuery("from PhanQuyen");
        List<PhanQuyen> lstPQ = query.getResultList();
        return lstPQ;
    }

    public PhanQuyen getQuyen(String quyen) {
        try (Session session = Connect.getFACTORY().openSession();) {

            Query query = session.createQuery("from PhanQuyen where quyen = ?1");
            query.setParameter(1, quyen);
            PhanQuyen pQ = (PhanQuyen) query.getSingleResult();

            return pQ;
        } catch (Exception e) {
        }
        return null;
    }

    public  boolean themQuyen(PhanQuyen pq){
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            session.save(pq);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    public  boolean xoaQuyen(String quyen){
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            Query query = session.createQuery("update TaiKhoan set phanQuyen = 'Default'  "
                    + "where phanQuyen = ?1");
            query.setParameter(1, quyen);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean  suaQuyen(PhanQuyen pq){
        Transaction trans = null;
        try  (Session session = Connect.getFACTORY().openSession()){
            trans = session.beginTransaction();
            Query query = session.createQuery("update PhanQuyen set nhapHang =?1"
                    + ", qlSanPham=?2 ,qlNhanVien =?3 , QlKhachHang=?4 , thongKe =?5"
                    + "where quyen = ?6");
            query.setParameter(1, pq.getNhapHang());
            query.setParameter(2, pq.getQlSanPham());
            query.setParameter(3, pq.getQlNhanVien());
            query.setParameter(4, pq.getQlKhachHang());
            query.setParameter(5, pq.getThongKe());
            query.setParameter(6, pq.getQuyen());
            query.executeUpdate();
           
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    
}
