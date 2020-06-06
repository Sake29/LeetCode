import org.junit.Test;

import java.util.HashSet;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])){
                set.add(nums[i]);
            }
        }
        return set.size()<nums.length?true:false;
    }

    @Test
    public void test(){
        int[] nums = {1,2,3,1};
        boolean res = containsDuplicate(nums);
        System.out.println(res);
    }
}
