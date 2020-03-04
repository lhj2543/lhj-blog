package com.nuiwcp.pojofactory.client.bin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.uniwc.pojofactory.builder.service.BuilderService;

public class SqlFactoryMain {
	
	private static String outputdir="./target";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuilderService builderService=new BuilderService();
		Map<String, Object> config=new HashMap<String, Object>();
		
		config.put("seed", "01");		//	生成随机SID的种子
		config.put("appid", "VIP");	//	应用ID
		config.put("app", "vip");	//		应用名称
		config.put("menuid", "1000201611280000000010");	//	按钮所属菜单ID
		config.put("depname", "levels");	//	js组件名称，小写
		config.put("depName", "Levels");	//	js组件名称，大写
		config.put("deps", "/script/vip/levels");	//js组件路径
		config.put("name", "会员级别");	//	模块名称
		config.put("addmenuid", "1000201611300000000002");	//	
		config.put("updatemenuid", "1000201611300000000000");	//	
		config.put("detailmenuid", "1000201611300000000001");	//	
		
		builderService.process("/sql/button.sql", config, createFileName("sql", "button.sql"));

	}
	
	
	public static Writer createFileName(String dir,String fileName){
		dir=outputdir+"/"+dir;
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
