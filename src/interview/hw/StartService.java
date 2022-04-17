package interview.hw;

import java.util.*;

public class StartService {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        in.nextLine();//用来接收换行符
        List<List<Integer>> graph = new ArrayList<>(N);  // 二维list存储每个服务的启动依赖
        for (int i = 0; i < N; i++) {  // 初始化
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            String[] split = in.nextLine().split(",");
            for (int j = 1; j < split.length; j++) {
                graph.get(i).add(Integer.parseInt(split[j]));
            }
        }

        // bfs
        Deque<Integer> queue = new LinkedList<>();
        queue.add(M);
        Set<Integer> visited = new HashSet<>();
        visited.add(M);
        Set<Integer> ans = new HashSet<>();
        while (!queue.isEmpty()) {
            int poll = queue.pollFirst();
            visited.add(poll);  // 用来判断是否成环
            for (int next : graph.get(poll)) {
                if (visited.contains(next)) {
                    System.out.println(-1);
                    return;
                }
                if (ans.contains(next)) {
                    continue;
                }
                ans.add(next);
                queue.add(next);
            }
        }
        Integer[] ansArr = ans.toArray(new Integer[ans.size()]);  // 集合转数组要用Integer
        Arrays.sort(ansArr);
        for (int i = 0; i < ansArr.length - 1; ++i) {
            System.out.print(ansArr[i] + ",");
        }
        if (ansArr.length == 0) {
            System.out.println("null");
        } else {
            System.out.println(ansArr[ansArr.length - 1]);
        }
    }
}
