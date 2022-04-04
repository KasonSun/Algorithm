package string;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 */
public class StringMultiply_43 {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(multiply(num1, num2));

    }

    /**
     * 先不做进位，存放在数组中，最后统一做进位
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        //两个数相乘，乘积的长度只可能①两个数长度相加（999*999=998001）；②两个数长度相加减1（100*100=10000）
        int[] resultArr = new int[num1.length() + num2.length()];
        //1.将同一位置竖式计算的竖式列累加存起来，不进位
        for (int i = num1.length()-1; i>=0; i--) {
            int x = num1.charAt(i)-'0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                resultArr[i + j + 1] += x * y;  //此处应该是+=，因为同一竖式计算的竖式列结果放在同一个索引位置
            }
        }
        //2.进行进位
        for (int i = num1.length() + num2.length() - 1; i > 0; i--) {
            resultArr[i - 1] += resultArr[i] / 10;
            resultArr[i] %= 10;
        }

        //3.处理转换为字符串
        StringBuilder result = new StringBuilder();
        int index = resultArr[0] == 0 ? 1 : 0;
        while (index < num1.length() + num2.length()) {
            result.append(resultArr[index]);
            index++;
        }
        return result.toString();
    }
}
