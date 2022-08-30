package sort.selection;

import java.util.PriorityQueue;

/**
 * 最小堆（小根堆）：使用PriorityQueue实现，默认是小根堆
 */
public class MinHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(1);
        minHeap.offer(6);
        minHeap.offer(7);
        minHeap.offer(4);
        minHeap.offer(9);
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}
