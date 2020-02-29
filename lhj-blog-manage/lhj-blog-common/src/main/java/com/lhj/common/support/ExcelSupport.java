package com.lhj.common.support;
/*package com.lhj.core.support;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lhj.core.exception.BusinessServiceException;
import com.lhj.model.test.User;

*//**
 * Title: ExcelSupper.java<br>
 * Description: Excel2007/2003 工具类<br>
 * Copyright: Copyright (c) 2019 ITDCL All right reserved.<br>
 * Company: ITDCL<br>
 * 
 * @author HaiJiangLiu<br>
 * @version 1.0<br>
 * @history 1.2014-9-24 下午8:49:27 HaiJiangLiu new<br>
 *//*

public class ExcelSupport {
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			File file=new File("C:/Users/lhj/Desktop/test.xls");
			
			Map m=new HashMap();
			
			Map m2=new HashMap();
			m2.put("男", "1");
			m2.put("女", "2");
			m.put("sex", m2);
			
			
			List<Map<String, Object>> result = ExcelSupport.readExcelToListMap(file, m);
			
			JsonSupport js=new JsonSupport();
			String json = js.toJson(result);
			System.err.println(json);
			
			List<User> list = js.toList(new TypeReference<List<User>>() {}, json);
			
			for(User u:list){
				System.err.println(u.getName()+"=="+u.getAge()+"=="+u.getSex()+"=="+u.getCreatedTime());
			}
			
			
			InputStream in=new FileInputStream("C:/Users/lhj/Desktop/test.jpg");
			ByteArrayOutputStream bOut=new ByteArrayOutputStream();
			BufferedImage img=ImageIO.read(in);
			ImageIO.write(img, "JPEG", bOut);
			byte[] byteImg = bOut.toByteArray();
			Map imgMap=new HashMap();
			imgMap.put("name", byteImg);
			result.add(imgMap);
			
			File file2=new File("C:/Users/lhj/Desktop/test2.xlsx");
			boolean flag = ExcelSupport.exportListMapToExcel(result, "啦啦啦", "", new FileOutputStream(file2), null);
			System.err.println(flag);
			
			update_Excel_Screenshot("C:/Users/lhj/Desktop/test3.xlsx", 3, 4, new File("C:/Users/lhj/Desktop/test.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	*//**
     * 读取excel文件并转换文件中的记录为List<Map<String, Object>>集合<br/>
     * 【注意】：excel模版文件第一行是数据库字段名称（隐藏），第二行是字段说明（中文名称）<br/>
     * 只读取第一个sheet
     * 
     * @author HaiJiangLiu <br>
     * @version 1.0<br>
     * @history 1.2014-7-10 下午3:09:23 YangGuangJian new<br>
     * @param file
     * @param decoder 字段转码器 比如：sex:{男:1;女:2}
     * @return
	 * @throws IOException 
     *//*
	@SuppressWarnings("resource")
	public static List<Map<String, Object>> readExcelToListMap(File file,Map<String, Map<String, String>> decoder) throws IOException{
		
		List<Map<String, Object>> resutl=new ArrayList<Map<String,Object>>();
		
		Workbook wb= null;
		//FormulaEvaluator formulaEvaluator = null;
		
		InputStream in = new FileInputStream(file);
		
		//判断excel文件的版本，2007以上，以下的处理方式不一致 解决2003,-2007版本兼容性问题
		if (file.getName().endsWith("xlsx")) {
			wb = new XSSFWorkbook(in);
			//formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
		}else{
			wb = new HSSFWorkbook(in);
			//formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
		}
		
		Sheet sheet = wb.getSheetAt(0);//获取第一个Sheet
		
		int firstRowNum = sheet.getFirstRowNum();
		Row row0 = sheet.getRow(firstRowNum);//获取第一行数据
		short lastCellNum = row0.getLastCellNum();//获取行的列数
		
		// 循环行第一行获得字段名称
        Map<Integer, String> fieldNameMap = new HashMap<Integer, String>();
		for (Integer i=0;i<lastCellNum;i++) {
			Cell cell = row0.getCell(i);
			Object value = getXSSFCellValue(cell);
			fieldNameMap.put(i, value.toString());
		}
		
		int lastRowNum = sheet.getLastRowNum();//获取所有行（包括空行）
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取行数,空行不计
		
		for (int i = firstRowNum+1; i <= lastRowNum; i++) {//迭代所有行数
			Row row = sheet.getRow(i);
			if(row==null){
				continue;
			}
			Map<String, Object> rowMap=new HashMap<String, Object>();
			
			for (Integer j=0;j<lastCellNum;j++) {//迭代单元格
				Cell cell = row.getCell(j);
				if(cell==null){
					continue;
				}
				Object value = getXSSFCellValue(cell);
				String filedName=fieldNameMap.get(j);
				
				// 数值转码 BEGIN
				if(value!=null && decoder!=null && decoder.get(filedName)!=null){
					Map<String, String> decoderMap = decoder.get(filedName);
					value=decoderMap.get(value)==null?value:decoderMap.get(value);
				}
				
				rowMap.put(filedName, value);
			}
			
			resutl.add(rowMap);
		}
		
		return resutl;
		
	}
	
	
	*//**
	 * 导出Map集合到excel
	 * @author YangGuangJian <br>
	 * @version 1.0<br>
	 * @history 1.2014-9-24 下午9:43:40  YangGuangJian    new<br>
	 * @param title         sheel名称
	 * @param headers       表格标题
	 * @param data          Map集合
	 * @param datePattern   日期格式
	 * @param out           输出目标
	 * @param decoder       转码器
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	public static boolean exportListMapToExcel(List<Map<String,Object>> data,String title,String datePattern, OutputStream out,Map<String, Map<Object, Object>> decoder){
		try {

			XSSFWorkbook workbook= new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(title);//生成一个sheet表格
			sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节
			
			XSSFDrawing patriarch = sheet.createDrawingPatriarch();       // 声明一个画图的顶级管理器
			
			XSSFRow row0 = sheet.createRow(0);// 创建一行表格                   

			Map<String, Object> map = data.get(0);
			Set<Entry<String, Object>> entrySet = map.entrySet();
			
			String headName[]=new String[map.size()];
			int k=0;
			for (Entry e : entrySet) {
				XSSFCell cell = row0.createCell(k);//创建一个单元格
				//cell.setCellStyle(heardStyle);//设置单元格样式
				String colTitle=(String) e.getKey();
				if(decoder!=null){
					Map<Object, Object> store=decoder.get("heard");
					if(store!=null && store.get(colTitle)!=null){
						colTitle=store.get(colTitle).toString();
					}
				}
				cell.setCellValue(colTitle);//设置单元格值
				headName[k]=colTitle;
				k++;
			}
			
			for(int i=0;i<data.size();i++){
				
				Map<String, Object> rowMap = data.get(i);
				XSSFRow row = sheet.createRow(i+1);// 创建一行表格
				
				for (int j=0;j<headName.length;j++) {
					XSSFCell cell = row.createCell(j);//创建一个单元格
					//cell.setCellStyle(heardStyle);//设置单元格样式
					String key=headName[j];
					Object value = rowMap.get(key);
					
					// 使用 decoder 转码器进行转换
					if(decoder!=null){
						Map<Object, Object> store = decoder.get(key);
						if(store!=null){
							value = store.get(value);
						}
					}
					
					String textValue = "";
					
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						
						row.setHeightInPoints(60);                            // 有图片时，设置行高为60px;
						sheet.setColumnWidth(i, (short) (35.7 * 80));  // 设置图片所在列宽度为80px,注意这里单位的一个换算
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						int cols = j + 1;
						int rows = i - 1;
						XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,100, 100, j, rows, cols, rows);
						//anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						textValue =value==null?"":value.toString();                        // 其它数据类型都当作字符串简单处理
					}
					
					cell.setCellValue(textValue);//设置单元格值
					k++;
				}
				
				
			}

			workbook.write(out);

		}catch(Exception e){
			throw new BusinessServiceException("001012","excel导出异常！");
		}
		return true;
	}	
	
	
	public static void update_Excel_Screenshot(String filePath, int row, int col, File imgFile) {
		
		XSSFWorkbook workbook=null;
		
		BufferedImage bufferImage = null;
		FileOutputStream fileOutputStream = null;
		try {
			
			// 先把读入的图片放到第一个 ByteArrayOutputStream 中，用于产生ByteArray
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			bufferImage = ImageIO.read(imgFile);
			ImageIO.write(bufferImage, "JPEG", byteArrayOutputStream);
			
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();
			
			sheet.setColumnWidth(2, 400);
			
			XSSFRow createRow = sheet.createRow(0);
			createRow.setHeightInPoints(100);
			
			// 准备插入图片
			XSSFDrawing patriarch = sheet.createDrawingPatriarch();
			int cols = col + 1;
			int rows = row - 1;
			
			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 100, 100, (short) col, rows, (short) cols, row);
			// anchor.setAnchorType(3);
			// 准备插入图片
			byte[] pictureData = byteArrayOutputStream.toByteArray();
			int pictureFormat = XSSFWorkbook.PICTURE_TYPE_JPEG;
			int pictureIndex = workbook.addPicture(pictureData,pictureFormat);
			patriarch.createPicture(anchor, pictureIndex);
			
			fileOutputStream = new FileOutputStream(filePath);
			// 写入excel
			workbook.write(fileOutputStream);
			byteArrayOutputStream.close();
			fileOutputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}
	}

	
	
	//获取不同类型的单元格数据
	private static Object getXSSFCellValue(Cell cell){
		Object result=null;
		CellType cellType = cell.getCellType();
		
		switch (cellType) {
		case STRING:
			result=cell.getStringCellValue();
			break;
		case NUMERIC:
			result = cell.getNumericCellValue();
			
			if(DateUtil.isCellDateFormatted(cell)){//判断是否为日期类型
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				result=sdf.format(dateCellValue);
			}
			break;
		case BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case BLANK:
			result = cell.getRichStringCellValue();
			break;
		default:
			break;
		}
		
		return result;
		
	}
	
}
*/