package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] people=new int[9][2];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]==o1[0]?o1[1]-o2[1]:o2[0]-o1[0];
            }
        });
        List<int[]> que = new ArrayList<>();

    }
}
