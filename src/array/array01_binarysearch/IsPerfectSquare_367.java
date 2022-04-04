package array.array01_binarysearch;

/**
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，sqrt 。
 *
 * 输入：num = 16
 * 输出：true
 *
 * 输入：num = 14
 * 输出：false
 *
 */
public class IsPerfectSquare_367 {
    public static void main(String[] args) {
        int num1 = 16;
        int num2 = 14;
        System.out.println("内置函数求解" + num1 + "是否为完全平方数：" + isPerfectSquare01(num1));
        System.out.println("内置函数求解" + num2 + "是否为完全平方数：" + isPerfectSquare01(num2));
        System.out.println("二分法求解" + num1 + "是否为完全平方数：" + isPerfectSquare02(num1));
        System.out.println("二分法求解" + num2 + "是否为完全平方数：" + isPerfectSquare02(num2));

        Fibonacci(16);
    }

    /**
     * 使用内置函数进行求解
     *
     * @return
     */
    public static boolean isPerfectSquare01(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }

    /**
     * 使用二分查找进行求解 ：num为正整数，所以若x满足x*x=num，则x一定满足1<=x<=num.因此可以将1和num作为二分搜索的初始边界
     *
     * 因为我们在移动左侧边界left 和右侧边界right 时，新的搜索区间都不会包含被检查的下标mid，所以搜索区间的边界始终是我们没有检查过的。
     * 因此，当left=right 时，我们仍需要检查mid=(left+right)/2。
     *
     */
    public static boolean isPerfectSquare02(int num) {
        int l = 0, r = num;
        while (l <= r) {
            int mid = l + (r-l) / 2;
            if (mid * mid < num) {
                l = mid + 1;
            } else if (mid * mid > num) {
                r = mid - 1;
            } else {
                return true;
            }

        }
        return false;
    }

    public static int Fibonacci(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

}
