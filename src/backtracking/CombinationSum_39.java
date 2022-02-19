package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合问题
 *
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
 * 找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 */
public class CombinationSum_39 {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("39.组合总和的结果为：" + new CombinationSum_39().combinationSum(candidates, target));
    }

    /**
     * 1.无剪枝
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        backTracking(candidates, target, 0, 0);
        return result;
    }

    public void backTracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i);//注意此处下一次的startIndex还是i表示题目中的可以重复选取同一个数
            sum -= candidates[i];//回溯
            path.remove(path.size() - 1);//回溯
        }
    }
}

/**
 * 2.下面对以上的回溯过程进行剪枝操作
 */
class CombinationSum_Enhanced_39 {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("39.组合总和的结果为：" + new CombinationSum_Enhanced_39().combinationSum(candidates, target));
    }

    /**
     * 剪枝（1.数组排序+2.for循环中sum条件判断sum+candidates[i]<=target）
     * 1.2.3.三个地方进行优化
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);//1.
        backTracking(candidates, target, 0, 0);
        return result;
    }

    public void backTracking(int[] candidates, int target, int sum, int startIndex) {
        //3.
//        if (sum > target) {
//            return;
//        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        //2.
        for (int i = startIndex; i < candidates.length && sum+candidates[i]<=target; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i);//注意此处下一次的startIndex还是i表示题目中的可以重复选取同一个数
            sum -= candidates[i];//回溯
            path.remove(path.size() - 1);//回溯
        }
    }
}