package array.array01_binarysearch;

/**
 * 二分思想：有序数组，元素无重复元素
 * 二分查找:
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 */
public class BinarySearch_704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        System.out.println("二分搜索的结果为:" + binarySearch(nums, target));
    }

    public static int binarySearch(int nums[], int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) { //此处是前闭后闭区间，因此需要<=，后面需要end = mid - 1;start = mid + 1;
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
