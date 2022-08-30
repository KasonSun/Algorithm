package hashtable;

/**
 * 给定一个只包含整数的有序数组 nums，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 *  此题可联系FindNumsAppearOnce_NC_BM_52一起看，FindNumsAppearOnce_NC_BM_52找出两个只出现一次的数字
 */
public class SingleNonDuplicate_jianzhiOfferII_070 {
    /**
     * 此方法有序无序都可
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int result=0;
        for(int num:nums){
            result^=num;
        }
        return result;
    }
}
