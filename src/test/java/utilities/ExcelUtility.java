package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtility {

    public FileInputStream fis;
    public FileOutputStream fos;
    public XSSFWorkbook workBook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path){
        this.path= path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fis= new FileInputStream(path);
        workBook= new XSSFWorkbook(fis);
        sheet= workBook.getSheet(sheetName);
        int rowCount= sheet.getLastRowNum();
        workBook.close();
        fis.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fis= new FileInputStream(path);
        workBook= new XSSFWorkbook(fis);
        sheet= workBook.getSheet(sheetName);
        row= sheet.getRow(rowNum);
        int cellCount= row.getLastCellNum();
        workBook.close();
        fis.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
        fis= new FileInputStream(path);
        workBook= new XSSFWorkbook(fis);
        sheet= workBook.getSheet(sheetName);
        row= sheet.getRow(rowNum);
        cell= row.getCell(cellNum);

        DataFormatter formatter= new DataFormatter();
        String data;
        try{
            data= formatter.formatCellValue(cell);
        }catch (Exception e){
            data="";
        }
        workBook.close();
        fis.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
        File xlFile= new File(path);
        if(!xlFile.exists()) {
            fos = new FileOutputStream(path);
            workBook = new XSSFWorkbook();
            workBook.write(fos);
            }
            fis= new FileInputStream(path);
            workBook= new XSSFWorkbook(fis);

            if(workBook.getSheetIndex(sheetName)==-1){
                workBook.createSheet(sheetName);
            }
            sheet= workBook.getSheet(sheetName);

            if(sheet.getRow(rowNum)== null){
                sheet.createRow(rowNum);
            }
            row= sheet.getRow(rowNum);
            cell= row.createCell(cellNum);
            cell.setCellValue(data);
            fos= new FileOutputStream(path);
            workBook.write(fos);
            workBook.close();
            fis.close();
            fos.close();
        }

        public void fillGreenColor(String sheetName, int rowNum, int cellNum) throws IOException {
            fis= new FileInputStream(path);
            workBook= new XSSFWorkbook(fis);
            sheet= workBook.getSheet(sheetName);
            row= sheet.getRow(rowNum);
            cell= row.getCell(cellNum);

            style= workBook.createCellStyle();

            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            workBook.write(fos);
            workBook.close();
            fis.close();
            fos.close();
        }

        public void fillRedColor(String sheetName, int rowNum, int cellNum) throws IOException {
            fis= new FileInputStream(path);
            workBook= new XSSFWorkbook(fis);
            sheet= workBook.getSheet(sheetName);
            row= sheet.getRow(rowNum);
            cell= row.getCell(cellNum);

            style= workBook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            workBook.write(fos);
            workBook.close();
            fis.close();
            fos.close();
        }

}
