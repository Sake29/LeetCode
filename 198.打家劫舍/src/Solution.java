import org.junit.Test;

public class Solution {

    /**
     * 动态规划
     * 1.定义dp[n]的含义：到第n间房的最多可以偷盗的钱
     * 2.找到dp中的元素之间的关系：dp[n]=Math.max(dp[n-1],dp[n-2]+nums[n]);
     * 3.找初始条件：dp[0]=nums[0]
     *              dp[1]=Math.max(nums[0],nums[1]);
     * 4.求解dp
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
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
        int[] nums = {2,7,9,3,1};
        System.out.println(rob(nums));
    }
}
