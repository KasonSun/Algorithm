package greedy;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaxSubArray_53 {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("最大子序和为：" + maxSubArrays(nums));
    }

    /**
     * 方法一：暴力解法(Java超出时间限制)
     * @param nums
     * @return
     */
    public static int maxSubArrays01(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            for (int j = i; j < nums.length; j++) {
                count += nums[j];
                result = count > result ? count : result;
            }
        }
        return result;
    }

    /**
     * 方法二；贪心法
     *      思路：局部最优：当前连续和为负数时立刻放弃，从下一个元素重新计算连续和，因为负数加上下一个连续和只会越来越小
     *           全局最优：选取最大连续和
     *           局部最优的情况下，并记录最大的连续和，可以推出全局最优
     *           （从代码角度来讲：遍历nums，从头开始用count累计，如果count一旦加上nums[i]变成负数，那么应该从nums[i+1]开始从0累计count，因为已经变负的count只会拖累总和）
     * @param nums
     */
    public static int maxSubArrays(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            result = count > result ? count : result;//取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0) {//这个条件很重要
                count = 0;//相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return result;
    }
}
