package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.enity.CTHoaDon;
import com.mycompany.quanlypizza.enity.HoaDon;
import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.NhanVien;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.main.Main;
import com.mycompany.quanlypizza.repository.DangNhapRespository;

import com.mycompany.quanlypizza.repository.HoaDonRepo;
import com.mycompany.quanlypizza.repository.SanPhamRespository;
import com.mycompany.quanlypizza.service.CTHoaDonServiceImp;
import com.mycompany.quanlypizza.service.DangNhapServiceIMP;
import com.mycompany.quanlypizza.service.DangNhapservice;
import com.mycompany.quanlypizza.service.HoaDonServiceImp;
import com.mycompany.quanlypizza.service.LoaiServiceImp;
import com.mycompany.quanlypizza.service.NhanVienServiceImp;
import com.mycompany.quanlypizza.service.SanPhamServiceImp;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
    private SanPhamServiceImp sanPhamServiceImp = new SanPhamServiceImp();
    private HoaDonServiceImp hoaDonServiceImp = new HoaDonServiceImp();
    private NhanVienServiceImp nhanVienServiceImp = new NhanVienServiceImp();
    private LoaiServiceImp loaiServiceImp = new LoaiServiceImp();
    private DangNhapRespository dangNhapRepo = new DangNhapRespository();
    private SanPhamRespository sanPhamRespository = new SanPhamRespository();
    HoaDonRepo hoaDonRepo = new HoaDonRepo();

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
        cmbNhanVienBan.setPreferredSize(new Dimension(240,25));

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
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetData();
            }
        });
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
              tblBanHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblBanHang();
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
              tblGioHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblGioHang();
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
              btnThemVaoGio.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyThemVaoGioHang();
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
              btnXoaSPGioHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyXoaSPGioHang();
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
              btnXuatHoaDonSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyXuatHoaDonBanHang();
                btnReset.doClick();
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
              cmbLoaiSPBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTableSanPhamBan();
            }
        });

        txtTenSPBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoTen();
            }
        });

        listHoaDon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyHienCTHoaDon();
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

        tblCTHoaDon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblCTHoaDon();
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

        btnResetCTHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblCTHoaDon();
            }
        });

        btnResetHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataListHoaDon();
                loadDataTblCTHoaDon();
            }
        });

        txtMinSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxSearch.requestFocus();
            }
        });

        txtMaxSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimTheoKhoangGia();
            }
        });

        txtMinNgayLap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxNgayLap.requestFocus();
            }
        });

        txtMaxNgayLap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimTheoKhoangNgay();
            }
        });
        
    }

    private void loadDataComboboxLoaiBanSP() {
        
        cmbLoaiSPBanHang.removeAllItems();
        cmbLoaiSPBanHang.addItem("0 - Chọn loại");
        List<LoaiSP> dsl = loaiServiceImp.getDanhSach();

        for (LoaiSP loai : dsl) {
            if (!loai.getTenLoai().equalsIgnoreCase("Nguyên liệu")) {
                cmbLoaiSPBanHang.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
            }
        }
    }

    private void loadDataComboboxNhanVienBan() {
        
        cmbNhanVienBan.removeAllItems();
        List<NhanVien> dsnv = nhanVienServiceImp.getDSNV();
        if (dsnv != null) {
            for (NhanVien nv : dsnv) {
                cmbNhanVienBan.addItem(nv.getMaNV() + " - " + nv.getHo() + " " + nv.getTen());
            }
        }

        for (int i = 0; i < cmbNhanVienBan.getItemCount(); i++) {
            String[] cmbValue = cmbNhanVienBan.getItemAt(i).split(" - ");
            if (cmbValue[0].equals(DangNhapServiceIMP.taiKhoanLogin.getNhanVien().getMaNV()+ "")) {
                cmbNhanVienBan.setSelectedIndex(i);
                break;
            }
        }
    }

    DecimalFormat dcf = new DecimalFormat("###,###");
    
    private void loadDataListHoaDon() {
        List<HoaDon> dshd = hoaDonServiceImp.getListHoaDon();
        addDataListHoaDon(dshd);
    }


    private void loadDataTableSanPhamBan() {
        dtmSanPhamBan.setRowCount(0);
        List<SanPham> dssp = null;

        if (cmbLoaiSPBanHang.getItemCount() > 0) {
            String loai = cmbLoaiSPBanHang.getSelectedItem() + "";
            String loaiArr[] = loai.split("-");
            String loaiSP = loaiArr[0].trim();

            if (loaiSP.equals("0")) {
                dssp = sanPhamServiceImp.getListSP();
            } else {
                dssp = sanPhamServiceImp.getListSPByCategory(loaiSP);
            }
        } else {
            dssp = sanPhamServiceImp.getListSP();
        }

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPhamBan.addRow(vec);
        }
    }
    
    File fileAnhSP;
    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File("image/SanPham/" + src);

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
    
    private void xuLyClickTblBanHang() {
        int row = tblBanHang.getSelectedRow();
        if (row > -1) {
            String ma = tblBanHang.getValueAt(row, 0) + "";
            String ten = tblBanHang.getValueAt(row, 1) + "";
            String donGia = tblBanHang.getValueAt(row, 2) + "";
            String anh = tblBanHang.getValueAt(row, 5) + "";
            int soLuong = Integer.parseInt(tblBanHang.getValueAt(row, 3) + "");
            if (soLuong < 1) {
                MyDialogCommon dlg = new MyDialogCommon("Sản phẩm đã hết hàng", MyDialogCommon.ERROR_DIALOG);
                return;
            }

            SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, soLuong, 1);
            spnSoLuongBanHang.setModel(modeSpinner);
            JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
            ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
            txtSpinner.setEditable(false);
            txtSpinner.setHorizontalAlignment(JTextField.LEFT);

            txtMaSPBanHang.setText(ma);
            txtTenSPBanHang.setText(ten);
            txtDonGiaBanHang.setText(donGia);
            loadAnh(anh);
        }
    }
    
    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }
    
    private void xuLyThemVaoGioHang() {
        int row = tblBanHang.getSelectedRow();
        if (row < 0) {
            return;
        }

        String ma = txtMaSPBanHang.getText();
        String ten = txtTenSPBanHang.getText();
        String donGia = txtDonGiaBanHang.getText();
        int soLuong = Integer.parseInt(spnSoLuongBanHang.getValue() + "");
        int soLuongConLai = Integer.parseInt(tblBanHang.getValueAt(tblBanHang.getSelectedRow(), 3) + "");

        if (soLuong > soLuongConLai || soLuongConLai <= 0) {
            new MyDialogCommon("Sản phẩm đã hết hàng", MyDialogCommon.ERROR_DIALOG);
            return;
        }

        txtMaSPBanHang.setText("");
        txtTenSPBanHang.setText("");
        txtDonGiaBanHang.setText("");
        spnSoLuongBanHang.setValue(0);

        if (ma.trim().equalsIgnoreCase(""))
            return;
        int key = Integer.parseInt(ma);
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int maTbl = Integer.parseInt(tblGioHang.getValueAt(i, 0) + "");
            if (maTbl == key) {
                int soLuongAdd = Integer.parseInt(tblGioHang.getValueAt(i, 2) + "");
                soLuongAdd += soLuong;
                donGia = donGia.replace(",", "");
                int donGiaSP = Integer.parseInt(donGia);

                tblGioHang.setValueAt(soLuongAdd, i, 2);
                tblGioHang.setValueAt(dcf.format(soLuong * donGiaSP), i, 4);

                // cập nhật lại số lượng trong db
                sanPhamRespository.suaSoLuongSP(key, -soLuong);
                sanPhamServiceImp.getListSP();
                loadDataTableSanPhamBan();
                return;
            }
        }

        Vector vec = new Vector();
        vec.add(ma);
        vec.add(ten);
        vec.add(soLuong);
        vec.add(donGia);
        donGia = donGia.replace(",", "");
        int donGiaSP = Integer.parseInt(donGia);
        vec.add(dcf.format(soLuong * donGiaSP));
        // cập nhật lại số lượng trong db
        sanPhamRespository.suaSoLuongSP(key, -soLuong);
        sanPhamServiceImp.getListSP();
        loadDataTableSanPhamBan();
        dtmGioHang.addRow(vec);
    }
    
    private void xuLyXoaSPGioHang() {
        int row = tblGioHang.getSelectedRow();
        if (row > -1) {
            int ma = Integer.parseInt(tblGioHang.getValueAt(row, 0) + "");
            int soLuong = Integer.parseInt(tblGioHang.getValueAt(row, 2) + "");
            sanPhamRespository.suaSoLuongSP(ma, soLuong);
            loadDataTableSanPhamBan();
            dtmGioHang.removeRow(row);
        }
    }
    
    private void xuLyXoaSPGioHang(int row) {
        if (row > -1) {
            int ma = Integer.parseInt(tblGioHang.getValueAt(row, 0) + "");
            int soLuong = Integer.parseInt(tblGioHang.getValueAt(row, 2) + "");
            sanPhamRespository.suaSoLuongSP(ma, soLuong);
            loadDataTableSanPhamBan();
        }
    }

    private void xuLyXuatHoaDonBanHang() {
        ArrayList<Vector> dsGioHang = new ArrayList<>();
        int row = tblGioHang.getRowCount();
        if (row == 0) return;
        int tongTien = 0;
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tblGioHang.getValueAt(i, 0));
            vec.add(tblGioHang.getValueAt(i, 1));
            vec.add(tblGioHang.getValueAt(i, 2));
            vec.add(tblGioHang.getValueAt(i, 3));
            vec.add(tblGioHang.getValueAt(i, 4));
            tongTien += Integer.parseInt((tblGioHang.getValueAt(i, 4) + "").replace(",", ""));
            dsGioHang.add(vec);
        }

        XuatHoaDonGUI hoaDonUI = new XuatHoaDonGUI(dsGioHang, tongTien, cmbNhanVienBan.getSelectedItem());
        hoaDonUI.setVisible(true);
        if (hoaDonUI.checkBanHang) {
            dtmGioHang.setRowCount(0);
        }
    }
    
    private void xuLyClickTblGioHang() {
        int row = tblGioHang.getSelectedRow();
        if (row < 0) {
            return;
        }
        String ma = tblGioHang.getValueAt(row, 0) + "";
        loadAnh(sanPhamServiceImp.getAnh(Integer.parseInt(ma)));
    }

    private void xuLyTimKiemTheoTen() {
        String ten = txtTenSPBanHang.getText().toLowerCase();
        dtmSanPhamBan.setRowCount(0);
        List<SanPham> dssp = null;
        dssp = sanPhamServiceImp.getListSPByName(ten);

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPhamBan.addRow(vec);
        }
    }
    
    public void xuLyThoat() {
        int row = tblGioHang.getRowCount();
        if (row > 0) {
            for (int i = 0; i < row; i++) {
                xuLyXoaSPGioHang(i);
            }
        }
    }


    private void addDataListHoaDon(List<HoaDon> dshd) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (dshd != null) {
            for (HoaDon hd : dshd) {
                listModel.addElement(hd.getMaHD() + " | " + hd.getNgayLap() + " === " + dcf.format(hd.getTongTien()) + " VND");
            }
            listHoaDon.setModel(listModel);
        }
    }
    
    private void xuLyHienCTHoaDon() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String hoaDon = listHoaDon.getSelectedValue();
        String[] stMaHD = hoaDon.split(" | ");

        HoaDon hd = hoaDonServiceImp.getHoaDon(Integer.parseInt(stMaHD[0]));
        txtMaHD.setText(hd.getMaHD() + "");
        txtMaKH.setText(hd.getKhachHang().getMaKH()+ "");
        txtMaNV.setText(hd.getNhanVien().getMaNV()+ "");

        txtNgayLap.setText(sdf.format(hd.getNgayLap()));
        txtTongTien.setText(dcf.format(hd.getTongTien()));
        txtGhiChu.setText(hd.getGhiChu());

        // Gọi hiển thị data trên tblCTHoaDon
        loadDataTblCTHoaDon(stMaHD[0]);
    }

    private CTHoaDonServiceImp cTHoaDonServiceImp = new CTHoaDonServiceImp();

    private void loadDataTblCTHoaDon() {
        
        List<CTHoaDon> listCTHoaDon = cTHoaDonServiceImp.getList();
        addDataTableCTHoaDon(listCTHoaDon);
    }
    
    private void loadDataTblCTHoaDon(String maHD) {
        List<CTHoaDon> listCTHoaDon = cTHoaDonServiceImp.getListByMaHD(Integer.parseInt(maHD));

        addDataTableCTHoaDon(listCTHoaDon);
    }
    
    private void addDataTableCTHoaDon(List<CTHoaDon> listCTHoaDon) {
        
        dtmCTHoaDon.setRowCount(0);
        for (CTHoaDon ct : listCTHoaDon) {
            Vector<String> vec = new Vector<>();
            vec.add(ct.getHoaDon().getMaHD()+ "");
            vec.add(ct.getSanPham().getMaSP()+ "");
            vec.add(ct.getSoLuong() + "");
            vec.add(dcf.format(ct.getDonGia()));
            vec.add(dcf.format(ct.getThanhTien()));
            dtmCTHoaDon.addRow(vec);
        }
    }
    
    private void xuLyClickTblCTHoaDon() {
        int row = tblCTHoaDon.getSelectedRow();
        if (row > -1) {
            String maHD = tblCTHoaDon.getValueAt(row, 0) + "";
            String maSP = sanPhamServiceImp.getListSPByID("" + tblCTHoaDon.getValueAt(row, 1)).getTenSP();
            String soLuong = tblCTHoaDon.getValueAt(row, 2) + "";
            String donGia = tblCTHoaDon.getValueAt(row, 3) + "";
            String thanhTien = tblCTHoaDon.getValueAt(row, 4) + "";

            txtMaHDCT.setText(maHD);
            txtMaSPCT.setText(maSP);
            txtSoLuongCT.setText(soLuong);
            txtDonGiaCT.setText(donGia);
            txtThanhTienCT.setText(thanhTien);
        }
    }

    private void xuLyResetData() {
        loadDataComboboxLoaiBanSP();
        cmbLoaiSPBanHang.setSelectedIndex(0);
        loadDataComboboxNhanVienBan();
        dtmGioHang.setRowCount(0);
    }
    
    private void xuLyTimTheoKhoangNgay() {
        List<HoaDon> listHoaDon = hoaDonServiceImp.getListHoaDonTheoNgay(txtMinNgayLap.getText(), txtMaxNgayLap.getText());
        addDataListHoaDon(listHoaDon);
    }

    private void xuLyTimTheoKhoangGia() {
        List<HoaDon> listHoaDon = hoaDonServiceImp.getListHoaDonTheoGia(Integer.parseInt(txtMinSearch.getText()), Integer.parseInt(txtMaxSearch.getText()));
        addDataListHoaDon(listHoaDon);
    }
}
