package util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static DecimalFormat df = new DecimalFormat("0");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DecimalFormat nf = new DecimalFormat("0.00");

    /**
     * 创建表格标题
     * @param wb            Excel文档对象
     * @param sheet         工作表对象
     * @param headString    标题名称
     * @param col           标题占用列数
     */
  
	public static void createHeadTittle(HSSFWorkbook wb,HSSFSheet sheet,String headString,int col){
        HSSFRow row = sheet.createRow(0);           // 创建Excel工作表的行
        HSSFCell cell = row.createCell(0);          // 创建Excel工作表指定行的单元格
        row.setHeight((short) 1000);                // 设置高度

        cell.setCellType(HSSFCell.ENCODING_UTF_16); // 定义单元格为字符串类型
        cell.setCellValue(new HSSFRichTextString(headString));

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));  // 指定标题合并区域

        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);             // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 指定单元格垂直居中个对齐
        cellStyle.setWrapText(true);                                    // 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 16); // 字体大小

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 创建表头
     * @param wb            Excel文档对象
     * @param sheet         工作表对象
     * @param thead         表头内容
     * @param sheetWidth    每一列宽度
     */
    public static void createThead(HSSFWorkbook wb,HSSFSheet sheet,String[] thead,int[] sheetWidth){
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) 600);
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  // 设置背景色
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);                // 设置右边框类型
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);               // 设置右边框颜色

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        // 设置表头内容
        for(int i=0;i<thead.length;i++){
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellType(HSSFCell.ENCODING_UTF_16);
            cell1.setCellValue(new HSSFRichTextString(thead[i]));
            cell1.setCellStyle(cellStyle);
        }

        // 设置每一列宽度
        for(int i=0;i<sheetWidth.length;i++){
            sheet.setColumnWidth(i, sheetWidth[i]);
        }
    }

    /**
     * 填入数据
     * @param wb        // Excel文档对象
     * @param sheet     // 工作表对象
     * @param result    // 表数据
     */
    public static void createTable(HSSFWorkbook wb,HSSFSheet sheet,List<Map<String, String>> result){
        // 定义单元格格式，添加单元格表样式，并添加到工作薄
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        // 循环插入数据
        for(int i = 0; i < result.size(); i++ ){
            HSSFRow row = sheet.createRow(i+2);
            row.setHeight((short) 400); // 设置高度
            HSSFCell cell = null;
            int j = 0;
            for (String key : (result.get(i).keySet())) {
                cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(result.get(i).get(key)));
                j++;
            }
        }
    }
    
    public static String formatCell(HSSFCell hssfCell){
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(hssfCell.getNumericCellValue());
			}else{
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
    
    public static ArrayList<ArrayList<Object>> readExcel2003(InputStream is) {
		try {
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Object value = null;
			for (int i = sheet.getFirstRowNum() + 1, rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if (row == null) {
					if (i != sheet.getPhysicalNumberOfRows()) {// 判断是否是最后一行
						rowList.add(colList);
					}
					return rowList;
				} else {
					rowCount++;
				}
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						if (j != row.getLastCellNum()) {
							colList.add("");
						}
						continue;
					}
					if (null != cell) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								break;
							} else {
								Double d = cell.getNumericCellValue();
								DecimalFormat df = new DecimalFormat("#.##");
								value = df.format(d);
							}
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = cell.getBooleanCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula() + "";
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "非法字符";
							break;
						default:
							value = "未知类型";
							break;
						}

					}
					colList.add(value);
				}
				rowList.add(colList);
			}
			if (is != null) {
				is.close();
			}
			return rowList;
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<ArrayList<Object>> readExcel2007(InputStream is) {
		try {
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			XSSFWorkbook wb = new XSSFWorkbook(is);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Object value = null;

			for (int i = sheet.getFirstRowNum() + 1, rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if (row == null) {
					if (i != sheet.getPhysicalNumberOfRows()) {
						rowList.add(colList);
					}
					return rowList;
				} else {
					rowCount++;
				}
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						if (j != row.getLastCellNum()) {
							colList.add("");
						}
						continue;
					}

					if (null != cell) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								break;
							} else {
								Double d = cell.getNumericCellValue();
								DecimalFormat df = new DecimalFormat("#.##");
								value = df.format(d);
							}
							break;

						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;

						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = cell.getBooleanCellValue() + "";
							break;

						case HSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula() + "";
							break;

						case HSSFCell.CELL_TYPE_BLANK:
							value = "";
							break;

						case HSSFCell.CELL_TYPE_ERROR:
							value = "非法字符";
							break;

						default:
							value = "未知类型";
							break;
						}

					}
					colList.add(value);
				}
				rowList.add(colList);
			}
			if (is != null) {
				is.close();
			}
			return rowList;
		} catch (Exception e) {
			System.out.println("exception");
			return null;
		}
	}

	public static DecimalFormat getDf() {
		return df;
	}

	public static void setDf(DecimalFormat df) {
		ExcelUtil.df = df;
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		ExcelUtil.sdf = sdf;
	}

	public static DecimalFormat getNf() {
		return nf;
	}

	public static void setNf(DecimalFormat nf) {
		ExcelUtil.nf = nf;
	}
}
