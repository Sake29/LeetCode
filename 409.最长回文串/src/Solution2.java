import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution2 {
    public int longestPalindrome(String s) {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
            else {
                map.put(s.charAt(i),1);
            }
        }
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> entry = iterator.next();
                res += entry.getValue()%2;
        }
        return res == 0?s.length():s.length()-res+1;
    }

    @Test
    public void test(){
        String s = "bananas";
        int res = longestPalindrome(s);
        System.out.println(res);
    }
}
