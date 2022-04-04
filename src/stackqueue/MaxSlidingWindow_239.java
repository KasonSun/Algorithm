package stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 *
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 *
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 */
public class MaxSlidingWindow_239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(new MaxSlidingWindow_239().maxValueWindows(nums, k).toString());
    }
    /**
     * 单调队列实现(O(n))
     *      1.pop(value)：如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
     *      2.push(value)：如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，直到push元素的数值小于等于队列入口元素的数值为止
     *
     *  实际单调队列的操作①队头操作：head++，
     *                 ②队尾入队：++tail（即将插入元素小于上一步滑动窗口的队尾元素直接++tail入队；即将插入的元素大于等于上一步滑动窗口的队尾元素则先删除后插入tail--；++tail）
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] result = new int[n - k + 1];//n - k + 1=滑动窗口的个数
        int index = 0;
        for (int i = 0; i < n; i++) {
            //由题意，i为nums下标，是要在[i-k+1]中取得最大值，需要保证两点
            //1.队列头结点需要在[i-k+1，i]的范围内，不符合则弹出（队中存储索引）
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();//弹出队头元素
            }
            //2.既然是单调，就要保证每次放进去的数字要比末尾的都小，否则也弹出
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);//元素添加到队列的末尾

            //因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头结点放入结果就行了
            if (i >= k - 1) {
                result[index++] = nums[deque.peek()];
            }
        }
        return result;
    }

    /**
     * 暴力解法
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> result=new ArrayList<>();
        if(num.length==0 || size<1 || num.length<size){
            return result;
        }
        for(int i=0;i+size-1<num.length;i++){
            int j=i+size-1;
            int max=num[j];
            for(int k=i;k<j;k++){
                max=Math.max(max,num[k]);
            }
            result.add(max);
        }
        return result;
    }

    public ArrayList<Integer> maxValueWindows(int[] nums, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums.length == 0 || size < 1 || nums.length < size) {
            return result;
        }
        int left=0;
        int right = size - 1;
        while (left < right && right < nums.length) {
            int maxValue = calMax(nums, left, right);
            result.add(maxValue);
            left++;
            right++;
        }
        return result;
    }

    public static int calMax(int[] nums, int left, int right) {
        int max = nums[right];
        for (int i = left; i < right; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}
