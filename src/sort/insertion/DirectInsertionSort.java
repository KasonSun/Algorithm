package sort.insertion;

import java.util.Arrays;

/**
 *  直接插入排序
 *      原地稳定的插入排序   time：平均O(n^2)  最好O(n)   最坏O(n^2)  space:O(1)
 *
 *      步骤：
 *          1.从第二个元素开始（一个元素自有序），记录当前值temp；
 *          2.将当前值与从已经排序的右边开始向左比较，找到比temp小的数；
 *          3.如果能找到，则当前索引位置即是要插入的位置；如果找不到说明已经有序；
 */
public class DirectInsertionSort {
    public static void main(String[] args) {
        int[] nums = {5, 4, 5, 1, 7, 5, 8, 7};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));

    }

    public static void insertionSort(int[] nums) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i]; // 记录要插入的数据
            int j = i; // 从已经排序的序列最右边的开始比较，j作为游标，找到比temp小的数
            while (j >=1 && temp < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            //已经有序序列存在比其小的数，插入
            if (j != i) {
                nums[j] = temp;
            }

        }
    }
}
