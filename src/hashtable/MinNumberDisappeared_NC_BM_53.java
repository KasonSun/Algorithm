package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组nums，请你找出其中没有出现的最小的正整数
 *
 * 进阶： 空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 */
public class MinNumberDisappeared_NC_BM_53 {
    public int minNumberDisappeared (int[] nums) {
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i], 1);
        }
        int result=1;
        while(map.containsKey(result)){
            result++;
        }
        return result;
    }
}
