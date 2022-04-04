package array.array01_binarysearch;

/**
 * 找到数组中只有一个数是只有唯一一个，其他都是两个
 *      1.若数组无序，需要先排序，使用异或实现；
 *      2.若数组有序，直接进行异或
 */
public class SelectUnique {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3, 4, 6, 6};
        System.out.println(selectUnique(nums));
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int selectUnique(int[] nums) {
        for(int i=1;i<nums.length;i+=2){
            if ((nums[i-1] ^ nums[i]) == 0) {
                continue;
            }else{
                return nums[i-1];
            }
        }
        return -1;
    }
}
