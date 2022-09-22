package interview.tianyi;

import java.util.*;

public class TestProrityQueue {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(0);
        list.add(-10);
        list.add(4);
        System.out.println(list.toString());
        //1.将list放入优先权队列
        PriorityQueue<Integer> queue = new PriorityQueue<>(new ArrayList<>(list));
        System.out.println(queue.toString());

        //2.list转Array，toArray方法得到的默认是Object数组，但是可以使用下面的方式得到Integer数组
        Integer[] nums = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(nums));

        //3.Array转list(此时的list不能使用add和remove方法，需要重新构造才可以)
        List<Integer> list1 = Arrays.asList(nums);
        System.out.println(list1.toString());

        //4.使用Collections.addAll()方法将Array转List
        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, nums);
        System.out.println(list2.toString());
    }
}
