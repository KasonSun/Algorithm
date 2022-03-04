package array.array02_removeelement;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 */
public class DeleteRepeatElement_26 {
    public static void main(String[] args) {
        int nums1[] = {1, 1, 2};
        int nums2[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("删除后的数组长度为：" + deleteRepeatElement(nums1));
        System.out.println("删除后的数组长度为：" + deleteRepeatElement(nums2));
    }

    /**
     * 采用双指针法实现(本题实际就是27题)
     *
     * @param nums
     * @return
     */
    public static int deleteRepeatElement(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];//注意此处与27的区别，left先加，在进行赋值
            }
        }
        return left + 1;//上面得到的是下标范围，因此长度加1
    }
}
