package interview.microsoft;

import java.util.ArrayList;
import java.util.List;


public class Test02 {
    public static void main(String[] args) {
        int A[] = {-3, -2, 1, 0, 8, 7, 1};
        System.out.println(new Test02().solve(A, 7, 3));
    }

    public int solve(int[] A, int N, int M) {
        List<Integer> list = new ArrayList<>();
        int result=1;
        for(int i=0;i<A.length;i++){
            list.add(A[i]);
            int temp=1;
            for(int j=i+1;j<A.length;j++){
                list.add(A[j]);
                temp++;
                if(list.size()>=2 && valid(list, M)){
                    result = Math.max(result, temp);
                }else if(list.size()>=2){
                    list.remove(list.size()-1);
                    continue;
                }
            }
        }
        return result;
    }

    public boolean valid(List<Integer> list, int M) {
        for(int i=0;i<list.size();i++){
            for(int j=i+1;j< list.size();j++){
                int distance = Math.abs(list.get(j)-list.get(i));
                if (!(distance % M == 0)) {
                    return false;
                }
            }
        }
        return true;
    }
}
