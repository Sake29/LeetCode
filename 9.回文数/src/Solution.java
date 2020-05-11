public class Solution {
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        String str = Integer.toString(x);
        int length = str.length();
        for (int i =0,j=length-1;i<length/2;i++,j--){
            if (str.charAt(i)!=str.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
