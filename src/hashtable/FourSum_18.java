package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 */
public class FourSum_18 {
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(forSum(nums, target).toString());

    }

    /**
     * 双指针法
     * 思路：与三数之和的思路类似，只是多了一层for循环（双指针法需要去重）
     *      1.首先将数组排序，然后两层for循环，i从下标0的地方开始，j从1开始，同时定一个下标left 定义在j+1的位置上，定义下标right 在数组结尾的位置上。
     *      2.依然还是在数组中找到 abcd 使得a + b +c +d =0，我们这里相当于 a = nums[i] b = nums[j] c = nums[left] d = nums[right]。
     *      3.接下来如何移动left 和right呢， 如果nums[i] + nums[j] + nums[left] + nums[right] > 0 就说明 此时四数之和大了，因为数组是排序后了，所以right下标就应该向左移动，这样才能让四数之和小一些。
     *      如果 nums[i] + nums[j] + nums[left] + nums[right] < 0 说明 此时 四数之和小了，left 就向右移动，才能让四数之和大一些，直到left与right相遇为止。
     *
     * 时间复杂度：O(n^3)
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> forSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //此处不需要进行下面的判断，因为target是任意值，而区别三数之和的0，任意值就有可能出现当target=-4时，若此时nums[i]=-3>-4直接返回就会出现错误，
            // 因为后面还有存在满足条件的组合存在，如另外数分别-1,0,0
//            if (nums[i] > target) {
//                return result;
//            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {//此处的j=i+1实际上是需要满足j一定要在i后面，实际上i，j，left，right一定有小于的关系i<j<left<right

                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }


                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
