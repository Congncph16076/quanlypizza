/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.util.Connect;
import com.mycompany.quanlypizza.enity.CTHoaDon;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vinh Kute
 */
public class CTHoaDonRepo {
    Session session = Connect.getFACTORY().openSession();

    public List<CTHoaDon> getList() {
        Query query = session.createQuery("From CTHoaDon ");// truy vấn trên entity(HQL)
        List<CTHoaDon> list = query.getResultList();
        return list;
    }

    public Boolean save(CTHoaDon cthd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(cthd);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean delete(CTHoaDon cthd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cthd);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean update1(CTHoaDon cthd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update CTHoaDon set hoaDon = :hoaDon, sanPham =:sanPham, soLuong =:soLuong, "
                    + "donGia =:donGia, thanhTien=:thanhTien"
                    + " where maCTHD = :maCTHD");
            query.setParameter("hoaDon", cthd.getHoaDon());
            query.setParameter("sanPham", cthd.getSanPham());
            query.setParameter("soLuong", cthd.getSoLuong());
            query.setParameter("donGia", cthd.getDonGia());
            query.setParameter("thanhTien", cthd.getThanhTien());
            query.setParameter("maCTHD", cthd.getMaCTHD());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }


    public CTHoaDon getById(int id) {
        try (Session session = Connect.getFACTORY().openSession()) {
            CTHoaDon cthd = session.get(CTHoaDon.class, id);
            return cthd;
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<CTHoaDon> getByMaSP(int maSP) {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("From CTHoaDon c "
                    + " where c.maSP = :maSP");
            query.setParameter("maSP", maSP);
            return query.getResultList();
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<CTHoaDon> getByMaHD(int maHD) {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("From CTHoaDon c "
                    + " where c.maHD = :maHD");
            query.setParameter("maHD", maHD);
            return query.getResultList();
        } catch (Exception e) {
        }
        return null;
    }

}
