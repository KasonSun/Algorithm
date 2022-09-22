package interview.tianyi;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; i++) {
            String[] str = sc.nextLine().split(" ");
            long n = Long.valueOf(str[0]);
            long a = Long.valueOf(str[1]);
            long b = Long.valueOf(str[2]);
            long result = handler(n, a, b);
            System.out.println(result);
        }
    }

    private static long handler(long n, long a, long b) {
        //计算平均票价
        long result = 0;
        if (a / 2 < b / 3) {
            result = n / 2 * a;
            if (n % 2 == 1) {
                result = Math.min(result + Math.max(a, b), result - a + b);
            }
        }else{
            result = n / 3 * b;
            if (n % 3 == 1) {
                result = Math.min(result + Math.min(a, b), result - b + 2 * a);
            } else if (n % 3 == 2) {
                result += Math.min(a, b);
            }
        }
        return result;
    }
}
