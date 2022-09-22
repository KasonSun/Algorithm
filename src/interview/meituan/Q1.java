package interview.meituan;

import java.util.Scanner;

/**
 * Q1：同时写到编号为K的题目谁的时间少
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; i++) {
            String[] str = sc.nextLine().split(" ");
            int n = Integer.valueOf(str[0]);
            int x = Integer.valueOf(str[1]);
            int y = Integer.valueOf(str[2]);
            int k = Integer.valueOf(str[3]);
            double x_consumer = 0;
            double y_consumer = 0;
            for (int l = 1; l <= k; l++) {
                x_consumer += 1.0 / x;
            }
            for (int r = n; r >= k; r--) {
                y_consumer += 1.0 / y;
            }
            if (x_consumer == y_consumer) {
                System.out.println("Tie");
            } else if (x_consumer > y_consumer) {
                System.out.println("Lose");
            } else {
                System.out.println("Win");
            }
        }
    }
}
