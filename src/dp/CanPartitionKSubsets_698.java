package dp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 */
public class CanPartitionKSubsets_698 {
    public static void main(String[] args) {

    }

    /**
     * 回溯法
     *      将集合中的所有元素分为k组，且每组的总和相等，考虑使用回溯。
     *      首先如果数组中的元素总和不是k的倍数可以直接返回false，这种情况下肯定不能分成总和相等的k个子集。
     *      使用一个visited数组记录已访问过的元素，访问过的元素不能再次访问
     *
     * @param nums
     * @param k
     * @return
     */

    int sum;
    int target;
    int[] visited;
    public boolean canPartitionKsubsets(int[] nums, int k) {
        sum = IntStream.of(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        visited = new int[nums.length];
        Arrays.sort(nums);
        target = sum / k;
        return backTracking(nums, k, 0, 0);
    }

    public boolean backTracking(int[] nums, int k, int startIndex, int count) {
        //完成划分K组的要求
        if (k == 1) {
            return true;
        }

        //当前划分字节中元素总和为目标值（数组总和1/k值）
        if (count == target) {
            //还需要划分k-1个字节，且总和要重置为0，并从头开始寻找
            return backTracking(nums, k - 1, 0, 0);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (visited[i] == 1) {//如果该元素已经被访问过了
                continue;
            }
            //因为对数组已经排序，如果当前count+nums[i]已经大于target
            //数组后面的元素只可能大于等于num[i]不用继续向后判断，直接break
            if (count + nums[i] > target) {
                break;
            }
            //count + nums[i] <= target表示可能存在满足结果的值，继续递归
            visited[i] = 1;//标记当前元素已经访问过
            if (backTracking(nums, k, i, count + nums[i])) {//如果加入的元素可以满足要求
                return true;
            }
            visited[i] = 0;//回溯
        }
        return false;
    }
}
