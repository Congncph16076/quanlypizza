/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.enity.GiamGia;
import com.mycompany.quanlypizza.main.Main;
import com.mycompany.quanlypizza.service.GiamGiaServiceImp;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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


/**
 *
 * @author congc
 */
public class PnQuanLyKhuyenMaiView extends  JPanel{

    public PnQuanLyKhuyenMaiView() {
        
        Main.changLNF("Windows");
        addControls();
        addEvents();
    }

    private GiamGiaServiceImp giamGiaServiceImp;
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
        
         Dimension lblSize = lblTen.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblPhanTram.setPreferredSize(lblSize);
        lblDieuKien.setPreferredSize(lblSize);
        lblNgayBD.setPreferredSize(lblSize);
        lblNgayKT.setPreferredSize(lblSize);
        dateBD.setPreferredSize(txtDieuKien.getPreferredSize());
        dateKT.setPreferredSize(txtDieuKien.getPreferredSize());
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
        
        
        DefaultTableCellRenderer centerCellRenderer = new  DefaultTableCellRenderer();
        centerCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerCellRenderer);
        
        DefaultTableCellRenderer rightCellRenderer = new  DefaultTableCellRenderer();
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
        loadDataTblKhuyenMai();
        
    }   

    private void addEvents() {
        tblKhuyenMai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKhuyenMai();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtPhanTram.setText("");
                txtDieuKien.setText("");
                dateBD.setDate(null);
                dateKT.setDate(null);
                loadDataTblKhuyenMai();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhuyenMai();
            }

        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhuyenMai();
            }

        });
    }
    private void loadDataTblKhuyenMai() {
        dtmKhuyenMai.setRowCount(0);
        giamGiaServiceImp = new GiamGiaServiceImp();

        List<GiamGia> dsg = giamGiaServiceImp.getDanhSachMaGiam();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###");

        for (GiamGia gg : dsg) {
            Vector vec = new Vector();
            vec.add(gg.getMaGiam());
            vec.add(gg.getTenGiamGia());
            vec.add(gg.getPhanTramGiam());
            vec.add(dcf.format(gg.getDieuKien()));
            vec.add(sdf.format(gg.getNgayBD()));
            vec.add(sdf.format(gg.getNgayKT()));
           
            Date now = new Date();
            if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add("Có hiệu lực");
            } else {
                vec.add("Hết hiệu lực");
            }
            dtmKhuyenMai.addRow(vec);
        }
    }
    
     private void xuLyClickTblKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row > -1) {
            String ma = tblKhuyenMai.getValueAt(row, 0) + "";
            String ten = tblKhuyenMai.getValueAt(row, 1) + "";
            String phanTram = tblKhuyenMai.getValueAt(row, 2) + "";
            String dieuKien = tblKhuyenMai.getValueAt(row, 3) + "";
            String start = tblKhuyenMai.getValueAt(row, 4) + "";
            String end = tblKhuyenMai.getValueAt(row, 5) + "";

            dieuKien = dieuKien.replace(">", "");
            dieuKien = dieuKien.replace(",", "");
            Date ngayBD = new Date();
            Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);

            } catch (Exception e) {
                e.printStackTrace();
            }
            txtMa.setText(ma);
            txtTen.setText(ten);
            txtPhanTram.setText(phanTram);
            txtDieuKien.setText(dieuKien);
            dateBD.setDate(ngayBD);
            dateKT.setDate(ngayKT);
        }
    }
     private void xuLyThemKhuyenMai() {
        giamGiaServiceImp = new GiamGiaServiceImp();
        boolean flag = giamGiaServiceImp.themMaGiam(txtTen.getText(), txtPhanTram.getText(), txtDieuKien
                .getText(), dateBD.getDate(), dateKT.getDate());
        if (flag) {
            loadDataTblKhuyenMai();
        }
    }
      private void xuLySuaKhuyenMai() {
        giamGiaServiceImp = new GiamGiaServiceImp();
        boolean flag = giamGiaServiceImp.suaMaGiam(txtMa.getText(),txtTen.getText(), txtPhanTram.getText(), txtDieuKien
                .getText(), dateBD.getDate(), dateKT.getDate());
        if (flag) {
            loadDataTblKhuyenMai();
        }
    }
    
}
