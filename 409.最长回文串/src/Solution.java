import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public int longestPalindrome(String s) {
        int res = 0;
        int[] c = new int[26*2];//大小写分别存储
        for (char ch:s.toCharArray()) {
            if (ch >= 'a'){//如果是小写
                c[ch - 'a']++;
            }
            else {//小写的ASCII码比大写的大，如果小于a则为大写
                c[ch - 'A' + 26]++;
            }
        }
        for(int num : c){
            res += num%2;
        }
        return res==0?s.length():s.length()-res+1;
    }

    @Test
    public void test(){
        String s = "abccccdd";
        int res = longestPalindrome(s);
        System.out.println(res);
    }
}
