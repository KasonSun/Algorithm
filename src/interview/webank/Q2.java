package interview.webank;

import java.util.Scanner;

/**
 * 移位游戏
 *  一天你正在玩一个游戏,游戏中给定一个数a,你需要通过一些简单的移位摄作来将其变成b,
 *  在每次损作中,你可以将当前的数x变成以下六个数中的一个:x*2, x*4, x*8, x/2(如果x被2整除),x/4 (如果x被4整除),x/8 (如果x被8整除)
 *  例如,如果当前的数x=12,你可以将他变成24, 48,96, 6, 3.你不能将其变成x/8.因为12不能被8整除。
 *  现在请问将给定的初始值a通过上述摄作变成目标值b需要的最少的摄作次数。
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] str = scanner.nextLine().split(" ");
            int a = Integer.valueOf(str[0]);
            int b = Integer.valueOf(str[1]);
            count(a, b);
        }
    }

    private static void count(int a, int b) {
        if (a < b) {
            count(b, a);
            return ;
        }
        if (a == b) {
            System.out.println(0);
            return;
        }
        if (a % b != 0) {
            System.out.println(-1);
            return;
        }
        int d = a / b;
        int cur = 1;
        int ans = 0;
        int res = 0;
        while (cur < d) {
            ans += 1;
            cur *= 2;
        }
        if (cur != d) {
            System.out.println(-1);
            return;
        }
        res += ans / 3;
        ans %= 3;
        res += ans / 2;
        ans %= 2;
        res += ans;
        System.out.println(res);
    }
}

/*
    from math import gcd

t=int(input())

def cnt(a,b):
    if a<b:
        return cnt(b,a)
    elif a==b:
        return 0
    if a%b!=0:
        return -1
    d=a//b
    cur=1
    ans=0
    res=0
    while cur<d:
        ans+=1
        cur<<=1
    if cur!=d:
        return -1
    res+=(ans//3)
    ans%=3
    res+=(ans//2)
    ans%=2
    res+=ans
    return res

for i in range(t):
    a, b=list(map(int, input().split()))
    print(cnt(a, b))
     */
