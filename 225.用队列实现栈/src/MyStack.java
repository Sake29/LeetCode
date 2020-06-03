import org.junit.Test;

import java.util.LinkedList;

public class MyStack {

    private LinkedList<Integer> queue1;
    private LinkedList<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (queue1.isEmpty()) queue2.add(x);
        if (queue2.isEmpty()) queue1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue1.isEmpty()){
            while (queue2.size()>1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }
        else {
            while (queue1.size()>1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        int top = 0;
        if (queue1.isEmpty()){
            while (queue2.size()>1){
                queue1.add(queue2.poll());
            }
            top = queue2.poll();
            queue1.add(top);
        }
        else {
            while (queue1.size()>1){
                queue2.add(queue1.poll());
            }
            top = queue1.poll();
            queue2.add(top);

        }
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty()&&queue2.isEmpty();
    }

    @Test
    public void test(){
        push(1);
        push(2);
        push(3);
        System.out.println("栈是否为空:"+empty());
        System.out.println("栈顶元素："+top());
        System.out.println("栈顶元素出栈");
        pop();
        System.out.println("栈是否为空:"+empty());
        System.out.println("栈顶元素："+top());
        System.out.println("栈顶元素出栈");
        pop();
        System.out.println("栈是否为空:"+empty());
        System.out.println("栈顶元素："+top());
        System.out.println("栈顶元素出栈");
        pop();
        System.out.println("是否为空:"+empty());
    }

}
