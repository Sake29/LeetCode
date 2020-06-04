import org.junit.Test;

import java.util.Stack;

public class MinStack2 {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()||minStack.peek()>=x){
            minStack.push(x);
        }
    }

    public void pop() {
        int num = stack.pop();//自动装箱
        if (num == minStack.peek()){
            minStack.pop();
        }
    }

    public int top() {
       return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    private void printStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            System.out.println("EMPTY!");
        }
        else {
            while (!stack.isEmpty()){
                Integer nums = stack.pop();
                System.out.print("|");
                System.out.print(" ");
                System.out.print(" ");
                System.out.print(nums);
                System.out.print(" ");
                System.out.print(" ");
                System.out.print(" ");
                System.out.print("|");
                System.out.println();
            }
            System.out.println("_______");
        }
    }

    @Test
    public void test(){
        push(Integer.MAX_VALUE);
        System.out.println("当前最小："+getMin());
        push(Integer.MIN_VALUE);
        System.out.println("当前最小："+getMin());
        pop();
        push(3);
        System.out.println("当前最小："+getMin());
        pop();
        System.out.println("当前最小："+getMin());
        pop();

    }

}
