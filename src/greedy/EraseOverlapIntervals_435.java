package greedy;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 *  1.可以认为区间的终点总是大于它的起点。
 *  2.区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 */
public class EraseOverlapIntervals_435 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals1 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] intervals2 = {{1, 2}, {2, 3}};
        System.out.println("删除重叠区间个数为："+eraseOverlapIntervals1(intervals2));
    }

    /**
     * 贪心法：T:O(nlogn)  S:O(1)
     *      先排序，再利用452题的思想，直接统计
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }
        //1.先排序
        Arrays.sort(intervals, ((o1, o2) -> Integer.compare(o1[0], o2[0])));

        //模拟
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {
                continue;
            }else{
                count++;
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return count;
    }

    /**
     * 贪心法：T:O(nlogn)  S:O(1)
     *      先排序，再利用452题的思想，间接统计
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }
        //1.先排序
        Arrays.sort(intervals, ((o1, o2) -> Integer.compare(o1[0], o2[0])));

        //模拟
        int count = 1; // points 不为空至少需要一支箭
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {
                count++; // 需要一支箭
            }else{// 气球i和气球i-1挨着
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return intervals.length-count;
    }
}
