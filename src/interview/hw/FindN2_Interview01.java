package interview.hw;

import java.util.Arrays;

/**
 * 华为一面手撕：
 *      找到数组中某个数大于n/2,n为数组长度
 */
public class FindN2_Interview01 {
    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 1, 1, 3, 1};
        int[] nums1 = {1, 3, 1};
        int[] nums2 = {1};
        int[] nums3 = {3, 1, 1, 3, 3};
        System.out.println(findCount(nums));
    }

    public static int findCount(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
                if (count > n / 2) {
                    return nums[i];
                }
            }else{
                count = 1;
            }
        }
        return -1;
    }
}
