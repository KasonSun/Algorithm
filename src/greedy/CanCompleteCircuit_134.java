package greedy;

/**
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 */
public class CanCompleteCircuit_134 {
    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("加油站：" + canCompleteCircuit(gas, cost));
    }

    /**
     * 1.暴力解法 T:O(n^2) S:O(n)  提交力扣出现位置错误，不是代码错误
     *      跑了一圈，中途没有断油，而且最后油量>=0,说明这个起点是ok的
     *      for循环适合模拟从头到尾遍历，而while循环适合模拟环形遍历
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            int rest = gas[i] - cost[i];//记录剩余油量
            int index = (i + 1) % cost.length;
            while (rest > 0 && index != i) {//模拟以i为起点行驶一圈
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.length;
            }
            //如果以i为起点跑一圈，剩余油量>=0,返回该起始位置
            if (rest >= 0 && index == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 2.贪心算法 T: O(n)  S:O(1)
     *      思路：如果总油量减去总消耗大于等于零那么一定可以跑完一圈，说明 各个站点的加油站 剩油量rest[i]相加一定是大于等于零的，
     *      每个加油站的剩余量rest[i]为gas[i] - cost[i]，i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，
     *      说明[0, i]区间都不能作为起始位置，起始位置从i+1算起，再从0计算curSum（结合图理解）
     *
     *      局部最优：当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，因为从j开始一定不行；全局最优：找到可以跑一圈的起始位置
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;//记录哪个索引可以满足走过一圈
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                start = i + 1;//更新start
                curSum = 0;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }
}
