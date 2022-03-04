package array.array01_binarysearch;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。（注意：此时数组可能存在重复）
 * 时间复杂度O（logn）
 * 示例 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 */
public class SearchFirstLastPos_34 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] pos = new int[2];
        pos = searchFirstLastPos(nums, 8);
        System.out.println(Arrays.toString(pos));
        pos = searchFirstLastPos(nums, 6);
        System.out.println(Arrays.toString(pos));
        pos = searchFirstLastPos(nums, 0);
        System.out.println(Arrays.toString(pos));

//        System.out.println(searchFirstLastPos(nums,6));
//        System.out.println(searchFirstLastPos(nums,0));

    }

    public static int[] searchFirstLastPos(int nums[], int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstPos = findFirstPos(nums, target);//第一个位置
        if (firstPos == -1) {
            return new int[]{-1, -1};
        }
        int lastPos = findLastPos(nums, target);//最后一个位置
        return new int[]{firstPos, lastPos};
    }

    public static int findLastPos(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end + 1) >> 1; //if中有两个start在左边，start=mid，start=mid+1，此时需要进行向上取整，否则会出现死循环
            if (nums[mid] == target) {
                //下一轮搜索区间就是[mid, end]
                start = mid;
            } else if (nums[mid] < target) {
                //下一轮搜索区间就是[mid+1, end]
                start = mid + 1;
            } else {
                //下一轮搜索区间就是[start, mid-1]
                end = mid - 1;
            }
        }
        return start; //由于已经做了if (firstPos == -1) {return new int[]{-1,-1}; }判断，因此这里可以直接返回
    }

    public static int findFirstPos(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == target) {
                //下一轮搜索区间就是[start, mid]
                end = mid;
            } else if (nums[mid] < target) {
                //下一轮搜索区间就是[mid+1, end]
                start = mid + 1;
            } else {
                //下一轮搜索区间就是[start, mid-1]
                end = mid - 1;
            }
        }
        //退出循环一定有start==end，但是并不能保证目标一定会出现，即不一定有nums[mid] == target
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

}
