/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        ListNode result=new ListNode(0);
        int addNum=0;//进位
        while(l1!=null && l2!=null && addNum==0){
            if (l1.next == null){
                l1.next = new ListNode(0);
            }
            if (l2.next == null) {
                l2.next = new ListNode(0);
            }
            int sum = l1.val+l2.val+addNum;
            //System.out.println(sum);
            if (sum<10){
                result.val=sum;
            }
            else {
                result.val = sum%10;
                addNum = sum/10;
            }
            l1=l1.next;
            l2=l2.next;
        }
        return result;

         */
        int sum = 0;
        int addSum = 0;
        while (l1 != null || l2 !=null){
            if(l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            ListNode result = new ListNode(sum % 10);
            addSum/=10;
            result.next = new ListNode(addSum);
            result=result.next;
        }

       return null;
    }
}
