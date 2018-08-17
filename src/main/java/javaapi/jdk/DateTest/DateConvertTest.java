package javaapi.jdk.DateTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javaapi.jdk.utils.DateUtil;
import javaapi.jdk.utils.XMLGregorianCalendarUtil;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateConvertTest {
    public static void main(String[] args) {
//        sortStringTest();
//        sortTimeTest();
        toLocalDateTime();
    }

    private static void toLocalDateTime(){
        String bookingDate = "2018-08-25 10:20:21";
        LocalDateTime bookTime = LocalDateTime.parse(bookingDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(bookTime.toString());
    }

    private static void XMLGregorianCalendarTest(){
        XMLGregorianCalendar deadline = XMLGregorianCalendarUtil.dateToXmlDate(DateUtil.formatToDate("2018-06-11T23:59:00.000+02:00",
            "yyyy-MM-dd'T'HH:mm:ss.SSS"));
        System.out.println(deadline.getMonth());
        System.out.println(deadline.getDay());
        System.out.println(deadline.getHour());
        System.out.println(deadline.getMinute());
    }


    private static void sortStringTest(){
        List<String> optionDateList = new ArrayList<>();
        optionDateList.add("2018-04-29");
        optionDateList.add("2018-04-21");
        optionDateList.add("2018-05-19");
        optionDateList.add("2018-05-01");

        optionDateList.sort((s1,s2)->s1.compareTo(s2));
        System.out.println(optionDateList.get(0));

        for (String s : optionDateList) {
            System.out.println(s);
        }
    }

    /**
     * List 使用compare 如果只有一个元素，是不会报空指针的。
     */
    private static void sortTimeTest(){
        List<String> optionDateList = new ArrayList<>();

//        optionDateList.add("2018-04-29");
//        optionDateList.add("2018-04-21");
//        optionDateList.add("2018-05-19");
        optionDateList.add("2018-05-01");
//
//        optionDateList.add("2018/04/29");
//        optionDateList.add("2018/04/21");
//        optionDateList.add("2018/05/19");
//        optionDateList.add("2018/05/01");

//        optionDateList.sort((s1,s2)->s1.compareTo(s2));
//        System.out.println(optionDateList.get(0));
        optionDateList.sort((s1,s2)->DateUtil.formatToDate(s1,"yyyy/MM/dd").compareTo(DateUtil.formatToDate(s2,"yyyy/MM/dd")));

        for (String s : optionDateList) {
            System.out.println(s);
        }
    }

}
