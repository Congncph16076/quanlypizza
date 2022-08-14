package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import com.mycompany.quanlypizza.service.DangNhapServiceIMP;
import com.mycompany.quanlypizza.util.Connect;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaiKhoanRespository {

    Session session = Connect.getFACTORY().openSession();

    public boolean themTaiKhoan(TaiKhoan tk) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.save(tk);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDN) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("From TaiKhoan where tenDangNhap =?1");
            query.setParameter(1, tenDN);
            query.getSingleResult();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNV) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("SELECT matKhau From TaiKhoan where maNV =?1");
            query.setParameter(1, maNV);
            String taiKhoanKiemTra = (String) query.getSingleResult();
            trans.commit();
            return taiKhoanKiemTra;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getQuyenTheoMa(int maNV) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("SELECT tk.phanQuyen.quyen From TaiKhoan AS tk where tk.nhanVien.maNV =?1");
            query.setParameter(1, maNV);
            String taiKhoanKiemTra = (String) query.getSingleResult();
            trans.commit();
            return taiKhoanKiemTra;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean datLaiMK(TaiKhoan tk) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update TaiKhoan set matKhau =?1 where maNV = ?2");
            query.setParameter(1, tk.getMatKhau());
            query.setParameter(2, tk.getNhanVien().getMaNV());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean datLaiQuyen(TaiKhoan tk) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update TaiKhoan set quyen =?1 where maNV = ?2");
            query.setParameter(1, tk.getPhanQuyen().getQuyen());
            query.setParameter(2, tk.getNhanVien().getMaNV());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean khoaTaiKhoan(TaiKhoan tk) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update TaiKhoan set trangThai =0 where maNV = ?1");
            query.setParameter(1, tk.getNhanVien().getMaNV());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(TaiKhoan tk) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            Query query = session.createQuery("update TaiKhoan set trangThai =1 where maNV = ?1");
            query.setParameter(1, tk.getNhanVien().getMaNV());
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Chưa xong
    public boolean doiMK(String matKhauCu , String matKhauMoi) {
        Transaction trans = null;
        try (Session session = Connect.getFACTORY().openSession()) {
            trans = session.beginTransaction();
           Query query = session.createQuery("update TaiKhoan set matKhau = ?1 where maNV = ?2 AND matKhau= ?3");
            query.setParameter(1, matKhauMoi);
            query.setParameter(2, DangNhapServiceIMP.taiKhoanLogin.getNhanVien().getMaNV());
            query.setParameter(3,matKhauCu);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ///
    public int getTrangThai(int ma) {
        
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery("SELECT tk.trangThai from TaiKhoan AS tk WHERE maNV =?1");
            query.setParameter(1, ma);
            int taiKhoan = Integer.parseInt((String) query.getSingleResult());
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public TaiKhoan getTK(int ma) {
        
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createQuery(" from TaiKhoan AS tk WHERE maNV =?1");
            query.setParameter(1, ma);
            TaiKhoan taiKhoan = (TaiKhoan)query.getSingleResult();
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

}
