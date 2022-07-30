/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyTableCommon;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin
 */
public class DlgTimMaGiamGiaView extends JDialog{
    public DlgTimMaGiamGiaView(){
        this.setSize(750, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private JTextField txtTuKhoa;
    private JTable tblMaGiam;
    private DefaultTableModel dtmMaGiam;
    private JButton btnChon, btnThoat;
    
    private void addControls(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khóa tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);
        
        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmMaGiam = new DefaultTableModel();
        dtmMaGiam.addColumn("Mã");
        dtmMaGiam.addColumn("Chương trình");
        dtmMaGiam.addColumn("% KM");
        dtmMaGiam.addColumn("Điều kiện");
        dtmMaGiam.addColumn("Bắt đầu");
        dtmMaGiam.addColumn("Kết thúc");
        dtmMaGiam.addColumn("Trngj thái");
        tblMaGiam = new MyTableCommon(dtmMaGiam);
        JScrollPane scrMaGiam = new JScrollPane(tblMaGiam);
        pnTable.add(scrMaGiam, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);
        
        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThoat = new JButton("Thoát");
        btnChon.setFont(font);
        btnThoat.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThoat);
        con.add(pnButton, BorderLayout.SOUTH);
        
        TableColumnModel columModelBanHang = tblMaGiam.getColumnModel();
        columModelBanHang.getColumn(0).setPreferredWidth(56);
        columModelBanHang.getColumn(1).setPreferredWidth(213);
        columModelBanHang.getColumn(2).setPreferredWidth(30);
        columModelBanHang.getColumn(3).setPreferredWidth(62);
        columModelBanHang.getColumn(4).setPreferredWidth(58);
        columModelBanHang.getColumn(5).setPreferredWidth(61);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblMaGiam.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tblMaGiam.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        
        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThoat.setPreferredSize(btnChon.getPreferredSize());
        
    }
}
