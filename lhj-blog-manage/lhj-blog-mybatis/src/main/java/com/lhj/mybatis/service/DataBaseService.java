package com.lhj.mybatis.service;

import com.lhj.common.model.Pages;
import com.lhj.common.model.Pojo;
import com.lhj.mybatis.support.GeneratorIdSupport;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class DataBaseService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*@Value("#{datasource.dialect}")*/
	private String dialect;

    @Autowired
	private GeneratorIdSupport sidGenerator;

	public SqlSessionTemplate getSqlSession()
	{
		return this.sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession)
	{
		this.sqlSession = sqlSession;
	}

	public String getDialect()
	{
		return this.dialect;
	}

	public void setDialect(String dialect)
	{
		this.dialect = dialect;
	}

	public void setSidGenerator(GeneratorIdSupport sidGenerator)
	{
		this.sidGenerator = sidGenerator;
	}

	private String getSqlStatement(String statement)
	{
		if (this.sqlSession.getConfiguration().hasStatement(statement + "_" + this.dialect)) {
			return statement + "_" + this.dialect;
		}
		return statement;
	}

	public <T> T getMapper(Class<T> type)
	{
		Configuration config = this.sqlSession.getConfiguration();
		if (!config.hasMapper(type)) {
			this.sqlSession.getConfiguration().addMapper(type);
		}
		return config.getMapper(type, this.sqlSession);
	}

	public int insert(String statement)
	{
		return this.sqlSession.insert(getSqlStatement(statement));
	}
	
	public int insert(String statement, Pojo entity) throws Exception
	{
		if (StringUtils.isEmpty(entity.getSid())) {
			entity.setSid(this.sidGenerator.generate());
		}
		return this.sqlSession.insert(getSqlStatement(statement), entity);
	}

	public <E> int insertBatch(String statement, List<E> entitys) throws Exception
	{
		int len = 0;
		for (Iterator localIterator = entitys.iterator(); localIterator.hasNext(); ) { Object entity = localIterator.next();
		len += insert(statement, (Pojo)entity);
		}
		return len;
	}

	public int update(String statement)
	{
		return this.sqlSession.update(getSqlStatement(statement));
	}

	public <E> int update(String statement, E entity)
	{
		return this.sqlSession.update(getSqlStatement(statement), entity);
	}

	public <E> int updateBatch(String statement, List<E> entitys)
	{
		return this.sqlSession.update(getSqlStatement(statement), entitys);
	}

	public int delete(String statement)
	{
		return this.sqlSession.delete(getSqlStatement(statement));
	}

	public <E> int delete(String statement, E entity)
	{
		return this.sqlSession.delete(getSqlStatement(statement), entity);
	}

	public <E> int deleteBatch(String statement, List<E> entitys)
	{
		return this.sqlSession.delete(getSqlStatement(statement), entitys);
	}

	public <T> List<T> selectList(String statement)
	{
		return this.sqlSession.selectList(getSqlStatement(statement));
	}

	public <E> List<E> selectList(String statement, E entity)
	{
		//String sqlStatement = getSqlStatement(statement);
		return this.sqlSession.selectList(getSqlStatement(statement), entity);
	}

	public <E> List<E> selectListByPage(String statement, Pages<E> entity)
	{
		int offset = entity.getStart();
		int limit = entity.getLimit();
		return this.sqlSession.selectList(getSqlStatement(statement), entity, new RowBounds(offset, limit));
	}

	public <E> E selectOne(String statement)
	{
		return this.sqlSession.selectOne(getSqlStatement(statement));
	}

	public <E> E selectOne(String statement, E entity)
	{
		return this.sqlSession.selectOne(getSqlStatement(statement), entity);
	}

	public <E> Map<String, E> selectMap(String statement, String mapKey)
	{
		return this.sqlSession.selectMap(getSqlStatement(statement), mapKey);
	}

	public <E> Map<String, E> selectMap(String statement, E entity, String mapKey)
	{
		return this.sqlSession.selectMap(getSqlStatement(statement), entity, mapKey);
	}

	public <E> Map<String, E> selectMapByPage(String statement, Pages<E> entity, String mapKey)
	{
		int offset = entity.getStart();
		int limit = entity.getLimit();
		return this.sqlSession.selectMap(getSqlStatement(statement), entity, mapKey, new RowBounds(offset, limit));
	}

	public <E> void select(String statement, ResultHandler<E> handler)
	{
		this.sqlSession.select(getSqlStatement(statement), handler);
	}

	public <E> void select(String statement, E entity, ResultHandler<E> handler)
	{
		this.sqlSession.select(getSqlStatement(statement), entity, handler);
	}

	public <E> void selectByPage(String statement, Pages<E> entity, ResultHandler<E> handler)
	{
		int offset = entity.getStart(); int limit = entity.getLimit();
		this.sqlSession.select(getSqlStatement(statement), entity, new RowBounds(offset, limit), handler);
	}
}

