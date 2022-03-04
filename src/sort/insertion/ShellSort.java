package sort.insertion;

import java.util.Arrays;

/**
 *  希尔排序（缩小增量排序）
 *      原地不稳定的插入排序   time：平均O(n^1.3)  最好O(n)   最坏O(n^2)  space:O(1)
 *
 *      步骤：
 *          1.把记录按步长gap分组，对每组记录采用直接插入排序；
 *          2.随着步长的减小，所分成的组包含的记录越来越多，当步长减小到1时，整个数据合成一组有序记录，完成排序
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] nums = {5, 4, 5, 1, 7, 5, 8, 7};
        int[] nums = {5, -4, 15, 1, 27, 5, -8, 7};
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 初始时，有一个大小为 10 的无序序列。
     * 在第一趟排序中，我们不妨设 gap1 = N / 2 = 5，即相隔距离为 5 的元素组成一组，可以分为 5 组。
     * 接下来，按照直接插入排序的方法对每个组进行排序。
     * 在第二趟排序中，我们把上次的 gap 缩小一半，即 gap2 = gap1 / 2 = 2 (向下取整数)。这样每相隔距离为 2 的元素组成一组，可以分为 2 组。
     * 按照直接插入排序的方法对每个组进行排序。
     * 在第三趟排序中，再次把 gap 缩小一半，即gap3 = gap2 / 2 = 1。 这样相隔距离为 1 的元素组成一组，即只有一组。
     * 按照直接插入排序的方法对每个组进行排序。此时，排序已经结束。
     * 需要注意一下的是，图中有两个相等数值的元素 5 和 5 。我们可以清楚的看到，在排序过程中，两个元素位置交换了。
     * 所以，希尔排序是不稳定的算法。
     * @param nums
     */
    public static void shellSort(int[] nums) {
        //step:步长
        for (int step = nums.length / 2; step > 0; step /= 2) {
            //对一个步长区间进行比较 [step,arr.length)
            for (int i = step; i < nums.length; i+=step) {
                int temp = nums[i];
                int j = i;
                while (j >= step && temp < nums[j - step]) {
                    nums[j] = nums[j - step];
                    j -= step;
                }
                if (j != i) {
                    nums[j] = temp;
                }
            }
        }
    }
}
