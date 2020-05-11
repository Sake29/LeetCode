/**
 * 执行用时 :4 ms,在所有 Java 提交中击败了17.89%的用户
 * 内存消耗 :39 MB,在所有 Java 提交中击败了5.00%的用户
 *
 * 这道题写的太垃圾了，看了题解没明白，直接暴力求。
 */
import org.junit.Test;

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length==1){
            return strs[0];
        }

        int length = strs.length;
        String result = "";
        if (strs.length!=0){
             result = util(strs[0],strs[1]);
            for (int i = 2; i < length; i++) {
                result=util(result,strs[i]);
            }
        }

        return result;
    }

    /**
     * 获取两个String的公共前缀
     * @param str1
     * @param str2
     * @return 公共前缀
     */
    public String util(String str1,String str2){

        StringBuilder result = new StringBuilder();

        int length1 = str1.length();
        int length2 = str2.length();
        int index = Math.min(length1, length2);

        for (int i = 0; i < index; i++) {
            if (str1.charAt(i)==str2.charAt(i)){
                result.append(str1.charAt(i));
            }
            else break;
        }
        return result.toString();
    }
    @Test
    public void test(){
        //String[] str1 = new String[]{"flower","","flight"};
        //String[] str1 = new String[]{"flower","flat","flight"};
        //String[] str1 = new String[]{"flower"};
        //String[] str1 = new String[]{};
        String[] str1 = new String[]{"flower"};
        System.out.println(longestCommonPrefix(str1));
    }

    @Test
    public void test2(){
        String str1="";
        String str2="a";
        String result = util(str1,str2);
        System.out.println(result);
    }
}


