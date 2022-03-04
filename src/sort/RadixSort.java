package sort;

import java.util.Arrays;

/**
 *  基数排序
 *          非原地稳定排序   time：平均O(n*k)  最好O(n*k)   最坏O(n*k)  space:O(n+k)
 *
 *       步骤：1.按个位排序收集；
 *            2.按百位排序收集，以此类推，直到最高位
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] nums = {26, 3, 49, 556, 81, 9, 863, 0};
        radixSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void radixSort(int[] nums) {
        //分解数字
        //value/div%10
        //找到最大值
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        //分别取各个位数排序     value/div%10
        for (int div = 1; max / div > 0; div *= 10) {
            //创建十个桶
            int[] bucket = new int[10];
            //创建临时数组，统计相同位值的个数
            int[] temp = new int[nums.length];

            //计算每个桶中数量
            for (int i = 0; i < nums.length; i++) {
                bucket[nums[i] / div % 10]++;
            }
            //计算每个桶中的累计数据
            for (int i = 1; i < bucket.length; i++) {
                bucket[i] = bucket[i-1] + bucket[i];
            }

            //从后向前（保证稳定性）遍历nums数组，放在temp中
            for (int i = nums.length - 1; i >= 0; i--) {
                temp[bucket[nums[i] / div % 10]-1] = nums[i];
                bucket[nums[i] / div % 10]--;
            }

            //将当前位排序结果再放回nums数组，进行下一位的排序
            for (int i = 0; i < temp.length; i++) {
                nums[i] = temp[i];
            }
        }
    }
}
