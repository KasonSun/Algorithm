package sort.selection;

import java.util.Arrays;

/**
 * 简单选择排序
 *      原地不稳定的选择排序   time：平均O(n^2)  最好O(n^2)   最坏O(n^2)  space:O(1)
 *
 *      步骤：
 *          1.第一层for顺序定位一个值为最小数值索引minIndex；
 *          2.第二层for进行比较，将较小的数值索引不断进行迭代赋值给minIndex；
 *          3.内存for结束进行交换，此时可以找到数组中第一个最小值放在了第一个位置上，重复123步骤
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] nums = {5, 4, 5, 1, 7, 5, 8, 7};
        selectSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, min, i);
        }
    }
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
