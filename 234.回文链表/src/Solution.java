import org.junit.Test;
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head==null||head.next==null) return true;
        ListNode fast=head;
        ListNode slow=head;
        while (fast.next!=null&&fast.next.next!=null){
            //快指针的速度是慢指针的两倍
            // 当快指针到达终点时，慢指针到达中点
            fast=fast.next.next;
            slow=slow.next;
        }
        //printListNode(slow);
        //slow的next是剩下半条链表
        slow=slow.next;
        ListNode slowHead = null;
        //反转链表
        while (slow!=null){
            ListNode temp = new ListNode(slow.val);
            temp.next=slowHead;
            slowHead=temp;
            slow=slow.next;
        }
        printListNode(slowHead);
        while (slowHead!=null){
            if (slowHead.val!=head.val) return false;
            head=head.next;
            slowHead=slowHead.next;
        }
        return true;
    }


    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        nodeA0.next=nodeA1;

        ListNode nodeB0 = new ListNode(1);
        ListNode nodeB1 = new ListNode(2);
        ListNode nodeB2 = new ListNode(3);
        ListNode nodeB3 = new ListNode(2);
        ListNode nodeB4 = new ListNode(1);

        nodeB0.next=nodeB1;
        nodeB1.next=nodeB2;
        nodeB2.next=nodeB3;
        nodeB3.next=nodeB4;

        //printListNode(nodeA0);
        //printListNode(nodeB0);

        System.out.println(isPalindrome(nodeB0));


    }

    public ListNode reverse(ListNode head) {
        return null;
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
