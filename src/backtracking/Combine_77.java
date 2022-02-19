package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合问题
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class Combine_77 {
    public static void main(String[] args) {
        int n = 4;
        int k = 3;
        System.out.println("组合结果为：" + new Combine_77().combine(n, k));
    }

    /**
     * 1.递归回溯：
     *      参数和返回值：n,k,startIndex(记录本层递归中集合从哪里开始遍历)
     *      终止条件：path这个数组大小如果到达了k，说明找到了一个子集大小为k的组合，path就是存了根节点到叶子节点的路径
     *      单层递归逻辑：for循环每次从startIndex开始遍历，用path保存取到的节点i；
     *                  然后backTracking（递归）调用自己往深处遍历，直到遇到叶子节点，进行返回；
     *                   最后回溯，撤销本次操作
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();//全局变量，最终结果集合
    List<Integer> path = new ArrayList<>();//全局变量，满足条件过程集合
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, 1);
        return result;
    }

    public void backTracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        //优化过程
        //举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2
        //已经选择的元素个数：path.size();
        //还需要的元素个数为: k - path.size();
        //在集合n中至多要从该起始位置 : n - (k - path.size()) + 1，开始遍历
        //for(int i = startIndex; i <= n-(k-path.size()) + 1; i++)
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backTracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
