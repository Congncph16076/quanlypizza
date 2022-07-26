package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mycompany.quanlypizza.common.MyTableCommon;
import com.mycompany.quanlypizza.common.TransparentPanelCommon;
import com.mycompany.quanlypizza.common.xuLyExcellCommon;
import com.mycompany.quanlypizza.enity.LoaiSP;
import com.mycompany.quanlypizza.enity.SanPham;
import com.mycompany.quanlypizza.main.Main;
import com.mycompany.quanlypizza.service.LoaiServiceImp;
import com.mycompany.quanlypizza.service.SanPhamServiceImp;
import com.toedter.calendar.demo.DemoTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLySanPhamView extends JPanel {

    public PnQuanLySanPhamView() {
        Main.changLNF("Windows");
        addControls();
        addEvents();

    }
    SanPhamServiceImp sanPhamServiceImp;
    LoaiServiceImp loaiServiceImp = new LoaiServiceImp();

    final Color colorPanel = new Color(247, 247, 247);
    MyTableCommon tblSanPham;
    DefaultTableModel dtmSanPham;
    JTextField txtMa, txtTen, txtsoLuong, txtdonViTinh, txtdonGia, txtTimKiem;
    JComboBox<String> cmbLoai;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset,
            btnXuatExcel, btnNhapExcel;
    JLabel lblAnhSP;
    File fileAnhSP;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 844;
        //TITLE

        JPanel pnTitle = new TransparentPanelCommon();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        JPanel pnThongTin = new TransparentPanelCommon();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));

        //PANEL INPUT
        JPanel pnTextField = new TransparentPanelCommon();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        JLabel lblMa, lblTen, lblLoai, lblSoLuong, lblDonViTinh, lblDonGia;

        lblMa = new JLabel("Mã SP");
        lblTen = new JLabel("Tên SP");
        lblLoai = new JLabel("Loại");
        lblSoLuong = new JLabel("Số Lượng");
        lblDonViTinh = new JLabel("Đơn vị tính");
        lblDonGia = new JLabel("Đơn giá");

        txtMa = new JTextField(15);
        txtMa.setEditable(false);
        txtTen = new JTextField(15);
        cmbLoai = new JComboBox<String>();
        txtsoLuong = new JTextField(15);
        txtdonViTinh = new JTextField(15);
        txtdonGia = new JTextField(15);

        JPanel pnMa = new TransparentPanelCommon();
        lblMa.setFont(font);
        txtMa.setFont(font);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanelCommon();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnLoai = new TransparentPanelCommon();
        lblLoai.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtMa.getPreferredSize());
        pnLoai.add(lblLoai);
        pnLoai.add(cmbLoai);
        pnTextField.add(pnLoai);

        JPanel pnSoLuong = new TransparentPanelCommon();
        lblSoLuong.setFont(font);
        txtsoLuong.setFont(font);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtsoLuong);
        pnTextField.add(pnSoLuong);

        JPanel pnDonViTinh = new TransparentPanelCommon();
        lblDonViTinh.setFont(font);
        txtdonViTinh.setFont(font);
        pnDonViTinh.add(lblDonViTinh);
        pnDonViTinh.add(txtdonViTinh);
        pnTextField.add(pnDonViTinh);

        JPanel pnDonGia = new TransparentPanelCommon();
        lblDonGia.setFont(font);
        txtdonGia.setFont(font);
        pnDonGia.add(lblDonGia);
        pnDonGia.add(txtdonGia);
        pnTextField.add(pnDonGia);

        Dimension lblSize = lblDonViTinh.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblLoai.setPreferredSize(lblSize);
        lblSoLuong.setPreferredSize(lblSize);
        lblDonViTinh.setPreferredSize(lblSize);
        lblDonGia.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);

        //PANEL ẢNH
        JPanel pnAnh = new TransparentPanelCommon();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnChuaAnh = new TransparentPanelCommon();
        pnChuaAnh.setPreferredSize(new Dimension(pnAnh.getPreferredSize()));
        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setIcon(getAnhSP(""));
        pnChuaAnh.add(lblAnhSP);
        pnAnh.add(pnChuaAnh);

        JPanel pnButtonAnh = new TransparentPanelCommon();
        pnButtonAnh.setPreferredSize(new Dimension((int) pnChuaAnh.getPreferredSize().getHeight(), 40));
        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(font);
        pnButtonAnh.add(btnChonAnh);
        pnChuaAnh.add(pnButtonAnh);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        JPanel pnButton = new TransparentPanelCommon();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        JPanel pnTimKiem = new TransparentPanelCommon();
        JLabel lblTimKiem = new JLabel("Từ khóa tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        this.add(pnTimKiem);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.add(btnTim);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTim.getPreferredSize();
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
        btnTim.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        this.add(pnButton);
        //PANEL BẢNG
        JPanel pnTable = new TransparentPanelCommon(new BorderLayout());
        //Bảng hàng hóa
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên SP");
        dtmSanPham.addColumn("Loại SP");
        dtmSanPham.addColumn("Đơn giá");
        dtmSanPham.addColumn("Số lượng");
        dtmSanPham.addColumn("Đơn vị tính");
        dtmSanPham.addColumn("Ảnh");
        tblSanPham = new MyTableCommon(dtmSanPham);

        tblSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel colunmModelBanHang = tblSanPham.getColumnModel();
        colunmModelBanHang.getColumn(0).setPreferredWidth(77);
        colunmModelBanHang.getColumn(1).setPreferredWidth(282);
        colunmModelBanHang.getColumn(2).setPreferredWidth(120);
        colunmModelBanHang.getColumn(3).setPreferredWidth(85);
        colunmModelBanHang.getColumn(4).setPreferredWidth(138);
        colunmModelBanHang.getColumn(5).setPreferredWidth(140);
        colunmModelBanHang.getColumn(6).setPreferredWidth(0);

        JScrollPane srcTblSanPham = new JScrollPane(tblSanPham);
        pnTable.add(srcTblSanPham, BorderLayout.CENTER);
        this.add(pnTable);
        loadDataCmbLoai();
        loadDataLenBangSanPham();
    }

    private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                txtdonViTinh.setText("");
                txtsoLuong.setText("");
                cmbLoai.setSelectedIndex(0);
            }
        });
        tblSanPham.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
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

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xulyThemLoai();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }

        });
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }

        });
        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSanPham();
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
    }

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

    private void loadDataCmbLoai() {
        loaiServiceImp = new LoaiServiceImp();
        cmbLoai.removeAllItems();
        List<LoaiSP> listLoai = loaiServiceImp.getDanhSach();
        cmbLoai.addItem("0-Chọn loại");
        for (LoaiSP loaiSP : listLoai) {
            cmbLoai.addItem(loaiSP.getMaLoai() + "-" + loaiSP.getTenLoai());

        }
        cmbLoai.addItem("khác");
    }

    private void loadDataLenBangSanPham() {
        sanPhamServiceImp = new SanPhamServiceImp();

        dtmSanPham.setRowCount(0);
        List<SanPham> listSP = sanPhamServiceImp.getListSP();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : listSP) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getLoaiSP().getTenLoai());
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());

            dtmSanPham.addRow(vec);
        }
    }

    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            String donGia = tblSanPham.getValueAt(row, 3) + "";
            String soLuong = tblSanPham.getValueAt(row, 4) + "";
            String donViTinh = tblSanPham.getValueAt(row, 5) + "";
            String anh = tblSanPham.getValueAt(row, 6) + "";

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtdonGia.setText(donGia);
            txtsoLuong.setText(soLuong);
            txtdonViTinh.setText(donViTinh.replace(",", ""));

            int flag = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flag = i;
                    break;
                }
            }
            cmbLoai.setSelectedIndex(flag);
            loadAnh("image/SanPham/" + anh);

        }
    }

    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }

    private void xulyThemLoai() {
        int x = cmbLoai.getSelectedIndex();
        if (x == cmbLoai.getItemCount() - 1) {
            DlgQuanLyLoai loaiGUI = new DlgQuanLyLoai();
            loaiGUI.setVisible(true);
            loadDataCmbLoai();
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new JFileChooser("image/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }

    private void xuLyThemSanPham() {
        String anh = fileAnhSP.getName();
        System.out.println(fileAnhSP.getName());
        boolean flag = sanPhamServiceImp.themSP(txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText());
        if (flag) {
            loadDataLenBangSanPham();
            luuFileAnh();
        }
    }

    private void luuFileAnh() {
        BufferedImage blmage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            blmage = ImageIO.read(initialImage);
            ImageIO.write(blmage, "png", new File("image/SanPham/" + fileAnhSP.getName()));

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyTimKiem() {
        String ten = txtTimKiem.getText().toLowerCase();
//         sanPhamServiceImp = new SanPhamServiceImp();
        dtmSanPham.setRowCount(0);
        List<SanPham> listSP = sanPhamServiceImp.getListSPByName(ten);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (SanPham sp : listSP) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getLoaiSP().getTenLoai());
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());

            dtmSanPham.addRow(vec);
        }

        MyDialogCommon dlg = new MyDialogCommon("kq: " + listSP.size(), MyDialogCommon.INFO_DIALOG);

    }

    private void xuLySuaSanPham() {
        String anh = fileAnhSP.getName();
        System.out.println(fileAnhSP.getName());
        boolean flag = sanPhamServiceImp.suaSP(txtMa.getText(), txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText());
        if (flag) {
            loadDataLenBangSanPham();
            luuFileAnh();
        }
    }

    private void xuLyNhapExcel() {
        xuLyExcellCommon nhapFile = new xuLyExcellCommon();
        nhapFile.nhapExcel(tblSanPham);

        int row = tblSanPham.getRowCount();
        for (int i = 0; i < row; i++) {
            String ten = tblSanPham.getValueAt(i, 1) + "";
            String loai = tblSanPham.getValueAt(i, 2) + "";
            String donGia = tblSanPham.getValueAt(i, 3) + "";
            String soLuong = tblSanPham.getValueAt(i, 4) + "";
            String donViTinh = tblSanPham.getValueAt(i, 5) + "";
            String anh = tblSanPham.getValueAt(i, 6) + "";

            sanPhamServiceImp.nhapSPTuExcel(ten, loai, soLuong, donViTinh, anh, donGia);
            loadDataLenBangSanPham();
        }
    }

    private void xuLyXuatExcel() {
        xuLyExcellCommon xuatFile = new xuLyExcellCommon();
        xuatFile.XuatFileExcel(tblSanPham);
    }
}
