package algorithm.sortAlgorithm;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] oriArr = {3,4,6,9,2,4,4,5,9};
        System.out.println(Arrays.toString(qs(0,oriArr.length,oriArr)));
    }

    /**
     * 终止条件： p>=r
     * 递归公式： qs(p...r) = qs(p...q-1) + qs(q+1...r)
     * @param p 取值为初始值，最开始从0 开始
     * @param r 取值为 length 并不是 length-1
     * @param oriArr 需要排序的数组
     * @return
     */
    private static int[] qs(int p,int r,int[] oriArr){
        int temp=0;
        if(p>=r){
            return oriArr;
        }
        int q = p;
        int pivot = oriArr[p];
        boolean swap = false;
        //排序后
        for (int i = 0; i < r-p; i++) {
            if(oriArr[p+i]>pivot){
                temp = oriArr[p+i];
                oriArr[p+i]=oriArr[q];
                oriArr[q]=temp;
                q++;
                swap=true;
            }
        }
        if(!swap){
            return oriArr;
        }
        System.out.println(Arrays.toString(oriArr));
        qs(p,q,oriArr);
        qs(q+1,r,oriArr);
        return oriArr;
    }
}
