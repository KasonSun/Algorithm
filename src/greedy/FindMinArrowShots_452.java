package greedy;

import java.util.Arrays;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足 xstart≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 *
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 *
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 *
 * 输入：points = [[1,2]]
 * 输出：1
 *
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 *
 */
public class FindMinArrowShots_452 {
    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("用了几支箭引爆气球："+findMinArrowShots(points));
    }

    /**
     * 贪心法：T:O(nlogn)  S:O(1)
     *      思路：局部最优：当气球出现重叠，一起射，所用的弓箭最少；全局最优：把所有气球射爆，所有弓箭最少
     *          按照气球数组元素的起始位置进行排序，那么就从前向后遍历气球数组，靠左尽可能让气球重复，
     *          如果遇到气球重叠则重叠气球中右边边界的最小值之前的区间一定需要一个弓箭
     *          如[[10,16],[2,8],[1,6],[7,12]]-->排序后：[[1,6],[2,8],[7,12],[10,16]]
     *
     *          1      6
     *           2     |  8
     *                  7   |  12
     *                     10      16
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points,(o1,o2)->Integer.compare(o1[0],o2[0]));//从小到大排序

        int count = 1;//不为空至少为一支箭
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {//右边气球的最小坐标>左边气球的最大坐标；也就是相邻两个气球不挨着
                count++;
            }else{//右边气球的最小坐标<=左边气球最大坐标；也就是相邻两个气球挨着
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }
}
