import org.junit.Test;

public class Solution {
    public int reverse(int x) {
        long rs = 0;
        while(x != 0){
            //x%10取到最后一位
            //rs*10将首位进位
            rs = rs*10+x%10;
            System.out.println(rs);
            //x/=10去除最后一位
            x /= 10;
        }
        //防止溢出
        return (rs<Integer.MIN_VALUE || rs>Integer.MAX_VALUE) ? 0:(int)rs;
    }

    @Test
    public void test(){
        reverse(1321);
    }
}
