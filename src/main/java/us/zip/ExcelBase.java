package us.zip;

import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>Description: EXCEL导出工具基础类</p>
 * @date 2016年5月4日
 * @author Jianwen Zhu
 * @version 1.0
 * <p>Company:XiPinTech</p>
 * <p>Copyright:Copyright(c)2015</p>
 */
public class ExcelBase {

	/**
	 * 工作簿
	 */
	protected static HSSFWorkbook workbook;

	/**
	 * 工作表
	 */
	protected static HSSFSheet sheet;

	/**
	 * 画图管理器
	 */
	protected static HSSFPatriarch patriarch;

	/**
	 * Long正则表达式
	 */
	public static final String LONG_MATCHES_STRING = "^\\-?\\d+$";

	/**
	 * Double正则表达式
	 */
	public static final String DOUBLE_MATCHES_STRING = "^\\-?\\d+(\\.\\d+)?$";

	/**
	 * 列宽（默认：20）
	 */
	private static Integer columnWidth = 20;

	/**
	 * 样式集合
	 */
	private static Map<String, CellStyle> cellStyleMap;

	/**
	 * 当前行号
	 */
	protected static int rowIndex = 0;

	/**
	 * 单个工作表最大行数
	 */
	public static final int MAX_ROW = 5000;

	/**
	 * 单个工作表最大工作表数
	 */
	public static final int MAX_SHEET = 5;

	/**
	 * 工作表的数量
	 */
	private static int sheetSize = 0;

	/**
	 * 临时文件集合
	 */
	private static LinkedList<File> fileList = new LinkedList<>();

	/**
	 * 临时文件根目录
	 */
	private static String fileRootPath = "";

	/**
	 * 请求对象
	 */
	private static HttpServletRequest request;

	/**
	 * 
	 * 方法用途: 新增工作表<br>
	 * 如果单个工作簿中工作表数量超过最大值，则将该工作簿写到临时文件中，并新建工作簿<br>
	 * 实现步骤: <br>
	 * @param fileName 文件名
	 * @param headers 表头
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	protected static void addSheet(String fileName, Object[] headers) throws FileNotFoundException,
			IOException {
		if (sheetSize != 0 && sheetSize % MAX_SHEET == 0) {
			writeFile(fileName);
			initWorkbook();
		}

		sheetSize++;
		sheet = workbook.createSheet(fileName + sheetSize);
		sheet.setDefaultColumnWidth(columnWidth);
		patriarch = sheet.createDrawingPatriarch();
		cellStyleMap = createCellStyleMap(workbook);
		setTitle(fileName, headers.length);
		setHeader(headers);
		rowIndex = 2;
	}

	/**
	 * 
	 * 方法用途: 将工作簿写入临时文件<br>
	 * 实现步骤: <br>
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void writeFile(String fileName) throws FileNotFoundException, IOException {
		String filePath = fileRootPath + File.separator + fileName + (fileList.size() + 1) + ".xls";
		File file = new File(filePath);
		file.createNewFile();
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		workbook.write(out);
		out.close();
		fileList.add(file);
	}

	/**
	 * 
	 * 方法用途: 初始化工作簿<br>
	 * 并以当前系统毫秒数新建临时文件夹<br>
	 * 实现步骤: <br>
	 */
	protected static void initWorkbook() {
		workbook = new HSSFWorkbook();
		if (fileRootPath.isEmpty()) {
			fileRootPath = request.getServletContext().getRealPath("/") + "temp" + File.separator
					+ System.currentTimeMillis();
		}
		File file = new File(fileRootPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 
	 * 方法用途: 获取表头样式<br>
	 * 水平居中 细边框，黑色 填充蓝色<br>
	 * 实现步骤: <br>
	 * @return 表头样式
	 */
	protected static HSSFCellStyle getHeaderCellStyle() {
		HSSFCellStyle headerCellStyle = workbook.createCellStyle();

		// 设置表头样式
		HSSFPalette palette = workbook.getCustomPalette();
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 0, (byte) 176, (byte) 240);
		palette.setColorAtIndex(HSSFColor.BLACK.index, (byte) 58, (byte) 56, (byte) 56);
		headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置标题字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerCellStyle.setFont(font);

		// 指定当单元格内容显示不下时自动换行
		headerCellStyle.setWrapText(true);

		return headerCellStyle;
	}

	/**
	 * 
	 * 方法用途: 获取标题样式<br>
	 * 水平居中、垂直居中 字体：15，粗体<br>
	 * 实现步骤: <br>
	 * @return
	 */
	protected static HSSFCellStyle getTitlecCellStyle() {
		HSSFCellStyle titleCellStyle = workbook.createCellStyle();

		// 设置标题样式
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 15);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleCellStyle.setFont(titleFont);

		return titleCellStyle;
	}

	/**
	 * 
	 * 方法用途: 获取合计样式<br>
	 * 字体：10，粗体<br>
	 * 实现步骤: <br>
	 * @return
	 */
	protected static HSSFCellStyle getCountCellStyle() {
		HSSFCellStyle countCellStyle = workbook.createCellStyle();
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 10);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		countCellStyle.setFont(titleFont);
		return countCellStyle;
	}

	/**
	 * 
	 * 方法用途: 设置标题行<br>
	 * 实现步骤: <br>
	 * @param titel 标题
	 * @param headerWidth 表头宽度
	 */
	protected static void setTitle(String titel, int headerWidth) {
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerWidth - 1));
		HSSFRow rowHeader = sheet.createRow(0);
		HSSFCell cellHeader = rowHeader.createCell(0);
		cellHeader.setCellStyle(getTitlecCellStyle());
		cellHeader.setCellValue(titel);
	}

	/**
	 * 
	 * 方法用途: 设置表头<br>
	 * 实现步骤: <br>
	 * @param headers 表头数组
	 */
	protected static void setHeader(Object[] headers) {
		HSSFRow row = sheet.createRow(1);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(getHeaderCellStyle());
			cell.setCellValue(headers[i].toString());
		}
	}

	/**
	 * 
	 * 方法用途: 输出工作簿<br>
	 * 实现步骤: <br>
	 * @param fileName 文件名
	 * @param response 回应对象
	 * @throws Exception 异常
	 */
	protected static void outWorkbook(String fileName, HttpServletResponse response)
			throws Exception {
		OutputStream out = response.getOutputStream();
		response.reset();

		String outFileName = "";
		if (request.getHeader("User-Agent").indexOf("MSIE") != -1) {
			outFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		} else {
			outFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		if (fileList.size() == 0) {
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment; filename=" + outFileName
					+ ".xls");

			workbook.write(out);
		} else {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + outFileName + ".zip");
			// 输出最后一张工作簿
			writeFile(fileName);

			String zipFilePath = fileRootPath + File.separator + fileName + ".zip";
			createZipFile(zipFilePath);

			// 输出ZIP文件
			byte[] buffer = new byte[1024];
			int len;
			FileInputStream zipIn = new FileInputStream(zipFilePath);
			while ((len = zipIn.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			zipIn.close();

			// 删除临时文件
			deleteFile(fileRootPath);
		}

		reset();
	}
	
	/**
	 * 
	 * 方法用途: 将临时文件打包成ZIP压缩包<br>
	 * 实现步骤: <br>
	 * @param zipFilePath 压缩包路径
	 * @throws IOException 
	 */
	private static void createZipFile(String zipFilePath) throws IOException{
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
		byte[] buffer = new byte[1024];
		int len;
		for (File file : fileList) {
			FileInputStream fileIn = new FileInputStream(file);
			zipOut.putNextEntry(new ZipEntry(file.getName()));
			while ((len = fileIn.read(buffer)) > 0) {
				zipOut.write(buffer, 0, len);
			}
			zipOut.closeEntry();
			fileIn.close();
		}
		zipOut.close();
	}
	
	/**
	 * 
	 * 方法用途: 重置参数<br>
	 * 实现步骤: <br>
	 */
	private static void reset(){
		fileList.clear();
		fileRootPath = "";
		sheetSize = 0;
	}
	
	/**
	 * 
	 * 方法用途: 删除文件或文件夹<br>
	 * 实现步骤: <br>
	 * @param filePath 文件或文件夹路径
	 * @throws IOException
	 */
	public static void deleteFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(!file.exists()){
			return;
		}
		if (file.isDirectory()) {
			if (file.listFiles().length == 0) {
				file.delete();
			} else {
				File delFile[] = file.listFiles();
				for (File fileItem : delFile) {
					if (fileItem.isDirectory()) {
						deleteFile(fileItem.getAbsolutePath());
					}
					fileItem.delete();
				}
			}
		}
		file.delete();
	}

	/**
	 * 
	 * 方法用途: 将值对象格式化为字符串<br>
	 * 实现步骤: <br>
	 * @param value 值对象
	 * @param dateFormat 时间格式
	 * @param rowIndex 行标
	 * @param columnIndex 列标
	 * @return 值对象字符串
	 */
	protected static String getValueString(Object value, String dateFormat, int rowIndex,
			int columnIndex) {
		String valueString = "";
		if (value == null) {
			return valueString;
		} else if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			valueString = sdf.format((Date) value);
		} else if (value instanceof Timestamp) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			valueString = sdf.format((Timestamp) value);
		} else if (value instanceof byte[]) {
			// 有图片时，设置行高为60px;
			HSSFRow row = sheet.getRow(rowIndex);
			row.setHeightInPoints(60);
			// 设置图片所在列宽度为80px,注意这里单位的一个换算
			sheet.setColumnWidth(columnIndex, (short) (35.7 * 80));
			byte[] valueByte = (byte[]) value;
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, rowIndex,
					(short) 6, rowIndex);
			anchor.setAnchorType(2);
			patriarch.createPicture(anchor,
					workbook.addPicture(valueByte, HSSFWorkbook.PICTURE_TYPE_JPEG));
		} else if(value instanceof String[]) {
			//字符串数组
			for (String string : (String[]) value){
				valueString = valueString +  string + "," ;
			}
		}else {
			valueString = value.toString();
		}
		return valueString;
	}

	/**
	 * 
	 * 方法用途: 获取值对象<br>
	 * 实现步骤: <br>
	 * @param resultItem 行数据
	 * @param fieldName 值名称
	 * @return 值对象
	 * @throws Exception 异常
	 */
	protected static <T> Object getValueObject(T resultItem, String fieldName) throws Exception {
		Object value = new Object();
		if (resultItem instanceof Map) {
			value = ((Map<?, ?>) resultItem).get(fieldName);
		} else {
			Class<? extends Object> resultItemClass = resultItem.getClass();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			Method getMethod = resultItemClass.getMethod(getMethodName, new Class[] {});
			value = getMethod.invoke(resultItem, new Object[] {});
		}
		return value;
	}

	/**
	 * 
	 * 方法用途: 设置单元格值<br>
	 * 实现步骤: <br>
	 * @param valueString 单元格值
	 * @param cell 单元格
	 * @param cellStyleMap 单元格样式集合
	 */
	protected static void setCellValue(String valueString, Cell cell) {
		if (StringUtils.isEmpty(valueString)) {
			return;
		}
		if (valueString.matches(LONG_MATCHES_STRING)) {
			cell.setCellStyle(getCellStyle("longCellStyle"));
			cell.setCellValue(Long.valueOf(valueString));
		} else if (valueString.matches(DOUBLE_MATCHES_STRING)) {
			cell.setCellValue(Double.valueOf(valueString));
		} else {
			cell.setCellValue(valueString);
		}
	}

	/**
	 * 
	 * 方法用途: 创建单元格格式集合<br>
	 * 实现步骤: <br>
	 * @param workbook
	 * @return
	 */
	private static Map<String, CellStyle> createCellStyleMap(Workbook workbook) {
		Map<String, CellStyle> cellStyleMap = Maps.newHashMap();

		CellStyle longCellStyle = workbook.createCellStyle();
		longCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		cellStyleMap.put("longCellStyle", longCellStyle);

		CellStyle doubleCellStyle = workbook.createCellStyle();
		doubleCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		cellStyleMap.put("doubleCellStyle", doubleCellStyle);

		return cellStyleMap;
	}

	/**
	 * 
	 * 方法用途: 获取单元格格式<br>
	 * 如果没有抛出空指针异常<br>
	 * 实现步骤: <br>
	 * @param styleName 格式名称
	 * @param cellStyleMap 单元格格式集合
	 * @return 单元格样式
	 */
	protected static CellStyle getCellStyle(String styleName) {
		if (cellStyleMap.containsKey(styleName)) {
			return cellStyleMap.get(styleName);
		} else {
			throw new NullPointerException(styleName + "is null");
		}
	}

	/**
	 * 
	 * 方法用途: 判断对象属性或集合中是否存在该元素<br>
	 * 实现步骤: <br>
	 * @param t 对象或者集合
	 * @param fieldName 元素
	 * @return 判断结果
	 */
	protected static <T> boolean hasField(T t, Object fieldName) {
		if (t instanceof Map) {
			if (((Map<?, ?>) t).containsKey(fieldName)) {
				return true;
			}
		} else {
			Field[] fields = t.getClass().getFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * 方法用途: 创建数据行<br>
	 * 当数据行超过行最大值时，新建一个工作表
	 * 实现步骤: <br>
	 * @return 新数据行
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	protected static HSSFRow createRow(String fileName, Object[] headers)
			throws FileNotFoundException, IOException {
		if (rowIndex >= MAX_ROW) {
			addSheet(fileName, headers);
		}
		return sheet.createRow(rowIndex);
	}

	/**
	 * {@linkplain #columnWidth}
	 */
	public static Integer getColumnWidth() {
		return columnWidth;
	}

	/**
	 * {@linkplain #columnWidth}
	 */
	public static void setColumnWidth(Integer columnWidth) {
		ExcelBase.columnWidth = columnWidth;
	}

	/**
	 * {@linkplain #request}
	 */
	public static void setRequest(HttpServletRequest request) {
		ExcelBase.request = request;
	}
}
