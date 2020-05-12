import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {

    //使用ArrayList
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        while(l1!=null){
            arrayList1.add(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            arrayList2.add(l2.val);
            l2=l2.next;
        }
        arrayList1.addAll(arrayList2);
        Collections.sort(arrayList1);
        ListNode head=new ListNode(arrayList1.get(0));

        //生成链表
        for (int i = 1; i < arrayList1.size(); i++) {
            ListNode last = head;
            while (last.next!=null){
                last=last.next;
            }
            last.next=new ListNode(arrayList1.get(i));
        }

        return head;
    }

    //使用递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1.val<l2.val){
            l1.next=mergeTwoLists2(l1.next,l2);
            return l1;
        }
        else{
            l2.next=mergeTwoLists2(l1,l2.next);
            return l2;
        }


    }


    @Test
    public void test(){
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(4);//创建节点
        ListNode nodeB0 = new ListNode(1);//创建节点
        ListNode nodeB1 = new ListNode(3);//创建节点
        ListNode nodeB2 = new ListNode(4);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeB0.next=nodeB1;
        nodeB1.next=nodeB2;
        printListNode(nodeA0);//打印新链表的全部节点
        printListNode(nodeB0);//打印新链表的全部节点
        ListNode result1 = mergeTwoLists1(nodeA0, nodeB0);//ArrayList
        ListNode result2 = mergeTwoLists2(nodeA0, nodeB0);//递归
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
