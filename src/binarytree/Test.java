package binarytree;

public class Test {
    public static void main(String[] args) {
        int c=0;
        int d=0;
        System.out.println(c++);
        System.out.println(c);
        for (int i = 0; i < 10; i++) {
            c = c++;
//            System.out.println("c="+c);
        }
        System.out.println(c);
        for (int i = 0; i < 10; i++) {
            c = c++;
            System.out.println("c==="+c);
            d = c++;
            System.out.println("c========="+c);
        }
        System.out.println(c);
        System.out.println(d);

        int m=1;
        int n=2;
        m=m^n;
        n=m^n;
        m=m^n;
        System.out.println(m);
        System.out.println(n);
    }

}
