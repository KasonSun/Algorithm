package linkedlist;

/**
 * 删除链表的倒数第N个结点
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class RemoveNthFromEnd_19 {
    /**
     * 双指针法
     *      给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
     *      思路：双指针的经典应用，如果要删除倒数第n个节点，让fast移动n步，然后让fast和slow同时移动，直到fast指向链表末尾。删掉slow所指向的节点就可以了。
     *               1.fast首先走n + 1步 ，为什么是n+1呢，因为只有这样同时移动的时候slow才能指向删除节点的上一个节点（方便做删除操作）
     *               2.fast和slow同时移动，直至fast走到末尾
     *               3.删除slow指向的下一个结点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next==null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        while(n>0 && fast!=null){
            fast = fast.next;
            n--;
        }
        fast = fast.next;//多走一步，需要让slow指向删除节点的前一个节点（此时fast与slow之间的距离即为n+1）
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;//删除第n个结点

        return dummyHead.next;
    }
}
