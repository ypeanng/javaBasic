package javaapi.jdk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by wcydh on 16/4/1.
 */
public class XMLGregorianCalendarUtil {

    public static XMLGregorianCalendar parse(String time) {

        XMLGregorianCalendar date = null;
        try {
            if (time.indexOf(":") < 0) time = time + "T00:00:00";
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(time);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static XMLGregorianCalendar parse(String time, int timezone) {
        XMLGregorianCalendar date = null;
        try {
            if (time.indexOf(":") < 0) time = time + "T00:00:00";
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(time);
            date.setTimezone(timezone);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static XMLGregorianCalendar parse(long time) {
        return parse(time, 0);
    }

    public static XMLGregorianCalendar parse(long time, int timezone) {
        GregorianCalendar cal = new GregorianCalendar();

        cal.setTimeInMillis(time);
        XMLGregorianCalendar xCal = null;
        try {
            xCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            xCal.setTimezone(timezone);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return xCal;
    }

    /**
     * 转成服务器Date  2016-05-04T23:59:00+02:00 -> Thu May 05 05:59:00 CST 2016   中国
     *
     * @param xmlGregorianCalendar
     * @return
     */
    public static Date convertToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        GregorianCalendar ca = xmlGregorianCalendar.toGregorianCalendar();
        return ca.getTime();
//       return new Date(xmlGregorianCalendar.getMillisecond());
    }

    /**
     * 仅仅去掉时区,不进行时区转换
     *
     * @param xmlGregorianCalendar
     * @return
     */
    public static String toStr(XMLGregorianCalendar xmlGregorianCalendar) {
        // 1970-01-01  => hour minute secend timezone :-2147483648
        String result = "";
        result += xmlGregorianCalendar.getYear();
        result += "-";
        result += zeroize(xmlGregorianCalendar.getMonth());
        result += "-";
        result += zeroize(xmlGregorianCalendar.getDay());
        result += "T";
        result += zeroize(xmlGregorianCalendar.getHour());
        result += ":";
        result += zeroize(xmlGregorianCalendar.getMinute());
        result += ":";
        result += zeroize(xmlGregorianCalendar.getSecond());
        return result;
//       return new Date(xmlGregorianCalendar.getMillisecond());
    }

    /**
     * 将Date类转换为XMLGregorianCalendar
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar dateToXmlDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DatatypeFactory dtf = null;
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            return null;
        }
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
        dateType.setYear(cal.get(Calendar.YEAR));
        //由于Calendar.MONTH取值范围为0~11,需要加1
        dateType.setMonth(cal.get(Calendar.MONTH) + 1);
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
        dateType.setMinute(cal.get(Calendar.MINUTE));
        dateType.setSecond(cal.get(Calendar.SECOND));
        return dateType;
    }

    private static String zeroize(int num) {
        if (num < 10) return "0" + num;
        return "" + num;
    }

    public static void main(String[] args) {
//        String time = "2016-05-04T23:59:00+02:00";
//        String time = "2016-05-29T23:59:00Z";
        String time = "2016-05-29T23:59:00";
        XMLGregorianCalendar cal = XMLGregorianCalendarUtil.parse(time);
        System.out.println(cal.toString());
        XMLGregorianCalendar cal2 = XMLGregorianCalendarUtil.parse(time, 0);
        System.out.println(cal2.toString());

        Date date2 = XMLGregorianCalendarUtil.convertToDate(cal2);
        System.out.println(date2.toString());

//        Date date3 = XMLGregorianCalendarUtil.convertToDate2(cal2);
        System.out.println(XMLGregorianCalendarUtil.toStr(cal2));

        javax.xml.datatype.Duration duration = null;
        try {
            duration = DatatypeFactory.newInstance().newDuration(-1000);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        cal.add(duration);
        System.out.println(XMLGregorianCalendarUtil.toStr(cal));
        Date cDate = XMLGregorianCalendarUtil.convertToDate(cal);
        System.out.println(cDate.toString());
        System.out.println("DateTime.formatTime:");

        SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        formatterTime.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        formatterTime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        formatterTime.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(formatterTime.format(cDate));

        XMLGregorianCalendar now = XMLGregorianCalendarUtil.dateToXmlDate(new Date());

//        XMLGregorianCalendar cal = XMLGregorianCalendarUtil.parse(now);
        Date date = XMLGregorianCalendarUtil.convertToDate(now);
        System.out.println(date.toString());

        System.out.println(now.toString());

    }


}
