package hashtable;

/**
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 */
public class CanConstruct_383 {
    /**
     *与242题类似，只需要判断条件小于就可以
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] record = new int[26];
        for (char c:magazine.toCharArray()) {
            record[c - 'a'] += 1;
        }
        for (char c: ransomNote.toCharArray()) {
            record[c - 'a'] -= 1;
        }
        for (int i :record) {
            if(i<0){
                return false;
            }
        }
        return true;
    }
}
