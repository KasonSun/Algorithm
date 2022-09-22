package interview.meituan;

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str1 = sc.nextLine().split(" ");
        String[] str2 = sc.nextLine().split(" ");
        int n = Integer.valueOf(str1[0]);
        int m = Integer.valueOf(str1[1]);
        double[] nums = new double[n+1];
        int[] adjust = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Double.valueOf(str2[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            if (nums[i] == 0) {
                adjust[i] = 0;
            }else{
                int count=0;
                if(nums[i]%2==1){
                    for (int k = 0; k <= 9; k++) {
                        nums[i]=nums[i]+Math.pow(10, k);
                        count++;
                        if (nums[i] % 4 == 0) {
                            adjust[i] = count;
                            break;
                        }
                        nums[i]=nums[i]%4;
                    }
                }else{
                    for (int k = 1; k <= 9; k++) {
                        nums[i]=nums[i]+Math.pow(10, k);
                        count++;
                        if (nums[i] % 4 == 0) {
                            adjust[i] = count;
                            break;
                        }
                        nums[i]=nums[i]%4;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(adjust[i]+" ");
        }
    }
}
