/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlypizza.view;

import com.mycompany.quanlypizza.main.Main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class MainQuanLyView extends JFrame{

    public MainQuanLyView() {
        this.setTitle("Phần mềm quản lý loong pizza");
        this.setSize(1280,900);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
        addControl();
        addEvent();
    
    }
    
    public  void showWindow(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
    
    
    
    JLabel btnDoiMK,lblDangXuat;
    JPanel  pnTille,pnMenuLeft,pnCard,pnBanHang,pnKhuyenMai,pnNhapHang
            ,pnSanPham,pnNhanVien,pnKhachHang,pnThongKe;
    
    PnQuanLyKhuyenMai khuyenMaiPanel;
    PnQuanLyNhanVienView nhanVienPanel;
    
    JLabel btnClose,btnMinimize,lblBanHang,lblKhuyenMai,lblNhapHang
            ,lblSanPham,lblNhanVien,lblKhachHang,lblThongKe;
    final Color clLeftItem = new Color(63,74,89);
    final Color clLeftItemHover = new Color(72,88,107);
    final Color clLeftItemSelected = new Color(51,202,187);
    ArrayList<JLabel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();

    private void addControl() {
        int width = this.getWidth();
        int height = this.getHeight();
        
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        
        //TITLE BAR
        pnTille= new JPanel(null);
        pnTille.setPreferredSize(new Dimension(width,46));
        pnTille.setBackground(new Color(242,153,74));
        
        btnDoiMK  = new JLabel(new ImageIcon("image/ManagerUI/icons8_gear_46px.png"));
        btnDoiMK.setToolTipText("Đổi mật khẩu");
        btnDoiMK.setBounds(0,0,46,46);
        btnDoiMK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTille.add(btnDoiMK);
        
        JLabel lblTitleText = new JLabel(new ImageIcon("image/ManagerUI/title-text.png"));
        lblTitleText.setBounds(width / 2-428 /2,3,428,38);
        pnTille.add(lblTitleText);
        
        btnMinimize = new JLabel(new ImageIcon("image/ManagerUI/btn-minimize.png"));
        btnMinimize.setBounds(width-85,5,38,35);
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTille.add(btnMinimize);
        
        btnClose = new JLabel(new ImageIcon("image/ManagerUI/btn-close.png"));
        btnClose.setBounds(width-40,5,35,35);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTille.add(btnClose);
        
        pnMain.add(pnTille,BorderLayout.NORTH);
        
        //SIDEBAR MENU
        
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height- pnTille.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));
        
        JLabel lblAvatar = new JLabel(new ImageIcon("image/ManagerUI/avatar.png"),JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250,210));
        pnMenuLeft.add(lblAvatar);
        
        lblBanHang = new JLabel(new ImageIcon("image/ManagerUI/lblBanHang.png"));
        lblKhuyenMai = new JLabel(new ImageIcon("image/ManagerUI/lblKhuyenMai.png"));
        lblNhapHang = new JLabel(new ImageIcon("image/ManagerUI/lblNhapHang.png"));
        lblSanPham = new JLabel(new ImageIcon("image/ManagerUI/lblSanPham.png"));
        lblKhachHang = new JLabel(new ImageIcon("image/ManagerUI/lblKhachHang.png"));
        lblNhanVien = new JLabel(new ImageIcon("image/ManagerUI/lblNhanVien.png"));
        lblThongKe = new JLabel(new ImageIcon("image/ManagerUI/lblThongKe.png"));
        
        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lblBanHang);
        listMenuLeft.add(lblKhuyenMai);
        listMenuLeft.add(lblNhapHang);
        listMenuLeft.add(lblSanPham);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblKhachHang);
        listMenuLeft.add(lblNhapHang);
        listMenuLeft.add(lblThongKe);
        
        for (JLabel lbl : listMenuLeft) {
            lbl.setVisible(false);
            lbl.setPreferredSize(new Dimension(250,65));
            lbl.setOpaque(true);
            lbl.setBackground(clLeftItem);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
        }
        
        lblBanHang.setBackground(clLeftItemSelected);
        lblBanHang.setVisible(true);
        lblKhuyenMai.setVisible(true);
        
        pnMain.add(pnMenuLeft, BorderLayout.WEST);
        
        
        //CARD PANEL
        
        pnCard = new JPanel(cardMenuLeftGroup);
        pnBanHang = new JPanel();
        pnKhuyenMai = new JPanel();
        pnNhapHang = new JPanel();
        pnSanPham = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnThongKe = new JPanel();
        
        pnCard.add(pnBanHang,"1");
        pnCard.add(pnKhuyenMai,"2");
        pnCard.add(pnNhapHang,"3");
        pnCard.add(pnSanPham,"4");
        pnCard.add(pnNhanVien,"5");
        pnCard.add(pnKhachHang,"6");
        pnCard.add(pnThongKe,"7");
        
        //ADD panel bán hàng + khuyến mãi (không phân quyền)
        
        khuyenMaiPanel = new PnQuanLyKhuyenMai();
        pnKhuyenMai.setLayout(new BorderLayout());
        pnKhuyenMai.add(khuyenMaiPanel, BorderLayout.CENTER);
        
        
        nhanVienPanel = new PnQuanLyNhanVienView();
        pnNhanVien.setLayout(new BorderLayout());
        pnNhanVien.add(nhanVienPanel, BorderLayout.CENTER);
        lblNhanVien.setVisible(true);
        
        
        pnMain.add(pnCard);
        con.add(pnMain);
    }
    
     int xMouse, yMouse;

    private void addEvent() {
        
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                    moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        
        btnDoiMK.addMouseListener(new MouseListener() {
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
                btnDoiMK.setOpaque(true);
                btnDoiMK.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDoiMK.setOpaque(true);
                btnDoiMK.setBackground(new Color(0,0,0,0));
            }
        });
        
        btnMinimize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               thuNhoFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/ManagerUI/btn-minimize--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/ManagerUI/btn-minimize.png"));
            }
        });
        
        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
              xuLyThoat();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/ManagerUI/btn-close--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/ManagerUI/btn-close.png"));
            }
        });
        for (JLabel lbl : listMenuLeft) {
            lbl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lblDisable : listMenuLeft) {
                        lblDisable.setBackground(clLeftItem);
                    }
                    lbl.setBackground(clLeftItemSelected);
                    
                    String cardName = "";
                     if (lbl == lblBanHang) {
                        cardName = "1";
                    } else if (lbl == lblKhuyenMai) {
                        cardName = "2";
                    } else if (lbl == lblNhapHang) {
                        cardName = "3";
                    } else if (lbl == lblSanPham) {
                        cardName = "4";
                    } else if (lbl == lblNhanVien) {
                        cardName = "5";
                    } else if (lbl == lblKhachHang) {
                        cardName = "6";
                    } else {
                        cardName = "7";
                    }
                     cardMenuLeftGroup.show(pnCard, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                       if (lbl.getBackground().equals(clLeftItem)) {
                        lbl.setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (lbl.getBackground().equals(clLeftItem)) {
                        lbl.setBackground(clLeftItemHover);
                    }
                }
            });
        }
    }
    
    
    private  void moverFrame(int x, int y){
        this.setLocation(x-xMouse, y-yMouse);
    }
    
    private  void thuNhoFrame(){
        this.setState(Frame.ICONIFIED);
    }
    private void xuLyThoat() {
               Main.changLNF("Nimbus");
               System.exit(0);
          }
}
