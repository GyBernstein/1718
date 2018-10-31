package javawebframework;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;


public class DBSchema2Excel {
	
	private static List<String> tables = Arrays.asList("MES_Base_Holiday",
			"MES_Quality_Head_IncomingMaterial","MES_Equip_FaultKnowledgeBase_Solution","MES_Equip_DefectCode",
			"MES_Quality_IncomingChecking","MES_Quality_IncomingChecking_Item","MES_Warehouse_RFID_Out","MES_Warehouse_RFID_Out_Location",
			"MES_Warehouse_RFID_Out_Location_Details","MES_Quality_ProductInspect","MES_Quality_ProductInspect_UnqualifiedItem");
	
	private static final String queryTable = "select TABLE_NAME, TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA = database() AND TABLE_NAME=";
	private static final String queryColumn = "select COLUMN_NAME, DATA_TYPE, ( CASE DATA_TYPE WHEN 'int' THEN NUMERIC_PRECISION WHEN 'decimal' THEN CONCAT( CONCAT( NUMERIC_PRECISION, ',' ), NUMERIC_SCALE ) WHEN 'datetime' THEN DATETIME_PRECISION ELSE CHARACTER_MAXIMUM_LENGTH END ) LENGTH, COLUMN_COMMENT FROM information_schema.COLUMNS where TABLE_SCHEMA = database() AND TABLE_NAME=" ;
	private static final String queryAllTableName = "select TABLE_NAME from information_schema.`TABLES` where TABLE_SCHEMA = database()";
	private static boolean ALL_TABLE = false;
	private static Connection connection = null;
	
	static {
		connection = DBUtil.getConnection();
		// 设置查出数据库所有表
		ALL_TABLE = true;
	}
	
	
	
	public static void main(String[] args) {
		
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		sheet.setVerticallyCenter(true);
		int row = 0;
		HSSFCellStyle gray = workbook.createCellStyle();
		gray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		gray.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
		
		try {
			if (ALL_TABLE) {
				getTableNames();
			}
			for(String table: tables) {
				// 执行查询sql并向excel写入
				// 1. 查询表信息
				PreparedStatement tablestmt = connection.prepareStatement(queryTable + "'" + table + "'");
				ResultSet trs = tablestmt.executeQuery();
				
				// 写入表信息
				HSSFRow tableinfo = sheet.createRow(row);
				tableinfo.createCell(0).setCellValue("表名");
				tableinfo.createCell(1);
				tableinfo.createCell(2).setCellValue("表描述");
				tableinfo.createCell(3).setCellValue("业务描述");
				
				tableinfo.getCell(0).setCellStyle(gray);
				tableinfo.getCell(1).setCellStyle(gray);
				tableinfo.getCell(2).setCellStyle(gray);
				tableinfo.getCell(3).setCellStyle(gray);
				sheet.addMergedRegion(new CellRangeAddress((short)row, (short)row, 0, 1));
				
				row += 1;
				while (trs.next()) {
					HSSFRow tableRow =  sheet.createRow(row);
					row += 1;
					tableRow.createCell(0).setCellValue(trs.getString(1));
					tableRow.createCell(1);
					tableRow.createCell(2).setCellValue(trs.getString(2));
					tableRow.createCell(3).setCellValue(" ");
					if (null == trs.getString(2) || "".equals(trs.getString(2))) {
						System.out.println(trs.getString(1));
					}
				}
				
				// 2. 查询字段信息
				PreparedStatement columnstmt = connection.prepareStatement(queryColumn + "'" + table + "'");
				ResultSet crs = columnstmt.executeQuery();
				// 写入表信息
				HSSFRow columninfo = sheet.createRow(row);
				row += 1;
				columninfo.createCell(0).setCellValue("字段名");
				columninfo.createCell(1).setCellValue("类型");
				columninfo.createCell(2).setCellValue("长度");
				columninfo.createCell(3).setCellValue("描述");
				columninfo.getCell(0).setCellStyle(gray);
				columninfo.getCell(1).setCellStyle(gray);
				columninfo.getCell(2).setCellStyle(gray);
				columninfo.getCell(3).setCellStyle(gray);
				while (crs.next()) {
					HSSFRow tableRow =  sheet.createRow(row);
					row += 1;
					tableRow.createCell(0).setCellValue(crs.getString(1));
					tableRow.createCell(1).setCellValue(crs.getString(2));
					tableRow.createCell(2).setCellValue(crs.getString(3));
					tableRow.createCell(3).setCellValue(crs.getString(4));
				}
				// 空出一行
				sheet.createRow(row);
				row += 1;
			}
			
			// 保存文件
			 FileOutputStream out = new FileOutputStream("E:/dbinfos.xls");
			 workbook.write(out);
			 out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}



	private static void getTableNames() throws SQLException {
		// TODO Auto-generated method stub
		List<String> tableList = new ArrayList<>();
		// 查询数据信息
		PreparedStatement columnstmt = connection.prepareStatement(queryAllTableName);
		ResultSet atrs = columnstmt.executeQuery();
		while (atrs.next()) {
			tableList.add(atrs.getString(1));
		}
		tables = tableList;
		
	}



	private static void getColumnInfo(int row, String table, HSSFCellStyle gray, HSSFSheet sheet) throws SQLException {
		// TODO Auto-generated method stub
		// 查询数据信息
		
	}



	private static void getTableInfo(int row, String table, HSSFCellStyle gray, HSSFSheet sheet) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
