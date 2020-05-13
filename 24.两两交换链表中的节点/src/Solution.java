import org.junit.Test;
public class Solution {

    public ListNode swapPairs(ListNode head){
        ListNode node = new ListNode(-1);//哑结点
        node.next = head;
        ListNode pre = node;
        while (head!=null&&head.next!=null){
            ListNode l1 = head;
            ListNode l2 = head.next;

            //交换
            pre.next=l2;//pre删去l1结点
            l1.next=l2.next;//l1删去l2结点
            l2.next=l1;

            //移动头结点
            pre=l1;
            head=l1.next;
        }

        return node.next;
    }

    @Test
    public void test(){
        ListNode nodeA0 = new ListNode(1);//创建节点
        ListNode nodeA1 = new ListNode(2);//创建节点
        ListNode nodeA2 = new ListNode(3);//创建节点
        ListNode nodeA3 = new ListNode(4);//创建节点
        nodeA0.next=nodeA1;
        nodeA1.next=nodeA2;
        nodeA2.next=nodeA3;
        printListNode(nodeA0);//打印新链表的全部节点
        ListNode result1 = swapPairs(nodeA0);
        printListNode(result1);
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

