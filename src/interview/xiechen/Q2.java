package interview.xiechen;

import java.util.Scanner;

/**
 * a个‘y’， b个‘o’， c个‘u’。使这个字母拼成一个字符串。三个相邻字母“you”得两分，“oo”得一分。最多可以获得多少分？
 *
 * 贪心法：以凑得两分的先算，再算一分的
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < q; i++){
            String[] split = sc.nextLine().split(" ");
            int a = Integer.parseInt(split[0]), b = Integer.parseInt(split[1]), c = Integer.parseInt(split[2]);
            int ans = Math.min(a, Math.min(b, c));
            b -= ans;//表示最多凑了几个you后，b的剩余数量。因为两个oo也会有得分，贪心思想
            ans *= 2;//凑得you得分
            ans += b >= 2 ? b - 1 : 0;//注意得分只要连续两个就可以o就可以的一分，例：0000可以的3分，00000可以的4分，n就可以得n-1分
            System.out.println(ans);
        }
    }
}
