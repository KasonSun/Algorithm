package interview.rongyao;

import java.util.Scanner;

public class OddPair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        if (m < 6 || m > 65536 || m % 2 == 1) {
            System.out.println(0);
        }
        int mid = m / 2;
        for (int i = mid; i > 0; i--) {
            if (isPrime(i) && isPrime(m - i)) {
                System.out.println(i + " " + (m - i));
                break;
            }
        }

    }

    /**
     * 质数定义：大于1的自然数中，除了1和它本身以外不再有其他因数
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        boolean flag=true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
