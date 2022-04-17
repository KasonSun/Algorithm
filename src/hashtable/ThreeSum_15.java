package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]

 * 输入：nums = []
 * 输出：[]

 * 输入：nums = [0]
 * 输出：[]
 */
public class ThreeSum_15 {
    /**
     * 注意：java中的list可以保存顺序（此题不易使用哈希表实现，一个数组中存在多组解，并且不可以重复）
     * 方法一：使用暴力法，三重for循环
     * 方法二：三指针法实现，需要去重
     *  思路：   1.首先将数组排序，然后有一层for循环，i从下标0的地方开始，同时定一个下标left 定义在i+1的位置上，定义下标right 在数组结尾的位置上。
     *          2.依然还是在数组中找到 abc 使得a + b +c =0，我们这里相当于 a = nums[i] b = nums[left] c = nums[right]。
     *          3.接下来如何移动left 和right呢， 如果nums[i] + nums[left] + nums[right] > 0 就说明 此时三数之和大了，因为数组是排序后了，所以right下标就应该向左移动，这样才能让三数之和小一些。
     *          如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动，才能让三数之和大一些，直到left与right相遇为止。
     *
     * 时间复杂度：O(n^2)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();//作为返回的结果列表
        if (nums == null || nums.length < 3) {
            return result;
        }

        //1.排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {//i的后界条件可以为i < nums.length，i < nums.length - 1实际上这几种条件都不会满足（三元组角度i < nums.length - 2比较好）
            //排序后如果第一个指针i所对元素的值>o，则一定不存在满足条件的情况
            if (nums[i] > 0) {
                return result;
            }

            //因为三个指针i，left以及righ都会移动，故要针对三种情况去重
            //i指针去重（i>0是为了排除i初始位置情况i=0则i-1失去意义）
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {//此处保证三个数，一定是<
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                }else{
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //左右left和right指针去重
                    while(left<right&&nums[right]==nums[right-1]) right--;//只是移动到了前一个相同的位置，此时还是之前满足=0的情况，因此下面的right--；left同理
                    while(left<right&&nums[left]==nums[left+1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }
}
