package interview.webank;

import java.util.*;

/**
 * 拼接数字
 *桌面上有n张卡片,每张卡片上都写有一个正整数,现在你可以从中选出三张卡片,
 * 将卡片上的三个数按任意顺序连接成一个新的数。例如,对于三个数字123,45. 678,
 * 你可以将它们连成12345678,45123678, 67845123, 67812345或12367845等,可以证明67845123是能拼接出的最大的数。
 * 注意,对于卡片上的数字,你不能将其拆开。
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        String[] strArr = str.split(" ");
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //先取长度大的，若长度相等取值大的，-1表示降序排列
                if (o1.length() > o2.length() || o1.length() == o2.length() && Integer.valueOf(o1) > Integer.valueOf(o2)) {
                    return -1;
                }
                return 1;
            }
        });
        String[] strArr1 = Arrays.copyOfRange(strArr, 0, 3);
        Arrays.sort(strArr1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //取交换两个位置值较大的降序排列
                if (Integer.valueOf(o1 + o2) > Integer.valueOf(o2 + o1)) {
                    return -1;
                }
                return 1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : strArr1) {
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
/*
from functools import cmp_to_key

n=int(input())
nums=input().split()
def cmp1(x,y): #降序：先取长度大的，若长度相等取值大的
    if len(x)>len(y) or len(x)==len(y) and int(x)>int(y):
        return -1
    else:
        return 1
def cmp2(x,y):
    if int(x+y)>int(y+x):
        return -1;
    else:
        return 1

nums.sort(key=cmp_to_key(cmp1))
nums=nums[:3]
nums.sort(key=cmp_to_key(cmp2))
ans=""
for num in nums:
    ans+=num
print(ans)
 */