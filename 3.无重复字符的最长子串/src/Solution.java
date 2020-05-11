import org.junit.Test;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int startIndex = 0;
        int maxLength = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++){
            for (int innerIndex = startIndex; innerIndex<i;innerIndex++) {
                if(chars[innerIndex]==chars[i]){
                    maxLength = Math.max(i-startIndex,maxLength);
                    startIndex=innerIndex+1;
                    break;
                }
            }
        }
        return Math.max(maxLength,chars.length-startIndex);
    }

    @Test
    public void test(){
        String s = " ";
        System.out.println(s.length());
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
