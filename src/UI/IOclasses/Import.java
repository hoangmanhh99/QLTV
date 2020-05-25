package UI.IOclasses;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Import {
	public static DefaultTableModel Import() {
		DefaultTableModel model = new DefaultTableModel(new Object[]{"BookID", "BookName", "Author", "Category", "Publisher", "PublishYear", "Price", "Available"}, 0);
		File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "E:\\\\DataProjectI";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
                
                for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    
                    XSSFCell BookID = excelRow.getCell(0);
                    XSSFCell BookName = excelRow.getCell(1);
                    XSSFCell Author = excelRow.getCell(2);
                    XSSFCell Category = excelRow.getCell(3);
                    XSSFCell Publisher = excelRow.getCell(4);
                    XSSFCell PublishYear = excelRow.getCell(5);
                    XSSFCell Price = excelRow.getCell(6);
                    XSSFCell Available = excelRow.getCell(7);
                    
                    //JOptionPane.showMessageDialog(null, BookName);
                    model.addRow(new Object[]{BookID, BookName, Author, Category, Publisher, PublishYear, Price, Available});
                }
                //JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        } 
		return model;
	}

}
