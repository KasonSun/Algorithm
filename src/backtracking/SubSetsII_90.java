package backtracking;

import java.util.*;

/**
 * 给你一个整数数组 nums ，‘其中可能包含重复元素’，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 */
public class SubSetsII_90 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println("Nums数组所有可能的子集II为："+new SubSetsII_90().subSetsWithDup(nums));
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subSetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        backTracking(nums, 0, used);//used去重
//        backTracking1(nums, 0);//startIndex去重
        return result;
    }

    /**
     * 相比较78题的子集问题，只需要去重即可
     * 注意：有一种方法是对最后结果进行去重，这种单纯使用Set无法实现对嵌套List<>的去重，除非重写equals和hashCode方法
     * 此外所说的去重实现：CombinationSum2_40中的去重思想一致，使用uesd数组实现，前提需要对原始nums数组进行排序
     *
     * @param nums
     * @param startIndex
     */
    public void backTracking(int nums[], int startIndex, int[] used) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {//终止条件可以不加，因为startIndex >= nums.size()，本层for循环本来也结束了。
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backTracking(nums, i + 1,used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }

    /**
     * 使用startIndex去重
     * @param nums
     * @param startIndex
     */
    public void backTracking1(int nums[], int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {//终止条件可以不加，因为startIndex >= nums.size()，本层for循环本来也结束了。
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            //使用startIndex去重
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTracking1(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
