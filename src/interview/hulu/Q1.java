package interview.hulu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int k = Integer.valueOf(sc.nextLine());
        String[][] record = new String[k][2];
        for (int i = 0; i < k; i++) {
            String[] str = sc.nextLine().split(" ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long time = sdf.parse(str[1]).getTime();

            record[i][0] = str[0];
            record[i][1] = String.valueOf(time);
        }
        int n = Integer.valueOf(sc.nextLine());

        Arrays.sort(record, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (Long.valueOf(o1[1]) > Long.valueOf(o2[1])) {
                    return 1;
                } else if (Long.valueOf(o1[1]) > Long.valueOf(o2[1])) {
                    return 0;
                }else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                long sub = Long.valueOf(record[j][1]) - Long.valueOf(record[i][1]);
                long days = sub / (1000 * 60 * 60 * 24);
            }
        }
    }
}
/**
 from collections import defaultdict

 k=int(input())

 memo=set()
 for i in range(k):
 memo.add(input())
 n=int(input())
 months=[31,28,31,30,31,30,31,31,30,31,30,31]
 pm=[0]
 for m in months:
 pm.append(pm[-1]+m)
 nums=defaultdict(set)
 for cur in memo:
 user, ymd=cur.split(' ')
 y,m,d=list(map(int, ymd.split('-')))
 ind=pm[m-1]+d
 nums[ind].add(user)
 ans=366
 left,cnt=-1,set()
 for right in range(366):
 cnt |=nums[right]
 if(len(cnt)>=n):
 while(left+1<right and len(cnt-nums[left+1])>=n):
 cnt-=nums[left+1]
 left+=1
 ans=min(ans, right-left)
 print(ans if ans<366 else -1)
 */
