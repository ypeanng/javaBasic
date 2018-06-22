package javaapi.jdk.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javaapi.jdk.utils.StringUtil;


public class DateUtil {

	private static final String dateFormat = "yyyy-MM-dd";
	public static final long oneDay = 1000 * 3600 * 24;
	public static final long oneHour = 1000 * 3600;

	/**
	 * 字符串转java.sql.Date
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatToDate(String dateStr, String format){
		Date date = null;
		try {
			if(!StringUtil.isEmpty(dateStr)){
				date = new SimpleDateFormat(format).parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date;
	}

	/**
	 * TimeStamp转字符串(ymd)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTimeStampToString(Timestamp date,String format){
		if(date==null) return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		String str = df.format(date);
		return str;
	}

	/**
	 * 获取当前timestamp(仅精确到日期)
	 * @return
	 */
	public static Timestamp getCurrentTimeStampYMD(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String time = df.format(new Date());
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}

	/**
	 * 字符串转java.sql.Timestamp
	 * @param date
	 * @param format
	 * @return
	 */
	public static Timestamp formatToTimestamp(String date, String format){
		Timestamp timestamp=null;
		try {
			if(date!=null && date.length()>0){
				Date  _date  =  new SimpleDateFormat(format).parse(date);
				timestamp  =  new Timestamp(_date.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

/*	public static String formatToStr(java.sql.Date date,String format){
		String dateStr=null;
		if(date!=null){
			dateStr=new SimpleDateFormat(format).format(date);
		}
		return dateStr;
	}*/

	/**
	 *
	 * @param date
	 * @return
     */
	public static String formatToDayStr(Date date) {
		return formatToStr(date, dateFormat);
	}

	/**
	 *
	 * @param date
	 * @param format
     * @return
     */
	public static String formatToStr(Date date, String format){
		String dateStr=null;
		if(date!=null){
			dateStr=new SimpleDateFormat(format).format(date);
		}
		return dateStr;
	}

	/**
	 *
	 * @param date
	 * @param format
     * @return
     */
	public static String formatToStr(Timestamp date, String format){
		String dateStr=null;
		if(date!=null){
			dateStr=new SimpleDateFormat(format).format(date);
		}
		return dateStr;
	}
	
	/*//日期格式字符串格式化
 	public static String formatDate(String time){
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	Date date = null;
	if(StringUtils.isNotEmpty(time) && !"null".equalsIgnoreCase(time)){
		try {
			date = sdf.parse(time);
			time=sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return time;
 	}
 	
 	//日期格式字符串格式化
	public static String formatDateTime(String time){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = null;
	if(StringUtils.isNotEmpty(time) && !"null".equalsIgnoreCase(time)){
		try {
			date = sdf.parse(time);
			time=sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return time;
	}*/

	/**
	 * 两日期间相差天数
	 * @param begin
	 * @param end
     * @return
     */
	public static int dateDiff(Date begin, Date end) {
		return (int) ((getDayBegin(end).getTime() - getDayBegin(begin).getTime()) / oneDay);
	}

	/**
	 * 两日期相差小时数
	 * @param begin
	 * @param end
     * @return
     */
	public static int hourDiff(Date begin, Date end) {
		return (int) ((getDayBegin(end).getTime() - getDayBegin(begin).getTime()) / oneHour);
	}

	public static Date getDayBegin(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(day.getTime());
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		return c.getTime();
	}

 	//计算日期相隔天数
 	public static long dateDiff(String startTime, String endTime, String format) throws ParseException {
		//按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
		long diff =   sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		long day = diff/nd;//计算差多少天
		long hour = diff%nd/nh;//计算差多少小时
		long min = diff%nd%nh/nm;//计算差多少分钟
		long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
		//System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		return day;
	}

	/**
	 * 日期转化为字符串
	 * @param date
	 * @return
     */
 	public static String getStrFromDate(Date date){
 		return getStrFormDate(date, dateFormat);
 	}

	/**
	 * 日期转化为字符串
	 * @param date
	 * @param format
     * @return
     */
	public static String getStrFormDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}

    /**
	 * 获得指定日期的前后日期 t为正表示后 负表示前
	 * @param date
	 * @param t
	 * @return
     */
    public static Date getSpecifiedDay(Date date, int t) {
    	if(date == null) return null;
    	
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, t);
        return c.getTime();
    }

	/**
	 * 获取当天的开始时间
	 * @param date
	 * @param t
     * @return
     */
	public static Date getSpecifiedDayStart(Date date, int t) {
		if(date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当天的最后时间
	 * @param date
	 * @param t
     * @return
     */
	public static Date getSpecifiedDayEnd(Date date, int t) {
		if(date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, t);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
    
 	public static void main(String[] args) throws ParseException  {
		System.out.println(getSpecifiedDayStart(new Date(), 1));
	}
 	
}
