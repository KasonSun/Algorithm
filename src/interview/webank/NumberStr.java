package interview.webank;

import java.util.Scanner;

/**
 * 数字串
 */
public class NumberStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] param = sc.nextLine().split(" ");
        int n = Integer.parseInt(param[0]);//数字串的长度
        int k = Integer.parseInt(param[1]);//能被那个数整除
        String numStr = sc.nextLine();
        int count = 0;
        for (int i = 0; i < numStr.length(); i++) {
            int cur = 0;
            for (int j = i; j < numStr.length(); j++) {
                cur = (cur * 10 + (Integer.parseInt(numStr.charAt(j)+"")))%k;//写一下手算的竖式过程就知道了
                System.out.println(cur);
                if (cur==0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
