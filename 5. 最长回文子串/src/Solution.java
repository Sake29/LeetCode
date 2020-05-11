import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public String longestPalindrome(String s) {
        int count = 0;
        int midIndex = 0;
        if (s==null||s.length()==1)
            return s;
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length()-1; i++) {
            if(chars[i-1]==chars[i+1]){
                //System.out.println(chars[i]);
                count=1;
                for (int j = 2; j < s.length(); j++) {
                    if(i-j <0 || i+j>s.length()-1){
                        break;
                    }
                    else{
                        if (chars[i-j]==chars[i+j]){
                            count++;
                        }
                        else {count=0;break;}
                    }
                }

            }
            else{
                count=0;
                continue;
                }
            midIndex = i;
        }
        //count+=1;
        if (count==0){
            return "";
        }
        else {
            return new String(chars,midIndex-count-1,count*2+1);
        }
    }
    public String longest(String s){
        if (s==null||s.length()==1){return s;}
        int count = 0;
        int midIndex= 0;
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length-1; i++) {
            count = 0;
            if (chars[i-1]!=chars[i+1]){
                continue;
            }
            else {
                midIndex = i;
                count=1;
                left=midIndex;
                right=midIndex;
                while (chars[--left]==chars[++right]&& left > 0 && right < chars.length-1){
                    count++;
                }
                break;
            }
        }
        System.out.println("count:"+count);
        //return "";
        if (count==0){
            return "";
        }
        else {
            return new String(chars,midIndex-count,count*2+1);
        }
    }

    public String lo(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int midIndex = 0;
        int count = 0;
        int left = 0;
        int right = 0;
        //改造字符串
        String t = "#";
        for (int i = 0; i < s.length(); i++) {
            t += s.charAt(i);
            t += "#";
        }
        System.out.println(t);
        int[] r = new int[t.length()];
        for (int i = 1; i < t.length()-1; i++) {
            //System.out.println();
            if (t.charAt(i - 1) != t.charAt(i + 1)) {
                r[i] = 0;
                continue;
            } else {
                midIndex = i;
                count = 1;
                left = midIndex-1;
                right = midIndex+1;
                while (t.charAt(left--) == t.charAt(right++) && left >= 0 && right < t.length()) {
                    //System.out.println("mid"+midIndex+",l:"+left+","+"r"+right);
                    //System.out.println("left:"+t.charAt(left)+","+"right"+t.charAt(right));
                    count++;
                }
                r[i] = count;
            }
        }
        int max =0;//最大回文半径
        int index =0;//最大回文的中心索引
        for (int i = 0; i < r.length; i++) {
            //System.out.print(r[i]+",");
            if(r[i]>=max){
                index=i;
                max=r[i];
            }
        }
        System.out.println("max:"+max+",index:"+index+"，元素："+t.charAt(index));
        String res = t.substring(index-max+1,index+max);
        res=res.replace("#","");
        return res;
    }

    @Test
    public void test(){
        String s = "abb";
        String s3 ="ccd";
        String s2="babad";
        String s1 = lo(s3);
        System.out.println(s1);
    }

}
