import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] num=new int[nums1.length+nums2.length];
        double result=0.0;
        for (int i = 0; i < nums1.length; i++) {
            num[i]=nums1[i];
        }
        for (int j = 0; j < nums2.length; j++) {
            num[j+nums1.length]=nums2[j];
        }
        Arrays.sort(num);
        System.out.println(Arrays.toString(num));
        int length = num.length;
        if(length % 2 ==0)//偶数
        {
            return (double)(num[length/2-1]+num[(length/2)])/2;
        }
        else{
            return num[(length/2)+1];
        }

    }

    @Test
    public void test(){
        int[] a= {1,4,3,5,2};
        int[] b= {2,4,6,1,9};
        double x = findMedianSortedArrays(a,b);
        System.out.println(x);
    }
}
