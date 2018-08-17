package javaapi.jdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListSetTest {


    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        for (int i=0;i<1000;i++){
            stringList.add(i+"a");
            stringSet.add(i+"a");
        }
        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            stringList.contains(456+"a");
        }
        long end = System.currentTimeMillis();
        System.out.println(start-end);

        long setStart = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            stringSet.contains(456+"a");
        }
        long setEnd = System.currentTimeMillis();
        System.out.println(setStart-setEnd);

    }
    public static List<Integer> getIntegerList(){
        List<Integer> integerList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(10-i);
        }
        return integerList;
    }

    //默认排序是从小到大
    public static void sortList(List<Integer> integerList){
        integerList.sort((a,b)->(a.compareTo(b)));
    }

}
