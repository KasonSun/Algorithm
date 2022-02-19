package linkedlist;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 */
public class SwapPairs_24 {
    /**
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if(head==null ||(head.next==null)){
            return head;
        }
        ListNode pre = new ListNode(-1);//建立指向头结点的虚拟结点
        pre.next = head;
        ListNode cur = pre;
        while(cur.next!=null && cur.next.next!=null){
            ListNode temp = cur.next;
            ListNode temp01 = cur.next.next.next;

            cur.next = cur.next.next;//1.
            cur.next.next = temp;//2.
            cur.next.next.next = temp01;//3.

            cur = cur.next.next;//移动两位准备下一次交换

        }
        return pre.next;
    }
}
