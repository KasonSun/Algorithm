package sort.selection;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大堆（大根堆）：使用PriorityQueue实现，因为默认是小根堆，因此要实现大根堆需要传入比较器
 */
public class MaxHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        maxHeap.offer(1);
        maxHeap.offer(9);
        maxHeap.offer(4);
        maxHeap.offer(6);
        maxHeap.offer(7);
        maxHeap.offer(3);
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
     }
}
