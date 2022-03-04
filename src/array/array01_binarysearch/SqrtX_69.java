package array.array01_binarysearch;

/**
 * 给你一个非负整数 x ，计算并返回X算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * 输入：x = 4
 * 输出：2
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 思路：由于X平方根的整数部分是满足K^2<=X的最大K值，因此使用二分查找，下界设置为0，上界设置为X，比较mid平方与X的大小，调整上下界
 */
public class SqrtX_69 {
    public static void main(String[] args) {
        System.out.println(sqrtX(4));
        System.out.println(sqrtX(8));
    }

    public static int sqrtX(int x) {
        int l = 0, r = x, ans = 0;
        while (l <= r) {
            int mid = (l + r)>>1;
            if ((long)mid * mid <= x) {//注意此处的类型转换，会超时需要强制类型转换
                ans = mid;//注意此处的ans=mid操作只在mid*mid<=x才进行赋值（满足K^2<=X的最大K值）
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
