package interview.xiechen;

import java.util.Arrays;
import java.util.Scanner;

public class Test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        int max = Integer.MIN_VALUE;
        int index=-1;
        for(int i=1;i<nums.length;i++){
            int temp=Math.abs(nums[i]-nums[i-1]);
            if(temp>max){
                max=temp;
                index=i;
            }
        }
        if(index-1==0){
            System.out.println(Math.abs(nums[index]-nums[index+1]));
            return;
        }
        if(index==nums.length-1){
            System.out.println(Math.abs(nums[index-2]-nums[index-1]));
            return;
        }
        System.out.println((nums[index-1]+nums[index+1])/2);
    }
}
