package sort.swap;

import java.util.Arrays;

/**
 *  冒泡排序
 *      原地稳定的交换排序   time：平均O(n^2)  最好O(n)   最坏O(n^2)  space:O(1)
 *
 *      改进：设置标志位：某次内层循环没有交换，说明此时已经有序，排序结束
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {5, 4, 5, 1, 7, 5, 8, 7};
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        bubbleSortAdvanced(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 直接双重for循环
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    /**
     * 冒泡改进
     * @param nums
     */
    public static void bubbleSortAdvanced(int[] nums) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                    flag = true;
                }
            }
            if (!flag) {
                return;//break;
            }
        }
    }


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
