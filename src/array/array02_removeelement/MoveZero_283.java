package array.array02_removeelement;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * (必须在原数组上操作，不能拷贝额外的数组。尽量减少操作次数。)
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 */
public class MoveZero_283 {
    public static void main(String[] args) {
        int nums[] = {0, 1, 0, 3, 12};
        int output[] = new int[nums.length];
        output = move_Zero(nums);
        System.out.println(Arrays.toString(output));
    }

    /**
     * 使用双指针法实现：一个存0位置，一个存非0位置，从前往后顺序交换
     *
     * @param nums
     * @return
     */
    private static int[] move_Zero(int[] nums) {
        int len = nums.length;

        int left = 0;
        int temp = -1;//作为交换临时变量
        for (int right = 1; right < len; right++) {
            if (nums[left] == 0) {
                if (nums[right] != 0) {//快指针不等于0进行交换，将0往后推
                    temp = nums[right];
                    nums[left++] = temp;//交换完成需要往后移动一位进行下一次交换
                    nums[right] = 0;
                }
            }
        }
        return nums;
    }


}
