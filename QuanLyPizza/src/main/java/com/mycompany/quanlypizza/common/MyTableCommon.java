package com.mycompany.quanlypizza.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.ui.DateCellRenderer;

public class MyTableCommon extends JTable{

    public MyTableCommon() {
        //CUSTOM TABLE
        this.setFocusable(false);
        this.setIntercellSpacing(new Dimension(0,0));
        this.setRowHeight(25);
        this.setSelectionBackground(new Color(50,154,114));
        this.setSelectionForeground(Color.WHITE);
        this.setFont(new Font("Arial",Font.PLAIN, 16));
       
        JTableHeader header = this.getTableHeader();
        header.setBackground(new Color(242,153,74));
        header.setFont(new Font("Arial",Font.PLAIN, 16));
        header.setOpaque(false);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    
    }

    public MyTableCommon(DefaultTableModel dtm) {
      this();
      this.setModel(dtm);
      DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
      cellRenderer.setHorizontalAlignment(JLabel.CENTER);
      this.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
      //sắp xếp bảng
        TableRowSorter<DefaultTableModel> sorter= new TableRowSorter<>(dtm);
        this.setRowSorter(sorter);
    }
    
    
    
    
}
