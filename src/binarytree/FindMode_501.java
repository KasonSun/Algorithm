package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *  结点左子树中所含结点的值小于等于当前结点的值
 *  结点右子树中所含结点的值大于等于当前结点的值
 *  左子树和右子树都是二叉搜索树
 *
 *  提示：如果众数超过1个，不需考虑输出顺序
 *  进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindMode_501 {
    public static void main(String[] args) {
        int[] Pre = {1, 2, 2};
        int[] In = {1, 2, 2};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉搜索树的众数为：" + Arrays.toString(findMode(root)));
    }

    /**
     * 1.迭代实现
     *      使用前一个结点pre和当前节点cur进行判断
     * @param root
     * @return
     */
    public static int[] findMode(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int maxCount = 0;//最大频率
        int count = 0;//统计频率
        List<Integer> result = new ArrayList<>();
        while (cur != null || !stack.isEmpty()) {//访问结点，访问到最底层
            if (cur != null) {//左
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();//中
                if (pre == null) {//第一个节点
                    count = 1;
                } else if (pre.val == cur.val) {//与第一个节点相同数值的节点
                    count++;
                } else {//与前一个结点数值不同
                    count = 1;
                }

                //更新结果
                if (count == maxCount) {//如果与前一个频率相同，放进result
                    result.add(cur.val);
                }
                if (count > maxCount) {//大于前一个频率，更新最大频率
                    maxCount = count;
                    result.clear();//不要忘记清空result，之前的result都失效了
                    result.add(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        //将list转换为数组返回
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
