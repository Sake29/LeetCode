import org.junit.Test;

public class Solution {

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int count = 1;
            for (int j = i+1; j < T.length; j++) {
                if (T[i]<T[j]){
                    res[i] = count;
                    break;
                }
                count++;
            }
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
