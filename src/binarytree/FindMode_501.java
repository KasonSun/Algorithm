package binarytree;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 2.递归实现
     */
    int maxCount=0;
    int count=0;
    TreeNode pre=null;
    List<Integer> result=new ArrayList<>();
    public int[] findMode01(TreeNode root) {
        inorder(root);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public void inorder(TreeNode root){
        if(root==null){
            return ;
        }
        inorder(root.left);
        if(pre==null){
            count=1;
        }else if(pre.val==root.val){
            count++;
        }else{
            count=1;
        }
        pre=root;
        if(count==maxCount){
            result.add(root.val);
        }
        if(count>maxCount){
            maxCount=count;
            result.clear();
            result.add(root.val);
        }
        inorder(root.right);
    }

    /**
     * 二叉树中的众数
     * 如果该二叉树不是二叉搜索树，仅仅是二叉树，则可以
     *      ①先遍历，使用map统计每个数的频率；
     *      ②对map排序，遍历取频率最大的（可能存在多个频率最大）
     */
    public static int[] findModeUniversal(TreeNode root){
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        if(root==null){
            return list.stream().mapToInt(Integer::intValue).toArray();//list转换为数组
        }
        traverseCount(root, map);
        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream()
                .sorted((c1,c2)->c2.getValue().compareTo(c1.getValue()))//按值从大到小排序
                .collect(Collectors.toList());//将Entry作为整体放入list

        list.add(mapList.get(0).getKey());//把频率最高的加入list
        for(int i=1;i<mapList.size();i++){//可能存在多个最大频率数
            if(mapList.get(i).getValue()==mapList.get(i-1).getValue()){
                list.add(mapList.get(i).getKey());
            }else{
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void traverseCount(TreeNode cur, Map<Integer, Integer> map){
        if(cur==null){
            return ;
        }
        map.put(cur.val, map.getOrDefault(cur.val,0)+1);
        traverseCount(cur.left, map);
        traverseCount(cur.right, map);
    }
}
