package sort;

import java.util.Arrays;

/**
 *  计数排序(桶排序思想，只不过每一个桶中只有相同数据)
 *      需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据。
 *      非原地稳定排序   time：平均O(n+k)  最好O(n+k)   最坏O(n+k)  space:O(k)
 *
 * 计数排序的基本思想是：
 *      对每一个输入的元素arr[i]，确定小于 arr[i] 的元素个数。
 *      所以可以直接把 arr[i] 放到它输出数组中的位置上。假设有5个数小于 arr[i]，所以 arr[i] 应该放在数组的第6个位置上。
 *
 *      待排序数组 int[] arr = {4,3,6,3,5,1};
 *      辅助计数数组 int[] bucket = new int[max - min + 1]; //该数组大小为待排序数组中的最大值减最小值+1
 *      输出数组 int[] res = new int[arr.length];
 *                1.求出待排序数组的最大值max=6， 最小值min=1
 *                2.实例化辅助计数数组bucket， bucket用来记录每个元素之前出现的元素个数
 *                3.计算 arr 每个数字应该在排序后数组中应该处于的位置，此时 help = [1,1,4,5,6,7];
 *                4.根据 help 数组求得排序后的数组，此时 res = [1,3,3,4,5,6]
 */
public class countSort {
    public static void main(String[] args) {
        int[] nums = {4, 3, 6, 3, 5, 1};
        System.out.println(Arrays.toString(countSort(nums)));
    }

    public static int[] countSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        //找出数组中的最大值
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
        }

        int[] bucket = new int[max + 1];
        //1.找出每个数字出现的次数
        for(int i = 0; i < nums.length; i++){
            bucket[nums[i]]++;
        }

        //2.计算每个数字应该在排序后数组中应该处于的位置
        for(int i = 1; i < bucket.length; i++){
            bucket[i] = bucket[i-1] + bucket[i];
        }

        //3.根据bucket数组获取当前值在结果数组中下标索引
        int res[] = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int index = --bucket[nums[i]];//当确定一个数以后，需要更新bucket数组--，操作都在这里实现了--bucket[nums[i]]（实现了索引index--和bucket[nums[i]]--）
            res[index] = nums[i];
        }
        return res;
    }
}
