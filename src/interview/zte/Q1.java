package interview.zte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 字符串分割：要求对只包含T和S的字符串，进行分割，要求分割后的每个子串有且仅包含两个TT，计算分割的种数
 *
 * 找到所有字符T的下标，每两个T的下标差的总和即为总分割方式
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'T') {
                list.add(i);
            }
        }
        int count=0;
        if(list.size()%2==1 || list.size()==0){
            System.out.println(0);
        }else if(list.size()==2){
            System.out.println(1);
        }else{
            for (int i = 1; i < list.size()-1; i += 2) {
                count += list.get(i + 1) - list.get(i);
            }
            System.out.println(count);
        }
    }
}
