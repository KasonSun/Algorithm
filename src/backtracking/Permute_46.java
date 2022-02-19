package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 */
public class Permute_46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("nums数组的全排列为：" + new Permute_46().permute(nums));
    }

    /**
     * 保证每个元素被选到一次，且只需要在叶子节点进行结果收集，此外每一个排列是有序的（[1,2]不一样[2,1]）
     *
     * 总结：大家此时可以感受出排列问题的不同：
     *              每层都是从0开始搜索而不是startIndex
     *              需要used数组记录path里都放了哪些元素了
     * @param nums
     * @return
     */

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int[] used = new int[nums.length];
        backTracking(nums, used);
        return result;
    }

    public void backTracking(int[] nums, int[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0) {
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backTracking(nums, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }
}
