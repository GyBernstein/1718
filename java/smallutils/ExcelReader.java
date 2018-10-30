package com.gaoyuan.hot.util;

public class ExcelReader {
//	private final static String excel2003L = ".xls"; // 2003- 版本的excel
//	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
//
//	/**
//	 * 描述：根据文件后缀，自适应上传文件的版本
//	 * 
//	 * @param inStr
//	 *            ,fileName
//	 * @return
//	 * @throws Exception
//	 */
//	public static Workbook getWorkbook(InputStream inStr, String fileName)
//			throws Exception {
//		Workbook wb = null;
//		String fileType = fileName.substring(fileName.lastIndexOf("."));
//		if (excel2003L.equals(fileType)) {
//			wb = new HSSFWorkbook(inStr); // 2003-
//		} else if (excel2007U.equals(fileType)) {
//			wb = new XSSFWorkbook(inStr); // 2007+
//		} else {
//			throw new Exception("解析的文件格式有误！");
//		}
//		return wb;
//	}
//
//	// excel导入功能
//	public static Map<String, String> execelImport(InputStream in,
//			String fileName) throws Exception {
//		Map<String, String> resultMap = new HashMap<String, String>();
//		// 创建Excel工作薄
//		Workbook wookbook = getWorkbook(in, fileName);
//		if (null == wookbook) {
//			resultMap.put("errorMsg", "Excel内容为空");
//			return resultMap;
//		}
//		// 得到一个工作表
//		Sheet sheet = wookbook.getSheetAt(0);
//		// 获得数据的总行数
//		int totalRowNum = sheet.getLastRowNum();
//		System.out.println(totalRowNum);
//		// 获得所有数据
//		for (int i = 0; i <= totalRowNum; i++) {
//			// 获得第i行对象
//			Row row = sheet.getRow(i);
////			String code = row.getCell((short)1).getStringCellValue().toString();//人员
//
//			String code = row.getCell((short)1).getNumericCellValue()+"";//库位
//			code = code.substring(0, code.lastIndexOf("."));//库位
//			String name = row.getCell((short)2).getStringCellValue().toString();
//			resultMap.put(code, name);
//		}
//		return resultMap;
//	}
}
