package dp;

/**
 * 0/1背包遍历：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（一定是先遍历物品，再遍历背包，且背包倒序遍历）
 * 完全背包：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（物品和背包遍历顺序可以颠倒，背包从小到大遍历）
 */
public class CompleteBag_carl {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        completeBag(weight, value, bagSize);
    }

    public static void completeBag(int[] weight, int[] value, int bageSize) {
        int[] dp = new int[bageSize + 1];//默认值相当于初始化了
        for (int i = 0; i < weight.length; i++) {//先遍历物品
            for (int j = weight[i]; j <= bageSize; j++) {//再遍历背包
                dp[j] = Math.max(dp[j], dp[j - weight[i]]+ value[i]);
            }
        }
        for (int maxValue : dp){
            System.out.println(maxValue + "   ");
        }
    }

    public static void completeBag01(int[] weight, int[] value, int bageSize) {
        int[] dp = new int[bageSize + 1];//默认值相当于初始化了
        for (int j = 0; j <= bageSize; j++) {
            for (int i = 0; i < weight.length; i++) {
                if (j - weight[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
    }
}
