package com.wide.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 日期时间工具类
 */
public class DateUtil {

	private final static String DATEFORMAT = "yyyy-MM-dd";
	
	private final static String DATEFORMATTWO = "yyyy/MM/dd";

	private final static String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private final static String DATETIMEFORMATNOT = "yyyy-MM-dd HH:mm";

	/**
	 * 按指定格式计算日期
	 * 
	 * @param fmt
	 *            格式：Y年，M月，D日
	 * @param volume
	 * @return
	 */
	public static Date computeDate(Date date, char fmt, int volume) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (fmt) {
		case 'Y':
			cal.add(Calendar.YEAR, volume);
			break;
		case 'M':
			cal.add(Calendar.MONTH, volume);
			break;
		case 'D':
			cal.add(Calendar.DAY_OF_MONTH, volume);
			break;
		}

		return cal.getTime();
	}

	/**
	 * 计算天数的间隔
	 * 
	 * @param startDate
	 *            开始日期字符串(格式：yyyy-MM-dd)
	 * @param endDate
	 *            结束日期字符串(格式：yyyy-MM-dd)
	 * @return
	 */
	public static long getBetweenDays(String startDate, String endDate) {
		long quot = 0;

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(startDate);
			Date date2 = ft.parse(endDate);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 按指定格式获取日期元素
	 * 
	 * @param date
	 * @param fmt
	 *            格式：Y年，M月，D日
	 * @return
	 */
	public static int getDataElement(Date date, char fmt) {
		int reInt = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (fmt) {
		case 'Y':
			reInt = cal.get(Calendar.YEAR);
			break;
		case 'M':
			reInt = cal.get(Calendar.MONTH) + 1;
			break;
		case 'D':
			reInt = cal.get(Calendar.DAY_OF_MONTH);
			break;
		}
		return reInt;
	}

	/**
	 * 获取从从1月份开始的月份集合
	 * 
	 * @param month
	 * @return
	 */
	public static List<Integer> getsMonthList() {
		List<Integer> monthList = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			monthList.add(i);
		}
		return monthList;
	}

	/**
	 * 获取从指定月份开始的月份集合
	 * 
	 * @param month
	 * @return
	 */
	public static List<Integer> getsMonthList(int month) {
		List<Integer> monthList = new ArrayList<Integer>();
		for (int i = month; i <= 12; i++) {
			monthList.add(i);
		}
		return monthList;
	}

	/**
	 * 格式化字符串为日期：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDate(String dateStr) {
		return toFormatDate(dateStr, DATEFORMAT);
	}

	/**
	 * 格式化日期为字符串：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateStr(Date date) {
		if (date == null) {
			return "";
		}
		return toFormatDateStr(date, DATEFORMAT);
	}
	
	/**
	 * 格式化日期为字符串：yyyy/MM/dd
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateStrTwo(Date date) {
		if (date == null) {
			return "";
		}
		return toFormatDateStr(date, DATEFORMATTWO);
	}

	/**
	 * 格式化字符串为日期时间：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateTime(String dateStr) {
		return toFormatDate(dateStr, DATETIMEFORMAT);
	}
	
	/**
	 * 格式化字符串为日期时间：yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateTimeNot(String dateStr) {
		return toFormatDate(dateStr, DATETIMEFORMATNOT);
	}

	/**
	 * 格式化日期时间为字符串：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateTimeStr(Date date) {
		return toFormatDateStr(date, DATETIMEFORMAT);
	}

	/**
	 * 转换字符串为指定格式日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date toFormatDate(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 格式化日期为指定格式字符串
	 * 
	 * @param date
	 * @param format
	 *            格式
	 * @return
	 */
	public static String toFormatDateStr(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 转换Timestamp为String
	 * 
	 * 
	 * */
	public static String timestamp2Str(Timestamp time) {
		  if(null != time && !"".equals(time)){
		    Date date = new Date(time.getTime());
		    
		    return toDateTimeStr(date);
		  }
		  return null;
		}
	/**
	  * 将长整型数字转换为日期格式的字符串
	  * 
	  * @param time
	  * @param format
	  * @return
	  */
	 public static String convert2String(long time) {
		 SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt = new Date(time * 1000);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
		System.out.println(sDateTime);
		return sDateTime;
	 }
	 /**
	  * 对比两个时间长短
	  * 
	  * @param String DATE1  String DATE2
	  * @param format
	  * @return int
	  */
	 public static int compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2后");
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2前");
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	    /**
	     * 某一个月第一天和最后一天
	     * @param date
	     * @return
	     */
	    public static Map<String, String> getFirstday_Lastday_Month(Date date) {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, -1);
	        Date theDate = calendar.getTime();
	        
	        //上个月第一天
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        String day_first = df.format(gcLast.getTime());
	        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
	        day_first = str.toString();

	        //上个月最后一天
	        calendar.add(Calendar.MONTH, 1);    //加一个月
	        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
	        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
	        String day_last = df.format(calendar.getTime());
	        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
	        day_last = endStr.toString();

	        Map<String, String> map = new HashMap<String, String>();
	        map.put("first", day_first);
	        map.put("last", day_last);
	        return map;
	    }

	    /**
	     * 当月第一天
	     * @return
	     */
	    private static String getFirstDay() {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();
	        Date theDate = calendar.getTime();
	        
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        String day_first = df.format(gcLast.getTime());
	        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
	        return str.toString();
	    }
}
