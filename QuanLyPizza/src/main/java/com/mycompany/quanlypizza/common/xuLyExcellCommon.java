package com.mycompany.quanlypizza.common;

import com.mycompany.quanlypizza.main.Main;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xuLyExcellCommon {

    public void nhapExcel(JTable tbl) {
        Main.changLNF("Windows");
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooserCommon("export/");
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(fileSelected);
                BufferedInputStream bis = new BufferedInputStream(fis);

                XSSFWorkbook wb = new XSSFWorkbook(bis);
                Sheet sheet = wb.getSheetAt(0);

                DefaultTableModel dtmtbl = (DefaultTableModel) dtm;
                dtmtbl.setRowCount(0);
                for (int i = 0; i < sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Vector vec = new Vector();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (dtmtbl.getColumnCount() != row.getLastCellNum()) {
                            new MyDialogCommon("Thất bại", MyDialogCommon.ERROR_DIALOG);
                            return;
                        }
                        Cell cell = row.getCell(j);
                        vec.add(cell.getStringCellValue());
                    }
                    dtmtbl.addRow(vec);
                }
                new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);

            }
        } catch (Exception e) {
            new MyDialogCommon("Thất bại", MyDialogCommon.ERROR_DIALOG);
            e.printStackTrace();
        }
    }
    
    
    public  void XuatFileExcel(JTable tbl){
         Main.changLNF("Windows");
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooserCommon("export/");
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path+=".xlsx";
                }
                
                XSSFWorkbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Sheet 1");
                
                Font headerFont = wb.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short)14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = wb.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
                
                Row headerRow = sheet.createRow(0);
                
                //tạo Header
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }
                
                Font contentFont = wb.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short)14);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = wb.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);
                
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i+1);
                    for (int j = 0; j <dtm.getColumnCount() ; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j) + "");
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }
                //ghi file
                FileOutputStream fos = new FileOutputStream(path);
                wb.write(fos);
                fos.close();
                wb.close();
                 new MyDialogCommon("Thành công", MyDialogCommon.SUCCESS_DIALOG);
            }   
            
        }catch (Exception e) {
            new MyDialogCommon("Thất bại", MyDialogCommon.ERROR_DIALOG);
            e.printStackTrace();
        }
    }

}
