package com.nuiwcp.pojofactory.client.bin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uniwc.pojofactory.builder.service.BuilderService;
import com.uniwcp.pojofactory.jdbc.JdbcConnector;
import com.uniwcp.pojofactory.jdbc.TableMeta;

public class PojofactoryMain {
	
	private static String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
	private static String username="root";
	private static String password="123456";
	private static String outputdir="./target";
	private static String pojopackage="com.lhj.model.system";

	public static void main(String[] args) {
		
		JdbcConnector jdbcConn=JdbcConnector.getInstance();
		BuilderService builderService=new BuilderService();
		
		jdbcConn.setUrl(url);
		jdbcConn.setUsername(username);
		jdbcConn.setPassword(password);
		List<TableMeta> tableMetas=jdbcConn.loadTableMetaes("pojofactory");
		System.out.println("======================================");
		System.out.println();
		
		for(TableMeta table:tableMetas){
			table.setPackageName(pojopackage);
			Map<String, Object> model=new HashMap<String, Object>();
			model.put("table", table);
			
			builderService.process("/pojo/model.java", model, createFileByBackageName(table.getPackageName(), table.getClassName()+".java"));
			builderService.process("/pojo/mapper.xml", model, createFileByBackageName(table.getPackageName(), table.getClassName()+".mapper.xml"));
		}
		
		

	}
	
	
	
	
	
	
	public static Writer createFileByBackageName(String packageName,String fileName){
		String dir=outputdir+"/"+packageName.replaceAll("\\.", "/");
		File dirF=new File(dir);
		if(!dirF.exists()){
			dirF.mkdirs();
		}
		File file=new File(dirF,fileName);
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
			System.out.println("file path:"+file.getAbsolutePath());
			return new OutputStreamWriter(new FileOutputStream(file), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	

}
