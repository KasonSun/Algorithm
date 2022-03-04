package array.array01_binarysearch;

import java.util.Arrays;

/**
 * 改进:SearchFirstLastPos_34中方法实现边界问题容易出错
 * 此处则很清晰，找到原值向两边扩散查找，中心扩散法
 *
 */
public class SearchFirstLastPosII_34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] pos = new int[2];
        pos = searchRange(nums, 8);
        System.out.println(Arrays.toString(pos));
    }
    public static int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target); // 二分查找

        if (index == -1) { // nums 中不存在 target，直接返回 {-1, -1}
            return new int[] {-1, -1}; // 匿名数组
        }
        // nums 中存在 targe，则左右滑动指针，来找到符合题意的区间
        //“中心扩散法”
        int left = index;
        int right = index;
        // 向左滑动，找左边界
        while (left - 1 >= 0 && nums[left - 1] == nums[index]) { // 防止数组越界。逻辑短路，两个条件顺序不能换
            left--;
        }
        // 向左滑动，找右边界
        while (right + 1 < nums.length && nums[right + 1] == nums[index]) { // 防止数组越界。
            right++;
        }
        return new int[] {left, right};
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 不变量：左闭右闭区间

        while (left <= right) { // 不变量：左闭右闭区间
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1; // 不变量：左闭右闭区间
            }
        }
        return -1; // 不存在
    }
}
