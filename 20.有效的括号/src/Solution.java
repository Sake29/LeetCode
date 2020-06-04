import org.junit.Test;

import java.util.Stack;

public class Solution {

    public boolean isValid(String s) {
        if (s.length()%2!=0) return false;
        Stack<Character> bracket = new Stack<>();//用于存放括号的栈
        for (char ch : s.toCharArray()) {
            if (ch=='('){
                bracket.push(')');
            }
            else if (ch=='['){
                bracket.push(']');
            }
            else if (ch=='{'){
                bracket.push('}');
            }
            else if (bracket.isEmpty()||bracket.pop()!=ch){
                return false;
            }
        }
        boolean b = bracket.isEmpty();
        return bracket.isEmpty();
    }

    @Test
    public void test(){
        String str1 = "{([])}";
        String str2 = "])";
        String str3 = "{([)}";
        isValid(str1);
        System.out.println("测试结果："+isValid(str1)+"预期结果：true");
        System.out.println("测试结果："+isValid(str2)+"预期结果：false");
        System.out.println("测试结果："+isValid(str3)+"预期结果：false");
    }
}
