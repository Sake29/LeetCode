import org.junit.Test;

import java.util.HashMap;

public class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i],i);
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {2,7,11,15};
        int[] res = twoSum(nums, 9);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
