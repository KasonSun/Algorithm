package interview.tianyi;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] str = input.split(" ");
        int a = Integer.valueOf(str[0]);//对立攻击
        int h = Integer.valueOf(str[1]);//对立血量
        int b = Integer.valueOf(str[2]);//光攻击
        int k = Integer.valueOf(str[3]);//光血量

//        long hong = (long) Math.pow(10, 999999999);
        long temp = 0;
        while (h > 0 || k > 0) {
            if (h > 0 && k > 0) {
                temp += b;
                temp += a;
                h -= b;
                k -= a;
            } else if (h > 0 && k < 0) {
                temp += a * 10;
                h = 0;
                break;
            } else if (h < 0 && k > 0) {
                temp += b * 10;
                k = 0;
                break;
            }
        }
        System.out.println(temp);
    }
}
