/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import com.mycompany.quanlypizza.repository.DangNhapRespository;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DangNhapServiceIMP implements DangNhapservice{
    private final static int EMPTY_ERROR = 1;
    private final static int WRONG_ERROR = 2;
    public static TaiKhoan taiKhoanLogin = null;
    @Override
    public TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean selected) {
       if (kiemTraDangNhap(user, password) == EMPTY_ERROR){
           new MyDialogCommon("Không được để trống thông tin!", MyDialogCommon.ERROR_DIALOG);
           return  null;
       }
       TaiKhoan tk = new TaiKhoan();
       tk.setTenDangNhap(user);
       tk.setMatKhau(password);
       
       DangNhapRespository dangNhapRes = new DangNhapRespository();
       TaiKhoan account = dangNhapRes.dangNhap(tk);
       taiKhoanLogin = account;
       
        if (account == null) {
            new MyDialogCommon("Sai thông tin đăng nhập hoặc tài khoản đã bị khoá!", MyDialogCommon.ERROR_DIALOG);
            
        }else{
            PhanQuyenServiceImp phanQuyenIMP = new PhanQuyenServiceImp();
            phanQuyenIMP.Quyen(account.getPhanQuyen().getQuyen());
            xuLyGhiNhoDangNhap(user, password, selected);
            new MyDialogCommon("Thanh Cong", MyDialogCommon.SUCCESS_DIALOG);
        }
        return account;
    }

    @Override
    public String getTaiKhoanGhiNho() {
        
         
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader be = new BufferedReader(new InputStreamReader(fis));
            String line = be.readLine();
            be.close();
            return line;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    private int kiemTraDangNhap(String user, String password){
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        int result = 0;
        
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);
        
        DangNhapRespository dangNhapRes = new DangNhapRespository();
        TaiKhoan account = dangNhapRes.dangNhap(tk);
        
        if(user.length() <= 0 || password.length() <=0){
            result = EMPTY_ERROR;
        }else if(account== null){
            result = WRONG_ERROR;
        }
        return result;
    }
    private void xuLyGhiNhoDangNhap(String user, String password, boolean selected){
        try {
            if(!selected){
                user = "";
                password = "";
                
            }
            FileWriter fw = new FileWriter("remember.dat");
            fw.write(user + " | " +password);
            fw.close();
        } catch (Exception e) {
        }
    }
}
