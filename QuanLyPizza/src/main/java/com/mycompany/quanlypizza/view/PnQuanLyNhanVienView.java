package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.ImagePanelCommon;
import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.common.xuLyExcellCommon;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.PhanQuyen;
import com.mycompany.quanlypizza.enity.TaiKhoan;
import com.mycompany.quanlypizza.main.Main;
import com.mycompany.quanlypizza.repository.TaiKhoanRespository;
import com.mycompany.quanlypizza.service.NhanVienServiceImp;
import com.mycompany.quanlypizza.service.PhanQuyenServiceImp;
import com.mycompany.quanlypizza.service.TaiKhoanServiceImp;
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
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    private PhanQuyenServiceImp phanQuyenServiceImp = new PhanQuyenServiceImp();
    private NhanVienServiceImp nhanVienServiceImp = new NhanVienServiceImp();
    private TaiKhoanServiceImp taiKhoanServiceImp = new TaiKhoanServiceImp();
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
        pnPhanQuyen.add(pnButtonQuyen);
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
        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();

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
        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }

        });
        btnSuaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }

        });
        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }

        });
        btnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimNhanVien();
            }

        });
        txtTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimNhanVien();
            }

        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapExcel();
            }

        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatExcel();
            }

        });
        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });
        btnResetMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }

        });
        btnXoaTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }

        });

    }

    private void addEventsPhanQuyen() {
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
    }

    private void loadDataTblNhanVien() {
        dtmNhanVien.setRowCount(0);
        List<NhanVien> dsnv = nhanVienServiceImp.getDSNV();

        for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());
            int trangThai = taiKhoanServiceImp.getTrangThai(nv.getMaNV());
            for (int i = 0; i < vec.size(); i++) {
                System.out.println(vec.get(i));
            }

            if (trangThai == 0) {
                vec.add("Khoá");
            } else if (trangThai == 1) {
                vec.add("Hiệu lực");
            } else {
                vec.add("Chưa có");
            }
            dtmNhanVien.addRow(vec);
        }
    }

    private void xuLyClickTblNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row > -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(row, 0) + "");
            txtHo.setText(tblNhanVien.getValueAt(row, 1) + "");
            txtTen.setText(tblNhanVien.getValueAt(row, 2) + "");
            txtChucVu.setText(tblNhanVien.getValueAt(row, 4) + "");

            String gioiTinh = "";
            if ((tblNhanVien.getValueAt(row, 3) + "").equals("Nam")) {
                cmbGioiTinh.setSelectedIndex(1);
            } else {
                cmbGioiTinh.setSelectedIndex(2);
            }
        }
    }

    private void loadDataCmbQuyen() {
        phanQuyenServiceImp.getDanhSachQuyen();
        List<PhanQuyen> dsq = phanQuyenServiceImp.getDanhSachQuyen();
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        for (PhanQuyen pq : dsq) {
            cmbQuyen.addItem(pq.getQuyen());
        }
    }

    private void xuLyHienThiChiTietQuyen() {
        List<PhanQuyen> dsq = phanQuyenServiceImp.getDanhSachQuyen();
        PhanQuyen phanQuyen = new PhanQuyen();
        for (PhanQuyen pq : dsq) {
            if (pq.getQuyen().equals(cmbQuyen.getSelectedItem())) {
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        if (phanQuyen.getNhapHang() == 1) {
            ckbNhapHang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLSanPham.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLNhanVien.setSelected(true);
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLKhachHang.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbThongKe.setSelected(true);
        }
    }

    private void xuLyThemQuyen() {
        String ten = JOptionPane.showInputDialog("Nhập: ");
        boolean flag = phanQuyenServiceImp.themQuyen(ten);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialogCommon("Vui lòng chọn quyền để sửa!", MyDialogCommon.WARNING_DIALOG);
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;
        boolean flag = phanQuyenServiceImp.suaQuyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyXoaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialogCommon("Vui lòng chọn quyền để xóa!", MyDialogCommon.ERROR_DIALOG);
            return;
        }
        MyDialogCommon dlg = new MyDialogCommon("Bạn chắc chắn muốn xóa", MyDialogCommon.WARNING_DIALOG);
        if (dlg.getAction() == MyDialogCommon.CANCEL_OPTION) {
            return;
        }
        boolean flag = phanQuyenServiceImp.xoaQuyen((String) cmbQuyen.getSelectedItem());
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyThemNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialogCommon("Hãy chọn giới tính!", MyDialogCommon.ERROR_DIALOG);
            return;
        }
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String gioiString = String.valueOf(cmbGioiTinh.getSelectedItem());
        String chucVu = txtChucVu.getText();
        boolean flag = nhanVienServiceImp.themNV(ho, ten, gioiString, chucVu);
        if (flag) {
            loadDataTblNhanVien();
        }
    }

    private void xuLySuaNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialogCommon("Hãy chọn giới tính!", MyDialogCommon.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String gioiString = String.valueOf(cmbGioiTinh.getSelectedItem());
        String chucVu = txtChucVu.getText();
        boolean flag = nhanVienServiceImp.suaNV(ma, ho, ten, gioiString, chucVu);
        if (flag) {
            loadDataTblNhanVien();
        }

    }

    private void xuLyXoaNhanVien() {
        String ma = txtMaNV.getText();
        System.out.println(ma);
        boolean flag = nhanVienServiceImp.xoaNV(ma);
        if (flag) {
            System.out.println("Success!!");
            nhanVienServiceImp.getDSNV();
            loadDataTblNhanVien();
        }
    }

    private void xuLyTimNhanVien() {

        List<NhanVien> dsnv = nhanVienServiceImp.timNV(txtTimNV.getText());
        dtmNhanVien.setRowCount(0);
        for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());

            dtmNhanVien.addRow(vec);
        }

    }

    private void xuLyNhapExcel() {
        xuLyExcellCommon excel = new xuLyExcellCommon();
        excel.nhapExcel(tblNhanVien);
        int row = tblNhanVien.getRowCount();
        for (int i = 0; i < row; i++) {
            String ho = tblNhanVien.getValueAt(i, 1) + "";
            String ten = tblNhanVien.getValueAt(i, 2) + "";
            String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
            String chucVu = tblNhanVien.getValueAt(i, 4) + "";
            nhanVienServiceImp.nhapExcel(ho, ten, gioiTinh, chucVu);
        }
    }

    private void xuLyXuatExcel() {
        xuLyExcellCommon excel = new xuLyExcellCommon();
        excel.XuatFileExcel(tblNhanVien);
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().equals("")) {
            new MyDialogCommon("Hãy chọn nhân viên!", MyDialogCommon.ERROR_DIALOG);
            return;
        }
        DlgCapTaiKhoan dlg = new DlgCapTaiKhoan(txtMaNV.getText());
        dlg.setVisible(true);
        loadDataTblNhanVien();
    }

    private void xuLyResetMatKhau() {
        String maNV = txtMaNV.getText();
        if (maNV.trim().equals("")) {
            new MyDialogCommon("Hãy chọn nhân viên!", MyDialogCommon.ERROR_DIALOG);
            return;
        }
        DlgQuyen_MatKhau dlg = new DlgQuyen_MatKhau(maNV);
        dlg.setVisible(true);
    }

    private void xuLyKhoaTaiKhoan() {

        int trangThai = taiKhoanServiceImp.getTrangThai(Integer.parseInt(txtMaNV.getText()));
        if (trangThai == 1) {
            new MyDialogCommon("Bạn muốn khóa tài khoản này!", MyDialogCommon.WARNING_DIALOG);
            taiKhoanServiceImp.khoaTaiKhoan(txtMaNV.getText());
            loadDataTblNhanVien();
        } else if (trangThai == 0) {
            MyDialogCommon dlgmessOpen = new MyDialogCommon("Bạn muốn mở khóa tài khoản này!", MyDialogCommon.WARNING_DIALOG);
            if (dlgmessOpen.getAction() == MyDialogCommon.OK_OPTION) {
                taiKhoanServiceImp.moKhoaTaiKhoan(txtMaNV.getText());
                loadDataTblNhanVien();
            } else {
                return;
            }

        } else {
            MyDialogCommon dlgmess = new MyDialogCommon("Nhân viên này chưa có tài khoản! Bạn có muốn tạo tài khoản cho nhân viên này?", MyDialogCommon.ERROR_DIALOG);
            if (dlgmess.getAction() == MyDialogCommon.OK_OPTION) {
                DlgCapTaiKhoan dlg = new DlgCapTaiKhoan(txtMaNV.getText());
                dlg.setVisible(true);
                loadDataTblNhanVien();
            } else {
                return;
            }
        }

    }
}
