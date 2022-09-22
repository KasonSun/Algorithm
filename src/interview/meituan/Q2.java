package interview.meituan;

import java.util.Scanner;

/**
 * Q2:调整数组中的一个数为当前值+1，使得所有数相加不为0，相乘不为0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] str = sc.nextLine().split(" ");
        int[] nums = new int[n];
        int sum = 0;
        long mutil = 1;
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(str[i]);
            sum += nums[i];
            mutil *= nums[i];
        }
        if (sum != 0 && mutil != 0) {
            System.out.println(0);
            return;
        }
        adjust(nums);
    }

    private static void adjust(int[] nums) {
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] + 1;
            count++;
            if (sum(nums) != 0 && mutil(nums) != 0) {
                System.out.println(count);
                return;
            }
        }
    }

    private static int sum(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }

    private static long mutil(int[] nums) {
        int result = 1;
        for (int num : nums) {
            result *= num;
        }
        return result;
    }
}
