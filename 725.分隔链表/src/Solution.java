import org.junit.Test;
import sun.dc.pr.PRError;

import java.util.Stack;

public class Solution {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode looper = root;
        int length=0;
        while (looper!=null){
            length++;
            looper=looper.next;
        }
        int aveLength=length/k;//每个子链表的平均长度
        int remainder=length%k;//余数
        ListNode[] result = new ListNode[k];
        int[] listLength = new int[k];//子链表的长度
        for (int i = 0; i < k ; i++) {
            listLength[i]=aveLength;
            if (remainder>0) listLength[i]++;
            remainder--;
        }
        ListNode head = root;
        for (int i = 0; i < k; i++) {
            int N = listLength[i];//当前链表的长度
            int count = 0;//计数器
            ListNode temp = null;//临时链表
            while (head!=null){
                //尾插法生成链表
                if (temp==null) temp=new ListNode(head.val);
                else {
                    ListNode last = temp;
                    while (last.next!=null){last=last.next;}
                    last.next=new ListNode(head.val);
                }
                count=count+1;
                if (count!=N){
                    head=head.next;
                }
                else {
                    count=0;
                    head=head.next;
                    break;
                }
            }
            result[i]=temp;
        }
        return result;
    }



    @Test
    public void test() {
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(3);//创建节点
        ListNode nodeA3 = new ListNode(4);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;


        ListNode[] result = splitListToParts(nodeA0, 5);
        printListNode(result);


    }
    //打印链表
    public void printListNode(ListNode head) {
        while(head!=null) {
            System.out.print(head.val);
            if (head.next!=null){
                System.out.print(",");
            }
            head = head.next;
        }
        //System.out.println();
    }

    //打印链表
    public void print(ListNode head) {
        while(head!=null) {
            System.out.print(head.val);
            if (head.next!=null){
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println();
    }
    //打印链表数组
    public void printListNode(ListNode[] head) {
        System.out.print("[");
        for (int i = 0; i < head.length; i++) {
            if (head[i]==null) System.out.print("null");
            else {
                System.out.print("[");
                printListNode(head[i]);
                System.out.print("]");
            }
            if (i!=head.length-1) System.out.print(",");
        }
        System.out.println("]");

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