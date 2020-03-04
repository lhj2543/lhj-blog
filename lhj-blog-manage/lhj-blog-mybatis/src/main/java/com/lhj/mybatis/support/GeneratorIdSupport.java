package com.lhj.mybatis.support;


import com.lhj.common.support.DateSupport;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratorIdSupport
{

  @Autowired
  private SqlSessionTemplate sqlSession;

  private String sysId = "1000";

  private String increment = "1";

  @Autowired
  private DateSupport dateSupport;

  /*日志*/
  Logger logger = LoggerFactory.getLogger(getClass());

  public void setSqlSession(SqlSessionTemplate sqlSession)
  {
    this.sqlSession = sqlSession;
  }

  public void setSysId(String sysId)
  {
    this.sysId = sysId;
  }
  
  /**
   * 获取唯一ID,通过数据库方式
   */
  /*public synchronized String generate() throws Exception
  {
    String currentDate = this.dateSupport.formatCharacter(this.dateSupport.getSystemDate(), "yyyyMMdd");
    Map sequence = new HashMap();
    sequence.put("currentDate", currentDate);

    Map map = this.sqlSession.selectMap("findSequence", sequence, "currentDate");
    if ((map == null) || (map.get(currentDate) == null))
    {
      sequence.put("sysId", this.sysId);
      sequence.put("currentValue", "0000000000");
      sequence.put("increment", this.increment);
      sequence.put("currentDate", currentDate);
      int len = this.sqlSession.insert("addSequence", sequence);
      if (len > 0)
        this.logger.debug("主键初始化【成功】，sysId:" + this.sysId + ",currentDate:" + currentDate);
      else
        this.logger.error("主键初始化【失败】，sysId:" + this.sysId + ",currentDate:" + currentDate);
    }
    else
    {
      sequence = (Map)map.get(currentDate);
      Integer increment = Integer.valueOf(Integer.parseInt((String)sequence.get("increment")));
      Integer currentValue = Integer.valueOf(Integer.parseInt((String)sequence.get("currentValue")) + increment.intValue());
      sequence.put("currentValue", String.format("%010d", new Object[] { currentValue }));
      int len = this.sqlSession.update("updateSequence", sequence);
      if (len > 0)
        this.logger.debug("主键更新【成功】，sysId:" + this.sysId + ",currentDate:" + currentDate + ",currentValue:" + (String)sequence.get("currentValue"));
      else {
        this.logger.error("主键更新【失败】，sysId:" + this.sysId + ",currentDate:" + currentDate + ",currentValue:" + (String)sequence.get("currentValue"));
      }
    }
    StringBuffer sid = new StringBuffer();
    sid.append((String)sequence.get("sysId")).append((String)sequence.get("currentDate")).append((String)sequence.get("currentValue"));
    return sid.toString();
  }*/
  
  public String generate() throws Exception{

	  String currentDate = this.dateSupport.formatCharacter(this.dateSupport.getSystemDate(), "yyyy-MM-dd HH:mm:ss");

	  String id = IdUtils.getIdByNanoTime();

	  this.logger.debug("主键初始化【成功】，id:" + id + ",currentDate:" + currentDate);

	return id;
  }
  
}
