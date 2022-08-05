
package com.mycompany.quanlypizza.main;

import com.mycompany.quanlypizza.view.DangNhapView;
import com.mycompany.quanlypizza.view.MainQuanLyView;


public class Main {

    
    public static void main(String[] args) {
            changLNF("Nimbus");
            DangNhapView dn = new DangNhapView();
            dn.showWindow();
//MainQuanLyView view = new MainQuanLyView();
//view.showWindow();
    }
    
    public  static void changLNF(String nameLNF){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
