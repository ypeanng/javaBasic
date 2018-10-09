package javaapi.jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;

public class ListArrayTest {

    public static void main(String[] args) {
        //Array to list
        String[] arrayDemo = {"a","b","c","d"};
        List<String> listDemo = Arrays.asList(arrayDemo);
        //转换的list不能进行add 操作
        try {
            listDemo.add("ad");
        }catch (UnsupportedOperationException ex){
            System.out.println("java.lang.UnsupportedOperationException  hhahah ");
        }
        //new 出来的list 可以支持所有操作
        List<String> listDemo2 = new ArrayList<>(Arrays.asList(arrayDemo));
        listDemo2.add("add");
        System.out.println(listDemo2.toString());
        //========================================================
        //List to Array
        List<String> listArr = new ArrayList<String>(){{add("23");add("14");add("45");}};
        String[]  arrFromList= listArr.toArray(new String[]{});
        System.out.println(Arrays.toString(arrFromList));
    }
}
