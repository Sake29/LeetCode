import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * BFS找每一层的结点
     * 判断该层是否为回文
     * 结果超时！中间遍历太多了。应该适当修改
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root==null) return true;
        int count = 2;
        Queue<TreeNode> rootQueue = new LinkedList<>();//用于存放根节点
        Queue<TreeNode> sonQueue = new LinkedList<>();//用于存放子节点
        LinkedList<Integer> num = new LinkedList<>();
        rootQueue.add(root);
        while (!rootQueue.isEmpty()){
            TreeNode rootNode = rootQueue.poll();
            if (rootNode.left==null){
                sonQueue.add(new TreeNode(-999));
            }
            if (rootNode.left!=null){
                sonQueue.add(rootNode.left);
            }
            if (rootNode.right==null){
                sonQueue.add(new TreeNode(-999));
            }
            if (rootNode.right!=null){
                sonQueue.add(rootNode.right);
            }
            if (rootQueue.isEmpty()){
                System.out.println("第"+count+"层");
                count++;
                while (!sonQueue.isEmpty()){
                    TreeNode sonNode = sonQueue.poll();
                    num.add(sonNode.val);
                    //System.out.println(sonNode.val);
                    rootQueue.add(sonNode);
                }
                boolean tag = false;
                for (Integer integer : num) {
                    if (integer!=-999){
                        tag=true;
                    }
                }
                if (!tag) break;
                if (isPalindrome(num)){
                    num.clear();
                    int temp = count-1;
                    System.out.println("第"+temp+"层是回文");
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 根左右遍历（前序遍历） == 根右左遍历
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root){
        return isMirror(root,root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2){
        if (t1==null&&t2==null) return true;
        if (t1==null||t2==null) return false;
        System.out.println("t1:"+t1.val+"，t2:"+t2.val);
        boolean left = isMirror(t1.left,t2.right);
        boolean right = isMirror(t2.left,t1.right);
        return t1.val==t2.val&&left&&right;

    }

    private boolean isPalindrome(LinkedList<Integer> nums){
        if (nums==null) return true;
        LinkedList<Integer> reverseNums = new LinkedList<>();
        for (Integer num : nums){
            System.out.println(num);
            reverseNums.addFirst(num);
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!nums.get(i).equals(reverseNums.get(i)))
            {
                return false;
            }

        }
        return true;
    }

    @Test
    public void test(){
        Integer[] nodes1 = {1,2,2,3,4,4,3};
        Integer[] nodes2 = {1,2,2,null,4,null,4};
        TreeNode tree1 = Utills.createTree(nodes1);
        TreeNode tree2 = Utills.createTree(nodes2);
        Utills.printTree(tree1);
        Utills.printTree(tree2);
        System.out.println(isSymmetric(tree1));
        System.out.println(isSymmetric(tree2));
    }

    @Test
    public void test2(){
        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(1);
        nums.add(null);
        nums.add(3);
        nums.add(null);
        nums.add(1);
        System.out.println(isPalindrome(nums));
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
