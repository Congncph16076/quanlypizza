package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.util.Connect;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class GiamGiaRespository {

    Session session = Connect.getFACTORY().openSession();

    public List<GiamGia> getList() {
        Query query = session.createQuery("From GiamGia gg Order By gg.maGiam desc");
        List<GiamGia> giamGiaList = query.getResultList();
        return giamGiaList;
    }

    public Boolean save(GiamGia gg) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = this.session.beginTransaction();
            session.save(gg);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    public Boolean suaMaGiamGia(GiamGia gg) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = this.session.beginTransaction();
            Query query = session.createQuery(" update GiamGia set tenGiamGia = :tenGiamGia ,  phanTramGiam = :phanTramGiam "
                    + ", dieuKien = :dieuKien, ngayBD = :ngayBD , ngayKT = :ngayKT"
                    + " where maGiam = :maGiam");
            query.setParameter("tenGiamGia", gg.getTenGiamGia());
            query.setParameter("phanTramGiam", gg.getPhanTramGiam());
            query.setParameter("dieuKien", gg.getDieuKien());
            query.setParameter("ngayBD", new java.sql.Timestamp(gg.getNgayBD().getTime()));
            query.setParameter("ngayKT", new java.sql.Timestamp(gg.getNgayKT().getTime()));
            query.setParameter("maGiam", gg.getMaGiam());
            query.executeUpdate();
            trans.commit();
            return true;
        } 
    }
}
