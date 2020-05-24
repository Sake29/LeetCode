import org.junit.Test;

import java.util.*;

public class Solution {

    /**
     * 递归的方式进行后序遍历
     * 和前序遍历类似
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
        }

        return res;
    }
    private List<Integer> res = new LinkedList<>();

    /**
     * 非递归的方式进行后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> res = new LinkedList<>();//用于存放遍历的结果
        if (root == null) return res;
        LinkedList<TreeNode> stack = new LinkedList<>();//用Linkedlist模拟栈
        TreeNode pre = null;//用于记录上一次出栈的节点
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode curr = stack.peek();//检索但不出栈
            if ((curr.left==null&&curr.right==null)||(pre!=null&&(pre==curr.left||pre==curr.right))){
                pre = curr;
                res.add(curr.val);
                stack.poll();//出栈
            }
            else{
                if (curr.right!=null){
                    stack.push(curr.right);
                }
                if (curr.left!=null) {
                    stack.push(curr.left);
                }
            }
        }
        return res;
    }


    @Test
    public void test(){
        TreeNode tree = Utills.createTree(new Integer[]{1, null, 2, 3});
        Utills.printTree(tree);
        List<Integer> list = postorderTraversal1(tree);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void test2(){
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            stack.add(i);
            System.out.print(i+"进栈！当前栈:"+"[");
            for (Integer integer : stack) {
                System.out.print(integer+",");
            }
            System.out.println("]");
        }
        for (int i = 0; i < 5; i++) {
            stack.pollLast();
            System.out.print("出栈！当前栈:"+"[");
            for (Integer integer : stack) {
                System.out.print(integer+",");
            }
            System.out.println("]");
        }
        /*stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.print("[");
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println("]");
        stack.addFirst(4);
        stack.addFirst(5);
        stack.addFirst(6);
        System.out.print("[");
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println("]");
        stack.pollLast();
        System.out.print("[");
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println("]");
        stack.pollLast();
        System.out.print("[");
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println("]");
        stack.poll();//删去头部
        stack.poll();
        System.out.print("[");
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println("]");*/
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

}
