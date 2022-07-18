/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.main.Main;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jfree.ui.DateCellRenderer;

/**
 *
 * @author congc
 */
public class PnQuanLyKhuyenMai extends  JPanel{

    public PnQuanLyKhuyenMai() {
        
        Main.changLNF("Windows");
        addControls();
        addEvents();
    }

    
    final  Color colorPanel = new Color(247,247,247);
    
    JButton btnReset,btnThem,btnSua;
    JTextField txtMa, txtTen, txtPhanTram,txtDieuKien;
    MyTableCommon tblKhuyenMai;
    DefaultTableModel dtmKhuyenMai;
    JDateChooser dateBD, dateKT;
    
    
    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN,16);
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        
        int w = 1030;
        int h = 844;
        
        //MAIN PANEL
        JPanel pnMain = new TransparentPanelCommon();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        
        //TITILE
        JPanel pnTitle = new TransparentPanelCommon();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ MÃ KHUYẾN MÃI</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40,40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnMain.add(pnTitle);
        
        
        //TEXTFIELD
        JPanel pnTextField = new TransparentPanelCommon();
        pnTextField.setLayout(new BoxLayout(pnTextField , BoxLayout.Y_AXIS));
        
        JLabel lblMa, lblTen, lblPhanTram, lblDieuKien, lblNgayBD, lblNgayKT;
        lblMa = new JLabel("Mã khuyến mãi");
        lblTen = new JLabel("Tên chương trình");
        lblPhanTram = new JLabel("Phần trăm giảm");
        lblDieuKien = new JLabel("Điều kiện (>x)");
        lblNgayBD = new JLabel("Ngày bắt đầu");
        lblNgayKT = new JLabel("Ngày kết thúc");
        
        lblMa.setFont(font);
        lblTen.setFont(font);
        lblPhanTram.setFont(font);
        lblDieuKien.setFont(font);
        lblNgayBD.setFont(font);
        lblNgayKT.setFont(font);
        
        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtPhanTram = new JTextField(20);
        txtDieuKien = new JTextField(20);
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");
        
        txtMa.setEditable(false);
        dateBD.getCalendarButton().setPreferredSize(new Dimension(32,32));
        dateBD.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());
        
        
        txtMa.setFont(font);
        txtTen.setFont(font);
        txtPhanTram.setFont(font);
        txtDieuKien.setFont(font);
        dateBD.setFont(font);
        dateKT.setFont(font);
        
        JPanel pnMa = new TransparentPanelCommon();
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);
        
        
        JPanel pnTen = new TransparentPanelCommon();
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);
        
        JPanel pnPhanTram = new TransparentPanelCommon();
        pnPhanTram.add(lblPhanTram);
        pnPhanTram.add(txtPhanTram);
        pnTextField.add(pnPhanTram);
        
        JPanel pnDieuKien = new TransparentPanelCommon();
        pnDieuKien.add(lblDieuKien);
        pnDieuKien.add(txtDieuKien);
        pnTextField.add(pnDieuKien);
        
        
        JPanel pnNgayBD = new TransparentPanelCommon();
        pnNgayBD.add(lblNgayBD);
        pnNgayBD.add(dateBD);
        pnTextField.add(pnNgayBD);
        
        
        JPanel pnNgayKT = new TransparentPanelCommon();
        pnNgayKT.add(lblNgayKT);
        pnNgayKT.add(dateKT);
        pnTextField.add(pnNgayKT);
        
        pnMain.add(pnTextField);
        
        //BUTTON PANEL
        JPanel pnButton = new TransparentPanelCommon();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnThem.setFont(font);
        btnSua.setFont(font);
        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        
        //TABLE
        JPanel pnTable = new TransparentPanelCommon(new BorderLayout());
        dtmKhuyenMai = new DefaultTableModel();
        dtmKhuyenMai.addColumn("Mã KM");
        dtmKhuyenMai.addColumn("Chương trình");
        dtmKhuyenMai.addColumn("Phần trăm KM");
        dtmKhuyenMai.addColumn("Điều kiện");
        dtmKhuyenMai.addColumn("Ngày bắt đầu");
        dtmKhuyenMai.addColumn("Ngày kết thúc");
        dtmKhuyenMai.addColumn("Tình trạng");
        
        
        tblKhuyenMai = new MyTableCommon(dtmKhuyenMai);
        JScrollPane scrTblKhuyenMai = new JScrollPane(tblKhuyenMai);
        
        
        DefaultTableCellRenderer centerCellRenderer = new  DateCellRenderer();
        centerCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerCellRenderer);
        
        DefaultTableCellRenderer rightCellRenderer = new  DateCellRenderer();
        rightCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(3).setCellRenderer(rightCellRenderer);
        tblKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(rightCellRenderer);
        tblKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(rightCellRenderer);
        
        TableColumnModel columnModelBanHang = tblKhuyenMai.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(24);
        columnModelBanHang.getColumn(1).setPreferredWidth(189);
        columnModelBanHang.getColumn(2).setPreferredWidth(66);
        columnModelBanHang.getColumn(3).setPreferredWidth(56);
        columnModelBanHang.getColumn(4).setPreferredWidth(81);
        columnModelBanHang.getColumn(5).setPreferredWidth(81);
        columnModelBanHang.getColumn(6).setPreferredWidth(92);
        
        
        pnTable.add(scrTblKhuyenMai, BorderLayout.CENTER);
        pnMain.add(pnTable);
        
        
        this.add(pnMain , BorderLayout.CENTER);
        
    }   

    private void addEvents() {

    }
    
    
    
}
