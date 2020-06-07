import org.junit.Test;

import java.util.Arrays;

public class Solution {

    public int findLHS(int[] nums){
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            boolean flag = true;//该字串的元素全部相同
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j]-nums[i]>1) break;
                if (nums[i]==nums[j]){
                    count++;
                }
                if (nums[i]+1==nums[j]){
                    count++;
                    flag = false;
                }
                if (!flag){
                    res = Math.max(res,count);
                }
            }
        }
        return res;
    }
    @Test
    public void test(){
        //int[] nums = {1,3,2,2,5,2,3,7};
        //int[] nums = {1,2,2,1};
        int[] nums = {1,1,1,1};
        int res = findLHS(nums);
        System.out.println(res);
    }
}
