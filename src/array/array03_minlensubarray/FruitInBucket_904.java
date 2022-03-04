package array.array03_minlensubarray;

import java.util.HashMap;

/**
 * 在一排树中，第 i 棵树产生tree[i] 型的水果。
 * 你可以从你选择的任何树开始，然后重复执行以下步骤：
 * 1.把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
 * 2.移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
 * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
 * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。用这个程序你能收集的水果树的最大总量是多少？
 *
 * 输入：[1,2,1]
 * 输出：3
 * 解释：我们可以收集 [1,2,1]。
 *
 * 输入：[0,1,2,2]
 * 输出：3
 * 解释：我们可以收集 [1,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
 *
 * 输入：[1,2,3,2,2]
 * 输出：4
 * 解释：我们可以收集 [2,3,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
 *
 */
public class FruitInBucket_904 {
    public static void main(String[] args) {
        int tree[] = {1, 1, 1, 2, 3, 2, 2};//4
        System.out.println("能收集的最大水果树数量为：" + fruitInBucket(tree));
    }

    /**
     * 本题实际可以看成是求包含两种元素的最长连续子序列问题(不定长的滑动窗口)，保证连续
     * （由分析可知只能放两种类型--“移动到当前树右侧的下一棵树。如果右边没有树，就停下来”）
     *
     * @param tree
     * @return
     */
    private static int fruitInBucket(int tree[]) {
        int result = 0, i = 0;//i始终指向第一种类型的第一个
        Counter count = new Counter();//用来统计某一类型的数有多少个
        for (int j = 0; j < tree.length; j++) {
            count.add(tree[j], 1);//对满足条件的某一类型的树自增
            while (count.size()== 3) {//大于等于3（实际等于3就可以），则移除前面的值，i往后滑动（此时第一种的类型的树的数目可能大于1）
                count.add(tree[i], -1);
                if (count.get(tree[i]) == 0) {
                    count.remove(tree[i]);
                }
                i++;//移除tree[i]类型的树，该类型有几个，i就得移动几次
            }

            result = Math.max(result, j - i + 1);
        }
        return result;
    }
}

class Counter extends HashMap<Integer, Integer> {
    public int get(int k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(int k, int v) {
        put(k, get(k) + v);//修改计数
    }
}
