package greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge_56 {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("合并区间的结果为：" );
        int[][] result = merge(intervals);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

    /**
     * 贪心法： T:O(nlogn)   S:O(1)
     *          思路：排序之后局部最优：每次合并都取最大右边界，这样就可以合并更多的区间；整体最优：合并所有重叠区间
     *          1.先按照左边界排序；2.找出所能合并的区间添加到数组；3.直到所有数组元素被合并完
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new LinkedList<>();
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int start = intervals[0][0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > intervals[i - 1][1]) {
                result.add(new int[]{start, intervals[i - 1][1]});//注意此处只能加到intervals.length-2,故最后一个位置需要单独处理
                start = intervals[i][0];
            } else {//intervals[i][0] <= intervals[i - 1][1]
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            }
        }
        result.add(new int[]{start, intervals[intervals.length - 1][1]});//intervals.length-1，最后一个位置需要单独处理
        return result.toArray(new int[result.size()][]);
    }
}
