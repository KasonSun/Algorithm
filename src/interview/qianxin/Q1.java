package interview.qianxin;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str1 = sc.nextLine().split(",");
        String[] str2 = sc.nextLine().split(",");
        int[] a = new int[str1.length];
        int[] b = new int[str2.length];
        for (int i = 0; i < str1.length; i++) {
            a[i] = Integer.valueOf(str1[i]);
            b[i] = Integer.valueOf(str2[i]);
        }


        if(a.length==2){
            int temp1 = a[0] + a[1];
            int temp2 = b[0] + b[1];
            int temp3 = a[0] + b[1];
            int temp4 = a[1] + b[0];
            int result = Math.min(temp1, Math.min(temp2, Math.min(temp3, temp4)));
            System.out.println(result);
            return;
        }
        if (a.length == 1) {
            int result = a[0] > b[0] ? b[0] : a[0];
            System.out.println(result);
            return;
        }

        //单独从A买
        int aSum = 0;
        for (int temp : a) {
            aSum += temp;
        }
        aSum = (int)(aSum*0.6);

        int[] c = Arrays.copyOf(b, b.length);
        Arrays.sort(c);
        //单独从B买
        int bSum = 0;
        if (c.length % 3 == 0) {
            for (int i = c.length - 1; i >= 2; i -= 3) {
                bSum += c[i] + c[i - 1];
            }
        } else if (c.length % 3 == 1) {
            for (int i = c.length - 1; i >= 2; i -= 3) {
                bSum += c[i] + c[i - 1];
            }
            bSum += c[0];
        }
        aSum = aSum > bSum ? bSum : aSum;
        System.out.println(aSum);
    }
}
