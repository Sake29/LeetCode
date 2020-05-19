import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    /**
     * 开始想用栈来处理
     * 提交时解答错误
     * 重新审题后发现s的子树必须要和t的子树结构相同
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if (s==null) return false;
        LinkedList<TreeNode> s_stack = new LinkedList<>();
        s_stack.add(s);
        //遍历s中的每一个结点
        while (!s_stack.isEmpty()){
            TreeNode s_root = s_stack.pollLast();//s出栈
            if (isSametree(s_root,t)) return true;
            if (s_root.right!=null){
                s_stack.add(s_root.right);
            }
            if (s_root.left!=null){
                s_stack.add(s_root.left);
            }
        }
        return false;
    }

    public boolean isSubtree(TreeNode s, TreeNode t){
        if (s==null) return false;//递归结束的条件
        //对每一个根节点 判断是否和t是相同的树
        boolean root =isSametree(s,t);
        //对左边结点遍历
        boolean left = isSubtree(s.left,t);
        //对右边结点遍历
        boolean right = isSubtree(s.right,t);
        //只要有一个为真，结果就为真
        return root||left||right;
    }
    public boolean isSametree(TreeNode s, TreeNode t){
        if (s==null&&t==null) return true;
        if (s==null||t==null) return false;

        if (s.val != t.val){
            return false;
        }
        boolean left = isSametree(s.left,t.left);
        boolean right = isSametree(s.right,t.right);
        return left&&right;

    }
    @Test
    public void test(){

        Integer[] nodes_s1 = {3,4,5,1,2,null,null};
        Integer[] nodes_t1 = {4,1,2};
        Integer[] nodes_s2 = {1,2,3};
        Integer[] nodes_t2 = {1,2};
        TreeNode s1 = Utills.createTree(nodes_s1);
        TreeNode t1 = Utills.createTree(nodes_t1);
        TreeNode s2 = Utills.createTree(nodes_s2);
        TreeNode t2 = Utills.createTree(nodes_t2);
        System.out.println(isSubtree(s1,t1));
        System.out.println(isSubtree(s2,t2));
    }

    @Test
    public void test2(){
        Integer[] nodes1 = {3,4,5,1,2,null,null,0};
        Integer[] nodes2 = {3,4,5,1,2};
        TreeNode tree1 = Utills.createTree(nodes1);
        TreeNode tree2 = Utills.createTree(nodes1);
        TreeNode tree3 = Utills.createTree(nodes2);
        System.out.println(isSametree(tree1,tree2));
        System.out.println(isSametree(tree1,tree3));
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
     * 判断两棵树是否相同
     * @param tree1 树1
     * @param tree2 树2
     * @return 相同/不相同
     */
    public static boolean isSameTree(TreeNode tree1, TreeNode tree2){
        if (tree1==null||tree2==null) return false;
        if (tree1==null&&tree2==null) return true;
        if (tree1.val!=tree2.val) return false;
        boolean left = isSameTree(tree1.left,tree2.left);
        boolean right = isSameTree(tree1.right,tree2.right);
        return left&&right;
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
