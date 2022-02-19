package hashtable;

import java.util.HashMap;

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 */
public class FourSumII_454 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};

        System.out.println(fourSumII(nums1, nums2, nums3, nums4));
    }

    /**
     * 四个数组中求满足和条件的个数，每个数组出一个数
     * 思路：1.定义一个hashmap，key存放两数之和，value存放两数出现的次数
     *      2.遍历nums1和nums2，统计两个数组元素之和，以及出现的次数，放到map中
     *      3.遍历nums3和nums4，找到满足0-（nums3[i]+nums4[j]）值在map中出现的次数，使用count统计map中key对应value出现的次数
     *      4.返回count
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public static int fourSumII(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //遍历前两个数组
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int temp = nums1[i] + nums2[j];
                if (map.containsKey(temp)) {
                    map.put(temp, map.getOrDefault(temp, 0) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }

        //遍历后两个数组
        int count = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int temp = 0 - (nums3[i] + nums4[j]);
                if (map.containsKey(temp)) {
                    count += map.get(temp);
                }
            }
        }

        return count;
    }
}
