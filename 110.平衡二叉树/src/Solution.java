import org.junit.Test;
import java.util.*;

public class Solution {
    /**
     * 层次遍历获取每个节点的值,对于每个结点获取左右两个子节点的深度
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        //层次遍历获取每个根节点的值
        List<TreeNode> allNodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
            allNodes.add(head);
            //获取左右两个子节点的深度
            int leftDepth = getDepth(head.left);
            int rightDepth = getDepth(head.right);
            if (Math.abs(leftDepth-rightDepth)>1) return false;
        }
        return true;
    }

    /**
     * 计算每棵树的左右子树高度（两层递归）
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root){
        if (root==null) return true;
        //递归求解每个结点的左右子树的深度
        if ((Math.abs(getDepth(root.left) - getDepth(root.right)) < 2)){
            //递归求解每个结点的左右子树是否为平衡二叉树
            //如果左子树为平衡二叉树且右子树为平衡二叉树，返回true
            return isBalanced2(root.left)&&isBalanced2(root.right);
        }
        else return false;
    }


    public int getDepth(TreeNode root){
        if (root==null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }

    @Test
    public void test(){
        Integer[] nodes1 = {1,null,2,null,3};
        Integer[] nodes2 = {1,2,2,3,3,null,null,4,4};
        TreeNode tree1 = Utills.createTree(nodes1);
        TreeNode tree2 = Utills.createTree(nodes2);
        System.out.println("测试树1：");
        Utills.printTree(tree1);
        System.out.println("测试树2：");
        Utills.printTree(tree2);

        System.out.println(isBalanced1(tree1));
        System.out.println(isBalanced1(tree2));
        System.out.println(isBalanced2(tree2));
        System.out.println(isBalanced2(tree2));

    }

    @Test
    public void test2(){
        Integer[] nodes1 = {3,9,20,null,null,15,7};
        Integer[] nodes2 = {1,2,2,3,3,null,null,4,4};
        TreeNode tree1 = Utills.createTree(nodes1);
        TreeNode tree2 = Utills.createTree(nodes2);
        Utills.levelOder(tree1);
        Utills.levelOder(tree2);
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
     * 树的层次遍历
     * @param root 树的根节点
     * @return 按照层次遍历存储树的所有结点的列表
     */
    public static List<TreeNode> levelOder(TreeNode root){
        List<TreeNode> allNodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode head = queue.poll();
            if (head.left!=null){
                queue.offer(head.left);
            }
            if (head.right!=null){
                queue.offer(head.right);
            }
            allNodes.add(head);
            System.out.println(head.val);
        }

        return allNodes;
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

