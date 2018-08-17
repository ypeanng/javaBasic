package javaapi.jdk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<Integer> integerList = getIntegerList();
        for (Integer integer : integerList) {
            if(integer==0){
                integer=1;
            }
            System.out.printf(integer+"");
        }
        sortList(integerList);
        System.out.println("====================");
        for (Integer integer : integerList) {
            System.out.printf(integer+"");
        }
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
