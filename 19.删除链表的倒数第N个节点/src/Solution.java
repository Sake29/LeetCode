import org.junit.Test;

public class Solution {
    //两次遍历法
    public ListNode removeNthFromEnd1(ListNode head, int n){
        if (head.next==null) return null;
        int length=0;//链表得长度
        int count=0;//计数器，用于删除链表的结点
        ListNode curr=head;//用于遍历链表
        while (curr!=null){
            length++;
            curr=curr.next;
        }
        int index=length-n;//索引，用于获取待删除的结点的索引
        curr=head;
        //如果索引为0，即删去链表得第一个结点
        if (index==0) {
            head=head.next;
        }
        //如果索引不为0，则遍历链表，找到要删除得结点
        else {
            while (curr!=null){
                if (count!=index-1){
                    curr=curr.next;
                }
                else{
                    curr.next=curr.next.next;
                    break;
                }
                count++;
            }
        }
        return head;
    }

/*    public ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode dummp=new ListNode(-1);//哑结点，避免删除头结点时,快指针报错
        dummp.next=head;//哑结点位于头结点之前
        ListNode fast=dummp;
        ListNode slow=dummp;
        for (int i = 0; i <n+1 ; i++) {
            fast=fast.next;
        }
        while (fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;


        return dummp.next;
    }*/
    //双指针法
    public ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode fast=head;
        ListNode slow=head;
        for (int i = 0; i <n ; i++) {
            fast=fast.next;
            if (fast==null) return head.next;
        }
        while (fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;


        return head;
    }

    @Test
    public void test(){
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(3);//创建节点
        ListNode nodeA3 = new ListNode(4);//创建节点
        ListNode nodeA4 = new ListNode(5);//创建节点
        ListNode nodeB0 = new ListNode(1);//创建节点
        ListNode nodeB1 = new ListNode(2);//创建节点
        ListNode nodeC0 = new ListNode(1);//创建节点

        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        nodeA3.next=nodeA4;
        nodeB0.next=nodeB1;

        ListNode node1 = removeNthFromEnd1(nodeA0, 5);
        ListNode node2 = removeNthFromEnd1(nodeB0, 2);
        ListNode node3 = removeNthFromEnd1(nodeC0, 1);
        ListNode node4 = removeNthFromEnd2(nodeA0, 5);
        ListNode node5 = removeNthFromEnd2(nodeB0, 2);
        ListNode node6 = removeNthFromEnd2(nodeC0, 1);

        System.out.println("两次遍历：");
        printListNode(node1);
        printListNode(node2);
        printListNode(node3);
        System.out.println("双指针：");
        printListNode(node4);
        printListNode(node5);
        printListNode(node6);
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

