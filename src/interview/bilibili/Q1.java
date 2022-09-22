/*
package interview.bilibili;

public class Q1 {
    public ListNode mergeNode (ListNode head) {
        // write code here
        ListNode dummyHead=new ListNode(-1);
        ListNode cur = dummyHead;
        while(head.next!=null){
            ListNode temp=new ListNode(head.val*head.next.val);
            cur.next=temp;
            cur=cur.next;
            head=head.next.next;
            if(head==null){
                break;
            }
        }
        return dummyHead.next;
    }
}
*/
