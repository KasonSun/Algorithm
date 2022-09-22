package interview.webank;

import java.util.Arrays;
import java.util.Comparator;

/**
 *197.最大数 此题与Q1类似
 */
public class MaxNum_179 {
    public static void main(String[] args) {
        int[] nums = {0,0};
        System.out.println(new MaxNum_179().largestNumber(nums));
    }
    public String largestNumber(int[] nums) {
        String[] newNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(newNums, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                if (Integer.valueOf(o1 + o2) > Integer.valueOf(o2 + o1)) {
                    return -1;
                }
                return 1;
            }
        });
        StringBuilder sb=new StringBuilder();
        for(String temp:newNums){
            sb.append(temp);
        }
        String result = sb.toString();
        if (result.charAt(0)=='0') {
            return "0";
        }
        return result;
    }
}
