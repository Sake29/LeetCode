public class LinkList {
    public ListNode head;//头节点
    public ListNode current;//当前节点

    public void add(int data){
        if (head == null) {
            head = new ListNode(data);
            current = head;
        }
        else {
            current.next = new ListNode(data);
            current = current.next;
        }
    }

    public void print(ListNode node){
        String next="-->";
        if (node == null){
            return;
        }
        else {
            if (node.next==null){
                System.out.println(node.val);
            }
            else{
                System.out.print(node.val+next);
            }
        }
    }
}
