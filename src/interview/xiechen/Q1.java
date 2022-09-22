package interview.xiechen;

import java.util.Scanner;

/**
 * 给定几个数，将这个数交换某个位置的值使之成为偶数，也可以不交换，但是交换后的数据不能存在前导0(不能有前导零 && 所有数字都要参与排列)
 */
public class Q1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for(int i = 0; i < q; i++){
            int x = sc.nextInt();
            if((x & 1) == 0){
                System.out.println(x);
                continue;
            }
            if (x < 10){
                System.out.println(-1);
                continue;
            }
            String str = String.valueOf(x);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); j++){
                int posValue = str.charAt(j) - '0';
                if(j + 1 < str.length() && (posValue & 1) == 0 && str.charAt(j + 1) != '0'){
                    //不为0字符是为了排除203..这种前两个都是偶数的，并且第二位为0，这样如果没这个条件就会出现前导0
                    sb.append(str.substring(j + 1));
                    sb.append(posValue);
                    break;
                }else{
                    sb.append(posValue);
                }
            }
            if (str.equals(sb.toString())){
                System.out.println(-1);
                continue;
            }
            System.out.println(sb);
        }
    }
}
