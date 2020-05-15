import org.junit.Test;
import sun.dc.pr.PRError;

import java.util.Stack;

public class Solution {

    //遍历链表 将偶数位的结点删除后另存为新链表
    //将新链表接在原链表的尾部
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even!=null&&even.next!=null){
            odd.next=odd.next.next;//删去偶数结点
            odd=odd.next;
            even.next=even.next.next;//删去奇数结点
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }

    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(2);//创建节点
        ListNode nodeA1 = new ListNode(1);//创建节点
        ListNode nodeA2 = new ListNode(3);//创建节点
        ListNode nodeA3 = new ListNode(5);//创建节点
        ListNode nodeA4 = new ListNode(6);
        ListNode nodeA5 = new ListNode(4);
        ListNode nodeA6 = new ListNode(7);
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        nodeA3.next=nodeA4;
        nodeA4.next=nodeA5;
        nodeA5.next=nodeA6;

        printListNode(nodeA0);
        printListNode(oddEvenList(nodeA0));



    }
    //打印链表
    public void printListNode(ListNode head) {
        while(head!=null) {
            System.out.print(head.val);
            if (head.next!=null){
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}