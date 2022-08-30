package interview.microsoft;

import java.util.*;

public class Test03 {
    public static void main(String[] args) {
        int[] A = {1, 2, 4, 3};
        int[] A1 = {3, 2, 1, 6, 5};
        int[] A2 = {1, 2};
        int[] B = {1, 3, 2, 3};
        int[] B1 = {4, 2, 1, 3, 3};
        int[] B2 = {1, 2};
        System.out.println(new Test03().solve(A2, B2, 2));
    }

    public int solve(int A[], int B[], int N) {
        List<Integer> list = new ArrayList<>();
        int[] C = new int[N];
        int result = Integer.MAX_VALUE;
        int index=0;
        for(int i=0;i<N;i++){

            if(A[i]==B[i]){
                C[index++] = A[i];
                list.add(A[i]);
                continue;
            }
            if (A[i] > B[i]) {
                C[index++] = A[i];
                list.add(A[i]);
                if (!list.contains(B[--index])) {
                    result = Math.min(result, B[i]);
                }
            }
            if (A[i] < B[i]) {
                C[index++] = B[i];
                list.add(B[i]);
                if (!list.contains(A[--index])) {
                    result = Math.min(result, A[i]);
                }
            }
        }
        if(N==1){
            if(C[0]==0 || C[0]==1){
                result=C[0]+1;
            }else{
                result=C[0]-1;
            }
        }
        if(N==2){
            Arrays.sort(C);
            if(C[0]==1){
                result=C[N-1]+1;
            }else if(C[0]==0 && C[1]!=1){
                result=C[1]-1;
            }
        }
        return result;
    }
}
