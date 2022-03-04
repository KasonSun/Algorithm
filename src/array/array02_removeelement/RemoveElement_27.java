package array.array02_removeelement;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 你不需要考虑数组中超出新长度后面的元素
 *
 */
public class RemoveElement_27 {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 2;
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        System.out.println("暴力法返回数组的长度为：" + removeElement01(nums1, val1));
        System.out.println("快慢指针法返回数组的长度为：" + removeElement02(nums2, val2));
        System.out.println(Arrays.toString(nums2));

    }

    /**
     * 此处使用暴力解法，双重循环
     *
     * @param nums
     * @param val
     * @return
     */
    private static int removeElement01(int nums[], int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < len; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;//由于i后面的数值都往前移动了一位，此时下一轮循环i++，因此此处应该将i--
                // (此处必须-1，因为内层循环执行完下一轮会自增，如果不减一则会漏掉数没有进行比较)
                len--;//数组长度减去1
            }
        }
        return len;
    }

    /**
     * 使用双指针法进行求解，即（快慢指针法），该方法在数组和链表以及字符串的操作中比较常见
     *
     * @param nums
     * @param val
     * @return
     */
    private static int removeElement02(int[] nums, int val) {
        int len = nums.length;
        int left = 0;
        for (int right = 0; right < len; right++) {
            if (nums[right] != val) {
                nums[left++] = nums[right];//left始终指向right的前一个位置
            }
        }
        return left;
    }
}
