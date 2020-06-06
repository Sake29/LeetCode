import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class Solution2 {
    //排序
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; ++i) {
            if (nums[i] == nums[i+1]) return true;
        }
        return false;
    }

    @Test
    public void test(){
        int[] nums = {1,2,3,1};
        boolean res = containsDuplicate(nums);
        System.out.println(res);
    }
}
