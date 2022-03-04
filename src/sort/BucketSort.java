package sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  桶排序
 *      需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据。
 *      非原地稳定排序   time：平均O(n+k)  最好O(n+k)   最坏O(n^2)  space:O(k)
 *
 *      步骤：1.根据待排序集合中的数据，确定映射规则和桶的数量；
 *           2.遍历待排序集合，将每一个元素根据映射规则，移动到对应的桶中；
 *           3.对每一个桶中元素进行排序；
 *           4.一次输出每个桶中的数据，得到整个有序的集合
 *
 *           桶数：计算规则通常：(max - min) / arr.length + 1;
 *           映射关系：通常为(arr[i] - min) / (arr.length)
 */
public class BucketSort {
    public static void main(String[] args){
        int[] array = {11, 6, 4, 1, 2, 3, 7, 9, 10};
        bucketSort(array);
    }


    public static void bucketSort(int[] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //1.桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<>());//每个桶中再放一个桶（一个桶会出现映射多个数值）
        }

        //2.将每个元素放入桶
        for(int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / (arr.length);//映射关系
            bucketArr.get(num).add(arr[i]);
        }

        //3.对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }
        System.out.println(bucketArr.toString());
    }
}
