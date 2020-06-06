import org.junit.Test;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
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
