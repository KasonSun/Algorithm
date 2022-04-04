package dp;

/**
 * 494. 目标和
 * 给你一个正整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 输入：nums = [1], target = 1
 * 输出：1
 */
public class FindTargetSumWays_494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println("目标和为："+findTargetSumWays(nums, S));
    }

    /**
     * 动态规划：使用0/1背包  T:O(n*m) S:O(m),n数的个数，m为背包容量
     * 五部曲：①确定dp数组以及下标的含义
     * dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
     * ②确定递推公式
     * dp[j] += dp[j - nums[i]]
     * ③dp数组的初始化
     * dp[0]=1
     * dp[0] = 1，理论上也很好解释，装满容量为0的背包，有1种方法，就是装0件物品。
     * dp[j]其他下标对应的数值应该初始化为0，从递归公式也可以看出，dp[j]要保证是0的初始值，才能正确的由dp[j - nums[i]]推导出来。
     * ④确定遍历顺序
     * 在动态规划：关于01背包问题（滚动数组）中就已经说明：
     * 如果使用一维dp数组，物品nums遍历的for循环放在外层，遍历背包target的for循环放在内层，且内层for循环倒序遍历！
     * ⑤举例推导dp数组
     *
     *
     *
     * 之前原题 int findTargetSumWays(vector<int>& nums, int S)
     * *分析题意: .01背包问题是选或者不选，但本题是必须选，是选+还是选-。先将本问题转换为01背包问题。
     * *可以将这组数看成两部分，P和N,其中P使用正号，N使用负号，有以下推导:
     * *我们想要的sum(P) - sum(N) = S所有的正数和加上所有的负数和就是我们的目标和target
     * *而已知sum(N)与sum(P)的和是数组总和: sum(P) + sum(N) = sum
     * *将上述两个式子相加消元sum(P) - sum(N) + sum(P) + sum(N) = s + sum
     * *可以求出sum(P)=(S+sum)/2=target
     * *也就是我们要从nums数组里选出几个数，令其和为target
     * *于是就转化成了求容量为target的01背包问题=>要装满容量为target的背包，有几种方案
     * *特例判断
     * *如果s大于sum,不可能实现，返回o
     * *如果sum(P)不是整数，也就是s + sum不是偶数，(S + sum) / 2除不尽，有余数不可能实现等号条件，返回0 .
     * * dp[j]代表的意义:填满容量为j的背包，有dp[j]种方法。因为填满容量为0的背包有且只有一种方法，所以dp[0] = 1
     * *状态转移: dp[j]+ = dp[j] + dp[j - num],
     * *当前填满容量为j的包的方法数=之前填满容量为j的包的方法数+之前填满容量为j - num的包的方法数
     * *也就是当前数num的加入，可以把之前和为j - num的方法数加入进来。
     * *返回dp[-1],也就是dp[ target]结束判断条件
     *
     * @param nums,target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        if (size < 0) size = -size;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }


    public static int finTargetSumWays01(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (Math.abs(S) > sum) {//此时没有方案
            return 0;
        }
        if ((S + sum) % 2 == 1) {//没有方案
            return 0;
        }
        int bagSize = (S + sum) / 2;//代表正数部分和
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[bagSize];
    }
}
