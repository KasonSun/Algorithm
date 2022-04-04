package sort.selection;

import java.util.Arrays;

/**
 * 堆排序实现：
 *      ①heapify:从上到下调整，递归实现;
 *      ②buildheap：从下到上建立（最后一个有孩子节点的节点开始）；
 *      ③heapsort：从上到下（先建堆，再交换拿出最值后调整）
 */
public class Heap_sort {
    public static void main(String[] args) {
        int[] tree = {2,5,3,1,10,4};
        heapify(tree, tree.length, 0);
        System.out.println(Arrays.toString(tree));
        heapSort(tree, tree.length);
        System.out.println(Arrays.toString(tree));
    }

    /**
     * heapsort：从上到下（先建堆，再交换拿出最值后调整）
     * @param tree  层序数组
     * @param n 数组长度
     */
    public static void heapSort(int[] tree, int n) {
        buildHeap(tree, n);
        for (int i = n - 1; i >= 0; i--) {
            int temp = tree[i];
            tree[i] = tree[0];
            tree[0] = temp;

            heapify(tree, i, 0);
        }
    }
    /**
     * ②buildheap：从下到上建立（最后一个有孩子节点的节点开始）；
     * @param tree 层序数组
     * @param n 数组长度
     */
    public static void buildHeap(int[] tree, int n) {
        int last = n - 1;
        int parent = (last - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(tree, n, i);
        }
    }
    /**
     * ①heapify:从上到下调整，递归实现
     * @param tree 层序数组
     * @param n 数组长度
     * @param i 当前调整的节点
     */
    public static void heapify(int[] tree, int n, int i) {
        //递归三要素
        //①递归参数和返回值
        // void heapify(int[] tree, int i)
        //②递归出口
        if (i >= n) {
            return ;
        }
        //③单层递归逻辑(此处大顶堆逻辑，如需修改需要在这里修改)
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < n && tree[left] > tree[max]) {
            max = left;
        }
        if (right < n && tree[right] > tree[max]) {
            max = right;
        }
        //若最大值还在原来地方，不交换
        //否则，交换，递归调整
        if (max != i) {
            int temp = tree[i];
            tree[i] = tree[max];
            tree[max] = temp;

            heapify(tree, n, max);
        }
    }
}
