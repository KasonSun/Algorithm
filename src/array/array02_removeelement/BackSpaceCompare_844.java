package array.array02_removeelement;

/**
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，请你判断二者是否相等。# 代表退格字符。如果相等，返回 true ；否则，返回 false 。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 *
 */
public class BackSpaceCompare_844 {
    public static void main(String[] args) {
        String s = "ab##c#", t = "c##c##";
        boolean b = backSpaceCompare(s, t);
        System.out.println(b);
    }

    /**
     * 双指针法
     * 思路：比较（反向比较：联想输入字符再输入backspace的操作）
     *  反向比较的核心在于要去除退格#的影响后找到一个待比较字符
     * @param s
     * @param t
     * @return
     */
    private static boolean backSpaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;//记录遍历过程中遇到的“#”的数量
        while (i >= 0 && j >= 0) {//经过测试，此处使用&&和||没有影响(因为此时的)
            //下面的两个while循环，则是在模拟输入字符后进行backspace的操作，找到去除#影响的待比较字符
            while (i >= 0) {
                if (s.charAt(i) == '#') skipS++;
                else if (skipS == 0) break;//=0则此时不含有#，以及找到待比较字符
                else skipS--;//此时的条件是（skipS>0 && s.charAt(i)!=#）
                i--; //从后往前比较
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') skipT++;
                else if (skipT == 0) break;
                else skipT--;
                j--;
            }

            if (i < 0 || j < 0) break;
            if (s.charAt(i) != t.charAt(j)) return false;
            i--;
            j--;

        }
        return (i < 0 && j < 0);
    }
}
