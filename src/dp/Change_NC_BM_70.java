package dp;

/**
 * 零钱兑换I
 * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 * 如果无解，请返回-1.
 * 数据范围：数组大小满足 0 \le n \le 100000≤n≤10000 ， 数组中每个数字都满足 0 < val \le 100000<val≤10000，0 \le aim \le 50000≤aim≤5000
 * 要求：时间复杂度 O(n \times aim)O(n×aim) ，空间复杂度 O(aim)O(aim)。
 */
public class Change_NC_BM_70 {
    public int minMoney (int[] arr, int aim) {
        //是普通完全背包问题，按照一般思路解决,此时货币数作为价值，货币面值作为重量
        int[] dp=new int[aim+1];
        for(int i=1;i<dp.length;i++){
            dp[i]=10000;
        }
        dp[0]=0;
        for(int i=0;i<arr.length;i++){
            for(int j=arr[i];j<=aim;j++){
                dp[j]=Math.min(dp[j],dp[j-arr[i]]+1);
            }
        }
        return dp[aim]==10000 ?-1:dp[aim];
    }
}
