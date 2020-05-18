import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {

    /**
     * 递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null)return false;
        sum-=root.val;
        //如果当前节点为子节点，比较sum是否为0
        //若为0，说明存在一条路径使得节点之和等于sum
        if (root.left==null&&root.right==null) return (sum==0);
        //不为子节点则递归
        boolean left = hasPathSum(root.left,sum);
        boolean right = hasPathSum(root.right,sum);
        return (left||right);
    }

    /**
     * 迭代，用LinkedList模拟stack
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum){
        if (root==null) return false;
        //LinkedList效率比stack高
        LinkedList<TreeNode> stack = new LinkedList<>();//用于遍历树
        LinkedList<Integer> sum_stack = new LinkedList<>();
        stack.add(root);
        sum_stack.add(sum-root.val);
        while (!stack.isEmpty()){
            TreeNode rnode = stack.pollLast();//出栈
            int curr_sum = sum_stack.pollLast();
            System.out.println("当前节点："+rnode.val);
            System.out.println("当前和："+curr_sum);
            if (rnode.left==null&&rnode.right==null&&curr_sum==0) return true;
            if (rnode.right!=null) {
                stack.add(rnode.right);
                sum_stack.add(curr_sum-rnode.right.val);
            }
            if (rnode.left!=null) {
                stack.add(rnode.left);
                sum_stack.add(curr_sum-rnode.left.val);
            }
        }
        return false;
    }
    @Test
    public void test(){
        Integer[] nodes = {5,4,8,11,null,13,4,7,2,null,null,null,1};
        TreeNode tree = Utills.createTree(nodes);
        Utills.printTree(tree);
        System.out.println(hasPathSum(tree,22));
        hasPathSum2(tree,22);
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
