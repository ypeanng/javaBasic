package algorithm;

public class TempTest {

    public static void main(String[] args) {
//        ((double) (result[result.length / 2 - 1] + result[result.length / 2])) / 2;
//        System.out.println(((double) (3+4))/2);
        int[] nums1 = {3,4};
        int[] nums2 = {};
        // [1,3,5,7]
        // [2,4,6,8,9]
        int total = nums1.length + nums2.length;
        int nums1Index = 0;
        int nums2Index = 0;
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        int[] result = new int[total];
        if (nums1Len == 0) {
            for (int k = 0; k < nums2Len; k++) {
                result[k] = nums2[nums2Index];
                nums2Index++;
            }
        } else if (nums2Len == 0) {
            for (int k = 0; k < nums1Len; k++) {
                result[k] = nums1[nums1Index];
                nums1Index++;
            }
        } else {
            int n = 0;
            for (int i = 0; i < total; i++) {
                    int n1 = nums1[nums1Index];
                    int n2 = nums2[nums2Index];

                    if (n1 < n2) {
                        result[i] = n1;
                        nums1Index++;
                    } else {
                        result[i] = n2;
                        nums2Index++;
                    }

                if (nums1Index == nums1.length) {
                    i++;
                    for (int k = i; k < total; k++) {
                        result[k] = nums2[nums2Index];
                        nums2Index++;
                    }
                    break;
                } else if (nums2Index == nums2.length) {
                    i++;
                    for (int k = i; k < total; k++) {
                        result[k] = nums1[nums1Index];
                        nums1Index++;
                    }
                    break;
                }
            }
        }
        if(result.length%2==1){
            System.out.println( result[result.length/2]);
        }else {
            System.out.println((result[result.length/2-1]+result[result.length/2])/2);
        }
    }
}
