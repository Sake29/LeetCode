import org.junit.Test;

public class Solution {

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int[] doubleNums = new int[nums.length*2];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            doubleNums[i] = nums[i];
            doubleNums[i+nums.length] = nums[i];
        }
        for (int i = 0; i < res.length; i++) {
            for (int j = i+1; j < doubleNums.length; j++) {
                if (doubleNums[i]<doubleNums[j]){
                    res[i] = doubleNums[j];
                    break;
                }
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {1,2,3,4,3};
        int[] res = nextGreaterElements(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
