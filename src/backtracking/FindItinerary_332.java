package backtracking;

import java.util.*;

/**
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 *
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 *
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 *
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 *
 */
public class FindItinerary_332 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> t1 = new ArrayList<>();
        List<String> t2 = new ArrayList<>();
        List<String> t3 = new ArrayList<>();
        List<String> t4 = new ArrayList<>();
        t1.add("MUC");
        t1.add("LHR");
        t2.add("JFK");
        t2.add("MUC");
        t3.add("SFO");
        t3.add("SJC");
        t4.add("LHR");
        t4.add("SFO");
        tickets.add(t1);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        System.out.println("航线有效行程为："+new FindItinerary_332().findItinernary(tickets));
    }

    /**
     * 排序存储后就能搜索到满足条件的即可返回，就是题目要求的排序最小
     * @param tickets
     * @return
     */
    Deque<String> result = new LinkedList<>();//此时的result相当于之前题目中回溯用的path
    //map<出发机场，map<到达机场，航班次数>> map
    Map<String, Map<String, Integer>> map = new HashMap<>();
    public List<String> findItinernary(List<List<String>> tickets) {
        for (List<String> t : tickets) {
            Map<String, Integer> temp;
            if (map.containsKey(t.get(0))) {//t->[出发机场，到达机场],get[0]对应的是出发机场，get[1]是到达机场
                temp = map.get(t.get(0));//获得的是出发机场对应的到达机场和航班次数键值map赋值给temp
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序map(//不存在则直接s申请有序图，放入)
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);
        }
        result.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(result);
    }

    public boolean backTracking(int ticketNum) {
        if (result.size() == ticketNum + 1) {
            return true;
        }
        String last = result.getLast();//返回双端队列的最后一个元素，并不删除元素
        if (map.containsKey(last)) {
            for (Map.Entry<String, Integer> target : map.get(last).entrySet()) {
                int count = target.getValue();
                if (count > 0) {//记录到达机场是否飞过了
                    result.add(target.getKey());
                    target.setValue(count - 1);
                    if (backTracking(ticketNum)) {
                        return true;
                    }
                    result.removeLast();//回溯
                    target.setValue(count);//回溯
                }
            }
        }
        return false;
    }
}
