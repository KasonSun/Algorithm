package dp;

/**
 *416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 */
public class CanPartition_416 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划：使用0/1背包  T:O(n^2) S:O(n)
     *      需要确定：①背包体积为sum/2；
     *              ②背包要放入的商品（集合里的元素）重量为元素的数值，价值也为元素的数值
     *              ③背包如果正好装满，说明找到了总和为sum/2的子集
     *              ④背包中每一个元素不可以重复放入
     *
     *
     *     五部曲：①确定dp数组以及下标的含义
     *              dp[j]表示 背包总容量是j，最大可以凑成j的子集总和为dp[j]。
     *           ②确定递推公式
     *              相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]
     *              dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);
     *           ③dp数组的初始化
     *              dp[0]=0
     *              如果如果题目给的价值都是正整数那么非0下标都初始化为0就可以了，如果题目给的价值有负数，那么非0下标就要初始化为负无穷
     *              本题题目中 只包含正整数的非空数组，所以非0下标的元素初始化为0就可以了
     *           ④确定遍历顺序
     *              在动态规划：关于01背包问题（滚动数组）中就已经说明：
     *              如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
     *           ⑤举例推导dp数组
     *              dp[i]的数值一定是小于等于i的
     *              如果dp[i] == i 说明，集合中的子集总和正好可以凑成总和i，理解这一点很重要。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //总和为奇书，直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int dp[] = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }
}
