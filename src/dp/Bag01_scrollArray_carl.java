package dp;

/**
 * 0/1背包遍历：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（一定是先遍历物品，再遍历背包，且背包倒序遍历）
 * 完全背包：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（物品和背包遍历顺序可以颠倒，背包从小到大遍历）
 *
 *   动态规划：经典0/1背包（滚动数组（一维）实现）
 *          int[] weight = {1, 3, 4};
 *          int[] value = {15, 20, 30};
 *          int bagsize = 4;
 */
public class Bag01_scrollArray_carl {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        bag01_scrollArray(weight, value, bagsize);
    }
    /**
     * 动态规划；T:O(n^2) S:O(n)
     *                五部曲：①确定dp数组以及下标的含义
     *                              dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]
     *                       ②确定递推公式
     *                              dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *
     *                              dp[j - weight[i]]表示容量为j - weight[i]的背包所背的最大价值
     *                              dp[j - weight[i]] + value[i] 表示 容量为 j - 物品i重量 的背包 加上 物品i的价值。（也就是容量为j的背包，放入物品i了之后的价值即：dp[j]）
     *                              此时dp[j]有两个选择，一个是取自己dp[j] 相当于 二维dp数组中的dp[i-1][j]，即不放物品i，
     *                              一个是取dp[j - weight[i]] + value[i]，即放物品i，指定是取最大的，毕竟是求最大价值
     *                       ③dp数组的初始化
     *                          dp数组在推导的时候一定是取价值最大的数，如果题目给的价值都是正整数那么非0下标都初始化为0就可以了
     *                          这样才能让dp数组在递归公式的过程中取的最大的价值，而不是被初始值覆盖了。
     *                          那么我假设物品价值都是大于0的，所以dp数组初始化的时候，都初始为0就可以了。
     *                       ④确定遍历顺序（背包必须逆序遍历决定）
     *                                  二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包是从大到小。
     *                                  倒序遍历是为了保证物品i只被放入一次！
     *                                  为什么呢？
     *                                      倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
     *                                      举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15
     *                                      如果正序遍历
     *                                      dp[1] = dp[1 - weight[0]] + value[0] = 15
     *                                      dp[2] = dp[2 - weight[0]] + value[0] = 30
     *                                      此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
     *                                   为什么倒序遍历，就可以保证物品只放入一次呢？
     *                                          倒序就是先算dp[2]
     *                                          dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp数组已经都初始化为0）
     *                                          dp[1] = dp[1 - weight[0]] + value[0] = 15
     *                                    所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了
     *                       ⑤举例推导dp数组
     *                             n=5      0  1  2  3  4  5
     *                             dp[i]    1  1  2  5  14 42
     * @param weight,value,bagsize
     * @return
     */
    public static void bag01_scrollArray(int[] weight, int[] value, int bagsize) {
        //初始化(默认值为0)
        int[] dp = new int[bagsize + 1];
        for (int i = 0; i < weight.length; i++) {//遍历物品
            for (int j = bagsize; j >= weight[i]; j--) {//遍历背包（一维实现必须逆序遍历背包）（此时j >= weight[i]条件是因为j < weight[i]被初始化0了）
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
            for (int j = 0; j <= bagsize; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.print("\n");
    }
}
