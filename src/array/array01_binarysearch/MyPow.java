package array.array01_binarysearch;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 */
public class MyPow {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2;
        System.out.println(new MyPow().mypow(x, n));
    }

    /**
     * 递归实现
     * @param x
     * @param n
     * @return
     */
    public static double mypow(double x, int n) {
        if (n < 0) {
            return 1/mypow(x, -n);
        }
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        double result = mypow(x, n >> 1);
        result *= result;
        if (n % 2 == 1) {
            result *= x;
        }
        return result;
    }
    /**
     * 力扣提交超时
     * @param x
     * @param n
     * @return
     */
    public double mypow01(double x, int n) {
        double result = 0.0;
        if (x == 0.0 && n < 0) {
            return result;
        }
        if (n == 0) {
            return 1.0;
        } else if (n > 0) {
            result = pow(x, n);
        } else if (n < 0) {
            result = pow(1/x, -n);
        }
        return result;
    }

    public static double pow(double x, int n) {
        double result = 1.0;
        for (int i = 1; i <= n; i++) {
            result *= x;
        }
        return result;
    }
}
