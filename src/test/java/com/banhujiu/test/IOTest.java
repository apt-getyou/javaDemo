package com.banhujiu.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/10/9 0009 17:34
 */
public class IOTest {
	@Test
	public void test_cell_style() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");

		// Create a row and put some cells in it. Rows are 0 based.
		Row row = sheet.createRow(0);
		row.setHeight((short) 1000);
		// Create a cell and put a value in it.
		Cell cell = row.createCell(0);
		cell.setCellValue(4);
		// Style the cell with borders all around.
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cell.setCellStyle(cellStyle);

		File excelDir = new File("D://excel/");
		if (!excelDir.exists()) {
			excelDir.mkdirs();
		}
		File excel = new File(excelDir, System.currentTimeMillis() + ".xlsx");
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(excel);
		wb.write(fileOut);
		fileOut.close();
	}
}
