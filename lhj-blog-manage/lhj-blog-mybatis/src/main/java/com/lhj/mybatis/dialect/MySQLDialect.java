package com.lhj.mybatis.dialect;

public class MySQLDialect extends DefaultDialect
{
	public boolean supportsLimitOffset()
	{
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	protected String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		}
		return sql + " limit " + limitPlaceholder;
	}
}
