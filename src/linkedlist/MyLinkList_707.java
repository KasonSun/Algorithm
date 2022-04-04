package linkedlist;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val和next。val是当前节点的值，next是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。
 * 如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
 *
 * 示例：
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 * 以下类为单链表实现
 */
public class MyLinkList_707 {
    int size;//存储元素的个数
    ListNode head;//虚拟头结点（头结点的前一个结点）

    //构造方法，初始化链表
    public MyLinkList_707() {
        this.size = 0;
        this.head = new ListNode(-1);
    }

    /**
     * 获取第i个索引的值
     *
     * @param index
     */
    public int get(int index) {
        //如果索引无效，则返回-1。
        if (index < 0 || index > size) {
            return -1;
        }
        ListNode temp = head;//当前节点
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * 在链表头节点之前插入一个值为val的节点，新节点将成为链表的第一个节点
     *
     * @param val
     * @return
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 链表最后一个结点插入一个结点
     *
     * @param val
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * 在链表中的第index个节点之前添加值为val 的节点。
     * 如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     *
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        //找到要插入结点的前驱
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode addNode = new ListNode(val);
        addNode.next = pre.next;
        pre.next = addNode;
    }

    /**
     * 如果索引index 有效，则删除链表中的第index 个节点。
     *
     * @param index
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return;
        }
        size--;
        //找到要删除节点的前驱
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }

}

