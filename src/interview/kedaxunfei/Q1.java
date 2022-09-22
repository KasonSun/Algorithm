package interview.kedaxunfei;

import java.util.Scanner;

/**
 *
 * Q1 取峰值
 input:
 11
 9068 -4506 -2044 8005 -10782 9152 -3785 -3048 8440 -10099 7325

 output:
 621296804 -374615533 -10315610 305239664 -353214964 152103579 152103579 -353214964 305239664 -10315610 - 374615533
 -374615533
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        long[] x = new long[N];
        String[] s = sc.nextLine().split(" ");
        for(int i=0;i<N;i++){
            x[i] = Integer.parseInt(s[i]);
        }
        long[] r = new long[N];
        for(int k=0;k<N;k++){
            long temp1=0;
            for(int n=0;n<=N-k-1;n++){
                temp1+=x[n]*x[n+k];
            }
            for(int n=N-k;n<=N-1;n++){
                temp1+=x[n]*x[n+k-N];
            }
            r[k]=temp1;
            System.out.print(temp1+" ");
        }
        System.out.println();
        long value = r[0];
        for(int i=2;i<N;i++){
            value &= r[i];
            if(i==N-1 && value==r[0]){
                System.out.println("NO");
                return;
            }else{
                continue;
            }
        }

        long result=0;
        for(int i=2;i<N;i++){
            long abs1 = Math.abs(r[i-1]);
            long abs2 = Math.abs(r[i]);
            result = abs1 > abs2 ? r[i-1] : r[i];
        }
        System.out.println(result);
    }
}
