package hashtable;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqChar_JianzhiOffer_50 {
    public char firstUniqChar(String s) {
        if(s==null) return ' ';
        if(s.length()==1) return s.charAt(0);

        Map<Character, Integer> map=new HashMap<>();
        for(char c:s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(map.get(c)==1){
                return c;
            }
        }
        return ' ';
    }
}
