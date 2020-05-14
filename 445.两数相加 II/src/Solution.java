import org.junit.Test;
import sun.dc.pr.PRError;

import java.util.Stack;

public class Solution {

    /**
     * 将两个链表相加
     * 如果不能改变链表的结构，则应该考虑使用栈
     * 如果可以改变链表的结构，则应考虑使用反转链表（头插法，递归法）
     * @param l1 待加链表1
     * @param l2 待加链表2
     * @return 求和链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null)return l2;
        if (l2==null)return l1;
        //获取长度
        int length1=0;
        int length2=0;
        ListNode looper1=l1;
        ListNode looper2=l2;
        while (looper1!=null){
            length1++;
            looper1=looper1.next;
        }
        while (looper2!=null){
            length2++;
            looper2=looper2.next;
        }
        //头插法反转链表
        ListNode l1Head = null;
        ListNode l2Head = null;
        while (l1!=null){
            ListNode temp = new ListNode(l1.val);
            temp.next=l1Head;
            l1Head=temp;
            l1=l1.next;
        }
        while (l2!=null){
            ListNode temp = new ListNode(l2.val);
            temp.next=l2Head;
            l2Head=temp;
            l2=l2.next;
        }
        //补位
        if (length1>length2){
            int tot=length1-length2;
            ListNode last = l2Head;
            while (last.next!=null){
                last=last.next;
            }
            for (int i = 0; i < tot; i++) {
                last.next=new ListNode(0);
                last=last.next;
            }
        }
        if (length1<length2){
            int tot=length2-length1;
            ListNode last = l1Head;
            while (last.next!=null){
                last=last.next;
            }
            for (int i = 0; i < tot; i++) {
                last.next=new ListNode(0);
                last=last.next;
            }
        }
        //printListNode(l1Head);
        //printListNode(l2Head);

        //相加
        ListNode l1Ptr = l1Head;
        ListNode l2Ptr = l2Head;
        ListNode result = null;
        int carry=0;//进位
        while (l1Ptr!=null&&l2Ptr!=null){
            int sum=l1Ptr.val+l2Ptr.val+carry;
            if (sum>=10){
                carry=1;
                sum=sum-10;
            }
            else {
                carry=0;
            }
            ListNode node = new ListNode(sum);
            if (result==null) result=node;
            else {
                ListNode last = result;
                while (last.next!=null){
                    last=last.next;
                }
                last.next=node;
            }
            printListNode(result);
            l1Ptr=l1Ptr.next;
            l2Ptr=l2Ptr.next;
        }
        if (carry==1){
            ListNode last = result;
            while (last.next!=null){
                last=last.next;
            }
            last.next=new ListNode(1);
        }
        //printListNode(result);
        //反转链表
        ListNode resultRevers=null;
        while (result!=null){
            ListNode temp = new ListNode(result.val);
            temp.next=resultRevers;
            resultRevers=temp;
            result=result.next;
        }

        printListNode(resultRevers);

        return resultRevers;
    }

    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(7);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(4);//创建节点
        ListNode nodeA3 = new ListNode(3);//创建节点
        ListNode nodeB0 = new ListNode(5);
        ListNode nodeB1 = new ListNode(6);
        ListNode nodeB2 = new ListNode(4);
        ListNode nodeB3 = new ListNode(4);
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        nodeB0.next=nodeB1;
        nodeB1.next=nodeB2;
        nodeB2.next=nodeB3;
        //addTwoNumbers(nodeA0,nodeB0);

        ListNode nodeC0=null;
        ListNode nodeC1=null;
        addTwoNumbers(nodeC0,nodeC1);

        addTwoNumbers(nodeA0,nodeC0);




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