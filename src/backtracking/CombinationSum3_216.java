package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 216.组合总和III
 * 找出所有相加之和为n 的k个数的组合。组合中只允许含有 1 -9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 *      所有数字都是正整数。
 *      解集不能包含重复的组合。
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3_216 {
    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        System.out.println("组合总和III结果为：" + new CombinationSum3_216().combinationSum3(k, n));
    }

    /**
     * 1.回溯
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(k, n, 1);
        return result;
    }

    /**
     * 回溯具体过程
     * @param k
     * @param n
     * @param startIndex
     */
    public void backTracking(int k, int n, int startIndex) {
        if (path.size() == k) {
            int sum = 0;
            for (int i = 0; i < path.size(); i++) {
                sum += path.get(i);
            }
            if (sum == n) {
                result.add(new ArrayList<>(path));
                return;//一定要注意不管条件满足不满足sum==n,都要进行返回，惨痛
            }
            return;//一定要注意不管条件满足不满足sum==n,都要进行返回，惨痛
        }

        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            backTracking(k, n, i + 1);
            path.remove(path.size() - 1);
        }
    }
}

/**
 * 针对上面的回溯过程进行剪枝操作和一些优化
 */
class CombinationSum3Enchanced{
    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        System.out.println("组合总和III结果为：" + new CombinationSum3Enchanced().combinationSum3(k, n));
    }
    /**
     * 1.回溯
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(k, n, 1, 0);
        return result;
    }

    /**
     * 回溯过程优化剪枝(sum>n,提前返回)，将for循环统计和部分放到回溯for中
     *
     * @param k
     * @param n
     * @param startIndex
     */
    public void backTracking(int k, int n, int startIndex, int sum) {
        //剪枝
        if (sum > n) {
            return ;
        }
        if (path.size() == k) {
            if (sum == n) result.add(new ArrayList<>(path));
            return;//一定要注意不管条件满足不满足sum==n,都要进行返回，惨痛
        }

        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backTracking(k, n, i + 1,sum);
            sum -= i;//回溯
            path.remove(path.size() - 1);//回溯
        }
    }
}
