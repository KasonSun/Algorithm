package interview.microsoft;

import java.util.HashMap;
import java.util.Map;

public class Test01 {
    public static void main(String[] args) {
        String s = "bdaaadadb";
        System.out.println(new Test01().solve(s));
    }
    public int solve(String s) {
        int result=0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if((j-i+1)%2==0 && valid(s, i,j)){
                    result=Math.max(result, j-i+1);
                }
            }
        }
        return result;
    }

    public boolean valid(String s, int i, int j){
        Map<Character, Integer> map = new HashMap<>();
        for(int k=i;k<=j;k++){
            char ch=s.charAt(k);
            if(map.containsKey(ch)){
                map.put(ch, map.getOrDefault(ch,0)+1);
            }else{
                map.put(ch, 1);
            }
        }
        int count=0;
        for(int num: map.values()){
            if(num%2==0){
                count+=num;
            }else{
                return false;
            }
        }
        if(count==j-i+1){
            return true;
        }
        return false;
    }
}
