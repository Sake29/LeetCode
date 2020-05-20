import org.junit.Test;

public class Solution {

    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)){
                count++;
            }
        }
        if (count==0||count==word.length()||(count==1&&Character.isUpperCase(word.charAt(0)))) return true;
        else return false;
    }

    @Test
    public void test(){
        String str1 = "USA";
        String str2 = "FlaG";
        String str3 = "leetcode";
        System.out.println(detectCapitalUse(str1));
        System.out.println(detectCapitalUse(str2));
        System.out.println(detectCapitalUse(str3));
    }

}
