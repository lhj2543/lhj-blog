package com.lhj.common.support;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
//@Component
public class DateSupport {
	public DateSupport() {}

	public static Date dateAddDay(Date date, int day)throws RuntimeException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, day);
		return calendar.getTime();
	}

	public static int dateCompareDay(String target, String source)throws RuntimeException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition spp = new ParsePosition(0);
		ParsePosition epp = new ParsePosition(0);
		Date start = format.parse(target, spp);
		Date end = format.parse(source, epp);
		return (int)((start.getTime() - end.getTime()) / 86400000L);
	}





	public static String formatCharacter(Date date, String pattern)throws RuntimeException{
		if (null == date) date = new Date();
		if (null == pattern) pattern = "yyyy-MM-dd";
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}



	public static Date formatDateTime(String date, String pattern)throws RuntimeException{
		if (date == null) return null;
		if (pattern == null) pattern = "yyyy-MM-dd";
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {}
		return null;
	}




	public static Date getSystemDate()
	{
		return new Date();
	}




	public static long getSystemDateTime()
	{
		return new Date().getTime();
	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * @param nowTime 当前时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @author lhj
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	* long时间转换日期字符串
	* @params time 时间
	* @formatType 转换的日期类型
	* */
	public static  String  timeToDataStr(long time,String formatType){

		String result = "";
		try{
			Date date = new Date(time);
			result = DateSupport.formatCharacter(date, formatType);
		}catch (Exception e){
			e.printStackTrace();
			result = String.valueOf(time);
		}

		return result;
	}

}
