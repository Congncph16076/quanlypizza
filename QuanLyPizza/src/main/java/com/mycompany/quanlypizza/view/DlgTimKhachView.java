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
import javax.swing.table.DefaultTableModel;

public class DlgTimKhachView extends  JDialog{

    public DlgTimKhachView() {
        addControls();
        addEvents();
        this.setSize(500,400);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private  JTextField txtTuKhoa;
    private JTable tblKhachHang;
    private DefaultTableModel dtmKhachHang;
    private  JButton btnChon,btnThemKhach;
    
    

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        
        Font font = new Font("Tahoma",Font.PLAIN,16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ Khóa: ");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop,BorderLayout.NORTH);
        
        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã KH");
        dtmKhachHang.addColumn("Họ");
        dtmKhachHang.addColumn("Tên");
        dtmKhachHang.addColumn("Giới tính");
        dtmKhachHang.addColumn("Chi tiêu");
        tblKhachHang = new MyTableCommon(dtmKhachHang);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        pnTable.add(scrKhachHang , BorderLayout.CENTER);
        con.add(pnTable , BorderLayout.CENTER);
        
        
        JPanel  pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThemKhach = new JButton("Thêm khách");
        btnChon.setFont(font);
        btnThemKhach.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThemKhach);
        con.add(pnButton, BorderLayout.SOUTH);
        
        btnChon.setPreferredSize(new Dimension(120,40));
        btnThemKhach.setPreferredSize(btnChon.getPreferredSize());
        
        loadDataLenTable();
    }

    private void addEvents() {
    }

    private void loadDataLenTable() {
    }
    
    
    
}
