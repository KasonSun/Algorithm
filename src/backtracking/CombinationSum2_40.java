package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合问题
 *
 * 给你一个由候选元素组成的集合 candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates 中的每个元素在每个组合中只能使用 一次（意思：同层相同元素不可选，同树枝相同元素可选） 。
 * 注意：解集不能包含重复的组合。
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 */


/**
 * 注意：此时代码可以出现结果，但是会包含重复的组合， 因此在另一个类要实现去重
 */
public class CombinationSum2_40 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println("40.组合总和的结果为：" + new CombinationSum2_40().combinationSum2(candidates, target));
    }

    /**
     * candidates 中的每个元素在每个组合中只能使用 一次
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    public void backTracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum+candidates[i]<=target; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1);//注意此处与39题的区别，不能重复取同一个数
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}

/**
 * 下面将在回溯过程中使用used数组进行去重
 */
class CombinationSum2_removeDuplication_40 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println("40.组合总和的结果为：" + new CombinationSum2_removeDuplication_40().combinationSum2(candidates, target));
    }

    /**
     * 回溯过程去重，前提是数组排序，使用uesed数组对使用过的数字进行记录（去重操作核心）
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        int[] used = new int[candidates.length];
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0, used);
        return result;
    }

    public void backTracking(int[] candidates, int target, int sum, int startIndex, int[] used) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum+candidates[i]<=target; i++) {
            // used[i - 1] == 1，说明同一树支candidates[i - 1]使用过，可以选取
            // used[i - 1] == 0，说明同一树层candidates[i - 1]使用过，不可以选取
            // 要对同一树层使用过的元素进行跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0) {
                continue;
            }
            path.add(candidates[i]);
            used[i] = 1;
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1, used);//注意此处与39题的区别，不能重复取同一个数
            sum -= candidates[i];
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }
}

/**
 * 下面将在回溯过程中直接使用startIndex进行去重
 */
class CombinationSum2_removeDuplication_Enhanced_40 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println("40.组合总和的结果为：" + new CombinationSum2_removeDuplication_Enhanced_40().combinationSum2(candidates, target));
    }

    /**
     * 回溯过程去重，前提是数组排序，使用startIndex进行去重
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    public void backTracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum+candidates[i]<=target; i++) {
            // 要对同一树层使用过的元素进行跳过
            //注意此处i>startIndex
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1);//注意此处与39题的区别，不能重复取同一个数
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}