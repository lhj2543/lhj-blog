package com.lhj.mybatis.dialect;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultDialectManager  implements IDialectManager
{
	private Map<String, IDialect> dialects;
	private IDialect defaultDialect = new DefaultDialect();

	private DefaultDialectManager(){
        initDialects();
    }

	public void setDialects(Map<String, IDialect> dialects)
	{
		this.dialects = dialects;
		initDialects();
	}

	protected void initDialects()
	{
		if (this.dialects == null) {
			this.dialects = new HashMap();
			this.dialects.put("db2", new DB2Dialect());
			this.dialects.put("mysql", new MySQLDialect());
			this.dialects.put("oracle", new OracleDialect());
			this.dialects.put("sqlServer2005", new SQLServer2005Dialect());
			this.dialects.put("sqlServer", new SQLServerDialect());
		}
	}

	public IDialect getDialect(String dialect)
	{
		IDialect dialectClass = (IDialect)this.dialects.get(dialect);
		if (dialectClass == null) {
			dialectClass = this.defaultDialect;
		}
		return dialectClass;
	}
}

