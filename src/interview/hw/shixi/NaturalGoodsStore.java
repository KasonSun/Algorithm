package interview.hw.shixi;

import java.util.Scanner;
import java.util.Stack;

/**
 * 单调栈实现：只有地平线下面可以摆放货物
 * 输入：
 * n:货物长度
 * m:下面一维数组长度
 * 一维数组指的是从左到右每个台阶的值
 */
public class NaturalGoodsStore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int m = Integer.valueOf(sc.nextLine());
        String[] str = sc.nextLine().split(",");
        int[] stair = new int[m];
        for (int i = 0; i < m; i++) {
            stair[i] = Integer.valueOf(str[i]);
        }

        //建一个新数组，默认两边为地平面，0，数组两端各加一个0
        int[] nums = new int[m + 2];
        //拷贝数组：（源数组，拷贝的起始位置，目标数组，拷贝的目标起始位置，拷贝长度）
        System.arraycopy(stair, 0, nums, 1, m);

        //单调栈存下标
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            //当栈顶为负数，且当前遍历的元素比栈顶的元素要大，此时要弹栈
            while (!stack.isEmpty() && nums[stack.peek()] < 0 && nums[i] > nums[stack.peek()]) {
                int midIndex = stack.pop();
                int midHeight = nums[midIndex];
                if (stack.isEmpty()) {
                    break;
                }
                int leftIndex = stack.peek();
                int leftHeight = nums[leftIndex];
                //i-leftIndex-1代表的是以midHeight为下界，可以装下的最大宽度
                //（Math.min(leftHeight, nums[i])-midHeight）代表的是可以装几层，因为可能不止
                result += (i - leftIndex - 1) / n * (Math.min(leftHeight, nums[i]) - midHeight);
            }
            stack.push(i);
        }
        System.out.println(result);
    }
}
