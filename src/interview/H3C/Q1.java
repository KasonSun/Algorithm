package interview.H3C;

import java.util.*;

/**
 * 数组去重
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[' || c == ']' || c==' ') {
                continue;
            }
            sb.append(c);
        }
        String[] split = sb.toString().split(",");
        if (split.length==0 || split == null) {
            System.out.println("[]");
            return;
        }
        if(split.length==1){
            System.out.println("["+split[0]+"]");
            return;
        }
        long[] nums = new long[split.length];
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(split[0]));
        for (int i = 1; i < nums.length; i++) {
            if (Long.valueOf(split[i - 1]) != Long.valueOf(split[i])) {
                list.add(Long.valueOf(split[i]));
            }
        }
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.println(",");
            }
        }
        System.out.println("]");
    }
}
