package com.mycompany.quanlypizza.util;

import com.mycompany.quanlypizza.common.MyDialogCommon;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConect {

    public static Connection conn = null;
    private String severName = "localhost:3306";
    private String dbName = "pizza";
    private String userName = "root";
    private String password = "1234";

//    public MyConect() {
//
//        String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
//        Properties pro = new Properties();
//        pro.put("user", userName);
//        pro.put("password", password);
//        try {
//            com.mysql.jdbc.Driver driver = new Driver();
//            conn = driver.connect(strConnect, pro);
//        } catch (SQLException ex) {
//            new MyDialogCommon("Không kết nối được tới CSDL!", MyDialogCommon.ERROR_DIALOG);
//            System.exit(0);
//        }
//
//    }
    
    public MyConect() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/pizza";
            String user ="root";
            String password = "1234";
            conn= DriverManager.getConnection(url,user,password);
             System.out.println("Kết nối thành công");
        
        } catch (Exception e) {
            new MyDialogCommon("Không kết nối được tới CSDL!", MyDialogCommon.ERROR_DIALOG);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        MyConect.conn = conn;
    }
    
    
}
