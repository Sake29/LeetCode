import org.junit.Test;

import java.util.*;

public class Solution {

    //解法1
    public int findSecondMinimumValue1(TreeNode root) {
        if (root == null||(root.right==null&&root.left==null)) return -1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> resultQueue = new ArrayList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.pollFirst();
            if (!resultQueue.contains(treeNode.val)&&treeNode.val!=root.val){
                resultQueue.add(treeNode.val);
            }
            if (treeNode.left!=null){
                queue.add(treeNode.left);
            }
            if (treeNode.right!=null){
                queue.add(treeNode.right);
            }
        }
        if (resultQueue.size()>0){
            Integer[] result = resultQueue.toArray(new Integer[resultQueue.size()]);
            Arrays.sort(result);
            return result[0]==root.val?-1:result[0];
        }
        else return -1;
    }

    //解法2
    private ArrayList<Integer> queue = new ArrayList<>();
    public int findSecondMinimumValue2(TreeNode root){
        if (root==null||(root.left==null&&root.right==null)) return -1;
        getNode(root);
        Integer[] result = queue.toArray(new Integer[queue.size()]);
        Arrays.sort(result);
        if (result.length==1) return -1;
        else return result[1];
    }
    private void getNode(TreeNode tree){
        if (tree==null) return;
        if (!queue.contains(tree.val)){
            queue.add(tree.val);
        }
        getNode(tree.left);
        getNode(tree.right);
    }


    //解法3
    private boolean hasSec = false;
    private int min = Integer.MAX_VALUE;
    private int secMin = Integer.MAX_VALUE;
    public int findSecondMinimumValue3(TreeNode root){
        getSecondMin(root);
        return hasSec ? secMin : -1;

    }
    private void getSecondMin(TreeNode root){
        if (root==null||root.val>secMin) return;//终止条件
        int currRoot = root.val;
        if (currRoot<min){
            secMin = min;
            min = currRoot;
        }
        else if(currRoot>min&&currRoot<=secMin){
            secMin = currRoot;
            hasSec = true;
        }
        getSecondMin(root.left);
        getSecondMin(root.right);
    }
    private void reTest() {
        min = Integer.MAX_VALUE;
        secMin = Integer.MAX_VALUE;
        hasSec = false;
    }

    @Test
    public void test(){
        System.out.println("#####解法1#####");
        int secondMinimumValue1 = findSecondMinimumValue1(Utills.createTree(new Integer[]{2, 2, 5, null, null, 5, 7}));
        int secondMinimumValue2 = findSecondMinimumValue1(Utills.createTree(new Integer[]{}));
        int secondMinimumValue3 = findSecondMinimumValue1(Utills.createTree(new Integer[]{1, 1, 3, 1, 1, 3, 4, 3, 1, 1, 1, 3, 8, 4, 8, 3, 3, 1, 6, 2, 1}));
        System.out.println("测试案例1："+secondMinimumValue1);
        System.out.println("测试案例2："+secondMinimumValue2);
        System.out.println("测试案例3："+secondMinimumValue3);

        System.out.println("#####解法2#####");
        secondMinimumValue1 = findSecondMinimumValue2(Utills.createTree(new Integer[]{2, 2, 5, null, null, 5, 7}));
        queue.clear();
        secondMinimumValue2 = findSecondMinimumValue2(Utills.createTree(new Integer[]{}));
        queue.clear();
        secondMinimumValue3 = findSecondMinimumValue2(Utills.createTree(new Integer[]{1, 1, 3, 1, 1, 3, 4, 3, 1, 1, 1, 3, 8, 4, 8, 3, 3, 1, 6, 2, 1}));
        System.out.println("测试案例1："+secondMinimumValue1);
        System.out.println("测试案例2："+secondMinimumValue2);
        System.out.println("测试案例3："+secondMinimumValue3);

        System.out.println("#####解法3#####");
        secondMinimumValue1 = findSecondMinimumValue3(Utills.createTree(new Integer[]{2, 2, 5, null, null, 5, 7}));
        reTest();
        secondMinimumValue2 = findSecondMinimumValue3(Utills.createTree(new Integer[]{}));
        reTest();
        secondMinimumValue3 = findSecondMinimumValue3(Utills.createTree(new Integer[]{1, 1, 3, 1, 1, 3, 4, 3, 1, 1, 1, 3, 8, 4, 8, 3, 3, 1, 6, 2, 1}));
        System.out.println("测试案例1："+secondMinimumValue1);
        System.out.println("测试案例2："+secondMinimumValue2);
        System.out.println("测试案例3："+secondMinimumValue3);
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
