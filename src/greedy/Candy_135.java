package greedy;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 */
public class Candy_135 {
    public static void main(String[] args) {
        int[] ratings = {1, 2, 2};
        System.out.println("分发的糖果数为："+candy(ratings));
    }

    /**
     * 贪心法：（两次贪心策略）
     *      思路：先确定右边评分大于左边的情况（从左到右）；再确定左边评分大于右边的情况（从右向左遍历:每次才能利用上一次的比较的结果）
     *      局部最优：（从左到右）只要右边评分比左边大，右边的孩子就多了一个糖果；全局最优：相邻的孩子中，评分高的右孩子获得比左边更多的糖果
     *              局部最优推出全局最优，（从右向左遍历）同理
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        candy[0] = 1;//初始化为1
        // 1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }else{
                candy[i] = 1;
            }
        }

        //2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，
        // 此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);//注意此处是在上面从左到右比较完的基础上进行操作，故还要满足之前的条件，此处用Max
            }
        }
        int result = 0;
        for (int c : candy) {
            result += c;
        }
        return result;
    }
}
