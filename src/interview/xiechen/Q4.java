package interview.xiechen;

import java.util.Scanner;

/**
 * 计算包括当前节点之前的任意两相邻数差的绝对值的最大值、再记录包括当前节点之后的任意两相邻数差的绝对值的最大值。
 * 暴力遍历每一个节点，对其作平滑处理：将该节点改成左右两点平均值后的绝对值最大值。再取三者最大值即为修改当前节点所能得到的平滑值。
 * 最后取最小的平滑值。
 * 算是暴力了。。。
 */
public class Q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int[] pre = new int[n];
        int[] suc = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            pre[i] = Math.abs(array[i] - array[i - 1]);
            if (pre[i] < pre[i - 1]) pre[i] = pre[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            suc[i] = Math.abs(array[i] - array[i + 1]);
            if (suc[i] < suc[i + 1]) suc[i] = suc[i + 1];
        }

        for (int i = 0; i < n; i++){
            int pre_val = i > 0 ? pre[i - 1] : 0;
            int suc_val = i < n - 1 ? suc[i + 1] : 0;
            int mid = (i > 0 && i < n - 1) ? (Math.abs(array[i - 1] - array[i + 1]) + 1) / 2 : 0;

            mid = Math.max(mid, Math.max(pre_val, suc_val));
            ans = Math.min(ans, mid);
        }
        System.out.println(ans);
    }
}
