package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * <p>
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * <p>
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 */
public class FindSubsequences_491 {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println("nums递增子序列为：" + new FindSubsequences_491().findSubsequences(nums));
    }

    /**
     * 注意：nums数组可能包含重复元素，通过画多叉树可以得知，同一父节点下的同层使用过的元素就不能再使用了
     *  还得使用used数组实现去重
     * @param nums
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    public void backTracking(int[] nums, int startIndex) {
        if (path.size() > 1) {//递增子序列中 至少有两个元素，故path.size()>1
            result.add(new ArrayList<>(path));
            //注意：此处不能进行返回，每次收集结果进行返回，只会收集刚刚path.size达到2的集合，会漏掉很多解
        }
        int[] used = new int[201];// 这里使用数组来进行去重操作，题目说数值范围[-100, 100]
        for (int i = startIndex; i < nums.length; i++) {
            //此处!path.isEmpty()为了保证第二个比较能够存在；used[nums[i] + 100] == 1已被访问过
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1;//[-100, 100]范围所以加100，记录这个元素在本层使用过了，本层后面不能再用了（used只负责本层，看used定义的位置：下一次递归会重新定义）
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
