package hashtable;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 说明：输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。我们可以不考虑输出结果的顺序。
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 */
public class ArraysIntersectionII_350 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(arraysIntersectionII01(nums1, nums2)));
        System.out.println(Arrays.toString(arraysIntersectionII02(nums1, nums2)));
    }

    /**
     * 方法一：三个哈希表实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] arraysIntersectionII01(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> map3 = new HashMap<>();//存放相交部分的数字以及对应的次数

        //遍历nums1存入map1，注意重复字符统计
        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }

        //遍历nums1存入map1，注意重复字符统计
        for (int i : nums2) {
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

        //遍历HashMap，并进行判断
        int count = 0;//统计数组长度
        for (int i : map1.keySet()) {
            if (map2.containsKey(i)) {
                int value1 = map1.get(i);
                int value2 = map2.get(i);
                if (value1 > value2) {
                    map3.put(i, value2);
                    count += value2;
                } else {
                    map3.put(i, value1);
                    count += value1;
                }
            }
        }

        //遍历map3，将对应的数字按照次数一个一个存入数组
        int[] result = new int[count];
        int index = 0;
        for (int i : map3.keySet()) {
            int nums = map3.get(i);
            while (nums-- != 0) {
                result[index++] = i;
            }
        }
        return result;
    }

    /**
     * 方法二：一个哈希表实现
     * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中"出现次数的最小值"。
     * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，
     * 如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] arraysIntersectionII02(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {//此处为了降低空间复杂度
            return arraysIntersectionII02(nums2, nums1);
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i : nums1) {
            hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
        }

        int count = 0;//记录交集元素个数
        int[] result = new int[nums1.length];
        for (int i : nums2) {
            //存在且计数为正（可能出现重复的数字，在执行几轮后值变为0，但是键值还存在，但题目要求返回出现次数的最小值），则将该数字添加到结果，并减少哈希表中该数字出现的次数。
            if (hashMap.containsKey(i) && hashMap.get(i) > 0) {
                result[count++] = i;
                hashMap.put(i, hashMap.getOrDefault(i, 0) - 1);
            }
        }
        return Arrays.copyOfRange(result, 0, count);//前闭后开区间， count退出循环刚好指向没有值的后一个数组空间
    }
}
