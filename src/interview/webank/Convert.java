package interview.webank;

import java.util.Scanner;

/**
 * 1.十六进制
 */
public class Convert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String hex = Integer.toHexString(num);
        int count = 0;
        for (int i = 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            if (ch >= '0' && ch <= '9') {
                continue;
            }
            if (ch == 'a' || ch == 'A' || ch == 'b' || ch == 'B' || ch == 'c' || ch == 'C' || ch == 'd' || ch == 'D' || ch == 'e' || ch == 'E' || ch == 'f' || ch == 'F') {
                count++;
            }
        }
        System.out.println(count);
    }
}
