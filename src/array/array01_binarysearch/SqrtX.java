package array.array01_binarysearch;

/**
 * x的平方根
 */
public class SqrtX {
    public static void main(String[] args) {
        int x=4;
        System.out.println(sqrtX(x));
    }

    public static int sqrtX(int x) {
        int left=0, right=x;
        while(left<right){
            int mid=(left+right)/2;
            if(mid*mid==x){
                return mid;
            }else if(mid*mid>x){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }
}
