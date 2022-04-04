package array;

import java.util.Arrays;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 输入：[3,2,3]
 * 输出：3
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */
public class MajorityElement_169 {
    /**
     * 1.排序后直接循环实现
     * @param nums
     * @return
     */
    public static int majorityElement01(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
                if (count > (nums.length / 2)) {
                    return nums[i];
                }
            }else{
                count = 0;
            }
        }
        return -1;
    }

    /**
     * 1.排序后直接循环实现(改进)
     *      实际上他们一定会在n/2下标处有重叠，因此直接排序后返回nums[n/2]一定正确
     * @param nums
     * @return
     */
    public static int majorityElement02(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
