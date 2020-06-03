import org.junit.Test;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> stack;
    private Stack<Integer> queueStack;

    //将栈反转，转换成队列
    private void stack2Queue(){
        //当队列栈为空的时候，才将栈逆序存到队列栈中
        //否则直接对队列栈进行处理
        if (queueStack.isEmpty()){
            while (!stack.isEmpty()){
                queueStack.push(stack.pop());
            }
        }
    }

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        queueStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {

        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        //如果队列栈为空，则将栈转化为队列栈
        //再对队列栈进行出栈操作
        stack2Queue();
        return queueStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        stack2Queue();
        return queueStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        //当且仅当两个栈都为空的时候返回true
        return stack.isEmpty()&&queueStack.isEmpty();
    }

    @Test
    public void test(){
        push(1);
        push(2);
        push(3);
        System.out.println(empty());
        System.out.println(peek());
        System.out.println(pop());
        System.out.println(peek());
    }

}
