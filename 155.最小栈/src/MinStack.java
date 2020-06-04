import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()||minStack.peek()>=x){
            minStack.push(x);
        }
        else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
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
        System.out.println(Integer.MIN_VALUE-1);
        push(1);
        System.out.println("当前最小："+getMin());
        push(2);
        System.out.println("当前最小："+getMin());
        push(-3);
        System.out.println("当前最小："+getMin());
/*        pop();
        System.out.println("当前最小："+getMin());
        pop();*/

    }

}
