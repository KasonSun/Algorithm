package interview.hw.shixi;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 舆情热词改进
 * 使用treeMap实现
 */
public class BreakWordTopNUpdate {
    public static void main(String[] args) {
        String[] lines = {"xinguan feiyan xinzeng bendi quezhen anli",
                "ju baodao chengdu xinzeng xinguan feiyan bendi quezhen anli yili shenzhen xinzeng bendi quezhen anli liangli yiqing zhhengti kongzhi lianghao",
                "xinguan yimiao linchuang shiyan",
                "wuzhong xinguan yimiao tongguo sanqi linchuang shiyan xiaoguo lianghao"};
        int topN = 3;
        System.out.println(search(lines, topN));
    }
    public static String search(String[] lines, int topN){
        HashMap<String, int[]> map = new HashMap<>();
        int index = 0;
        for(int i = 0; i < lines.length; i++){
            String[] words = lines[i].split("\\s+");
            for(String word: words){
                int[] value = map.getOrDefault(word, new int[]{index, 0});
                value[1] += 1;
                map.put(word, value);
                index++;
            }
        }
        TreeMap<int[], String> treeMap = new TreeMap<>((o1, o2) -> o1[1] == o2[1] ?o1[0]-o2[0]: o2[1]-o1[1]);
        for(String key : map.keySet()){
            int[] value = map.get(key);
            treeMap.put(value, key);
        }
        StringBuilder ans = new StringBuilder();
        for(int[] key : treeMap.keySet()){
            if(topN > 0){
                ans.append(treeMap.get(key));
                ans.append(" ");
                topN--;
            }else {
                break;
            }
        }
        return ans.substring(0, ans.length() - 1);
    }
}
