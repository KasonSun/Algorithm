package interview.zhaoyin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 map集合的遍历
 */
public class Q1 {
    public static void main(String[] args) {
        Map<String,Integer> maps=new HashMap<>();
        //添加元素：无序，不重复，无索引。
        maps.put("iphoneX",10);
        maps.put("娃娃",20);
        maps.put("huawei",100);
        maps.put("生活用品",10);
        maps.put("手表",10);

        //1.键找值遍历
        //第一步：先拿到集合的全部键
        Set<String> keys=maps.keySet();

        //第二步：遍历每个键，根据键提取值
        for (String key : keys) {
            int value =maps.get(key);
            System.out.println(key+"==>"+value);
        }

        System.out.println("----------------------------");

        //2.键值对遍历
        //第一步：把Map集合转化为Set集合
        Set<Map.Entry<String, Integer>> entries = maps.entrySet();

        //第二步：开始遍历
        for(Map.Entry<String, Integer> entry:entries){
            String key=entry.getKey();
            int value=entry.getValue();
            System.out.println(key+"==>"+value);
        }

        System.out.println("----------------------------");

        //3.Lambda表达式
        maps.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String key, Integer value) {
                System.out.println(key+"==>"+value);
            }
        });

        System.out.println("----------------------------");
        //简化
        maps.forEach(( k,  v)-> {System.out.println(k+"==>"+v);});

    }

}


