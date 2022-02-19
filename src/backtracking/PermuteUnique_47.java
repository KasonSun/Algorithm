package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermuteUnique_47 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("nums数组全排列II的结果为：" + new PermuteUnique_47().permuteUnique(nums));
    }

    /**
     * 区别于46.全排列问题，包含重复元素，就存在重复的全排列
     * 去重思路：对数组进行排序，进而满足 1.同层重复元素不能重复使用((i > 0 && nums[i] == nums[i - 1] && used[i-1]==1))，跳过
     *                              2.单纯的不重复元素已经访问过 （used[i] != 0）， 跳过
     *
     * @param nums
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        int[] used = new int[nums.length];
        Arrays.sort(nums);//区别46，这里需要排序去重(排序后重复元素才会在一起)
        backTracking(nums, used);
        return result;
    }

    public void backTracking(int[] nums, int[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < used.length; i++) {
            //1.同层重复元素不能重复使用((i > 0 && nums[i] == nums[i - 1] && used[i-1]==1))，跳过
            //2.单纯的不重复元素已经访问过 （used[i] != 0）， 跳过
            if ((i > 0 && nums[i] == nums[i - 1] && used[i-1]==1) || used[i] != 0) {
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
