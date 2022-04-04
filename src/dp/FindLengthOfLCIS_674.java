package dp;

import java.util.Arrays;

/**
 * 674. 最长（连续）递增序列
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 *
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 */
public class FindLengthOfLCIS_674 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,2,3,4,5};
        System.out.println("最长连续递增子序列为：" + findLengthOfLCIS01(nums));
    }

    /**
     *  动态规划：T(O(n))  S(O(n))
     *      相对于300.最长递增子序列最大区别在于连续
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i]:以下标i为结尾的数组的连续递增子序列长度dp[i]
     *                      注意这里的定义，一定是以下标i为结尾，并不是说一定以下标0为起始位置。
     *
     *                  ②状态转移方程
     *                      如果 nums[i + 1] > nums[i]，那么以 i+1 为结尾的数组的连续递增的子序列长度 一定等于 以i为结尾的数组的连续递增的子序列长度 + 1
     *                      即：dp[i + 1] = dp[i] + 1;
     *                      因为本题要求连续递增子序列，所以就必要比较nums[i + 1]与nums[i]，而不用去比较nums[j]与nums[i] （j是在0到i之间遍历）。
     *                      既然不用j了，那么也不用两层for循环，本题一层for循环就行，比较nums[i + 1] 和 nums[i]。
     *
     *                  ③dp[i]的初始化
     *                      dp[i]=1
     *
     *                  ④确定遍历顺序
     *                      根据状态转移方程，一定是从前向后遍历
     *
     *                  ⑤举例推导dp数组
     *                      nums=[1,3,5,4,7]
     *
     * @param nums
     * @return
     */
    private static int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        int result = 1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length - 1; i++) {
            //dp[0]不需要比较，只有一个数，已经初始化为1
            if (nums[i] < nums[i + 1]) {
                dp[i + 1] = dp[i] + 1;
            }
            if (dp[i + 1] > result) {
                result = dp[i + 1];
            }
        }
        return result;
    }

    /**
     * 贪心法：T(O(n))  S(O(1))
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS02(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int result = 1;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {//连续则记录，不连续从头开始(贪心准则，有点牵强)
                count++;
            }else{//不连续从头开始
                count = 1;
            }
            if (count > result) {
                result = count;
            }
        }
        return result;
    }

    /**
     * 暴力法 T(O(n^2))  S(O(1))
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS01(int[] nums) {
        int count = 1;
        int result = 1;
        int j;//此处为了优化下一轮循环起始位置，减少遍历次数
        for (int i =0; i < nums.length; i=j+1) {//i的起始位置应该是上一次不连续的j位置+1
            for (j = i; j < nums.length-1; j++) {
                if (nums[j] < nums[j+1]) {
                    count++;
                }else{
                    break;//当前循环中不连续立刻终止，外层进行下一轮循环
                }
            }
            if (count > result) {
                result = count;//取最大值
            }
            count = 1;//置1；进行下一轮循环
        }
        return result;
    }
}
