package com.mycompany.quanlypizza.repository;


import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.enity.ThongKe;
import com.mycompany.quanlypizza.service.SanPhamServiceImp;
import com.mycompany.quanlypizza.util.Connect;
import com.mycompany.quanlypizza.util.MyConect;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;

public class ThongKeRepository {

    Session session = Connect.getFACTORY().openSession();
    MyConect connect = new MyConect();
    Connection con;

    public ThongKe ThongKeRepository(int nam) {
        ThongKe thongKe = new ThongKe();
        int[] tongThuQuy = new int[4];
        thongKe.setSoLuongSP(getTongSoLuong());
        thongKe.setSoLuongKH(getSoLuongKhachHang());
        thongKe.setSoLuongNV(getSoLuongNhanVien());

        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        thongKe.setTopSanPhamBanChay(getTopBanChay());
        return thongKe;
    }

    public ThongKeRepository() {
        con = connect.getConn();
    }

    public List<SanPham> getTopBanChay() {
//        SanPhamRespository sanPhamRespository = new SanPhamRespository();
        try {
//            Query query = session.createSQLQuery("SELECT MaSP, DaBan FROM ("
//                    + "SELECT MaSP, SUM(SoLuong) AS DaBan FROM "
//                    + "cthoadon GROUP BY MaSP"
//                    + ") temp "
//                    + "ORDER BY DaBan "
//                    + "DESC LIMIT 5");
//            ListIterator itr = query.getResultList().listIterator();
//            List<SanPham> lstSP = new ArrayList<>();
//            while(itr.hasNext()){
//                SanPham sp = new SanPham();
//                Object[] ele = itr.next();
//                sp.setMaSP(ele[0]);
//                sp.setSoLuong(sp2.getDaBan());
//                lstSP.add(sp);
//            }
//
//        List<SanPham> lstSP = new ArrayList<>();
//            SanPhamServiceImp sanPhamServiceImp = new SanPhamServiceImp();
//            for (SanPham sp : lstSP) {
//                sp.setMaSP();
//           }

            String sql = "SELECT MaSP, DaBan FROM ("
                    + "SELECT MaSP, SUM(SoLuong) AS DaBan FROM "
                    + "cthoadon GROUP BY MaSP"
                    + ") temp "
                    + "ORDER BY DaBan "
                    + "DESC LIMIT 5";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<SanPham> dssp = new ArrayList<>();
            SanPhamServiceImp spBUS = new SanPhamServiceImp();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setTenSP(spBUS.getTenSP(sp.getMaSP()));
                dssp.add(sp);
            }
            return dssp;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private BigInteger getTongSoLuong() {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createNativeQuery("SELECT COUNT(sp.masp) from SanPham AS sp");
            BigInteger tongSoLuong = (BigInteger) query.getSingleResult();
            return tongSoLuong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01";
        String thangKetThuc = "04";
        String[] kq = new String[2];

        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "03";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "06";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "09";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
        String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    private int getTongThuQuy(int nam, int quy) {
        String[] dateString = getDateString(nam, quy);
//        try (Session session = Connect.getFACTORY().openSession()) {
//            Query query = session.createNativeQuery("SELECT SUM(HD.tongTien) FROM  HoaDon  AS HD"
//                    + " WHERE HD.ngayLap >= ?1 AND HD.ngayLap < ?2");
//            query.setParameter(1, dateString[0]);
//            query.setParameter(2, dateString[1]);
//            int kq = (int) query.getFirstResult();
//            return kq;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
        try {

            PreparedStatement prep = con.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private BigInteger getSoLuongNhanVien() {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createNativeQuery("SELECT COUNT(nv.maNV) FROM  NhanVien AS nv ");
            BigInteger kq = (BigInteger) query.getSingleResult();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private BigInteger getSoLuongKhachHang() {
        try (Session session = Connect.getFACTORY().openSession()) {
            Query query = session.createNativeQuery("SELECT COUNT(KH.maKH)  FROM  KhachHang as KH");
            BigInteger kq = (BigInteger) query.getSingleResult();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getDoanhThuThang(int thang, int nam) {
        String thangBD = nam + "-" + thang + "-01";
        String thangKT = nam + "-" + (thang + 1) + "-01";

        try (Session session = Connect.getFACTORY().openSession()) {
//            Query query = session.createNativeQuery("SELECT SUM(hd.tongTien) FROM HoaDon AS hd  "
//                    + "WHERE  hd.ngayLap BETWEEN CAST( ?1  AS DATE)  AND CAST( ?2  AS DATE)");
//            query.setParameter(1, thangBD);
//            query.setParameter(2, thangKT);
//            double doanhThu = (double) query.getMaxResults();
//            return doanhThu;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0.0f;

            try {

                String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setString(1, thangBD);
                pre.setString(2, thangKT);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    return Double.parseDouble(rs.getInt(1) + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return nam;
        }
    }

}
