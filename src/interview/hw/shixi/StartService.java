package interview.hw.shixi;

import java.util.*;

/**
 *
 */
public class StartService {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int m = Integer.valueOf(sc.nextLine());

        List<List<Integer>> map = new ArrayList<>(n);  //<编号，<前置依赖>>
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            String[] split = sc.nextLine().split(",");
            for (int j = 1; j < split.length; j++) {
                map.get(i).add(Integer.valueOf(split[j]));//存入当前标号i对应的前置依赖项
            }
        }

        //广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> result = new HashSet<>();//用来记录结果并判断前置依赖项中是否有自己的特殊判断
        queue.offer(m);//从编号m开始
        visited.add(m);//将已经遍历的点放入集合中

        while (!queue.isEmpty()) {
            int u = queue.poll();//出队
            visited.add(u);  // 加入集合，用于后续判断是否循环依赖
            for (int v : map.get(u)) {
                if (visited.contains(v) && map.get(v).contains(u)) {
                    //意思就是你是通过u遍历到v，此时如果v能找到u那说明循环依赖
                    System.out.println(-1);
                    return;
                }
                if (result.contains(v)) {//这个地方参考示例2就明白了
                    continue;
                }
                queue.offer(v);
                result.add(v);
            }
        }
        //在一个大环中是否包含自己，也就是自己是否依赖自己，这样也是循环依赖，特殊情况
        if (result.contains(m)) {
            System.out.println(-1);
            return;
        }
        Integer[] start = result.toArray(new Integer[result.size()]);
        if (start.length == 1) {
            System.out.println(start[0]);
            return;
        }
        for (int i = 0; i < start.length - 1; ++i) {
            System.out.print(start[i] + ",");
        }
        System.out.println(start[start.length - 1]);
    }
}
