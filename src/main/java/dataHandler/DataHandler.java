package dataHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataHandler {
	
	public Object Read_Excel (String datapath, int row, int col) throws Exception {
		File excel = new File(datapath);
		FileInputStream file = new FileInputStream(excel);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet = wbook.getSheetAt(0);
		Object value = "";
		
		if (sheet.getRow(row).getCell(col).getCellType().equals(CellType.NUMERIC))
		{
			value = sheet.getRow(row).getCell(col).getNumericCellValue();
			System.out.println("Reading as Numeric Cell Value: "+value.toString());
		}else if (sheet.getRow(row).getCell(col).getCellType().equals(CellType.STRING))
		{
			value = sheet.getRow(row).getCell(col).getStringCellValue();
			System.out.println("Reading as String Cell Value: "+value.toString());
		}
		
		wbook.close();
		file.close();
		return value;
		
		
	}
	
	public void Write_Data(String datapath, Object write, int row, int col, CellType type) throws IOException {
		File excel = new File(datapath);
		FileInputStream file = new FileInputStream(excel);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet = wbook.getSheetAt(0);
		
		sheet.getRow(row).createCell(col).setCellType(type);
		
		sheet.getRow(row).getCell(col).setCellValue(write.toString());
		
		FileOutputStream output = new FileOutputStream(excel);
		wbook.write(output);
		wbook.close();
		output.close();
		
	}

}
