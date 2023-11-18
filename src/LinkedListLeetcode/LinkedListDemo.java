package LinkedListLeetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LinkedListDemo {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode list : lists) {
            while (list != null) {
                //全部加入
                queue.add(list);
                list = list.next;
            }
        }

        ListNode dummy = new ListNode(-1);

        while(!queue.isEmpty()) {
            ListNode min = queue.poll();
            dummy.next = min;

            //如果弹出的节点具有next节点，那么将next节点加入队列(凡是只有弹出的才会加入下一个进入队列，然后做下一轮的判断）
            if (min.next != null) {
                queue.add(min.next);
            }
        }
        return dummy.next;
    }





    public static void main(String[] args) {

    }
}
