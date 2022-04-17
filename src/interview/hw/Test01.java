package interview.hw;

import java.util.HashSet;
import java.util.Set;

public class Test01 {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("请输入字符串（nextLine）：");
//        String str1 = input.nextLine();
//        System.out.println(str1);
//
//        System.out.println("请输入字符串（next）：");
//        String str = input.next();
//        System.out.println(str);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(set.toString());
    }
}
