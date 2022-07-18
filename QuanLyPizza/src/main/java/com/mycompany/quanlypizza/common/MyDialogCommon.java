package com.mycompany.quanlypizza.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialogCommon extends JDialog {

    private String content;
    private int type;
    public static final int ERROR_DIALOG = 1;
    public static final int SUCCESS_DIALOG = 2;
    public static final int INFO_DIALOG = 3;
    public static final int WARNING_DIALOG = 4;

    public MyDialogCommon(String content, int type) {
        this.content = content;
        this.type = type;
        addControls();
        addEvent();
        showWindow();

    }

    JPanel pnMain, pnTop, pnBottom, pnButton;
    JLabel lblIcon, lblContent, lblClose;
    JButton btnOK, btnCancel;
    final ImageIcon inconError = new ImageIcon("image/icons8_cancel_70px.png");
    final ImageIcon inconSuccess = new ImageIcon("image/icons8_checkmark_70px.png");
    final ImageIcon inconInfor = new ImageIcon("image/icons8_info_70px.png");
    final ImageIcon inconWarning = new ImageIcon("image/icons8_warning_shield_70px.png");

    private void addControls() {
        Container con = getContentPane();

        pnMain = new JPanel();
        pnTop = new JPanel();
        pnBottom = new JPanel();
        pnButton = new JPanel();
        lblIcon = new JLabel();
        lblContent = new JLabel();
        lblClose = new JLabel();

        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnTop.setLayout(new FlowLayout());
        pnBottom.setLayout(new FlowLayout());
        pnButton.setLayout(new FlowLayout());

        pnMain.setBackground(Color.white);
        pnTop.setBackground(Color.white);
        pnBottom.setBackground(Color.white);
        pnButton.setBackground(Color.white);

        lblContent.setFont(new Font("", Font.PLAIN, 18));
        lblContent.setHorizontalAlignment(JTextField.CENTER);
        lblContent.setForeground(Color.black);
        lblContent.setText("<html>"
                + "<div style='text-align: center; width:300px'>"
                + content
                + "</div></html>");

        btnOK.setPreferredSize(new Dimension(60, 30));
        btnCancel.setPreferredSize(btnOK.getPreferredSize());

        pnTop.add(lblIcon, BorderLayout.CENTER);
        pnBottom.add(lblContent);
        pnButton.add(btnOK);

        JPanel pnHeader = new JPanel();
        pnHeader.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnHeader.setPreferredSize(new Dimension(400, 25));
        lblClose = new JLabel(new ImageIcon("image/icons8_x_24px.png"));
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnHeader.add(lblClose);

        pnMain.add(pnHeader);
        pnMain.add(pnTop);
        pnMain.add(pnBottom);
        pnMain.add(pnButton);

        con.add(pnMain);

        Color backgroundHeader = new Color(0);

        switch (type) {
            case ERROR_DIALOG:
                backgroundHeader = new Color(220, 53, 69);
                lblIcon.setIcon(inconError);
                break;
            case SUCCESS_DIALOG:
                backgroundHeader = new Color(40, 167, 69);
                lblIcon.setIcon(inconSuccess);
                break;
            case INFO_DIALOG:
                backgroundHeader = new Color(0, 123, 255);
                lblIcon.setIcon(inconInfor);
                break;

            case WARNING_DIALOG:
                backgroundHeader = new Color(255, 193, 7);
                lblIcon.setIcon(inconWarning);
                pnButton.add(btnCancel);
                break;
        }

        pnMain.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));

        btnOK.setPreferredSize(new Dimension(80, 30));
        btnCancel.setPreferredSize(btnOK.getPreferredSize());
        pnHeader.setBackground(backgroundHeader);
    }

    private void addEvent() {
        lblClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeDialog();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        btnOK.addMouseListener((MouseListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
                action = OK_OPTION;
            }
        });

        btnCancel.addMouseListener((MouseListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
                action = CANCEL_OPTION;
            }
        });

        pnMain.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

    }

    int xMouse, yMouse;

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private int action;
    public static final int OK_OPTION = 1;
    public static final int CANCEL_OPTION = 2;

    private void showWindow() {
        this.setUndecorated(true);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setModal(true);
        this.setBackground(Color.white);
        this.setVisible(true);
        getRootPane().setDefaultButton(btnOK);
    }

    private void closeDialog() {
        this.setVisible(false);
    }

    public int getAction() {
        return action;
    }

}
