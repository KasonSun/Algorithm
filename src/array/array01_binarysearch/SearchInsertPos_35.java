package array.array01_binarysearch;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1: 输入: [1,3,5,6], 5 输出: 2
 * 示例 2: 输入: [1,3,5,6], 2 输出: 1
 * 示例 3: 输入: [1,3,5,6], 7 输出: 4
 * 示例 4: 输入: [1,3,5,6], 0 输出: 0
 *
 * 思路
 */

public class SearchInsertPos_35 {
    public static void main(String[] args) {
        int [] nums={1,3,5,6};
        System.out.println(searchInsertPos(nums,5));
        System.out.println(searchInsertPos(nums,2));
        System.out.println(searchInsertPos(nums,7));
        System.out.println(searchInsertPos(nums,0));
    }
    public static int searchInsertPos(int nums[], int target){
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return end + 1;//
    }
}
