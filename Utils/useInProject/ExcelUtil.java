package com.success.jdlk.common.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * excel导出工具类
 * 
 * @author bkk
 * @version 2016年8月11日上午10:47:25
 */
public class ExcelUtil {
	/**
	 * 添加图片
	 * 
	 * <pre>
	 * 例：ExcelUtil.addPicture(book, sheet, imgUrl, HSSFWorkbook.PICTURE_TYPE_PNG, 300, 0, 700, 0, 15, 2, 16, 8);
	 * </pre>
	 * 
	 * @param wb
	 *            workBook对象
	 * @param sheet
	 *            sheet对象
	 * @param picFileName
	 *            图片文件名称（全路径）
	 * @param picType
	 *            图片类型
	 * @param dx1
	 *            开始cell的像素x
	 * @param dy1
	 *            开始cell的像素y
	 * @param dx2
	 *            结束cell的像素x
	 * @param dy2
	 *            结束cell的像素y
	 * @param col1
	 *            图片开始所在的列
	 * @param row1
	 *            图片开始所在的行
	 * @param col2
	 *            图片结束所在的列
	 * @param row2
	 *            图片结束所在的行
	 */

	public static void addPicture(Workbook wb, Sheet sheet, String picFileName, int picType, int dx1, int dy1, int dx2, int dy2, int col1, int row1,
			int col2, int row2) {
		// 判断是否存在文件
		if (picFileName == "") {
			return;
		}
		InputStream is = null;
		try {
			// 读取图片
			is = new FileInputStream(picFileName);
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, picType);
			is.close();
			// 写图片
			Drawing drawing = sheet.createDrawingPatriarch();
			// 设置图片的位置
			drawing.createPicture(new HSSFClientAnchor(dx1, dy1, dx2, dy2, (short) col1, row1, (short) col2, row2), pictureIdx);
		} catch (Exception e) {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * 合并后加边框 <br>
	 * 
	 * <pre>
	 * 例: ExcelUtil.setRegionBorder(XSSFCellStyle.BORDER_THIN, new CellRangeAddress(1, 1, 0, 16), sheet, book);
	 * </pre>
	 * 
	 * @param border
	 * @param region
	 * @param sheet
	 * @param wb
	 */
	public static void setRegionBorder(int border, CellRangeAddress region, Sheet sheet, Workbook wb) {
		sheet.addMergedRegion(region);
		RegionUtil.setBorderBottom(border, region, sheet, wb);
		RegionUtil.setBorderLeft(border, region, sheet, wb);
		RegionUtil.setBorderRight(border, region, sheet, wb);
		RegionUtil.setBorderTop(border, region, sheet, wb);

	}

	/**
	 * 获得excel单元格样式
	 * 
	 * <pre>
	 * 例：ExcelUtil.getStyle(book, null, true, XSSFCellStyle.ALIGN_CENTER, (short) 11, XSSFCellStyle.VERTICAL_CENTER, true, false,true, XSSFCellStyle.BORDER_THIN);
	 * </pre>
	 * 
	 * @param book
	 *            poi工具对象
	 * @param color
	 *            填充颜色
	 * @param border
	 *            是否有边框
	 * @param align
	 *            水平对其方式
	 * @param fontSize
	 *            字体大小
	 * @param valign
	 *            垂直对齐方式
	 * @param fontBlod
	 *            是否加粗
	 * @param italic
	 *            斜体
	 * @param wraptext
	 *            是否换行
	 * @param borderStyle
	 */
	public static CellStyle getStyle(Workbook book, Short color, boolean border, Short align, Short fontSize, Short valign, boolean fontBlod,
			boolean italic, boolean wraptext, Short borderStyle) {
		CellStyle style = book.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat("text"));
		Font font = book.createFont();
		try {
			if (color != null) {
				style.setFillForegroundColor(color);
				style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			}
			if (border) {
				style.setBorderLeft(borderStyle);
				style.setBorderBottom(borderStyle);
				style.setBorderRight(borderStyle);
				style.setBorderTop(borderStyle);
			}
			if (align != null) {
				style.setAlignment(align);
			}
			if (valign != null) {
				style.setVerticalAlignment(valign);
			}
			if (fontSize != null) {
				font.setFontHeightInPoints(fontSize);
				style.setFont(font);
			}
			if (italic) {
				font.setItalic(italic);
				style.setFont(font);
			}
			if (fontBlod) {
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				style.setFont(font);
			}
			if (wraptext) {
				style.setWrapText(wraptext);
			}
			return style;
		} catch (Exception e) {
			e.printStackTrace();
			return book.createCellStyle();
		}
	}

}
