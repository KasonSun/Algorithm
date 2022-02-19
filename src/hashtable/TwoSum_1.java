package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出和为目标值 target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现（不能有相同的索引）
 * 你可以按任意顺序返回答案
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class TwoSum_1 {
    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums,target)));
    }
    /**
     * 使用哈希表实现，已经遍历过的存入哈希表，target - nums[i]在哈希表中搜索是否存在
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if(map.containsKey(temp)){//存在则存入对应的索引到结果数组
                res[1] = i;
                res[0] = map.get(temp);
                break;//题目说只有唯一的结果
            }
            map.put(nums[i], i);//不存在则加入哈希表（相当于循环第二个for的作用，提高时间效率）
        }
        return res;
    }
}
