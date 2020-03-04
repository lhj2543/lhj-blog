package com.uniwc.pojofactory.builder.service;

import java.io.IOException;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class BuilderService {

	private Configuration config = null;
	private String tempRealPath = "";

	public BuilderService() {
		config = new Configuration();
		config.setOutputEncoding("utf-8");
		config.setDefaultEncoding("utf-8");
		config.setTemplateUpdateDelay(0);
		config.setClassicCompatible(true);
		config.setClassForTemplateLoading(BuilderService.class,"/templates");
		tempRealPath = BuilderService.class.getResource("/").getPath()+ "templates";
		tempRealPath = tempRealPath.substring(1, tempRealPath.length());
	}
	
	
	
	public void process(String template,Object model,Writer out){
		try {
			config.getTemplate(template).process(model, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
