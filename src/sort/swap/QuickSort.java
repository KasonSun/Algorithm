package sort.swap;

import java.util.Arrays;

/**
 *  快速排序（分治法）
 * 原地不稳定交换排序   time:最好 O(nlogn) 最坏O(n^2) 平均O(nlogn)  space:O(logn)
 * 算法步骤
 * 从数列中挑出一个元素，称为 “基准”（pivot）;
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {8, 5, 9, 4, 3, 7};
        quickSortAdvanced(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 1.改进快排
     *
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSortAdvanced(int[] nums, int left, int right) {
        if (right - left < 1)
            return;
        int temp = nums[left], min = left, max = right;
        while (left < right) {
            //此时枢轴元素的值为temp = nums[left]，在左边，因此应该先从右边开始
            while (left < right && nums[right] > temp) right--;
            nums[left] = nums[right];//存在nums[right]>temp,立即覆盖到左边（为什么是覆盖，因为此时左边的值作为枢轴元素已经被保留下来）

            while (left < right && nums[left] < temp) left++;
            nums[right] = nums[left];
        }
        nums[left] = temp;
        quickSort(nums, min, left - 1);//此时枢轴已经确定位置，因此以枢轴元素为中心分左右两半继续递归
        quickSort(nums, left + 1, max);
    }

    /**
     * 2.快排分步
     *
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        return quickSort(nums, 0, nums.length - 1);
    }

    public static int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);//一轮确定一个partitionIndex
            quickSort(nums, left, partitionIndex - 1);
            quickSort(nums, partitionIndex + 1, right);
        }
        return nums;
    }

    public static int partition(int[] nums, int left, int right) {
        //设定基准值
        int pivot = left;
        int index = pivot + 1;//index始终指向每一轮比较能够确定位置的元素，指向一轮比较结束枢轴元素的索引的后一个位置
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, pivot, index - 1);
//        System.out.println(Arrays.toString(nums));
        return index - 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
