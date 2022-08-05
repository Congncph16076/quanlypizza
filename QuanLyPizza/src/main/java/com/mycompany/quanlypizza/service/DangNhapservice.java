/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.service;

import com.mycompany.quanlypizza.enity.TaiKhoan;

/**
 *
 * @author PC
 */
public interface DangNhapservice {
    public  TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean  selected);
    public  String getTaiKhoanGhiNho();
}
