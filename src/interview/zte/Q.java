package interview.zte;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 样例
public class Q {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
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
}

