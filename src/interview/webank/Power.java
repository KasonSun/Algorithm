package interview.webank;

import java.util.Scanner;

/**
 * 努力的人
 */
public class Power {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] num = sc.nextLine().split(" ");
        int n = Integer.parseInt(num[0]);
        int x = Integer.parseInt(num[1]);
        int y = Integer.parseInt(num[2]);

        int[] queue = new int[n];
        String[] power = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            queue[i] = Integer.parseInt(power[i]);
        }

        while (true) {
            //每个人
            for (int i = 0; i < n; i++) {
                //左侧筛查
                int lMin = Integer.MAX_VALUE;
                int count = x;
                for (int j = i - 1; j > -1 && count > 0; j--) {
                    lMin = queue[j] < lMin ? queue[j] : lMin;
                    count--;
                }
                //无左侧处理
                if (lMin == Integer.MAX_VALUE) {
                    lMin = 0;
                }
                //右侧筛查
                int rMin = Integer.MAX_VALUE;
                count = y;
                for (int j = i + 1; j < n && count > 0; j++) {
                    rMin = queue[j] < rMin ? queue[j] : rMin;
                    count--;
                }
                //无右侧处理
                if (rMin == Integer.MAX_VALUE) {
                    rMin = 0;
                }
                //检测自己是否需要努力
                if (queue[i] < lMin && queue[i] < rMin) {
                    System.out.println(i+1);
                    return;
                }
            }
        }
    }
}
