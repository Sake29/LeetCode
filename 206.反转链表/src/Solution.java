import org.junit.Test;

import java.util.Stack;

public class Solution {

    //遍历链表，将每个结点插在新链表的表头
    public ListNode reverseList(ListNode head){
        if (head==null||head.next==null) return head;
        ListNode reverseHead = null;
        while (head!=null){
            ListNode temp = new ListNode(head.val);
            temp.next=reverseHead;
            reverseHead = temp;
            head=head.next;
        }
        return reverseHead;
    }

    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(3);//创建节点
        ListNode nodeA3 = new ListNode(4);//创建节点
        ListNode nodeA4 = new ListNode(5);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        nodeA3.next=nodeA4;
        printListNode(nodeA0);//打印新链表的全部节点

        ListNode nodeRA2=reverseList(nodeA0);
        printListNode(nodeRA2);//打印新链表的全部节点




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
