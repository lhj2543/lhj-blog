package com.lhj.mybatis.dialect.limit;

import com.lhj.mybatis.dialect.IDialect;
import com.lhj.mybatis.dialect.IDialectManager;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component/*Springboot注入之定义分页插件 mybatis会拦截分页*/
@Intercepts({@org.apache.ibatis.plugin.Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	private IDialectManager dialectManager;

	/*
	#{} 获取bean 属性值
	${} 获取配置问 属性值
	*/
	@Value("${myBatis.custom.limit.dialect}")
	public String dialect;

	@Autowired(required=true)
	public void setDialectManager(IDialectManager dialectManager) {
		this.dialectManager = dialectManager;
	}

	public String getDialect() {
		return this.dialect;
	}

	public void setDialect(String dialect)
	{
		this.dialect = dialect;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	void processIntercept(Object[] queryArgs) {
		MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();

		IDialect dialectClass = this.dialectManager.getDialect(this.dialect);

		if ((dialectClass.supportsLimit()) && ((offset != 0) || (limit != 2147483647)))
		{
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			if (dialectClass.supportsLimitOffset())
			{
				sql = dialectClass.getLimitString(sql, offset, limit);
				offset = 0;
			} else {
				sql = dialectClass.getLimitString(sql, 0, limit);
			}
			limit = 2147483647;

			queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
			Configuration con = new Configuration();

			BoundSql newBoundSql = new BoundSql(con, sql, boundSql
					.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));

			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

			System.out.println(getClass() + "----------------mybatis 自定义分页sql---- \n" + sql);
		}
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms
				.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		if (ms.getKeyProperties() != null) {
			for (String property : ms.getKeyProperties())
				builder.keyProperty(property);
		}
		MappedStatement newMs = builder.build();
		return newMs;
	}

	public Object plugin(Object target) {
		if ((target instanceof Executor)) {
			return Plugin.wrap(target, this);
		}
		return target;
	}
	public void setProperties(Properties properties) {
	}
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSql getBoundSql(Object arg0) { return this.boundSql; }

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
	}
}
