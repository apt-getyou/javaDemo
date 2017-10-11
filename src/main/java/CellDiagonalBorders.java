import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

/**
 * @author banhujiu
 * @date 2017/10/9 0009 18:18
 */
public class CellDiagonalBorders {
	private static CTBorder getCTBorder(StylesTable _stylesSource, CTXf _cellXf) {
		CTBorder ct;
		if (_cellXf.getApplyBorder()) {
			int idx = (int) _cellXf.getBorderId();
			XSSFCellBorder cf = _stylesSource.getBorderAt(idx);
			ct = (CTBorder) cf.getCTBorder().copy();
		} else {
			ct = CTBorder.Factory.newInstance();
		}
		return ct;
	}

	public static void setBorderDiagonal(short border, StylesTable _stylesSource, CTXf _cellXf, ThemesTable _theme) {
		CTBorder ct = getCTBorder(_stylesSource, _cellXf);
		CTBorderPr pr = ct.isSetDiagonal() ? ct.getDiagonal() : ct.addNewDiagonal();
		if (border == BorderFormatting.BORDER_NONE) {
			ct.unsetDiagonal();
		} else {
			ct.setDiagonalDown(true);
			pr.setStyle(STBorderStyle.Enum.forInt(border + 1));
		}
		int idx = _stylesSource.putBorder(new XSSFCellBorder(ct, _theme));
		_cellXf.setBorderId(idx);
		_cellXf.setApplyBorder(true);
	}

	public static void main(String[] args) {
		try {

			Workbook wb = new XSSFWorkbook();

			Sheet sheet = wb.createSheet("Sheet1");
			Cell cell = sheet.createRow(2).createCell(2);

			CellStyle style = wb.createCellStyle();

			StylesTable _stylesSource = ((XSSFWorkbook) wb).getStylesSource();
			ThemesTable _theme = _stylesSource.getTheme();
			CTXf _cellXf = ((XSSFCellStyle) style).getCoreXf();

			setBorderDiagonal(BorderFormatting.BORDER_THICK, _stylesSource, _cellXf, _theme);

			cell.setCellStyle(style);

			FileOutputStream fileOut = new FileOutputStream("D://excel//CellDiagonalBorders.xlsx");
			wb.write(fileOut);

		} catch (IOException ioex) {

		}
	}
}
