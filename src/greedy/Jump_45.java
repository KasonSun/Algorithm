package greedy;

/**
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * '假设你总是可以到达数组的最后一个位置'
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *  从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class Jump_45 {
    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println("跳跃游戏II步数：" + jump(nums));
    }

    /**
     * 贪心法：时间复杂度 O(n)  空间复杂度 O(1)
     *      局部最优：当前可移动距离尽可能多走，如果还没到终点，步数再加一。整体最优：一步尽可能多走，从而达到最小步数
     *      思路：从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，覆盖范围一旦覆盖了终点，得到的就是最小步数
     *          需要统计两个覆盖范围，当前这一步的最大覆盖和下一步的最大覆盖
     *          如果移动下标达到了当前这一步的最大覆盖最远距离了，还没有到终点的话，那么就必须再走一步来增加覆盖范围，直到覆盖范围覆盖了终点。
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }

        int count = 0;//记录跳跃次数
        int curDistance = 0;//当前覆盖最大区域
        int maxDistance = 0;//最大覆盖区域
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖的区域内更新最大覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //说明当前一步，再跳一步就达到了末尾
            if (maxDistance >= nums.length - 1) {
                count++;//这个count++实际上就是加了最后一次跳到最后的步骤
                break;
            }
            //i走到当前覆盖的最大区域时，更新下一步可达的最大区域（相当于在[i,curDistance]范围内找maxDistance进行更新，i==curDistance时必须做下一步跳跃了）
            if (i == curDistance) {
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }
}
