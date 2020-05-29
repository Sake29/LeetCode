import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> nums = linkedListToArray(head);
        TreeNode tree = createTree(nums, 0, nums.size() - 1);
        return tree;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        if (head==null) return null;
        ListNode mid = findMiddleNode(head);
        TreeNode root = new TreeNode(mid.val);
        //如果链表只有一个元素，就返回一个树节点即可
        if (head == mid){
            return root;
        }
        root.left = sortedListToBST2(head);
        root.right = sortedListToBST2(mid.next);
        return root;
    }

    private ArrayList<Integer> linkedListToArray(ListNode head){
        ArrayList<Integer> res = new ArrayList<>();
        ListNode temp = head;
        while (temp!=null){
            res.add(temp.val);
            temp = temp.next;
        }
        return res;
    }

    private TreeNode createTree(ArrayList<Integer> nums,int left,int right){
        if (left>right) return null;
        int p = left+(right-left)/2;
        TreeNode root = new TreeNode(nums.get(p));
        root.left = createTree(nums,left,p-1);
        root.right = createTree(nums,p+1,right);
        return root;
    }



    /**
     * 快慢指针找中点
     * @param head
     * @return
     */
    private ListNode findMiddleNode(ListNode head){
        ListNode fast = head;
        ListNode slow =head;
        ListNode temp = null;
        while (fast!=null&&fast.next!=null){
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (temp!=null) temp.next = null;
        return slow;
    }
    @Test
    public void test(){
        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //Utills.printList(node1);
        TreeNode tree = Utills.createTree(new Integer[]{0, -3, 9, -10, null, 5});
        Utills.printTree(tree);
        TreeNode root = sortedListToBST2(node1);
        Utills.printTree(root);
    }

    @Test
    public void test2(){
        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode middleNode = findMiddleNode(node1);
        Utills.printList(middleNode);
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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Utills{
    /**
     * 获取树的深度
     * @param root 树的根节点
     * @return 树的深度
     */
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    /**
     * 打印树
     * @param root 树的根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null){
            System.out.println("EMPTY!");
            return;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 根据数组生成树
     * @param nums 输入的数组
     * @return 树的根节点
     */
    public static TreeNode createTree(Integer[] nums){
        if (nums.length == 0) return new TreeNode(0);
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        // 创建一个根节点
        TreeNode root = new TreeNode(nums[0]);
        nodeQueue.offer(root);
        TreeNode cur;
        // 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素的数量
        int restLength = nums.length - 1;

        while(restLength > 0) {
            // 只有最后一行可以不满，其余行必须是满的
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == nums.length) return root;
                cur = nodeQueue.poll();
                if (nums[i] != null) {
                    cur.left = new TreeNode(nums[i]);
                    nodeQueue.offer(cur.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == nums.length) return root;
                if (nums[i + 1] != null) {
                    cur.right = new TreeNode(nums[i + 1]);
                    nodeQueue.offer(cur.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }

        return root;
    }

    /**
     * 将所有需要显示的元素储存到了二维数组中
     * @param currNode
     * @param rowIndex
     * @param columnIndex
     * @param res
     * @param treeDepth
     */
    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static void printList(ListNode listNode){
        ListNode node = listNode;
        while (node!=null){
            System.out.print(node.val);
            if (node.next!=null){
                System.out.print("->");
            }
            node = node.next;
        }
        System.out.println();
    }
}