import org.junit.Test;

public class Solution {
    public int getValue(char ch){
        switch (ch){
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
            default:return 0;
        }
    }

    public int romanToInt(String s){
        int rtn = 0;
        //算到倒数第二位
        for (int i = 0; i <s.length()-1 ; i++) {
            int num = getValue(s.charAt(i));
            int nextNum = getValue(s.charAt(i+1));
            if (num<nextNum){
                rtn-=num;
            }
            else {
                rtn+=num;
            }
        }
        //加上最后一位
        rtn+=getValue(s.charAt(s.length()-1));

        return rtn;
    }

    @Test
    public void test1(){
        System.out.println(romanToInt("XXIV"));;//24
        System.out.println(romanToInt("XXVII"));;//27
        System.out.println(romanToInt("X"));
    }
}
