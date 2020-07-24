package ${table.packageName};

<#list table.dependens as dep>
import ${dep};
</#list>

import com.lhj.common.model.Pages;

/**
 * table name:		${table.name} <br/>
 * table nameCn:	${table.nameCn} <br/>
 * entity name:		${table.className} <br/>
 * author pojofactory pojo builder
 * created ${.now}
 */
public class ${table.className} extends Pages<${table.className}>{
	
	// fields start
	<#list table.fields as field>
	<#if field.name?lower_case!="sid" && field.name?lower_case!="createddate" && field.name?lower_case!="createdby" &&
		 field.name?lower_case!="updatedate" && field.name?lower_case!="updateby"
	>
	/**
	 * colNameCn 	${field.colNameCn}
	 * type 		${field.type}
	 * len 		${field.len}
	 */
	private ${field.type} ${field.name};
	</#if>
	</#list>
	// fields end
	
	
	// get,set methods
	<#list table.fields as field>
	<#if field.name?lower_case!="sid" &&  field.name?lower_case!="createddate" && field.name?lower_case!="createdby" &&
	 	 field.name?lower_case!="updatedate" && field.name?lower_case!="updateby"
	 >
	public ${field.type} get${field.methodName}(){
		return ${field.name};
	}
	public void set${field.methodName}(${field.type} ${field.name}){
		this.${field.name}=${field.name};
	}
	</#if>
	
	</#list>
	//get,set methods end
	

}
