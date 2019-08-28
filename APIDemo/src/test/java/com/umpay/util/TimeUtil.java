package com.umpay.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeUtil {
	

	public static String date(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}

	public static String date(String fmt, long t) {
		return new SimpleDateFormat(fmt).format(new Date(t));
	}
	public static String date(String fmt, Date date) {
		return new SimpleDateFormat(fmt).format(date);
	}
	
	public static Date date(String fmt, String date) {
		try {
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		format.setLenient(false);
		return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}			
	}
	
	public static String date8() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String getDateYear(Date date) {
		return new SimpleDateFormat("yyyy").format(date);
	}

	public static String date8(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	public static String date8(Timestamp date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	public static String date10(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String date10other(Date date) {
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}
	public static String time6() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}
	public static String time8(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	public static String time6(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}
	public static String time6(Timestamp date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	public static String datetime14() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String datetime14(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	public static String datetime14(long t) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(t));
	}

	public static long toMilliSec(String time14) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(time14.substring(0, 4)), Integer
				.parseInt(time14.substring(4, 6)) - 1, Integer.parseInt(time14
				.substring(6, 8)), Integer.parseInt(time14.substring(8, 10)),
				Integer.parseInt(time14.substring(10, 12)), Integer
						.parseInt(time14.substring(12, 14)));
		return cal.getTimeInMillis();
	}

	public static int getActualMaximum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)), Integer.parseInt(day8
				.substring(4, 6)) - 1, Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMaximum(field);
	}

	public static int getActualMinimum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)), Integer.parseInt(day8
				.substring(4, 6)) - 1, Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMinimum(field);
	}
	/**
	 * 
	 * description: 获得当天是当年的第几周
	 * @return
	 */
	public static int getWeekIndex(){
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 
	 * description: 在now基础上增加amount分钟
	 * @param now
	 * @param amount
	 * @return
	 */
	public static Date addMin(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}
	/**
	 * 
	 * description: 在now基础上增加amount秒
	 * @param now
	 * @param amount
	 * @return
	 */
	public static Date addSec(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.SECOND, amount);
		return cal.getTime();
	}
	/**
	 * 
	 * description: 在now基础上增加amount个日
	 * @param now
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date now, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}
	/**
	 * 
	 * description: 在now基础上增加amount个月
	 * @param now
	 * @param amount
	 * @return
	 */
	public static Date addMonth(Date now, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}
	/**
	 * 
	 * description: 在now基础上增加amount个年
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}
	/**
	 * 
	 * description: 将8位的日期文本解析成Date
	 * @param date 待解析的日期文本
	 * @return
	 */
	public static Date parseDate8(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.setLenient(false);
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 
	 * description: 校验date是否是合法的8位日期
	 * @param date
	 * @return 若合法返回true，否则返回false
	 */
	public static boolean validateDate8(String date){
		Date d = parseDate8(date);
		return d != null && date8(d).equals(date);
	}
	/**
	 * 
	 * description: 将14位的日期时间字符串解析成Date
	 * @param date 待解析的日期文本
	 * @return
	 */
	public static Date parseDatetime14(String datetime) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			format.setLenient(false);
			return format.parse(datetime);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 
	 * description: 将8位的日期时间字符串解析成Date
	 * @param date 待解析的日期文本
	 * @return
	 */
	public static Date parseDatetime6(String datetime) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
			format.setLenient(false);
			return format.parse(datetime);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 
	 * description: 将6位的时间文本解析成Date
	 * @param date 待解析的日期文本
	 * @return
	 */
	private static Date parseTime6(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
			format.setLenient(false);
			return format.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 
	 * description: 校验time是否是合法的6位时间
	 * @param time
	 * @return 若合法返回true，否则返回false
	 */
	public static boolean validateTime6(String time){
		Date d = parseTime6(time);
		return d != null && time6(d).equals(time);
	}
	/**
	 * 
	 * description: 返回date是一周的第几天。周日：0，周一：1，周六：6
	 * @param string
	 * @return
	 */
	public static int getDayOfWeek(String date) {
		Date day = parseDate8(date);
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}
	/**
	 * 
	 * description: 返回d1-d2之间相差多少分钟
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int diffInMin(Date d1, Date d2){
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		double unit = 60 * 1000;
		int absDiff = (int)Math.ceil(Math.abs((t1-t2))/unit);
		if(t1 > t2)
			return absDiff;
		else
			return -absDiff;
	}
	/**
	 * 
	 * description: 返回d1-d2之间相差多少分钟
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int diffInSec(Date d1, Date d2){
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		double unit = 1000;
		int absDiff = (int)Math.ceil(Math.abs((t1-t2))/unit);
		if(t1 > t2)
			return absDiff;
		else
			return -absDiff;
	}
	/**
	 * 
	 * description: 将beginDate，endDate之间的时间按照interval切片
	 * @param beginDate
	 * @param endDate
	 * @param interval_sec, 以秒为单位
	 * @return 切成的小片时间, 每片时间形式为[begin, end]
	 */
	public static List<Date[]> slice(Date beginDate, Date endDate, int interval_sec){
		List<Date[]> pieces = new ArrayList<Date[]>();
		while(beginDate.compareTo(endDate) <=0){
			Date nextEndDate = addSec(beginDate, interval_sec); 
			if(nextEndDate.after(endDate))
				nextEndDate = endDate;
			Date[] piece = new Date[2];
			piece[0] = beginDate;
			piece[1] = nextEndDate;
			pieces.add(piece);
			beginDate = addSec(nextEndDate, 1);
		}
		return pieces;
	}
	/**
	 * 
	 * @param days    -1 =昨天  0=今天        1=明天
	 * @return
	 */
	public static String rollDate(int days){
		SimpleDateFormat formator = new SimpleDateFormat();
		formator.applyPattern("yyyyMMdd");	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		Calendar calendar =  new GregorianCalendar();
		try {
			calendar.setTime(formator.parse(TimeUtil.date8()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DATE, days);	
		return df.format(calendar.getTime());
	} 
	
	// 获取当前时间前i天的日期（i为正数为之前的日期，为负数是之后的日期）
	public static String getBeforeTime(int i) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -i);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	// 获取excel包含的yyMMddHHmm格式时间
	public static String getExcelTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");// HH是24小时制，hh是12小时制
		Date date = new Date();
		System.out.println("获取Excel时间"+sdf.format(date));
		return sdf.format(date);
		
	}

	// 获取yyyyMMdd格式时间
	public static String date() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// HH是24小时制，hh是12小时制
		Date date = new Date();
		return sdf.format(date);
	}

	// 获取前一天时间yyyyMMdd格式时间
	public static String getPreDate(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// HH是24小时制，hh是12小时制
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -n);
		date = calendar.getTime();
		return sdf.format(date);
	}

	// 判断当前时间是否超时
	public static int compareTime(String webtime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -8); // 减8天
		String date1 = sdf.format(cal.getTime());
		Date fomatDate1 = sdf.parse(date1);
		Date fomatDate2 = sdf.parse(webtime);
		return fomatDate2.compareTo(fomatDate1);
	}
	
	/**
	 * 获取当前时间
	 * @return
	 * @throws ParseException
	 */
		public static String currentTime() {
			Date date = new Date();
			String  currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);		
			return currentTime;
		}
		
	/*
	 * 获取当前月的前一个月
	 * type =1 返回XXXX年XX月 获取当前月的前一个月
	 * type =2返回201707 
	 * type =3返回2017年7月
	 */
	public static String getPreMonth(int type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String[] arrary = sdf.format(date).split("-");
		String month = arrary[1];
		String time = "";
		int newMonth = Integer.valueOf(month) - 1;
		if (type == 3) {
			if (newMonth == 0){
				time = Integer.valueOf(arrary[0])-1 + "年12月";
			}else{
				time = arrary[0] + "年" + newMonth + "月";
			}			
		} else {
			if (newMonth < 10) {
				String year  = arrary[0];
				if( newMonth == 0 ){
					year = String.valueOf((Integer.valueOf(arrary[0])-1));
				}
				if (type == 1) {
					if(newMonth == 0){
						time = year + "年12月";	
					}else{
					time = year + "年0" + newMonth + "月";
					}
				} else if (type == 2) {
					if(newMonth == 0){
						time = year + "12";	
					}else{
					time = year + "0" + newMonth;
					}
				}	
			} else {
				if (type == 1) {
					time = arrary[0] + "年" + newMonth + "月";
				} else if (type == 2) {
					time = arrary[0] + newMonth;
				}
			}
		}
		return time;
	}
	
	// 获取本月第一天的日期，，yyyy-MM-dd格式
	public static String getFirstDayofThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String thisMonth = sdf.format(date);
		String firstDayofThisMonth = thisMonth + "-01";
		return firstDayofThisMonth;
	}
	
	/*
	 * 获取当前日期的前一天，返回XX月XX日
	 */
	public static String getPreDateMD(int n ) throws ParseException {
		String time = getPreDate(n);
		return time.substring(4, 6)+ "月" + time.substring(6, 8)+"日";
	}
	// 获取RealTime长度时间
	public static String RealTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return sdf.format(date);
	}

}