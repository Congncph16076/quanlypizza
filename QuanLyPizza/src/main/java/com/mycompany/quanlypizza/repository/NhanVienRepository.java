package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.util.Connect;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NhanVienRepository {

    Session session = Connect.getFACTORY().openSession();
    List<NhanVien> listNV;

    public List<NhanVien> getListNV() {
        try {
            Query query = session.createQuery(" from NhanVien ");
            listNV = new ArrayList<>();
            listNV = query.getResultList();
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien getNV(int maNV) {
        try (Session session = Connect.getFACTORY().openSession();) {
            Query query = session.createQuery("from NhanVien where maNV = ?1");
            query.setParameter(1, maNV);
            NhanVien nv = (NhanVien) query.getSingleResult();
            System.out.println("ma nv: " + maNV);
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getNV1(int maNV) {
        try (Session session = Connect.getFACTORY().openSession();) {
            Query query = session.createQuery("from NhanVien where maNV = ?1");
            query.setParameter(1, maNV);
            NhanVien nv = (NhanVien) query.getSingleResult();
            System.out.println("ma nv: " + maNV);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Boolean themNV(NhanVien nv) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.save(nv);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Boolean xoaNV(NhanVien nv) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(nv);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Boolean nhapNVTuExcel(NhanVien nv) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.save(nv);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Boolean suaNV(NhanVien nv) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update NhanVien set ho =?1"
                    + ",ten=?2,gioiTinh = ?3,chucVu = ?4"
                    + "where maNV = ?5");
            query.setParameter(1, nv.getHo());
            query.setParameter(2, nv.getTen());
            query.setParameter(3, nv.getGioiTinh());
            query.setParameter(4, nv.getChucVu());
            query.setParameter(5, nv.getMaNV());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

}
