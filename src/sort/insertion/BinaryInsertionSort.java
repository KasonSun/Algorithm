package sort.insertion;

import java.util.Arrays;

/**
 *  折半插入排序
 *      原地稳定的插入排序   time：平均O(n^2)  最好O(n)   最坏O(n^2)  space:O(1)
 *
 *      步骤：
 *          1.从第二个元素开始（一个元素自有序），记录当前值temp；
 *          2.将当前值与从已经排序的右边开始向左比较（使用二分查找找到插入位置），找到比temp小的数；
 *          3.找到插入位置，将有序部分插入位置到最后一个有序位置进行移动，将待插入值插入有序部分
 */
public class BinaryInsertionSort {
    public static void main(String[] args) {
        int[] nums = {5, 4, 5, 1, 7, 5, 8, 7};
        binaryInsertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void binaryInsertionSort(int[] nums) {
        int temp;
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i]; // 记录要插入的数据
//            int j = i; // 从已经排序的序列最右边的开始比较，找到比temp小的数
            //下面区别与直接插入，使用二分法搜索插入位置
            int left=0;
            int right = i - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] > temp) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            //退出循环时一定是left>right && left-right=1   此时（left位置）或者（right后一个位置）是插入位置
            for (int j = i - 1; j >= left; j--) {
                nums[j + 1] = nums[j];
            }
            nums[left] = temp;
        }
    }
}
