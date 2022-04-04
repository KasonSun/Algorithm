package stackqueue;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class TopKFrequent_347 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
    /**
     *  思路：
     *       1.统计元素出现频率
     *       2.对频率排序
     *       3.找出前K个高频
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        //1.利用HashMap统计频率
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //2.对频率进行排序
        //“根据map的value值正序排，相当于一个小顶堆（由小到大）”
        //①((o1, o2) -> o1.getValue() - o2.getValue()) Lambda表达式写法，传入比较规则
        //PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(((o1, o2) -> o1.getValue() - o2.getValue()));

        //①完整写法，传入comparator,重写compare方法
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });


        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//map.entrySet()返回每一个键值对，通常与forEach组合进行遍历
            queue.offer(entry);//入队
            if (queue.size() > k) {//维护
                queue.poll();//队头元素出队
            }
        }

        //3.返回前K个频率对应的值
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
     }

    //不去重
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result=new ArrayList<>();
        Arrays.sort(input);
        for(int i=0;i<k;i++){
            result.add(input[i]);
        }
        return result;
    }
}
