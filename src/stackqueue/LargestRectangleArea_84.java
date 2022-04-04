package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形(困难)
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 输入： heights = [2,4]
 * 输出： 4
 */
public class LargestRectangleArea_84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? heights.length : stack.peek());
            stack.push(i);
        }

        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, (right[i] - left[i] - 1) * heights[i]);
        }
        return result;
    }

    /**
     * 1.暴力枚举
     * @param heights
     * @return
     */
    public static int largestRectangleArea01(int[] heights) {
        int result = 0;
        //枚举左边界
        for (int left = 0; left < heights.length; left++) {
            int minHeight = Integer.MAX_VALUE;
            //枚举右边界
            for (int right = left; right < heights.length; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                result = Math.max(result, (right - left + 1) * minHeight);
            }
        }
        return result;
    }
}
