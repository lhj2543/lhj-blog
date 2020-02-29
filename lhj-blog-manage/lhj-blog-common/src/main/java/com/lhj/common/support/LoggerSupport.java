/*
package com.lhj.blog.common.support;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class LoggerSupport
{
	private static Logger auditLogger = LogManager.getLogger("auditLogger");

	private static Logger debugLogger = LogManager.getLogger("debugLogger");

	private static Logger errorLogger = LogManager.getLogger("errorLogger");

	private static Logger sqldbLogger = LogManager.getLogger("sqldbLogger");

	public LoggerSupport() {}

	public void debug(String message)
	{
		debugLogger.debug(message);
	}



	public void debug(String message, Object o)
	{
		debugLogger.debug(message, new Object[] { o });
	}



	public void debug(String message, Exception ex)
	{
		debugLogger.debug(message, ex);
	}



	public void debug(String message, Object o, Exception ex)
	{
		debugLogger.debug(message, new Object[] { o, ex });
	}



	public void audit(String message)
	{
		auditLogger.info(message);
	}



	public void audit(String message, Object o)
	{
		auditLogger.info(message, new Object[] { o });
	}



	public void audit(String message, Exception ex)
	{
		auditLogger.info(message, ex);
	}



	public void audit(String message, Object o, Exception ex)
	{
		auditLogger.info(message, new Object[] { o, ex });
	}



	public void error(String message)
	{
		errorLogger.error(message);
	}



	public void error(String message, Object o)
	{
		errorLogger.error(message, new Object[] { o });
	}



	public void error(String message, Exception ex)
	{
		errorLogger.error(message, ex);
	}



	public void error(String message, Object o, Exception ex)
	{
		errorLogger.error(message, new Object[] { o, ex });
	}



	public void sqldb(String message)
	{
		sqldbLogger.trace(message);
	}



	public void sqldb(String message, Object o)
	{
		sqldbLogger.trace(message);
	}



	public void sqldb(String message, Exception ex)
	{
		sqldbLogger.trace(message, ex);
	}



	public void sqldb(String message, Object o, Exception ex)
	{
		sqldbLogger.trace(message, ex);
	}
}
*/
