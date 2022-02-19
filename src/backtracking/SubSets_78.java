package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，‘数组中的元素 互不相同 ’。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class SubSets_78 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Nums数组所有可能的子集I为："+new SubSets_78().subSets(nums));
    }

    /**
     * 思路：求取子集问题，不需要任何剪枝！因为子集就是要遍历整棵树。(需要在每层每个节点进行结果收集)
     *
     * @param nums
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subSets(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    /**
     * 参数和返回值：void backTracking(int[] nums, int startIndex)
     * 终止条件：每个节点都需要进行结果收集，到每个分支所能取到的最大索引进行返回
     * 单层递归逻辑：一开始就进行节点保存，for循环进行层次遍历
     *
     * @param nums
     * @param startIndex
     */
    public void backTracking(int[] nums, int startIndex) {
        //树中每个节点都需要进行保存，直接进行结果收集
        result.add(new ArrayList<>(path));

        if (startIndex >= nums.length) {//终止条件可以不加，因为startIndex >= nums.size()，本层for循环本来也结束了。
            return;
        }
        //从上一层的startIndex开始遍历到结尾
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);//添加到当前路径
            backTracking(nums, i + 1);//从下一个结点开始，递归下一层
            path.remove(path.size() - 1);//回溯
        }
    }
}
