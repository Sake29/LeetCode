import org.junit.Test;

public class Solution {

    /*
        终止条件：没有重复的结点，即 node.val!=node.next.val
        返回值：已经去重的链表的头结点
        本级递归应该做什么：比较node.val和node.next.val的值，如果值相等，则递归。不相等，则终止。
     */
    public ListNode deleteDuplicates1(ListNode head){
        //head为空或只有一个结点，返回head
        if(head==null||head.next==null)return head;
        //如果head的值和head的下一个值相等，说明重复
        if (head.val==head.next.val){
            /*
                递归调用，deleteDuplicates(head.next)返回的是
                以head.next为头结点的链表去重之后的结果。
            */
            head=deleteDuplicates1(head.next);
        }
        //不相等，说明当前head结点没有重复了，开始处理下一个结点

        else {
            head.next=deleteDuplicates1(head.next);
        }
        return head;
    }

    //遍历法
    public ListNode deleteDuplicates2(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode curr = head;
        while (curr!=null&&curr.next!=null){
            if (curr.val==curr.next.val){
                curr.next=curr.next.next;
            }
            else {curr=curr.next;}
        }
        return head;
    }

    @Test
    public void test(){
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(1);//创建节点
        ListNode nodeA2 = new ListNode(1);//创建节点
        //ListNode nodeA3 = new ListNode(3);//创建节点
        //ListNode nodeA4 = new ListNode(3);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        //nodeA2.next=nodeA3;
        //nodeA3.next=nodeA4;
        printListNode(nodeA0);
        ListNode result1 = deleteDuplicates1(nodeA0);
        ListNode result2 = deleteDuplicates2(nodeA0);
        printListNode(result1);
        printListNode(result2);
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