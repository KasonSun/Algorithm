package greedy;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 *  * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 */
public class CanJump_55 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("跳跃游戏的结果为：" + canJump(nums));
    }

    /**
     * 贪心法：
     *      （这个问题可以转换为跳跃覆盖范围究竟是否能够覆盖到终点，每次移动取得最大跳跃步数（最大覆盖范围），每移动一个单位，就更新最大覆盖范围）
     *      局部最优解：每次取最大跳跃步数（取最大覆盖范围）；整体最优解：最后得到整体最大覆盖范围，看是否能到达终点
     *      思路：i每次移动只能在cover的范围内移动， 每移动一个元素，cover得到该元素数值（新的覆盖范围）的补充，让i继续移动下去，
     *      而cover每次只取max（该元素数值补充后的范围，cover本身的范围）如果cover大于等于了终点下标，直接return true就可以了
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        //覆盖范围，初始覆盖范围应该是0，因为下面的迭代是从0开始的
        int cover = 0;
        //在覆盖范围内更新最大覆盖范围
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            //System.out.println(cover);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
