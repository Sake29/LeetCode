import org.junit.Test;

import java.util.Stack;

public class Solution2 {

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        for (int i = 0; i < 2*nums.length-1; i++) {
            int index = i % nums.length;//通过取余数，可以对nums循环遍历
            System.out.println("当前元素："+nums[index]);
            while (!stack.isEmpty()&&nums[stack.peek()]<nums[index]){
                res[stack.pop()] = nums[index];//当前元素是栈顶元素的第一个最大元素
            }
            stack.push(index);
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
