/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.repository;

import com.mycompany.quanlypizza.util.Connect;
import org.hibernate.Session;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import javax.persistence.Query;
import org.hibernate.Transaction;

/**
 *
 * @author PC
 */
public class DangNhapRespository {

    Session session = Connect.getFACTORY().openSession();
    
    public TaiKhoan dangNhap(TaiKhoan tk) {
        
        try  {
            
            Query query = session.createQuery("from TaiKhoan where tenDangNhap = ?1 and matKhau =?2 and trangThai = 1");
            query.setParameter(1, tk.getTenDangNhap());
            query.setParameter(2, tk.getMatKhau());
            tk = (TaiKhoan) query.getSingleResult();
            
            return tk;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
