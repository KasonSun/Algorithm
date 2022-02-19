package greedy;

/**
 * 738. 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 输入: N = 10
 * 输出: 9
 *
 * 输入: N = 1234
 * 输出: 1234
 *
 * 输入: N = 332
 * 输出: 299
 */
public class MonotoneIncreasingDigits_738 {
    public static void main(String[] args) {
        int n = 332;
        System.out.println("单调递增数字为：" + monotoneIncreasingDigits(n));
    }

    /**
     * 贪心法：T:O(n);S:O(n)
     *      局部最优：局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数。
     *      全局最优：得到小于等于N的最大单调递增的整数。
     *      但这里局部最优推出全局最优，还需要其他条件，即遍历顺序，和标记从哪一位开始统一改成9。
     *      从后向前遍历，就可以重复利用上次比较得出的结果了，从后向前遍历332的数值变化为：332 -> 329 -> 299
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits(int n) {
        String str = String.valueOf(n);
        char[] strArray = str.toCharArray();

        int flag = strArray.length;//记录从哪一位开始改为9
        for (int i = strArray.length-1; i > 0; i--) {
                if (strArray[i - 1] > strArray[i]) {
                    flag=i;
                    strArray[i - 1]--;
            }
        }

        for (int i = flag; i < strArray.length; i++) {
            strArray[i] = '9';
        }

        return Integer.parseInt(String.valueOf(strArray));
    }

    /**
     * 暴力法 T：O(n*m) m-数字长度  S:O(1)
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits1(int n) {
        for (int i = n; i > 0; i--) {
            if (checkNum(i)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean checkNum(int num) {
        int max = 10;
        while (num!=0) {
            int t = num % 10;
            if (max >= t) {
                max = t;
            }else{
                return false;
            }
            num = num / 10;
        }
        return true;
    }
}
