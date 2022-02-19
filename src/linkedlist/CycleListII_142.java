package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 题意： 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
public class CycleListII_142 {
    /**
     * 1.可以借助哈希表实现
     *
     * @param head
     * @return
     */
    public static ListNode detectCylcleList01(ListNode head){
        if(head==null || head.next==null){
            return null;
        }
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if(visited.contains(pos)){
                return pos;
            }else{
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
    /**
     * 2.双指针实现（slow走一步， fast走两步）
     * 此种方法需要严格的数学推导，可以直接记结论
     * @param head
     * @return
     */
    public static ListNode detectCylcleList02(ListNode head){
        if(head==null || head.next==null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode index1 = fast;
                ListNode index2 = head;
                //两指针从头结点相遇，各走一步，直道相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
