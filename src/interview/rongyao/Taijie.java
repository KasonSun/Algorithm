package interview.rongyao;

import java.util.Scanner;

/**
 * 给出n阶台阶，每次只可以前进一步或者两步，中途有一次机会可以后退一步，这次机会也可以不使用，到达最后一个台阶一共有多少种走法
 */
public class Taijie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==1){
            System.out.println(1);
            return;
        }
        //表示到达i阶有dp[i]种走法
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2]+dp[i - 1];
        }
        int result=dp[n];//最后位置不能回跳
        for(int i=1;i<n;i++){
            //i从1到n-1中途都可以回跳（但是只有一次）
            result+=(dp[i]*dp[n-i+1]);
        }
        System.out.println(result);
    }
}
