package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.ImagePanelCommon;
import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.main.Main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PnQuanLyNhanVienView extends JPanel {

    public PnQuanLyNhanVienView() {
        Main.changLNF("Windows");
        addControlsNhanVien();
        addEventsNhanVien();
        addEventsPhanQuyen();
    }

    JLabel lblTabbedNhanVien, lblTabbedQuyen;
    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");
    final Color colorPanel = new Color(247, 247, 247);
    CardLayout cardNhanVienGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    JTextField txtMaNV, txtHo, txtTen, txtChucVu, txtTimNV;
    JComboBox<String> cmbGioiTinh, cmbQuyen;
    MyTableCommon tblNhanVien;
    DefaultTableModel dtmNhanVien;
    JButton btnReset, btnThemNV, btnSuaNV, btnXoaNV, btnTimNV, btnCapTaiKhoan, btnResetMatKhau, btnXoaTaiKhoan, btnXuatExcel, btnNhapExcel;

    JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe;
    JButton btnSuaQuyen, btnThemQuyen, btnXoaQuyen;

    private void addControlsNhanVien() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;
        int h = 844;

        // PANEL TABBED
        JPanel pnTop = new TransparentPanelCommon();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Nhân viên & Quyền">
        Font font = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedNhanVien = new JLabel("Nhân viên");
        lblTabbedNhanVien.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setIcon(tabbedSelected);
        lblTabbedNhanVien.setBounds(2, 2, 140, 37);
        lblTabbedNhanVien.setFont(font);
        lblTabbedNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuyen = new JLabel("Quyền");
        lblTabbedQuyen.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setIcon(tabbedDefault);
        lblTabbedQuyen.setBounds(143, 2, 140, 37);
        lblTabbedQuyen.setFont(font);
        lblTabbedQuyen.setForeground(Color.WHITE);
        lblTabbedQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedNhanVien);
        pnTop.add(lblTabbedQuyen);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

        //------------------------------------------------------------------------
        // ---------------------PANEL NHÂN VIÊN----------------------
        //------------------------------------------------------------------------
        JPanel pnNhanVien = new TransparentPanelCommon();
        pnNhanVien.setLayout(new BoxLayout(pnNhanVien, BoxLayout.Y_AXIS));

        JPanel pnTopNV = new TransparentPanelCommon();
        pnTopNV.setLayout(new BoxLayout(pnTopNV, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanelCommon();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ NHÂN VIÊN</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnTopNV.add(pnTitle);
        pnTopNV.setBackground(Color.DARK_GRAY);

        JPanel pnText = new TransparentPanelCommon();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        txtMaNV = new JTextField(25);
        txtMaNV.setEditable(false);
        txtHo = new JTextField(25);
        txtTen = new JTextField(25);
        cmbGioiTinh = new JComboBox<>();
        txtChucVu = new JTextField(28);

        txtMaNV.setFont(font);
        txtHo.setFont(font);
        txtTen.setFont(font);
        cmbGioiTinh.setFont(font);
        txtChucVu.setFont(font);

        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        JLabel lblMa, lblHo, lblTen, lblGioiTinh, lblChucVu;

        lblMa = new JLabel("Mã nhân viên");
        lblHo = new JLabel("Họ");
        lblTen = new JLabel("Tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblChucVu = new JLabel("Chức vụ");

        lblMa.setFont(font);
        lblHo.setFont(font);
        lblTen.setFont(font);
        lblGioiTinh.setFont(font);
        lblChucVu.setFont(font);

        JPanel pnMa = new TransparentPanelCommon();
        pnMa.add(lblMa);
        pnMa.add(txtMaNV);
        pnText.add(pnMa);

        JPanel pnHo = new TransparentPanelCommon();
        pnHo.add(lblHo);
        pnHo.add(txtHo);
        pnText.add(pnHo);

        JPanel pnTen = new TransparentPanelCommon();
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnText.add(pnTen);

        JPanel pnGioiTinh = new TransparentPanelCommon();
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
        pnText.add(pnGioiTinh);

        JPanel pnChucVu = new TransparentPanelCommon();
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);
        pnText.add(pnChucVu);

        Dimension lblSize = lblMa.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblHo.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblGioiTinh.setPreferredSize(lblSize);
        lblChucVu.setPreferredSize(lblSize);
        cmbGioiTinh.setPreferredSize(txtChucVu.getPreferredSize());

        pnTopNV.add(pnText);

        JPanel pnTimNV = new TransparentPanelCommon();
        JLabel lblTim = new JLabel("Từ khóa");
        lblTim.setFont(font);
        txtTimNV = new JTextField(25);
        txtTimNV.setFont(font);
        pnTimNV.add(lblTim);
        pnTimNV.add(txtTimNV);
        pnTopNV.add(pnTimNV);
        lblTim.setPreferredSize(lblSize);

        JPanel pnButton = new TransparentPanelCommon();

        btnThemNV = new JButton("Thêm");
        btnSuaNV = new JButton("Lưu");
        btnXoaNV = new JButton("Xóa");
        btnTimNV = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemNV.setFont(fontButton);
        btnSuaNV.setFont(fontButton);
        btnXoaNV.setFont(fontButton);
        btnTimNV.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThemNV.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaNV.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaNV.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTimNV.setIcon(new ImageIcon("image/Search-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        pnButton.add(btnThemNV);
        pnButton.add(btnSuaNV);
        pnButton.add(btnXoaNV);
        pnButton.add(btnTimNV);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTimNV.getPreferredSize();
        btnThemNV.setPreferredSize(btnSize);
        btnSuaNV.setPreferredSize(btnSize);
        btnXoaNV.setPreferredSize(btnSize);
        btnTimNV.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        JPanel pnButton2 = new TransparentPanelCommon();
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhau = new JButton("Mật khẩu/Quyền");
        btnXoaTaiKhoan = new JButton("Khóa tài khoản");
        btnCapTaiKhoan.setIcon(new ImageIcon("image/icons8_man_with_key_32px.png"));
        btnResetMatKhau.setIcon(new ImageIcon("image/icons8_password_reset_32px.png"));
        btnXoaTaiKhoan.setIcon(new ImageIcon("image/icons8_denied_32px.png"));
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhau.setFont(fontButton);
        btnXoaTaiKhoan.setFont(fontButton);
        pnButton2.add(btnCapTaiKhoan);
        pnButton2.add(btnResetMatKhau);
        pnButton2.add(btnXoaTaiKhoan);

        pnNhanVien.add(pnTopNV);
        pnNhanVien.add(pnButton);
        pnNhanVien.add(pnButton2);

        //------------------------------------------------------------------------
        // ---------------------BẢNG NHÂN VIÊN--------------------------
        //------------------------------------------------------------------------
        JPanel pnTableNhanVien = new TransparentPanelCommon();
        pnTableNhanVien.setLayout(new BorderLayout());
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã NV");
        dtmNhanVien.addColumn("Họ");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Tài khoản");
        tblNhanVien = new MyTableCommon(dtmNhanVien);
        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTableNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);
        pnNhanVien.add(scrTblNhanVien);

        //------------------------------------------------------------------------
        // ---------------------PANEL PHÂN QUYỀN---------------------
        //------------------------------------------------------------------------
        JPanel pnPhanQuyen = new TransparentPanelCommon();
        pnPhanQuyen.setLayout(new BoxLayout(pnPhanQuyen, BoxLayout.Y_AXIS));

        JPanel pnTitlePhanQuyen = new TransparentPanelCommon();
        JLabel lblTitlePhanQuyen = new JLabel("<html><h1>Quản lý phân quyền</h1></html>");
        pnTitlePhanQuyen.add(lblTitlePhanQuyen);
        pnPhanQuyen.add(pnTitlePhanQuyen);

        JPanel pnCmbQuyen = new TransparentPanelCommon();
        JLabel lblCmbQuyen = new JLabel("<html><b>Nhóm quyền:</b></html>");
        lblCmbQuyen.setFont(font);
        cmbQuyen = new JComboBox<String>();
        cmbQuyen.setFont(font);
        pnCmbQuyen.add(lblCmbQuyen);
        pnCmbQuyen.add(cmbQuyen);
        pnPhanQuyen.add(pnCmbQuyen);

        JPanel pnCheckNhapHang = new TransparentPanelCommon();
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng");
        ckbNhapHang.setFont(font);
        pnCheckNhapHang.add(ckbNhapHang);
        pnPhanQuyen.add(pnCheckNhapHang);

        JPanel pnCheckQLSanPham = new TransparentPanelCommon();
        ckbQLSanPham = new JCheckBox("Quản lý Sản phẩm");
        ckbQLSanPham.setFont(font);
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnPhanQuyen.add(pnCheckQLSanPham);

        JPanel pnCheckQLNhanVien = new TransparentPanelCommon();
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên");
        ckbQLNhanVien.setFont(font);
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnPhanQuyen.add(pnCheckQLNhanVien);

        JPanel pnCheckQLKhachHang = new TransparentPanelCommon();
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng");
        ckbQLKhachHang.setFont(font);
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnPhanQuyen.add(pnCheckQLKhachHang);

        JPanel pnCheckQLThongKe = new TransparentPanelCommon();
        ckbThongKe = new JCheckBox("Quản lý thống kê");
        ckbThongKe.setFont(font);
        pnCheckQLThongKe.add(ckbThongKe);
        pnPhanQuyen.add(pnCheckQLThongKe);

        Dimension ckbSize = ckbQLKhachHang.getPreferredSize();
        cmbQuyen.setPreferredSize(ckbSize);
        ckbNhapHang.setPreferredSize(ckbSize);
        ckbQLSanPham.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLKhachHang.setPreferredSize(ckbSize);
        ckbThongKe.setPreferredSize(ckbSize);

        JPanel pnButtonQuyen = new TransparentPanelCommon();
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xóa quyền");
        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);
        btnThemQuyen.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaQuyen.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaQuyen.setIcon(new ImageIcon("image/delete-icon.png"));
        pnButtonQuyen.add(btnThemQuyen);
        pnButtonQuyen.add(btnSuaQuyen);
        pnButtonQuyen.add(btnXoaQuyen);
        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());

        JPanel pnImage = new ImagePanelCommon("image/backgroundManagerment.jpg");
        pnImage.setPreferredSize(new Dimension(w, 450));
        pnPhanQuyen.add(pnImage);

        pnCardTabNhanVien = new JPanel(cardNhanVienGroup);
        pnCardTabNhanVien.add(pnNhanVien, "1");
        pnCardTabNhanVien.add(pnPhanQuyen, "2");
        this.add(pnCardTabNhanVien);

        loadDataTblNhanVien();
        loadDataCmbQuyen();
    }

    private void addEventsNhanVien() {
        lblTabbedNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedSelected);
                lblTabbedQuyen.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "1");
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
        lblTabbedQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedDefault);
                lblTabbedQuyen.setIcon(tabbedSelected);
                cardNhanVienGroup.show(pnCardTabNhanVien, "2");
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
                loadDataTblNhanVien();
                txtMaNV.setText("");
                txtHo.setText("");
                txtTen.setText("");
                txtChucVu.setText("");
                txtTimNV.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });
    }

    private void addEventsPhanQuyen() {
    }

    private void loadDataTblNhanVien() {
    }

    private void loadDataCmbQuyen() {
    }

}
