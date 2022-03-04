package array.array03_minlensubarray;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 */
public class MinLenSubArray_209 {
    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 2, 4, 3};
        int s = 7;
        System.out.println("暴力法最小长度子数组为：" + minLenSubArray01(nums, s));
        System.out.println("双指针法最小长度子数组为：" + minLenSubArray02(nums, s));
    }

    /**
     * 1.两个for循环暴力破解
     * O(n^2)
     * @param nums
     * @param s
     * @return
     */
    private static int minLenSubArray01(int[] nums, int s) {
        int result = Integer.MAX_VALUE;//最终的子序列结果
        int sum = 0;//用来计算子序列数值之和
        int subLength = 0;//临时存放子序列长度
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {//一旦条件成立，更新result
                    subLength = j - i + 1;
                    result = result < subLength ? result : subLength;
                    break;
                }

            }
        }
        return (result == Integer.MAX_VALUE) ? 0 : result;
    }

    /**
     * 2.滑动窗口（实际就是双指针法）
     * 不断调节子序列的起止位置进行求解
     * O(n)
     * @param nums
     * @param s
     * @return
     */
    private static int minLenSubArray02(int nums[], int s) {
        int result = Integer.MAX_VALUE;//最终的子序列结果
        int sum = 0;//滑动窗口数值之和
        int subLength = 0;//滑动窗口子序列长度
        int i = 0;//滑动窗口其起始位置
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= s) {
                subLength = j - i + 1;
                result = result < subLength ? result : subLength;
                sum-=nums[i++];//注意此处是滑动窗口的精髓，通过已经满足的条件的序列，不断从满足条件序列的起始位置进行缩小窗口继续判断，进而缩小序列长度
            }

        }
        return (result == Integer.MAX_VALUE) ? 0 : result;
    }
}
