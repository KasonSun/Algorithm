package interview.xiechen;

import java.util.Arrays;
import java.util.Scanner;

public class Test01 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] nums = new int[num];
        for(int i=0;i<num;i++){
            nums[i] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(nums));
        for(int i=0;i<num;i++){
            if(nums[i]%2==0){
                System.out.println(nums[i]);
            }else{
                output(nums[i]);
            }
        }
    }
    public static void output(int num){
        int n=0;
        int count=num;
        while(count!=0){
            count/=10;
            n++;
        }
        int[] bit = new int[n];
        boolean[] even = new boolean[n];
        char[] chars=String.valueOf(num).toCharArray();
        for(int i=0;i<n;i++){
            int temp=chars[i]-'0';
            bit[i]=temp;
            if(temp%2==0){
                even[i]=true;
            }
            if(i==n-1 && temp%2==1){
                for(int j=0;j<n;j++){
                    if(even[j]){
                        int swap=bit[j];
                        bit[j]=bit[n-1];
                        bit[n-1]=swap;
                        System.out.println(trans(bit));
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }
    public static int trans(int[] nums){
        int sum=0;
        for(int i=0;i< nums.length;i++){
            sum=sum*10+nums[i];
        }
        return sum;
    }
}
