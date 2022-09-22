package interview.xiaomi;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * 92. 反转链表 II
 * 区间反转链表
 * @param <T>
 */
class ListNode<T> {
    public T data;
    public ListNode next;
}

class Solution {

    /* Write Code Here */
    public ListNode<Integer> reverseBetween(ListNode<Integer> head, int left, int right) {
        ListNode cur=new ListNode();
        cur.next=head;
        ListNode pre, now, nxt;
        now=cur;
        for(int i=0;i<left-1;i++){
            now=now.next;
        }
        pre=now;
        now=now.next;
        nxt=now.next;
        for(int i=0;i<right-left;i++){
            now.next=nxt.next;
            nxt.next=pre.next;
            pre.next=nxt;
            nxt=now.next;
        }
        return cur.next;
    }
}

public class Q1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ListNode<Integer> res = null;

        int head_size = 0;
        head_size = in.nextInt();
        ListNode<Integer> head = null, head_curr = null;
        for(int head_i = 0; head_i < head_size; head_i++) {
            int head_item = in.nextInt();
            ListNode<Integer> head_temp = new ListNode<Integer>();
            head_temp.data = head_item;
            head_temp.next = null;

            if (head == null) {
                head = head_curr = head_temp;
            } else {
                head_curr.next = head_temp;
                head_curr = head_temp;
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        int left;
        left = Integer.parseInt(in.nextLine().trim());

        int right;
        right = Integer.parseInt(in.nextLine().trim());

        res = new Solution().reverseBetween(head, left, right);
        while (res != null) {
            System.out.print(String.valueOf(res.data) + " ");
            res = res.next;
        }
        System.out.println();

    }
}

