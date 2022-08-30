package simulation;


import java.util.HashMap;
import java.util.Map;

/**
 * leetcode.146 （LRU实现）
 * 实现一个LRU淘汰算法设计并且实现一个Least Recently Used (LRU)的缓存对象，
 * 他支持get和put操作
 *      get用来获取cache中的key对应的value，如果该key不存在，返回-1;
 *      put用来更新（如果key已经存在）或者插入（如果key不存在）数据。
 *
 *      使用双向链表（记录顺序：虚拟头尾节点head, tail）+哈希表(查找链表中是否存在我们需要的数据)实现
 */
class LinkNode{
    int key;
    int val;
    LinkNode pre;
    LinkNode next;

    public LinkNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
public class LRU{
    private int capacity;
    LinkNode head = new LinkNode(0, 0);//虚拟头结点
    LinkNode tail = new LinkNode(0, 0);//虚拟尾结点
    Map<Integer, LinkNode> map = new HashMap<>();

    public LRU(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            LinkNode node = map.get(key);
            moveNodeToTop(node);
            return node.val;
        }else{
            return -1;
        }
    }

    /**
     * 放入逻辑（相当于放入缓冲区）：
     *      ① 如果当前key不存在，达到最大容量，则删除最后一个结点，将当前节点加入链表头，并放入map；
     *      ② 如果当前key存在， 则获取key对应的map节点，更改key对应的value值；
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if(map.size()==capacity) deleteLastNode();
            LinkNode newNode = new LinkNode(key, value);
            LinkNode temp = head.next;
            head.next = newNode;
            newNode.pre = head;
            newNode.next=temp;
            temp.pre = newNode;
            map.put(key, newNode);
        }else{
            LinkNode node = map.get(key);
            node.val = value;
            moveNodeToTop(node);
        }
    }

    /**
     * 移除链表最后一个结点（相当于从缓冲区移除）
     */
    public void deleteLastNode() {
        LinkNode lastNode = tail.pre;
        lastNode.pre.next = tail;
        tail.pre = lastNode.pre;
        map.remove(lastNode.key);
    }

    /**
     * 将当前访问节点放到最前面:
     *      ① 将当前节点的前后节点连接起来；
     *      ② 将当前节点放到链表头
     * @param node
     */
    public void moveNodeToTop(LinkNode node) {
        //①
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //②
        LinkNode temp = head.next;
        head.next = node;
        node.pre = head;
        node.next = temp;
        temp.pre = node;
    }
}
