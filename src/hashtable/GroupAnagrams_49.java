package hashtable;

import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 *输入: strs = [""]
 * 输出: [[""]]
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class GroupAnagrams_49 {
    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strings).toString());

    }
    /**
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        if (strs == null || len == 0) {
            return new ArrayList();
        }

        Map<String, List> map = new HashMap<>();
        for(String s:strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);//排序后的字符串作为键key
            if(!map.containsKey(key)){//判断哈希表中是否存在key，没有则存进去
                map.put(key, new ArrayList());
            }
            map.get(key).add(s);//将该字符串放在对应key的列表中(上面判断排序改变了chars，实际放进列表的为s才对)
        }
        return new ArrayList(map.values());
    }

}
