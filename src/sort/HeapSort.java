package sort;

import java.util.Arrays;

/**
 * 堆排序
 *      原地不稳定排序   time:最好 O(nlogn) 最坏O(nlogn) 平均O(nlogn)  space:O(1)
 *      当前节点为i，则其父节点为：（i-1）/2，左子节点：2i+1，右子节点：2i+2
 *
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] tree = {2,5,3,1,10,4};
        buildHeap(tree, tree.length);
        System.out.println(Arrays.toString(tree));
        heapSort(tree);
        System.out.println(Arrays.toString(tree));
    }

    //堆排序：建堆，将根节点（此时最大）与最后一个结点交换，砍断最后一个结点，作heapify，重复上述过程
    public static void heapSort(int[] tree) {
        buildHeap(tree, tree.length);
        for (int i = tree.length - 1; i >= 0; i--) {
            swap(tree, i, 0);//交换完成，砍断最后一个结点，可以用i来维护此时节点个数
            heapify(tree, i, 0);//i此时数量刚好是每次砍掉一个以后的数量
        }
    }

    //建堆（从最后一个结点的父节点向上递归维护堆）
    public static void buildHeap(int[] tree, int n) {
        int last = n - 1;//最后一个结点
        int parent = (last - 1) / 2;//最后一个结点的父节点
        for (int i = parent; i >= 0; i--) {
            heapify(tree, n, 0);
        }
    }

    //维护堆
    public static void heapify(int[] tree, int n, int i) {//n节点个数，i当前节点
        //递归出口
        if (i >= n) {
            return;
        }
        //找到最大值
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int max = i;
        if (l < n && tree[l] > tree[max]) {
            max = l;
        }
        if (r < n && tree[r] > tree[max]) {
            max = r;
        }
        //交换
        if (max != i) {
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
