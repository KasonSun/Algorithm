package greedy;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组
 *  选择某个下标 i并将 nums[i] 替换为 -nums[i] 。
 *  重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *  以这种方式修改数组后，返回数组 可能的最大和 。
 *
 *
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 *
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 */
public class LargestSumAfterKNegations_1005 {
    public static void main(String[] args) {
        int[] nums = {4,2,3};
        int[] nums1 = {2, 3, -1, 5, -4};
        int k = 1;
        System.out.println("k次取反后的最大数组和为："+largestSumAfterKNegation(nums1,k));
    }

    /**
     * 贪心法：时间复杂度 O(nlogn)  空间复杂度O(n)  --都来自快速排序
     *      思路：局部最优：让绝对值大的负数变为正数，当前数值达到最大； 整体最优：整个数组和达到最大； 局部最优可以达到全局最优
     *          如果将负数变为正数了，k值依然大于0，此时的问题是一个有序正整数序列，如何转变K次正负，让数组和达到最大，此时又是一个贪心问题：
     *          局部最优：只找数值最小的正整数进行反转，当前数值可以达到最大（例如正整数数组{5, 3, 1}，反转1 得到-1 比 反转5得到的-5 大多了），全局最优：整个数组和达到最大。
     *      步骤：①将数组绝对值从大到小排序，注意要按照绝对值的大小
     *           ②从前向后遍历，遇到负数将其变为正数，同时k--
     *           ③如果k还大于0，那么反复转变数值最小的元素，直到k为0
     *           ④求和
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegation(int[] nums, int k) {
        //①将数组绝对值从大到小排序，注意要按照绝对值的大小
//        quickSort(nums);
        sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            //②从前向后遍历，遇到负数将其变为正数，同时k--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        //此时for循环结束只有两种情况k=0，nums中还有可能存在负数，此时直接下面返回；k>0，nums是全为正数的递减序列
        //如果k还大于0，那么反复转变数值最小的元素，直到k为0
        if (k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }
        return Arrays.stream(nums).sum();
    }

    /**
     * 使用快排(将数组绝对值从大到小排序，注意要按照绝对值的大小,快排变为比较绝对值并且比较规则要与之前相反)
     * @param nums
     */
    public static void quickSort(int[] nums){
        qsort(nums, 0, nums.length-1);
    }
    private static void qsort(int[] nums, int low, int high){
        if (low < high){
            int pivot=partition(nums, low, high);        //将数组分为两部分
            qsort(nums, low, pivot-1);                   //递归排序左子数组
            qsort(nums, pivot+1, high);                  //递归排序右子数组
        }
    }
    private static int partition(int[] nums, int low, int high){
        int pivot = nums[low];     //枢轴记录
        while (low<high){
            while (low<high && Math.abs(nums[high])<Math.abs(pivot)) --high;
            nums[low]=nums[high];             //交换绝对值比枢轴大的记录到左端
            while (low<high && Math.abs(nums[low])>Math.abs(pivot)) ++low;
            nums[high] = nums[low];           //交换绝对值比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        nums[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }

    public static void sort(int[] nums, int left, int right){
        if (right - left < 1) {
            return;
        }
        int temp=nums[left], min=left, max=right;
        while (left < right) {
            while(left<right && Math.abs(nums[right])<=Math.abs(temp)) right--;
            nums[left]=nums[right];
            while(left<right && Math.abs(nums[left])>=Math.abs(temp)) left++;
            nums[right]=nums[left];
        }
        nums[left]=temp;
        sort(nums, min, left-1);
        sort(nums, left + 1, max);
    }
}
