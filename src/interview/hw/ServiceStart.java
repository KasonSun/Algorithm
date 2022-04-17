package interview.hw;

import java.util.*;

public class ServiceStart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();//注意此处的换行
        //记录每一个服务启动依赖（当前索引指当前服务编号，List<Integer>指当前服务依赖项）
        List<List<Integer>> dependList = new ArrayList<>();
        //初始化
        for (int i = 0; i < n; i++) {
            dependList.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split(",");
            for (int j = 1; j < str.length; j++) {
                int num = Integer.parseInt(str[j]);
                dependList.get(i).add(num);
            }
        }

        //广度优先遍历
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> visitedSet = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        queue.offer(m);//启动服务编号先加入
        while (!queue.isEmpty()) {
            int head = queue.poll();
            visitedSet.add(head);
            System.out.println("next:"+dependList.get(head));
            for (int next : dependList.get(head)) {
                if (visitedSet.contains(next)) {//访问过，说明循环依赖
                    System.out.println(-1);
                    return;
                }
//                if (resultSet.contains(next)) {//结果集中存在，则跳过此次循环
//                    continue;
//                }
                queue.offer(next);
                resultSet.add(next);
            }
        }
        /*System.out.println("queue:"+queue.toString());
        System.out.println("visited:" + visitedSet.toString());
        System.out.println("result:"+resultSet.toString());*/
        Integer[] resultArray = resultSet.toArray(new Integer[resultSet.size()]);
        if (resultArray.length == 0) {
            System.out.println("null");
        }
        Arrays.sort(resultArray);
        for (int i = 0; i < resultArray.length - 1; i++) {
            System.out.print(resultArray[i] + ",");
        }
        System.out.print(resultArray[resultArray.length - 1]);
    }
}
