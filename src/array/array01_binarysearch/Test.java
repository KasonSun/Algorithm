package array.array01_binarysearch;

public class Test {
    public static void test(){
//        int [][] arr={{1,2,3}};
//        System.out.println(arr[0]);//地址值    [I@50cbc42f
//        System.out.println(arr[0][0]);//1
        int c = 0;
        int d = 0;
        System.out.println(c++);
        System.out.println(c);
        for (int i = 0; i < 10; i++) {
            c = c++;
        }
        System.out.println(c);

        for (int i = 0; i < 10; i++) {
            c = c++;
            d = c++;
        }
        System.out.println(c);
        System.out.println(d);
    }

    public static void main(String[] args) {
        test();
    }
}
