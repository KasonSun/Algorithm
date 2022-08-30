package interview.hw;

import java.util.*;

/**
 * 拓扑排序：服务启动(代码有问题)
 *
 */
public class Service {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//服务数
        int m = sc.nextInt();//启动编号
        sc.nextLine();
        List<List<Integer>> g = new ArrayList<>(n);
        List<List<Integer>> rg = new ArrayList<>(n);
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
            rg.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split(",");
            for (int j = 1; j < str.length; j++) {
                int num = Integer.parseInt(str[j]);
                g.get(j).add(i);
                rg.get(i).add(j);
                degree[i] += 1;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                if (i == m) {
                    System.out.println("null");
                    return;
                }
                queue.offer(i);
            }
        }
        boolean flag = false;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nx_node : g.get(node)) {
                degree[nx_node] -= 1;
                if (degree[nx_node] == 0) {
                    queue.offer(nx_node);
                    if (nx_node == m) {
                        flag=true;
                        break;
                    }
                }
            }
        }
        if (!flag) {
            System.out.println("-1");
            return;
        }
        Queue<Integer> queue01 = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[m]=true;
        List<Integer> result = new ArrayList<>();
        while (!queue01.isEmpty()) {
            int node = queue.poll();
            for (int nx_node : rg.get(node)) {
                if (!visited[nx_node]) {
                    visited[nx_node]=true;
                    queue.offer(nx_node);
                    result.add(nx_node);
                }
            }
        }
        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < result.size()-1; i++) {
            System.out.print(result.get(i) + ",");
        }
        System.out.print(result.get(result.size() - 1));
    }
}
