import org.junit.Test;

import java.util.HashSet;

public class Solution2 {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int num : set) {
            if (!set.contains(num-1)){
                int count = 1;
                while (set.contains(num+1)){
                    num++;
                    count++;
                }
                res = Math.max(res,count);
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {100, 4, 200, 1, 2, 3};
        long start = System.nanoTime();
        int res = longestConsecutive(nums);
        long stop = System.nanoTime();
        System.out.println(res);
        System.out.println("耗时："+(stop-start)/1000000.0);
    }
}
