package sort;

import java.util.Arrays;

/**
 *  归并排序（分治法）
 * 非原地稳定排序   time:最好 O(nlogn) 最坏O(nlogn) 平均O(nlogn)  space:O(n)
 *
 * 把长度为n的输入序列分成两个长度为n/2的子序列
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 5, 9, 4, 3, 7};
        System.out.println(Arrays.toString(mergeSort(arr)));
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {//i指向left数组，j指向right数组
            if (i >= left.length) {//说明此时left数组已经比较归并完毕，right可能还存在没有规并的值
                result[index] = right[j++];
            } else if (j >= right.length) {//说明此时right数组已经归并完毕，left数组可能还存在没归并的值
                result[index] = left[i++];
            } else if (left[i] > right[j]) {//此时左边数组的值大于右边，则将右边放入结果数组
                result[index] = right[j++];
            } else {//此时左边数组的值小于右边，则将左边的值放入结果数组
                result[index] = left[i++];
            }
        }
        return result;
    }
}
