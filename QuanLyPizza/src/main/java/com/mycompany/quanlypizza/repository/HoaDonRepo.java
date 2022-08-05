/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.HoaDon;
import com.mycompany.quanlypizza.util.Connect;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vinh Kute
 */
public class HoaDonRepo {
    Session session = Connect.getFACTORY().openSession();

    public List<HoaDon> getList() {
        Query query = session.createQuery("From HoaDon ");// truy vấn trên entity(HQL)
        List<HoaDon> list = query.getResultList();
        return list;
    }
    
    public List<HoaDon> getListTheoNgay(Date dateMin, Date dateMax) {
        try {
            Query query = session.createQuery("Frome HoaDon D Where "
                    + " NgayLap BETWEEN CAST(?1 AS DATE) AND CAST(?2 AS DATE)" );
            query.setParameter(1, dateMin);
            query.setParameter(2, dateMax);
            List<HoaDon> list = query.getResultList();
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaHoaDonMoiNhat() {
        Query query = session.createQuery("SELECT MAX(d.maHD) FROM hoadon d");// truy vấn trên entity(HQL)
        return (int) query.getSingleResult();
    }

    public Boolean save(HoaDon hd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update KhachHang set "
                    + "tongChiTieu=tongChiTieu+"
                    +  hd.getTongTien()
                    + " WHERE khachHang=:khachHang");
            
            query.setParameter("khachHang", hd.getKhachHang());
            query.executeUpdate();
            session.save(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean delete(HoaDon hd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean update1(HoaDon hd) {
        Transaction transaction = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update HoaDon set khachHang = :khachHang, nhanVien =:nhanVien, ngayLap =:ngayLap, "
                    + "tongTien =:tongTien, ghiChu=:ghiChu"
                    + " where maHD = :maHD");
            query.setParameter("khachHang", hd.getKhachHang());
            query.setParameter("nhanVien", hd.getNhanVien());
            query.setParameter("ngayLap", hd.getNgayLap());
            query.setParameter("tongTien", hd.getTongTien());
            query.setParameter("ghiChu", hd.getGhiChu());
            query.setParameter("maHD", hd.getMaHD());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
    }


    public HoaDon getById(int id) {
        try (Session session = Connect.getFACTORY().openSession()) {
            HoaDon hd = session.get(HoaDon.class, id);
            return hd;
        } catch (Exception e) {
        }
        return null;
    }
    
}
