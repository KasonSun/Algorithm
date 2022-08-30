package hashtable;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class SingleNumber_137 {
    public int singleNumber(int[] nums) {
        int[] bit=new int[32];
        for(int i=0;i<bit.length;i++){
            for(int num:nums){
                int temp=num >> i;
                bit[i]+=temp&1;
            }
        }

        int result=0;
        for(int i=0;i<bit.length;i++){
            if(bit[i]%3!=0){
                result+=(1<<i);
            }
        }
        return result;
    }
}
