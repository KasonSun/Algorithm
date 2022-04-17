package interview.rongyao;

import java.util.*;

public class LeftSpiral {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        List<Integer[]> list = new ArrayList<>(t);
        int maxScore = 0;
        int resultScore = 0;
        for (int i = 0; i < t; i++) {
            int days = sc.nextInt();
            sc.nextLine();
            String[] fault = sc.nextLine().split(" ");
            Integer[] faultNum = new Integer[fault.length];
            for (int j = 0; j < fault.length; j++) {
                faultNum[j] = Integer.parseInt(fault[j]);
            }
            list.add(faultNum);
//            System.out.println(list.toString());
        }

        for (int i = 0; i < list.size(); i++) {
            Integer[] temp = list.get(i);
            int[] energy = new int[temp.length];
            for (int j = 1; j < temp.length; j++) {
                int high = 0;
                int low = 0;
                for (int k = 1; k <= j; k++) {
                    if (temp[k - 1] > temp[j]) {
                        low++;
                    }else if(temp[k-1]<temp[j]){
                        high++;
                    }
                }
                energy[j] = energy[j-1] -low+high;
                if (energy[j] > maxScore) {
                    maxScore = energy[j];
                }
            }
            System.out.println(maxScore+" "+ energy[energy.length-1]);
        }
    }
}
