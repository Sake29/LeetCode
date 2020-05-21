import org.junit.Test;

public class Solution {

    /**
     * 两种情况：
     * 访问nums[0],访问nums[last-1]
     * 访问nums[1],访问nums[last]
     * 对于两种情况分别用动态规划
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];
        int[] nums1=new int[nums.length-1];
        int[] nums2=new int[nums.length-1];
        for(int i=0;i<nums.length-1;i++){
            nums1[i]=nums[i];
            nums2[i]=nums[i+1];
        }
        return Math.max(dp(nums1),dp(nums2));
    }

    public int dp(int[] nums){
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];
        if (nums.length==2) return Math.max(nums[0],nums[1]);
        int[] dp = new int[nums.length];//到第n间房的最多可以偷盗的钱
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    @Test
    public void test(){
        int[] nums = {};
        int rob = rob(nums);
        System.out.println(rob);
    }
}
