package interview.H3C;

import java.util.*;

/**
 * 有1,2,3,4个数字，能组成多个互补相同的无重复数字的三位数
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        int count = 0;
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    if (i != j && i != k && j != k) {
                        count++;
                        list.add(i +""+ j+""+ k );
                    }
                }
            }
        }
        System.out.println("总数:" + count);
        System.out.println("无重复组合:" + list.toString());
    }
}
