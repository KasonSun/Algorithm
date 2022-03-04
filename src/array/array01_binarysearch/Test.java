package array.array01_binarysearch;


public class Test {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        int c = 0;
        int d = 0;
        System.out.println(c++);
        System.out.println(c);
        for (int i = 0; i < 10; i++) {
            c = c++;//一定要注意每次在自增之前又将值赋值给了自己，无论循环多少次，都不会增加
        }
        System.out.println(c);

        for (int i = 0; i < 10; i++) {
            c = c++;
            d = c++;
        }
        System.out.println(c);
        System.out.println(d);
    }

}
