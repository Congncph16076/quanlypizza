package com.mycompany.quanlypizza.util;

import com.mycompany.quanlypizza.enity.CTHoaDon;
import com.mycompany.quanlypizza.enity.CTPhieuNhap;
import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.enity.HoaDon;
import com.mycompany.quanlypizza.enity.KhachHang;
import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.NhaCungCap;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.PhanQuyen;
import com.mycompany.quanlypizza.enity.PhieuNhap;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Connect {
    private  static  final SessionFactory FACTORY;
 
    static {
        Configuration conf = new Configuration();
        
       Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLInnoDBDialect");
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/pizza");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "1234");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(CTHoaDon.class);
        conf.addAnnotatedClass(CTPhieuNhap.class);
        conf.addAnnotatedClass(GiamGia.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(LoaiSP.class);
        conf.addAnnotatedClass(NhaCungCap.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(PhanQuyen.class);
        conf.addAnnotatedClass(PhieuNhap.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(TaiKhoan.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
        
        System.out.println("thành công!!!!");
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
    
    
}
