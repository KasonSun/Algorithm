package hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 力扣260. 牛客bm52
 * 找出数组中只出现一次的两个数字(其他数字都只出现两次)
 */
public class FindNumsAppearOnce_NC_BM_52 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 2, 9};
        System.out.println(Arrays.toString(new FindNumsAppearOnce_NC_BM_52().findNumsAppearOnce02(array)));
    }
    //1.哈希法  使用set实现  n - n
    public int[] findNumsAppearOnce01(int[] array) {
        if (array.length == 2 && array[0] != array[1]) {
            return array;
        }
        int[] result = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int temp : array) {
            if (set.contains(temp)) {
                set.remove(temp);
            }else{
                set.add(temp);
            }
        }
        int i = 0;
        for (Integer temp : set) {
            result[i++] = temp;
        }
        return result;
    }

    //2.位运算(异或)  n - 1
    //异或举例：4⊕ 4  二进制异或：0100 ⊕  0100 =0000
    //        4⊕ 4⊕ 5 二进制异或：0100⊕ 0100⊕ 0101=0101
    public int[] findNumsAppearOnce02(int[] array) {
        if (array.length == 2 && array[0] == array[1]) {
            return array;
        }
        //1.先将全部数进行异或运算，得到其中两个只出现一次的数的异或结果
        int temp=0;
        for (int num : array) {
            temp ^= num;
        }

        //2.找到temp作为分组的第一位（第一个相同位，说明之前那一位异或结果为0）
        int mask = 1;
        while ((temp & mask) == 0) {
            mask <<= 1;
        }

        //3.以上面得到的那一位进行分组，转换为两组分别异或得到只出现一次的那个数
        int a = 0;
        int b = 0;
        for (int num : array) {
            if ((num & mask) == 0) {
                a ^= num;
            }else{
                b ^= num;
            }
        }

        //题目要去小的放在前面，判断一下
        if (a > b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        return new int[]{a, b};
    }
}
