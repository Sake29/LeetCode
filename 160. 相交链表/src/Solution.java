import org.junit.Test;

public class Solution {
    /*
     A和B两个链表长度可能不同，
     但是A+B和B+A的长度是相同的，
     所以遍历A+B和遍历B+A一定是同时结束。
     如果A,B相交的话A和B有一段尾巴是相同的，
     所以两个遍历的指针一定会同时到达交点
     如果A,B不相交的话两个指针就会同时到达A+B（B+A）的尾节点
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA==null||headB==null)return null;
        //用于遍历链表A的指针
        ListNode pA=headA;
        //用于遍历链表B的指针
        ListNode pB=headB;
        while (pA!=pB){
            //A指针到链表A的尾部后，跳到链表B的头部
            pA=pA==null?headB:pA.next;
            //B指针到链表B的尾部后，跳到链表A的头部
            pB=pB==null?headA:pB.next;
            //pA和pB会遍历完两个链表后同时到达链表的尾部，此使pA=pB=null
            //跳出循环
        }
        return pA;
    }

    //暴力法
    //每遍历a的一个节点，就遍历一遍b，逐一对比。
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        if (headA==null||headB==null)return null;
        ListNode pA = headA;

        while (pA!=null){
            ListNode pB = headB;
            while (pB!=null){
                if (pA==pB){
                    return pA;
                }
                pB=pB.next;
            }
            pA=pA.next;
        }
        return pA;
    }

    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(4);//创建节点
        ListNode nodeA1 = new ListNode(1);//创建节点
        ListNode nodeA2 = new ListNode(8);//创建节点
        ListNode nodeA3 = new ListNode(4);//创建节点
        ListNode nodeA4 = new ListNode(5);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        nodeA3.next=nodeA4;
        printListNode(nodeA0);//打印新链表的全部节点

        ListNode nodeB0 = new ListNode(5);//创建节点
        ListNode nodeB1 = new ListNode(0);//创建节点
        ListNode nodeB2 = new ListNode(1);//创建节点
        nodeB0.next=nodeB1;
        nodeB1.next=nodeB2;
        nodeB2.next=nodeA2;
        printListNode(nodeB0);//打印新链表的全部节点

        ListNode node1 = getIntersectionNode1(nodeA0, nodeB0);
        System.out.println(node1.val);
        ListNode node2 = getIntersectionNode2(nodeA0,nodeB0);
        System.out.println(node2.val);

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