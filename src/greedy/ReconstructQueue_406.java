package greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组people 所表示的队列。
 * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * 示例 2：
 *
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *
 */
public class ReconstructQueue_406 {
    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] queue = reconstructQueue(people);
        System.out.print("根据身高重建队列的结果为：");
        for (int[] q:queue){
            System.out.print(Arrays.toString(q));
        }
    }

    /**
     * 贪心法  T=O(nlogn+n^2)  S=O(n)  两次贪心
     *      思路：先确定身高，在确定个数
     *            按身高从大到小排序（身高相同k小的站在前面），再优先按身高高的people的k来插入，后序插入的节点不会影响前面已经插入的节点，最终按照k的规则完成了队列
     *            局部最优：优先按照身高高的people的k来插入，插入后满足队列属性
     *            全局最优：最后都完成插入操作，整个队列满足题目的队列属性
     *
     * 排序完的people： [[7,0], [7,1], [6,1], [5,0], [5,2]，[4,4]]
     * 插入的过程：
     * 插入[7,0]：[[7,0]]
     * 插入[7,1]：[[7,0],[7,1]]
     * 插入[6,1]：[[7,0],[6,1],[7,1]]
     * 插入[5,0]：[[5,0],[7,0],[6,1],[7,1]]
     * 插入[5,2]：[[5,0],[7,0],[5,2],[6,1],[7,1]]
     * 插入[4,4]：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * @param people
     * @return
     */
    public static int[][] reconstructQueue(int[][] people) {
        //按身高从大到小排序（身高相同k小的站在前面）
        Arrays.sort(people,(a,b)->{
            if(a[0]==b[0]) return a[1] - b[1];//compare方法的返回值如果大于0，则交换位置，故应该用a[1] - b[1]（最后满足身高相同k小在前面，即为k从小到大）
            return b[0] - a[0];//compare方法的返回值如果大于0，则交换位置，故应该用b[0]-a[0],最后满足从大到小
        });

//        System.out.print("根据身高排序的结果为：");
//        for (int[] q:people){
//            System.out.print(Arrays.toString(q));
//        }

        LinkedList<int[]> que = new LinkedList<>();

        //这种按K值操作主要取决于java 链表所提供的这个add方法
        /*public void add(int index, E element) {
            checkPositionIndex(index);

            if (index == size)
                linkLast(element);
            else
                linkBefore(element, node(index));
        }*/
        for (int[] p : people) {
            que.add(p[1], p);//public void add(int index, E element)
        }

        return que.toArray(new int[people.length][]);
    }
}
