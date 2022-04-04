package linkedlist;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 输入：lists = []
 * 输出：[]
 *
 * 输入：lists = [[]]
 * 输出：[]
 */
public class MergeKLists_23 {
    /**
     * 2.分治合并
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int left, int right) {
        if(left==right) return lists[left];
        if(left>right) return null;
        int mid = (left + right) / 2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid+1, right));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1!=null) cur.next = list1;
        if(list2!=null) cur.next = list2;
        return dummy.next;
    }
    /**
     * 1.循环实现，第一次需要从数组中取出两个比较合并为一个，以后的每次取出一个与上一次合并的从头开始重新比较合并
     * @param lists
     * @return
     */
    public static ListNode mergeKLists01(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        if(lists.length==1){
            return lists[0];
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode list1=null,list2=null;//初始化两个游标
        for (int i = 1; i < lists.length; i++) {
            //第一次取两个，以后每次取一个和上一次合并的链表进行重头开始比较
            if(i==1){
                list1 = lists[i-1];
                list2 = lists[i];
            }
            //第二次以及以后，每次只需要取出一个
            list2=lists[i];
            while (list1 != null && list2!= null) {
                if (list1.val < list2.val) {
                    cur.next = list1;
                    list1 = list1.next;
                }else{
                    cur.next = list2;
                    list2 = list2.next;
                }
                cur = cur.next;
            }
            if(list1!=null) cur.next = list1;

            if(list2!=null) cur.next = list2;

            //这里需要将合并后的list作为新的list1,
            cur=dummy;
            list1=dummy.next;
        }
        return dummy.next;
    }
}
