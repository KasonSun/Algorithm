package interview.bytedance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 三数之和特殊版
 * 他要你满足 x + y + z == k
 * x、y、z 都是数组中的元素，k是数组中任意一个元素，且还要满足顺序关系
 * 在数组中，任意i,j,k都能找到l，使得ai+aj+ak=al,（1<=i<j<k<=n, 1<=l<=n）
 * 容斥原理，你出现了二个以上的正数或者负数，就不能满足这个条件了
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            sc.nextLine();
            int[] nums = new int[n];
            List<Integer> list = new ArrayList<>();
            String[] str = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                nums[i] = Integer.valueOf(str[i]);
                list.add(nums[i]);
            }
            fourSum(nums,list);
        }
    }

    public static void fourSum(int[] nums, List<Integer> list) {
        List<Integer> actualList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    actualList.add(sum);
                }
            }
        }
        for (int i = 0; i < actualList.size(); i++) {
            int temp = actualList.get(i);
            if (i == actualList.size() - 1 && list.contains(temp)) {
                System.out.println("YES");
            }
            if (!list.contains(temp)) {
                System.out.println("NO");
                return ;
            }
        }
    }
}
