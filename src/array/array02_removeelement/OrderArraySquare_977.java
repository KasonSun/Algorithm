package array.array02_removeelement;

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * （请你设计时间复杂度为 O(n) 的算法解决本问题）
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 */
public class OrderArraySquare_977 {
    public static void main(String[] args) {
        int nums[] = {-4, -1, 0, 3, 10};
        System.out.println("排序数组的平方为：" + Arrays.toString(orderArraySquare(nums)));
    }

    /**
     *
     * 1.直接对平方后的数组进行排序
     * 2.双指针法（分别指向0和n-1，选择较大的那个数的平方逆序放入额外数组，这种实现的基础是数组有序）
     * "本题实际是借助额外的数组进行实现"
     * time O(n), space O(1)  除了存储答案的空间外，只需要O(1)
     * @param nums
     * @return
     */
    public static int[] orderArraySquare(int nums[]) {
        int len = nums.length;
        int[] result = new int[len];
        for (int i = 0, j = len - 1, pos = len - 1; i <= j; ) {//pos用来作为新数组存放数据的游标
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[pos--] = nums[i] * nums[i];
                i++;
            } else {
                result[pos--] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }
}
