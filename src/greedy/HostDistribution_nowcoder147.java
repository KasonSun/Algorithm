package greedy;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 主持人调度
 * 有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，第 i 个活动的开始时间是 starti ,
 * 第 i 个活动的结束时间是 endi ,举办某个活动就需要为该活动准备一个活动主持人。
 * 一位活动主持人在同一时间只能参与一个活动。并且活动主持人需要全程参与活动，换句话说，一
 * 个主持人参与了第 i 个活动，那么该主持人在 (starti,endi) 这个时间段不能参与其他任何活动。求为了成功举办这 n 个活动，最少需要多少名主持人。
 *
 * 复杂度要求：时间复杂度 O(n \log n)O(nlogn) ，空间复杂度 O(n)O(n)
 *
 */
public class HostDistribution_nowcoder147 {
    public static void main(String[] args) {
        int[][] startEnd = {{1, 2}, {2, 3}};
        int n = 2;
        System.out.println(hostDistribution(2, startEnd));
    }

    /**
     * 1.贪心法（提交不通过）
     *      使用优先权队列实现
     *          ①先按照起始时间排序，起始相同则按照结束时间排序
     *          ②先将第一个时间段的结束时间入队
     *          ③遍历数组，若当前起始时间大于等于前一个结束时间，则将前一个结束时间出队；反之，则将当前结束时间入队；
     *          ④循环结束，当前队列的大小就是所需要的主持人数量；
     * @param startEnd
     * @return
     */
    public static int hostDistribution(int n,int[][] startEnd) {
        //按照起始时间进行排序
        Arrays.sort(startEnd,(o1,o2)->{
            return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
        });
        //保存每一个时间段的结束时间，从小到大
        PriorityQueue<Integer> queue=new PriorityQueue<>();//用来控制调度的结束时间
        queue.offer(startEnd[0][1]);
        for(int i=1;i<n;i++){
            //如果当前的活动的开始时间比前一个任务的结束时间长，那么不需要添加新的主持人
            if(startEnd[i][0]>=queue.peek()){
                queue.poll();//用队列中的元素数量记录主持人数量
            }
            //添加新的活动结束时间
            queue.offer(startEnd[i][1]);
        }
        return queue.size();
    }

    /**
     * 2.贪心法，分成两个数组实现（提交通过）
     * @param n
     * @param startEnd
     * @return
     */
    public static int hostDistribution01(int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int result = 0, index = 0;
        for (int i = 0; i < n; i++) {//i一定要从0开始，第一个才能保证需要一个主持人
            if (start[i] >= end[index]) {
                index++;
            }else{
                result++;
            }
        }
        return result;
    }
}
