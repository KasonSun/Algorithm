package sort.swap;

import java.util.Arrays;

/**
 * 快速排序（分治法）
 *      原地不稳定交换排序   time:最好 O(nlogn) 最坏O(n^2) 平均O(nlogn)  space:O(logn)
 * 算法步骤
 * 从数列中挑出一个元素，称为 “基准”（pivot）;
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 5, 9, 4, 3, 7};
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static int[] sort(int[] arr) {
        return quickSort(arr, 0, arr.length-1);
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);//一轮确定一个partitionIndex
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    public static int partition(int[] arr, int left, int right) {
        //设定基准值
        int pivot = left;
        int index = pivot + 1;//index始终指向每一轮比较能够确定位置的元素，指向一轮比较结束枢轴元素的索引的后一个位置
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
//        System.out.println(Arrays.toString(arr));
        return index - 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
