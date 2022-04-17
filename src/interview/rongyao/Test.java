package interview.rongyao;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        if (m < 6 || m > 65536 || m % 2 == 1) {
            System.out.println(0);
        }
        for (int n = m / 2; n > 0; n--) {
            if (check(n) && check(m - n)) {
                System.out.println(n+" "+(m - n));
                break;
            }
        }
    }

    public static boolean check(int n) {
        boolean flag = true;
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                flag=false;
                break;
            }
        }
        return flag;
    }
}
