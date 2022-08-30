package greedy;

import java.util.Arrays;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，
 * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:10
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 * 输入: g = [1,2],  s =[1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 */
public class FindContentChildren_455 {
    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println("分发饼干最大值：" + findContentChildren(g, s));
    }

    /**
     *
     * 思路：为了满足更多的小孩，就不要造成饼干尺寸的浪费。大尺寸的饼干既可以满足胃口大的孩子，也可以满足胃口小的孩子，那么就应该先满足胃口大的。
     *      这里的局部最优就是大饼干喂给胃口大的，充分利用饼干的尺寸喂饱一个，全局最优就是喂饱尽可能多的小孩，
     *      可以尝试使用贪心策略，先将饼干数组和小孩数组进行排序，然后从后向前遍历小孩数组，用大饼干优先满足胃口大的，并统计满足小孩的数量
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        // 思路2：优先考虑胃口，先喂饱大胃口
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        //遍历胃口
        for (int i = g.length - 1; i >= 0 && start >= 0; i--) {
            if (g[i] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    /**
     * 时间复杂度：O(nlogn) (排序O(nlogn)+遍历O(n))  空间复杂度；O(1)
     * 因为用小饼干优先喂饱小胃口的 这样可以尽量保证最后省下来的是大饼干（虽然题目没有这个要求）！这个思路更好一点
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren1(int[] g, int[] s) {
        // 思路1：优先考虑饼干，小饼干先喂饱小胃口
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }
}
