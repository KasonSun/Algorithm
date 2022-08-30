package test;

import java.util.Arrays;

public class Unique {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        int[] nums1 = {4,1,2,1,2};
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        System.out.println(result);
    }
}
