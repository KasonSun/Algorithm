package dp;

/**
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * 返回 你可以获得的最大乘积 。
 *
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 */
public class IntegerBreak_343 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("整数拆分的最大乘积为："+integerBreak(n));
    }

    /**
     * 动态规划；T:O(n^2) S:O(n)
     *
     *                五部曲：①确定dp[i]数组以及下标的含义
     *                            dp[i]的定义为：分拆数字i，可以得到的最大乘积为dp[i]
     *
     *                       ②确定递推公式
     *                            从1遍历j，可以从两个渠道得到dp[i]一个是j*(i-j)直接相乘; 一个是j*dp[i-j]，相当于是拆分（i-j）
     *                            j是从1开始遍历，拆分j的情况，在遍历j的过程中其实都计算过了。那么从1遍历j，比较(i - j) * j和dp[i - j] * j 取最大的。
     *                            递推公式：dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j));
     *                            也可以这么理解，j * (i - j) 是单纯的把整数拆分为两个数相乘，而j * dp[i - j]是拆分成两个以及两个以上的个数相乘。
     *
     *                       ③dp数组的初始化
     *                            dp[2]=1（dp[0]和dp[1]没有意义）
     *
     *                       ④确定遍历顺序
     *                            由递推公式，dp[i]得到是在dp[i-j]之后，枚举j的时候，从1开始，i从3开始，dp[2]=1
     *
     *                       ⑤举例推导dp数组
     *                             n=10
     *                             下标：2  3  4  5  6  7  8  9  10
     *                                  1  2  4  6  9 12 18 27 36
     *
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        //dp[i]为正整数i拆分结果的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {//注意此处j<i-1;当i=3，j如果等于i-1为2时，此时dp[i-j]=dp[1],dp[1]是不能再拆分的，故j<i-1
                //j*(i-j)代表把i拆分为j和i-j两个数相乘
                //j*dp[i-j]代表把i拆分成j和继续把(i-j)这个数拆分，取(i-j)拆分结果中的最大乘积与j相乘
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));//别忘记数组是有默认值的，故递推dp[3]=max(0,max(2*1,dp[2]*1))=2
                //那么在取最大值的时候，为什么还要比较dp[i]呢？
                //因为在递推公式推导的过程中，j需要移动多次，每次计算dp[i]，取最大的而已。

//                System.out.println(dp[i]);
            }
        }
        return dp[n];
    }
}
