package dp;

/**
 * 此题实际上和718.最长重复子数组（连续）一样，只需要在记录最长子串的最后一个元素的索引，最后截取子串即可
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 *
 */
public class LongestCommonSubstring_NC_BM_66 {
    public static void main(String[] args) {
        String str1 = "1AB2345CD";
        String str2 = "12345EF";
        String lcs = new LongestCommonSubstring_NC_BM_66().LCS(str1, str2);
        System.out.println(lcs);

    }
    /**
     * ①dp[i][j]指以i-1和j-1为结尾的str1和str2最长公共子串的长度
     * ②每次更新dp[i][j]后，我们维护最大值，并更新该子串结束位置
     * ③最后根据最大值结束位置即可截取出子串
     * @param str1
     * @param str2
     * @return
     */
    public String LCS (String str1, String str2) {
        //dp[i][j]指以i-1和j-1为结尾的str1和str2最长公共子串的长度
        int[][] dp=new int[str1.length()+1][str2.length()+1];
        int result=0;
        //记录最长公共子串最后一个元素，按照dp定义此时实际上是不包括在内
        int maxLastIndex=0;
        for(int i=1;i<=str1.length();i++){
            for(int j=1;j<=str2.length();j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                if(dp[i][j]>result){
                    result=dp[i][j];
                    maxLastIndex=i;
                }
            }
        }
        return str1.substring(maxLastIndex-result, maxLastIndex);
    }
}
