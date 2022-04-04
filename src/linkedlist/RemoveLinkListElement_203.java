package linkedlist;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
public class RemoveLinkListElement_203 {
    public static void main(String[] args) {
        int val = 6;
        int[] value = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(value[0]);
        //创建链表并进行赋值
        ListNode temp = head.next;
        for (int i = 1; i < 7; i++) {
            temp=new ListNode(value[i]);
            //System.out.println(temp.value);
            temp = temp.next;
        }

        ListNode result = removeElements(head, val);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

    /**
     *移除给定值的链表结点并返回头结点
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        //预判断
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;//定义一个虚拟结点指向头结点方便统一操作
        ListNode pre = dummy;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val == val) {
                pre.next = temp.next;
            }else {
                pre = temp;
            }
            temp = temp.next;
        }
        return dummy.next;
    }

}
//链表节点类
class ListNode{
    int val;
    ListNode next;
    //无参构造
    public ListNode(){

    }
    //有参构造
    public ListNode(int val) {
        this.val = val;
    }

    //
    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}