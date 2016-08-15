package us.zip;

import com.google.common.collect.Maps;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>Description: EXCEL导出工具</p>
 * @date 2016年4月22日
 * @author Jianwen Zhu
 * @version 1.0
 * <p>Company:XiPinTech</p>
 * <p>Copyright:Copyright(c)2015</p>
 */
public class ExcelUtil extends ExcelBase {

	/**
	 * 一维集合导出，普通
	 * 
	 * @param fileName 文件名称
	 * @param headers 表头数组
	 * @param columns 列名数组
	 * @param result 结果集
	 * @param dateFormat 时间格式
	 * @param response 回应对象
	 * @param request 请求对象
	 * @param totalColumns 需要计算合计的列标
	 * @throws Exception 抛出异常
	 */
	public static <T> void exportExcel(String fileName, String[] headers, String[] columns,
			Collection<T> result, String dateFormat, HttpServletResponse response,
			HttpServletRequest request, List<Integer> totalColumns) throws Exception {
		if (result == null || result.isEmpty()) {
			throw new NullPointerException("result is NULL");
		}

		setRequest(request);
		initWorkbook();
		addSheet(fileName, headers);

		// 合计列计算值
		Map<Integer, Double> totalColumnValues = Maps.newHashMap();
		HSSFRow row = null;
		// 遍历集合数据，产生数据行
		for (T resultItem : result) {
			row = createRow(fileName, headers);
			for (int columnIndex = 0; columnIndex < columns.length; columnIndex++) {
				HSSFCell cell = row.createCell(columnIndex);
				String fieldName = columns[columnIndex];

				Object valueObject = getValueObject(resultItem, fieldName);
				String valueString = getValueString(valueObject, dateFormat, rowIndex, columnIndex);

				if (StringUtils.isEmpty(valueString)) {
					continue;
				}
				setCellValue(valueString, cell);

				// 计算合计列的值
				if (totalColumns == null || !totalColumns.contains(columnIndex)) {
					continue;
				}
				// 验证该列是否有非数字值，有则从合计列中移除，空值不作为非数字值。
				if (!valueString.matches(DOUBLE_MATCHES_STRING)) {
					totalColumns.remove((Object) columnIndex);
					continue;
				}
				Double valueDouble = Double.valueOf(valueString);
				if (totalColumnValues.containsKey(columnIndex)) {
					Double tempDouble = totalColumnValues.get(columnIndex);
					tempDouble = tempDouble + valueDouble;
					totalColumnValues.put(columnIndex, tempDouble);
				} else {
					totalColumnValues.put(columnIndex, valueDouble);
				}
			}
			rowIndex++;
		}

		// 设置合计列的值
		if (CollectionUtils.isNotEmpty(totalColumns)) {
			row = createRow(fileName, headers);

			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(getCountCellStyle());
			cell.setCellValue("合计：");
			for (Integer totalIndex : totalColumns) {
				cell = row.createCell(totalIndex);
				cell.setCellStyle(getCellStyle("doubleCellStyle"));
				cell.setCellValue(totalColumnValues.get(totalIndex) == null ? 0 : totalColumnValues
						.get(totalIndex));
			}
		}

		outWorkbook(fileName, response);

	}

	/**
	 * 二维集合导出，合并
	 * 
	 * @param fileName 文件名
	 * @param collectionName 一维集合中二维集合的列名
	 * @param headers 工作表表头名称
	 * @param firstColumns 一维集合列名，二维集合中的列名使用""替代，二维集合第一列列名使用collectionName参数值替代
	 * @param secondColumns 二维集合列名
	 * @param result 一维集合结果集，包含二维集合结果集
	 * @param dateFormat 时间格式
	 * @param response 请求对象
	 * @param request 回复对象
	 * @throws Exception 抛出异常
	 */
	public static <T, V> void exportExcelForVerticalMerger(String fileName, String collectionName,
			String[] headers, String[] firstColumns, String[] secondColumns, Collection<T> result,
			String dateFormat, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		if (result == null || result.isEmpty()) {
			throw new NullPointerException("result is NULL");
		}

		setRequest(request);
		initWorkbook();
		addSheet(fileName, headers);

		// 遍历一维集合
		HSSFRow row = null;
		for (T firstResultItem : result) {
			row = createRow(fileName, headers);
			// 计算合并域
			int rowBegin = rowIndex;
			@SuppressWarnings("unchecked")
			int rowEnd = rowIndex
					+ ((Collection<V>) getValueObject(firstResultItem, collectionName)).size() - 1;

			for (int columnIndex = 0; columnIndex < firstColumns.length; columnIndex++) {
				String fieldName = firstColumns[columnIndex];
				if (fieldName.equals(collectionName)) {// 是二维集合元素
					@SuppressWarnings("unchecked")
					Collection<V> secondResult = (Collection<V>) getValueObject(firstResultItem,
							collectionName);
					Iterator<V> it = secondResult.iterator();
					// 遍历二维集合
					while (it.hasNext()) {
						V secondResultItem = it.next();
						for (int j = 0; j < secondColumns.length; j++) {
							HSSFCell secondCell = row.createCell(columnIndex + j);
							fieldName = secondColumns[j];

							Object valueObject = getValueObject(secondResultItem, fieldName);
							String valueString = getValueString(valueObject, dateFormat, rowIndex,
									columnIndex + j);

							setCellValue(valueString, secondCell);
						}
						// 如果是二维集合非最后一行，则由二维集合移动行标，否则由外层移动行标。
						if (it.hasNext()) {
							rowIndex++;
							row = sheet.createRow(rowIndex);
						}
					}

					// 移动列标至二维集合列后一列
					columnIndex = columnIndex + secondColumns.length - 1;
				} else {// 普通元素
					sheet.addMergedRegion(new CellRangeAddress(rowBegin, rowEnd, columnIndex,
							columnIndex));
					HSSFCell firstCell = sheet.getRow(rowBegin).createCell(columnIndex);

					Object valueObject = getValueObject(firstResultItem, fieldName);
					String valueString = getValueString(valueObject, dateFormat, rowIndex,
							columnIndex);

					setCellValue(valueString, firstCell);
				}
			}
			rowIndex++;
		}
		
		outWorkbook(fileName, response);
	}

	/**
	 * 二维集合导出，动态列
	 * @param fileName 文件名
	 * @param headers 一维集合表头
	 * @param headerIndex 动态列开始的列索引
	 * @param columnKey 动态列表头对应的属性名
	 * @param valueKey 动态列值对应的属性名
	 * @param columns 一维集合列名
	 * @param collectionName 一维集合中二维集合的列名
	 * @param result 一维集合结果集，包含二维集合结果集
	 * @param dateFormat 时间格式
	 * @param response 回复对象
	 * @param request 请求对象
	 * @throws Exception 抛出异常
	 */
	public static <T, V> void exportExcelDynamicColumn(String fileName, List<String> headers,
			int headerIndex, String columnKey, String valueKey, List<String> columns,
			String collectionName, Collection<T> result, String dateFormat,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		if (result == null || result.isEmpty()) {
			throw new NullPointerException("result is NULL");
		}
		// 构造动态表头
		for (T firstResultItem : result) {
			@SuppressWarnings("unchecked")
			Collection<V> secondResult = (Collection<V>) getValueObject(firstResultItem,
					collectionName);
			for (V secondResultItem : secondResult) {
				String headerTemp = getValueObject(secondResultItem, columnKey).toString();
				// 如果没有该列，则添加该列
				if (!headers.contains(headerTemp)) {
					// 添加表头列名
					headers.add(headerIndex, headerTemp);
					// 添加列标索引
					columns.add(headerIndex, headerTemp);
				}
			}
		}

		setRequest(request);
		initWorkbook();
		addSheet(fileName, headers.toArray());

		HSSFRow row = null;
		for (T firstResultItem : result) {
			boolean isWrite = false;
			row = createRow(fileName, headers.toArray());
			for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
				String fieldName = columns.get(columnIndex);
				// 如果一维集合中有该元素则从一维集合取值，否则从二维集合取值（动态列）
				if (hasField(firstResultItem, fieldName)) {
					HSSFCell cell = row.createCell(columnIndex);
					Object valueObject = getValueObject(firstResultItem, fieldName);

					String valueString = getValueString(valueObject, dateFormat, rowIndex,
							columnIndex);

					setCellValue(valueString, cell);
				} else if (!isWrite) {
					// 防止同一一维集合下的二维集合被重复设值
					isWrite = true;
					@SuppressWarnings("unchecked")
					Collection<V> secondResult = (Collection<V>) getValueObject(firstResultItem,
							collectionName);
					Iterator<V> it = secondResult.iterator();
					// 动态列设值
					while (it.hasNext()) {
						V secondResultItem = it.next();
						// 获取到对应的列标
						String headerTemp = getValueObject(secondResultItem, columnKey).toString();
						columnIndex = headers.indexOf(headerTemp);

						HSSFCell cell = row.createCell(columnIndex);
						Object valueObject = getValueObject(secondResultItem, valueKey);
						String valueString = getValueString(valueObject, dateFormat, rowIndex,
								columnIndex);
						setCellValue(valueString, cell);
					}
				}
			}
			rowIndex++;
		}

		outWorkbook(fileName, response);
	}

	/**
	 * 供应商出货汇总表导出（定制）
	 * @param fileName 文件名
	 * @param result 数据
	 * @param response 回应对象
	 * @param request 请求对象
	 * @throws Exception 异常
	 */
	public static void exportExcelSupplierShipment(String fileName,
			List<Map<String, String>> result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		if (result == null || result.isEmpty()) {
			throw new NullPointerException("result is NULL");
		}

		// 标题
		int headerWidth = result.get(0).size();
		// 表头
		Map<String, String> headerData = result.get(0);
		List<String> headers = new LinkedList<String>();
		for (int i = 0; i < headerWidth; i++) {
			String key = "c" + String.format("%02d", i + 1);
			headers.add(headerData.get(key));
		}
		
		setRequest(request);
		initWorkbook();
		addSheet(fileName, headers.toArray());
		// 数据
		for (int i = 1; i < result.size(); i++) {
			int columnIndex = 0;
			Map<String, String> rowData = result.get(i);
			HSSFRow row = createRow(fileName, headers.toArray());
			for (; columnIndex < headerWidth; columnIndex++) {
				String key = "c" + String.format("%02d", columnIndex + 1);
				HSSFCell cell = row.createCell(columnIndex);
				setCellValue(rowData.get(key), cell);
			}
			rowIndex++;
		}

		outWorkbook(fileName, response);
	}

}