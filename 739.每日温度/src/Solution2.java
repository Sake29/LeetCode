import org.junit.Test;

import java.util.Stack;

public class Solution2 {

    /**
     * 使用栈来解决
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty()&&T[i]>T[stack.peek()]){
                int pre = stack.pop();
                res[pre] = i - pre;
            }
            stack.push(i);
        }
        return res;
    }

    @Test
    public void test(){
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = dailyTemperatures(T);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
