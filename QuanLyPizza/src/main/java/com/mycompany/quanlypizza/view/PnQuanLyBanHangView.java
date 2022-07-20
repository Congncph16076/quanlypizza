package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.main.Main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.NumberFormatter;

public class PnQuanLyBanHangView extends JPanel {

    JLabel lblTabbedBanHang, lblTabbedHoaDon;
    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");
    final Color coloPanel = new Color(247, 247, 247);

    CardLayout cardBanHangGroup = new CardLayout();

    JPanel pnCardTabBanHang;
    MyTableCommon tblBanHang, tblGioHang;
    DefaultTableModel dtmSanPhamBan, dtmGioHang;
    JTextField txtMaSPBanHang, txtTenSPBanHang, txtDonGiaBanHang;
    JSpinner spnSoLuongBanHang;
    JComboBox<String> cmbLoaiSPBanHang, cmbNhanVienBan;
    JLabel btnThemVaoGio, lblAnhSP, btnXoaSPGioHang, btnXuatHoaDonSP;
    JTextField txtMaHD, txtNgayLap, txtMaKH, txtMaNV, txtTongTien, txtGhiChu, txtMaHDCT, txtMaSPCT, txtSoLuongCT, txtDonGiaCT, txtThanhTienCT;

    JTextField txtMinSearch, txtMaxSearch, txtMinNgayLap, txtMaxNgayLap;
    JList<String> listHoaDon;
    MyTableCommon tblCTHoaDon;
    DefaultTableModel dtmCTHoaDon;
    JButton btnReset, btnResetCTHoaDon, btnResetHoaDon;

    public PnQuanLyBanHangView() {
        Main.changLNF("Windows");
        addControlsBanHang();
        addEventsBanHang();
    }

    private void addControlsBanHang() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(coloPanel);

        int w = 1030;
        int h = 844;

        // PANEL TABBED
        JPanel pnTop = new TransparentPanelCommon();

        //panel TAB bán hàng & hóa đơn
//        this.add(pnTop, BorderLayout.NORTH);
        Font font = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedBanHang = new JLabel("Bán Hàng");
        lblTabbedBanHang.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedBanHang.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedBanHang.setIcon(tabbedSelected);
        lblTabbedBanHang.setBounds(2, 2, 140, 37);
        lblTabbedBanHang.setFont(font);
        lblTabbedBanHang.setForeground(Color.WHITE);
        lblTabbedBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedHoaDon = new JLabel("Hóa đơn");
        lblTabbedHoaDon.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedHoaDon.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedHoaDon.setIcon(tabbedDefault);
        lblTabbedHoaDon.setBounds(143, 2, 140, 37);
        lblTabbedHoaDon.setFont(font);
        lblTabbedHoaDon.setForeground(Color.WHITE);
        lblTabbedHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedBanHang);
        pnTop.add(lblTabbedHoaDon);
        this.add(pnTop, BorderLayout.NORTH);

        //BẢNG HÀNG HÓA
        JPanel pnTableBanHang = new TransparentPanelCommon();
        pnTableBanHang.setLayout(new BorderLayout());

        JPanel pnTitleBanHang = new TransparentPanelCommon();
        JLabel lblTitleBanHang = new JLabel("Danh sách sản phẩm");
        lblTitleBanHang.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleBanHang.add(lblTitleBanHang);
        pnTitleBanHang.add(btnReset);
        pnTableBanHang.add(pnTitleBanHang, BorderLayout.NORTH);

        dtmSanPhamBan = new DefaultTableModel();
        dtmSanPhamBan.addColumn("Mã SP");
        dtmSanPhamBan.addColumn("Tên SP");
        dtmSanPhamBan.addColumn("Đơn giá");
        dtmSanPhamBan.addColumn("Còn lại");
        dtmSanPhamBan.addColumn("Đơn vị tính");
        dtmSanPhamBan.addColumn("Ảnh");
        tblBanHang = new MyTableCommon(dtmSanPhamBan);

        tblBanHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelBanHang = tblBanHang.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(77);
        columnModelBanHang.getColumn(1).setPreferredWidth(282);
        columnModelBanHang.getColumn(2).setPreferredWidth(82);
        columnModelBanHang.getColumn(3).setPreferredWidth(85);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(0);

        JScrollPane scrTblBanHang = new JScrollPane(tblBanHang);
        pnTableBanHang.add(scrTblBanHang, BorderLayout.CENTER);
        //Panel thông tin giỏ hàng

        JPanel pnTableGioHang = new TransparentPanelCommon();
        pnTableGioHang.setLayout(new BorderLayout());

        JLabel lblTitleGioHang = new JLabel("Giỏ Hàng");
        lblTitleGioHang.setFont(new Font("Arial", Font.BOLD, 28));
        pnTableGioHang.add(lblTitleGioHang, BorderLayout.NORTH);

        dtmGioHang = new DefaultTableModel();
        dtmGioHang.addColumn("Mã SP");
        dtmGioHang.addColumn("Tên SP");
        dtmGioHang.addColumn("Số lượng");
        dtmGioHang.addColumn("Đơn giá");
        dtmGioHang.addColumn("Thành tiền");

        tblGioHang = new MyTableCommon(dtmGioHang);

        tblGioHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblGioHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblGioHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblGioHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelGioHang = tblGioHang.getColumnModel();
        columnModelGioHang.getColumn(0).setPreferredWidth(81);
        columnModelGioHang.getColumn(1).setPreferredWidth(279);
        columnModelGioHang.getColumn(2).setPreferredWidth(111);
        columnModelGioHang.getColumn(3).setPreferredWidth(101);
        columnModelGioHang.getColumn(4).setPreferredWidth(100);
        
        JScrollPane scrTblGioHang = new JScrollPane(tblGioHang);
        pnTableGioHang.add(scrTblGioHang, BorderLayout.CENTER);

        //THÔNG TIN BÁN HÀNG
        JPanel pnThongTinBanHang = new TransparentPanelCommon();
        pnThongTinBanHang.setLayout(new BoxLayout(pnThongTinBanHang, BoxLayout.Y_AXIS));
        //<editor-fold defaultstate="collapsed" desc="Thông tin bán hàng (textfield, button thêm)">
        JPanel pnTitleThongTin = new TransparentPanelCommon();
        JLabel lblTitleThongTin = new JLabel("Chi tiết sản phẩm", JLabel.LEFT);
        lblTitleThongTin.setFont(new Font("Arial", Font.BOLD, 28));
        pnTitleThongTin.add(lblTitleThongTin);
        pnThongTinBanHang.add(pnTitleThongTin);

        JPanel pnLoaiSP = new TransparentPanelCommon();
        JLabel lblLoai = new JLabel("Loại SP");
        lblLoai.setFont(font);
        cmbLoaiSPBanHang = new JComboBox<>();
        cmbLoaiSPBanHang.setFont(font);
        loadDataComboboxLoaiBanSP();
        pnLoaiSP.add(lblLoai);
        pnLoaiSP.add(cmbLoaiSPBanHang);
        pnThongTinBanHang.add(pnLoaiSP);

        JPanel pnMaSP = new TransparentPanelCommon();
        JLabel lblMa = new JLabel("Mã SP");
        lblMa.setFont(font);
        txtMaSPBanHang = new JTextField(15);
        txtMaSPBanHang.setFont(font);
        txtMaSPBanHang.setEditable(false);
        pnMaSP.add(lblMa);
        pnMaSP.add(txtMaSPBanHang);
        pnThongTinBanHang.add(pnMaSP);

        JPanel pnTenSP = new TransparentPanelCommon();
        JLabel lblTen = new JLabel("Tên SP");
        lblTen.setFont(font);
        txtTenSPBanHang = new JTextField(15);
        txtTenSPBanHang.setFont(font);
        txtTenSPBanHang.setEditable(false);
        pnTenSP.add(lblTen);
        pnTenSP.add(txtTenSPBanHang);
        pnThongTinBanHang.add(pnTenSP);

        JPanel pnDonGiaSP = new TransparentPanelCommon();
        JLabel lblDonGia = new JLabel("Đơn giá");
        lblDonGia.setFont(font);
        txtDonGiaBanHang = new JTextField(15);
        txtDonGiaBanHang.setFont(font);
        txtDonGiaBanHang.setEditable(false);
        pnDonGiaSP.add(lblDonGia);
        pnDonGiaSP.add(txtDonGiaBanHang);
        pnThongTinBanHang.add(pnDonGiaSP);

        JPanel pnSoLuongSP = new TransparentPanelCommon();
        JLabel lblSoLuong = new JLabel("Số Lượng");
        lblSoLuong.setFont(font);
        spnSoLuongBanHang = new JSpinner();
        spnSoLuongBanHang.setFont(font);
        SpinnerNumberModel modelSpinner = new SpinnerNumberModel(1, 1, 100, 1);
        spnSoLuongBanHang.setModel(modelSpinner);
        JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
        ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
        txtSpinner.setEditable(false);
        txtSpinner.setHorizontalAlignment(JTextField.LEFT);
        pnSoLuongSP.add(lblSoLuong);
        pnSoLuongSP.add(spnSoLuongBanHang);
        pnThongTinBanHang.add(pnSoLuongSP);

        JPanel pnNhanVienBan = new TransparentPanelCommon();
        JLabel lblNhanVien = new JLabel("Nhân viên");
        lblNhanVien.setFont(font);
        lblLoai.setFont(font);
        cmbNhanVienBan = new JComboBox<>();
        cmbNhanVienBan.setFont(font);
        loadDataComboboxNhanVienBan();
        pnNhanVienBan.add(lblNhanVien);
        pnNhanVienBan.add(cmbNhanVienBan);
        pnThongTinBanHang.add(pnNhanVienBan);

        JPanel pnButtonBan = new TransparentPanelCommon();
        btnThemVaoGio = new JLabel("Thêm vào giỏ");
        pnButtonBan.add(btnThemVaoGio);
        pnThongTinBanHang.add(pnButtonBan);

        cmbLoaiSPBanHang.setPreferredSize(new Dimension(txtMaSPBanHang.getPreferredSize()));
        Dimension sizeLabel = lblNhanVien.getPreferredSize();
        lblLoai.setPreferredSize(sizeLabel);
        lblMa.setPreferredSize(sizeLabel);
        lblTen.setPreferredSize(sizeLabel);
        lblDonGia.setPreferredSize(sizeLabel);
        lblSoLuong.setPreferredSize(sizeLabel);
        spnSoLuongBanHang.setPreferredSize(txtMaSPBanHang.getPreferredSize());
        cmbNhanVienBan.setPreferredSize(txtMaSPBanHang.getPreferredSize());

        //<editor-fold defaultstate="collapsed" desc="Ảnh hàng">
        JPanel pnAnhSanPham = new TransparentPanelCommon();
        pnAnhSanPham.setPreferredSize(new Dimension((int) pnThongTinBanHang.getPreferredSize().getWidth(), 220));
        lblAnhSP = new JLabel();
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        pnAnhSanPham.add(lblAnhSP);

        JPanel pnButtonBanHang = new TransparentPanelCommon();
        btnXoaSPGioHang = new JLabel("Xóa");
        btnXuatHoaDonSP = new JLabel("Xuất hóa đơn");
        pnButtonBanHang.setPreferredSize(new Dimension((int) pnThongTinBanHang.getPreferredSize().getWidth(), 50));

        //<editor-fold defaultstate="collapsed" desc="Action cho button">
        ArrayList<JLabel> btnSPList = new ArrayList<>();
        btnSPList.add(btnThemVaoGio);
        btnSPList.add(btnXoaSPGioHang);
        btnSPList.add(btnXuatHoaDonSP);

        for (JLabel lbl : btnSPList) {
            lbl.setFont(font);
            lbl.setForeground(Color.WHITE);
            lbl.setIcon(new ImageIcon("image/ManagerUI/btn-BanHang.png"));
            lbl.setHorizontalTextPosition(JLabel.CENTER);
            lbl.setVerticalTextPosition(JLabel.CENTER);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (lbl != btnThemVaoGio) {
                JPanel pnTemp = new TransparentPanelCommon();
                pnTemp.add(lbl);
                pnButtonBanHang.add(pnTemp);
            }
            lbl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    lbl.setIcon(new ImageIcon("image/ManagerUI/btn-BanHang--hover.png"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lbl.setIcon(new ImageIcon("image/ManagerUI/btn-BanHang.png"));
                }
            });

        }
        //</editor-fold>
        //</editor-fold>

        //--------------------------------------------------------------------------------------------------
        JPanel pnCenter = new TransparentPanelCommon();

        JPanel pnLeftBanHang = new TransparentPanelCommon();
        pnLeftBanHang.setLayout(new BoxLayout(pnLeftBanHang, BoxLayout.Y_AXIS));
        pnLeftBanHang.setPreferredSize(new Dimension(618, h - 41));
        pnTableBanHang.setPreferredSize(new Dimension(618, (h - 41) / 2));
        pnLeftBanHang.add(pnTableBanHang);
        pnLeftBanHang.add(pnTableGioHang);
        pnCenter.add(pnLeftBanHang);

        JPanel pnRightBanHang = new TransparentPanelCommon();
        pnRightBanHang.setLayout(new BoxLayout(pnRightBanHang, BoxLayout.Y_AXIS));
        pnRightBanHang.add(pnThongTinBanHang);
        pnThongTinBanHang.setPreferredSize(new Dimension((int) pnRightBanHang.getPreferredSize().getWidth(),
                (int) pnTableBanHang.getPreferredSize().getHeight()));

        pnRightBanHang.add(pnAnhSanPham);
        pnRightBanHang.add(pnButtonBanHang);
        pnCenter.add(pnRightBanHang);

        pnCardTabBanHang = new JPanel(cardBanHangGroup);
        pnCardTabBanHang.setPreferredSize(new Dimension(w, 
                (int) (h - pnTop.getPreferredSize().getHeight())));
        JPanel pnCTBanHang = new TransparentPanelCommon();
        pnCTBanHang.setLayout(new BorderLayout());
        pnCTBanHang.add(pnCenter, BorderLayout.CENTER);
        pnCardTabBanHang.add(pnCTBanHang, "1");
//        --------------------------------------------------------------------------------------
//         Panel Chi tiết hóa đơn
//        --------------------------------------------------------------------------------------
        JPanel pnCTHoaDon = new JPanel();
        pnCTHoaDon.setLayout(new BorderLayout());

        JPanel pnCTHoaDonLeft = new TransparentPanelCommon();
        pnCTHoaDonLeft.setPreferredSize(new Dimension(420,
                (int) pnCardTabBanHang.getPreferredSize().getHeight()));
        pnCTHoaDonLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY));
        pnCTHoaDonLeft.setLayout(new BoxLayout(pnCTHoaDonLeft, BoxLayout.Y_AXIS));
        pnCTHoaDon.add(pnCTHoaDonLeft, BorderLayout.WEST);

        JLabel lblMaHD, lblNgayLap, lblMaKH, lblMaNV, lblTongTien, lblGhiChu, lblMinsearch, lblMaxSearch, lblMinNgay, lblMaxNgay;

        lblMaHD = new JLabel("Mã HD");
        lblMaKH = new JLabel("Mã KH");
        lblMaNV = new JLabel("NV lập");
        lblNgayLap = new JLabel("Ngày lập");
        lblTongTien = new JLabel("Tổng tiền");
        lblGhiChu = new JLabel("Ghi chú");
        lblMinsearch = new JLabel("Từ: ");
        lblMaxSearch = new JLabel("đến: ");
        lblMinNgay = new JLabel("Ngày lập: ");
        lblMaxNgay = new JLabel("đến: ");

        txtMaHD = new JTextField(10);
        txtMaKH = new JTextField(10);
        txtMaNV = new JTextField(10);
        txtNgayLap = new JTextField(10);
        txtTongTien = new JTextField(10);
        txtGhiChu = new JTextField(10);
        txtMinSearch = new JTextField(7);
        txtMaxSearch = new JTextField(7);
        txtMinNgayLap = new JTextField(7);
        txtMaxNgayLap = new JTextField(7);

        JPanel pnTitleHoaDon = new TransparentPanelCommon(new FlowLayout());
        JLabel lblTitleHoaDon = new JLabel("THÔNG TIN HÓA ĐƠN");
        lblTitleHoaDon.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnResetHoaDon = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnResetHoaDon.setPreferredSize(new Dimension(40, 40));
        pnTitleHoaDon.add(lblTitleHoaDon);
        pnTitleHoaDon.add(btnResetHoaDon);
        pnCTHoaDonLeft.add(pnTitleHoaDon);

        JPanel pnMaHD = new TransparentPanelCommon(new FlowLayout());
        pnMaHD.add(lblMaHD);
        lblMaHD.setFont(font);
        txtMaHD.setFont(font);
        pnMaHD.add(txtMaHD);
        pnCTHoaDonLeft.add(pnMaHD);

        JPanel pnMaKH = new TransparentPanelCommon(new FlowLayout());
        pnMaKH.add(lblMaKH);
        lblMaKH.setFont(font);
        txtMaKH.setFont(font);
        pnMaKH.add(txtMaKH);
        pnCTHoaDonLeft.add(pnMaKH);

        JPanel pnMaNV = new TransparentPanelCommon(new FlowLayout());
        pnMaNV.add(lblMaNV);
        lblMaNV.setFont(font);
        txtMaNV.setFont(font);
        pnMaNV.add(txtMaNV);
        pnCTHoaDonLeft.add(pnMaNV);

        JPanel pnNgayLap = new TransparentPanelCommon(new FlowLayout());
        pnNgayLap.add(lblNgayLap);
        lblNgayLap.setFont(font);
        txtNgayLap.setFont(font);
        pnNgayLap.add(txtNgayLap);
        pnCTHoaDonLeft.add(pnNgayLap);

        JPanel pnTongTien = new TransparentPanelCommon(new FlowLayout());
        pnTongTien.add(lblTongTien);
        lblTongTien.setFont(font);
        txtTongTien.setFont(font);
        pnTongTien.add(txtTongTien);
        pnCTHoaDonLeft.add(pnTongTien);

        JPanel pnGhiChu = new TransparentPanelCommon(new FlowLayout());
        pnGhiChu.add(lblGhiChu);
        lblGhiChu.setFont(font);
        txtGhiChu.setFont(font);
        pnGhiChu.add(txtGhiChu);
        pnCTHoaDonLeft.add(pnGhiChu);

        JPanel pnSearchPrice = new TransparentPanelCommon(new FlowLayout());
        lblMinsearch.setFont(font);
        lblMaxSearch.setFont(font);
        txtMinSearch.setFont(font);
        txtMaxSearch.setFont(font);
        pnSearchPrice.add(lblMinsearch);
        pnSearchPrice.add(txtMinSearch);
        pnSearchPrice.add(lblMaxSearch);
        pnSearchPrice.add(txtMaxSearch);
        pnCTHoaDonLeft.add(pnSearchPrice);

        JPanel pnSearchDate = new TransparentPanelCommon(new FlowLayout());
        lblMinNgay.setFont(font);
        lblMaxNgay.setFont(font);
        txtMinNgayLap.setFont(font);
        txtMaxNgayLap.setFont(font);
        pnSearchDate.add(lblMinNgay);
        pnSearchDate.add(txtMinNgayLap);
        pnSearchDate.add(lblMaxNgay);
        pnSearchDate.add(txtMaxNgayLap);
        pnCTHoaDonLeft.add(pnSearchDate);

        Dimension lblHoaDonSize = lblTongTien.getPreferredSize();
        lblMaHD.setPreferredSize(lblHoaDonSize);
        lblNgayLap.setPreferredSize(lblHoaDonSize);
        lblMaKH.setPreferredSize(lblHoaDonSize);
        lblMaNV.setPreferredSize(lblHoaDonSize);
        lblTongTien.setPreferredSize(lblHoaDonSize);
        lblGhiChu.setPreferredSize(lblHoaDonSize);
        lblMinsearch.setPreferredSize(lblMinNgay.getPreferredSize());
//        lblMaHD.setPreferredSize(lblHoaDonSize);
//        lblMaHD.setPreferredSize(lblHoaDonSize);
//        lblMaHD.setPreferredSize(lblHoaDonSize);

        txtMaHD.setEditable(false);
        txtMaKH.setEditable(false);
        txtMaNV.setEditable(false);
        txtNgayLap.setEditable(false);
        txtTongTien.setEditable(false);
        txtGhiChu.setEditable(false);

        JPanel pnListHoaDon = new TransparentPanelCommon();
        listHoaDon = new JList<>();
        listHoaDon.setFont(font);
        listHoaDon.setPreferredSize(new Dimension(
                (int) (pnCTHoaDon.getPreferredSize().getWidth() - 22), 400
        ));
        loadDataListHoaDon();
        JScrollPane scrHoaDon = new JScrollPane(
                listHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrHoaDon.setPreferredSize(listHoaDon.getPreferredSize());
        pnListHoaDon.add(scrHoaDon);
        pnCTHoaDonLeft.add(pnListHoaDon);

        //----------------------------------------------------------------
        JPanel pnCTHoaDonRight = new TransparentPanelCommon();
        pnCTHoaDonRight.setLayout(new FlowLayout());

        JPanel pnTopCTHoaDonRight = new TransparentPanelCommon();
        pnTopCTHoaDonRight.setLayout(new BoxLayout(pnTopCTHoaDonRight, BoxLayout.Y_AXIS));
        JLabel lblMAHDCT, lblMaSPCT, lblSoLuongCT, lblDonGiaCT, lblThanhTienCT;
        lblMAHDCT = new JLabel("Mã HD");
        lblMaSPCT = new JLabel("Sản phẩm");
        lblSoLuongCT = new JLabel("Số lượng");
        lblDonGiaCT = new JLabel("Đơn giá");
        lblThanhTienCT = new JLabel("Thành tiền");

        txtMaHDCT = new JTextField(20);
        txtMaSPCT = new JTextField(20);
        txtSoLuongCT = new JTextField(20);
        txtDonGiaCT = new JTextField(20);
        txtThanhTienCT = new JTextField(20);

        JLabel lblTitleCTHD = new JLabel("Chi tiết hóa đơn");
        JPanel pnTitleCT = new TransparentPanelCommon();
        lblTitleCTHD.setFont(new Font("Tahoma", Font.BOLD, 28));

        btnResetCTHoaDon = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnResetCTHoaDon.setPreferredSize(new Dimension(40, 40));
        pnTitleCT.add(lblTitleCTHD);
        pnTitleCT.add(btnResetCTHoaDon);
        pnTopCTHoaDonRight.add(pnTitleCT);

        JPanel pnMaHDCT = new TransparentPanelCommon();
        lblMAHDCT.setFont(font);
        txtMaHDCT.setFont(font);
        pnMaHDCT.add(lblMAHDCT);
        pnMaHDCT.add(txtMaHDCT);
        pnTopCTHoaDonRight.add(pnMaHDCT);

        JPanel pnMaSPCT = new TransparentPanelCommon();
        lblMaSPCT.setFont(font);
        txtMaSPCT.setFont(font);
        pnMaSPCT.add(lblMaSPCT);
        pnMaSPCT.add(txtMaSPCT);
        pnTopCTHoaDonRight.add(pnMaSPCT);

        JPanel pnSoLuongCT = new TransparentPanelCommon();
        lblSoLuongCT.setFont(font);
        txtSoLuongCT.setFont(font);
        pnSoLuongCT.add(lblSoLuongCT);
        pnSoLuongCT.add(txtSoLuongCT);
        pnTopCTHoaDonRight.add(pnSoLuongCT);

        JPanel pnDonGiaCT = new TransparentPanelCommon();
        lblDonGiaCT.setFont(font);
        txtDonGiaCT.setFont(font);
        pnDonGiaCT.add(lblDonGiaCT);
        pnDonGiaCT.add(txtDonGiaCT);
        pnTopCTHoaDonRight.add(pnDonGiaCT);

        JPanel pnThanhTienCT = new TransparentPanelCommon();
        lblThanhTienCT.setFont(font);
        txtThanhTienCT.setFont(font);
        pnThanhTienCT.add(lblThanhTienCT);
        pnThanhTienCT.add(txtThanhTienCT);
        pnTopCTHoaDonRight.add(pnThanhTienCT);

       Dimension lblCTHDSize= lblThanhTienCT.getPreferredSize();
       lblMAHDCT.setPreferredSize(lblCTHDSize);
       lblMaSPCT.setPreferredSize(lblCTHDSize);
       lblSoLuongCT.setPreferredSize(lblCTHDSize);
       lblDonGiaCT.setPreferredSize(lblCTHDSize);
       lblThanhTienCT.setPreferredSize(lblCTHDSize);
        
        txtMaHDCT.setEditable(false);
        txtMaSPCT.setEditable(false);
        txtSoLuongCT.setEditable(false);
        txtDonGiaCT.setEditable(false);
        txtThanhTienCT.setEditable(false);
       
        
        pnCTHoaDonRight.add(pnTopCTHoaDonRight , BorderLayout.NORTH);
        
        
        dtmCTHoaDon = new DefaultTableModel();
        dtmCTHoaDon.addColumn("Mã HD");
        dtmCTHoaDon.addColumn("Mã SP");
        dtmCTHoaDon.addColumn("Số Lượng");
        dtmCTHoaDon.addColumn("Đơn giá");
        dtmCTHoaDon.addColumn("Thành tiền");
        tblCTHoaDon = new MyTableCommon(dtmCTHoaDon);
        JScrollPane scrCTHoaDon = new JScrollPane(tblCTHoaDon);
        pnCTHoaDonRight.add(scrCTHoaDon , BorderLayout.CENTER);
         loadDataTblCTHoaDon();
        
         
         pnCTHoaDon.add(pnCTHoaDonRight , BorderLayout.CENTER);
         pnCardTabBanHang.add(pnCTHoaDon , "2");
         //------------------------------------------------------------
         this.add(pnCardTabBanHang);
         loadDataTableSanPhamBan();
         txtTenSPBanHang.requestFocus();
         lblAnhSP.setIcon(getAnhSP(""));
         cmbNhanVienBan.setEnabled(false);
    }

    private void addEventsBanHang() {
            lblTabbedBanHang.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lblTabbedHoaDon.setIcon(tabbedDefault);
                    lblTabbedBanHang.setIcon(tabbedSelected);
                    cardBanHangGroup.show(pnCardTabBanHang, "1");
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
              lblTabbedHoaDon.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lblTabbedHoaDon.setIcon(tabbedSelected);
                    lblTabbedBanHang.setIcon(tabbedDefault);
                    cardBanHangGroup.show(pnCardTabBanHang, "2");
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
    }

    private void loadDataComboboxLoaiBanSP() {

    }

    private void loadDataComboboxNhanVienBan() {

    }

    private void loadDataListHoaDon() {

    }

    private void loadDataTblCTHoaDon() {
        
    }

    private void loadDataTableSanPhamBan() {

    }
    
    File fileAnhSP;
    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/SanPham/" + src);

        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);

        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }
        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

}
