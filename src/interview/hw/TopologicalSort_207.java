package interview.hw;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 207. 课程表(拓扑排序，有向无环图)
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *  例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 * 提示：
 *
 *     1 <= numCourses <= 105
 *     0 <= prerequisites.length <= 5000
 *     prerequisites[i].length == 2
 *     0 <= ai, bi < numCourses
 *     prerequisites[i] 中的所有课程对 互不相同
 */
public class TopologicalSort_207 {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        int[][] prerequisites01 = {{1, 0}};

        System.out.println(canFinish(numCourses, prerequisites01));
    }
    /**
     * BFS：
     *      ①在开始排序之前，扫描对应的邻接表，将入度为0的节点放入队列；
     *      ②只要队列非空，就从队首取出入度为0的节点，将这个节点输出到结果集中，
     *          并且将这个节点所有的邻接节点（他指向的节点）的入度减1，在减1以后，如果这被减1的节点的入度为0，就继续入队；
     *      ③当队列为空的时候，检查结果集中的顶点个数是否和课程数相等即可。
     *
     *      思考：为什么这里使用队列？
     *      在代码具体实现的时候，处理保存入度为0的队列，我们还需要两个辅助数据结构：
     *          ①邻接表：通过节点的索引，我们能够得到这个节点的后继节点；
     *          ②入度数组：用过节点的索引，我们能够得到指向这个节点的节点个数。
     *      这两个数据结构在遍历题目给出的邻边以后解可以很方便的得到。
     *
     *      时间复杂度（O（E+V）分别为邻边的条数和节点个数）
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        //特殊判断
        if (prerequisites.length == 0) {
            return true;
        }

        int[] inDegree = new int[numCourses];
        Set<Integer>[] adj = new HashSet[numCourses];
        //hashset数组初始化
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }
        //计算入度数组,建立邻接表
        for (int[] p : prerequisites) { //prerequisites={[1,0],[0,1]}这种形式  p-->[1,0]...
            inDegree[p[0]]++;//
            adj[p[1]].add(p[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        //首先加入入度为0的节点
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        //记录已经出队的课程数量
        int count = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            count++;
            //遍历当前出队节点的所有后继节点
            for (int successor : adj[top]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.offer(successor);
                }
            }
        }
        return count == numCourses;
    }

    /**
     * DFS：
     *      说明：深度优先遍历的思路有两个：
     *          ①首先检测是否存在环，然后使用深度优先遍历，在后序的部分把课程添加到结果集，然后再逆序，就是拓扑排序的结果；（没有提供代码）
     *          ②在深度优先遍历的过程张艳红，设置个别特殊意义的变量，通过这些变量得到拓扑排序的结果；（提供代码）
     *          这里要使用逆邻接表，其实就是检测这个有向图中有没有环， 只要存在环，这些课程就不能按照要求学完。
     *          具体步骤为：
     *              ①构建逆邻接表；
     *              ②递归处理每一个还没有被访问的节点，具体做法很简单：对于一个节点来首哦，先输出指向他的所有顶点，再输出自己；
     *              ③如果这个顶点还没有被遍历过，就递归遍历它， 把所有指向他的节点都输出了，再输出自己。
     *              注意：当访问一个结点的时候，应当先递归访问他的前驱节点，直至前驱节点没有前驱节点为止。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish01(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        if (prerequisites.length == 0) {
            return true;
        }
        int[] marked = new int[numCourses];

        //初始化有向图begin
        Set<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }

        //初始化有向图end
        //有向图的key是前驱节点，value是后继节点的集合
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, marked)) {
                //注意方法的语义，如果图中存在环，表示课程任务不能完成，应该返回false
                return false;
            }
        }
        //在遍历的过程中，一直dfs都没有遇到已经重复的访问的节点，就表示有向图中没有环
        //所有课程任务可以完成， 应该返回true
        return true;
    }

    /**
     * 注意这个dfs方法的语义
     * @param i 当前访问的课程节点
     * @param graph
     * @param marked 如果==1表示正在访问中，如果==2表示已经访问完了
     * @return true 表示图中存在环，false表示访问过了，不用再访问了
     */
    public static boolean dfs(int i, Set<Integer>[] graph, int[] marked) {
        //如果访问过了， 就不用再访问了
        if (marked[i] == 1) {
            //从正在访问中到正在访问中，表示遇到了环
            return true;
        }
        if (marked[i] == 2) {
            //表示在访问的过程中，没有遇到环，这个节点访问过了
            return false;
        }
        //走到这里，是因为初始化呢，此时mark[i]==0
        //表示正在访问中
        marked[i] = 1;
        //后继节点的集合
        Set<Integer> successorNodes = graph[i];
        for (Integer successor : successorNodes) {
            if (dfs(successor, graph, marked)) {
                //层次递归返回true， 表示图中存在环
                return true;
            }
        }
        //i的所有后继转态都访问完了，都没有存在环，则这个节点就可以被标记为依据访问结束
        //状态设置为2
        marked[i] = 2;
        //false表示图中不存在环
        return false;
    }
}
