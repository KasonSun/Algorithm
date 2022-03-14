package hashtable;


import java.util.*;

/**
 * 题意：给定两个数组，编写一个函数来计算它们的交集。
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 */
public class ArraysIntersection_349 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(arraysIntersectionBruteForce(nums1, nums2)));
    }
    /**
     * 使用HashSet实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] arraysIntersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        //遍历nums1，存入set1
        for (int i : nums1) {
            set1.add(i);
        }

        //遍历nums2，并判断是否在1中出现，若出现存入set2
        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }

        //遍历完成将HashMap转换为数组返回
        int[] result = new int[set2.size()];
        int index = 0;
        for (int i : set2) {
            result[index++] = i;
        }
        return result;
    }

    /**
     * 暴力法
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] arraysIntersectionBruteForce(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                } else if (nums1[i] > nums2[j]) {
                    continue;
                }else{
                    break;
                }
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (int i : set) {
            result[index++] = i;
        }
        return result;
    }
}
