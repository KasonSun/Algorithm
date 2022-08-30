package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 统计数组中出现次数超过一半的数字
 */
public class CountMoreThanHalfNum_NC_BM_51 {
    //0.特殊法 排序完成，中间位置一定为超过一半的数字
    public int MoreThanHalfNum_Solution00(int [] array) {
        if(array.length==1){
            return array[0];
        }
        Arrays.sort(array);
        return array[array.length/2];
    }
    //1.直接统计法  nlogn - 1
    public int MoreThanHalfNum_Solution01(int [] array) {
        if(array.length==1){
            return array[0];
        }
        int count=1;
        int result=-1;
        Arrays.sort(array);
        for(int i=1;i<array.length;i++){
            if(array[i-1]==array[i]){
                count++;
                if(count>array.length/2){
                    result=array[i];
                }
            }else{
                count=1;
            }
        }
        return result;
    }

    //2.哈希法 n - n
    public int MoreThanHalfNum_Solution02(int[] array){
        if(array.length==1){
            return array[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = array.length / 2;
        for (int temp : array) {
            if (map.containsKey(temp)) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
                if(map.get(temp)>count){
                    return temp;
                }
            }else{
                map.put(temp, 1);
            }
        }
        return -1;
    }
}
